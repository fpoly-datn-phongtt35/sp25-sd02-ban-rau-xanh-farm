package com.example.datnv1.Service.OrderSevice;

import com.example.datnv1.DTO.Req.Orders.OrderDetaiBatchReqDTO;
import com.example.datnv1.DTO.Req.Orders.OrderDetailsReqDTO;
import com.example.datnv1.DTO.Req.Orders.ProductDetailOrderReqDTO;
import com.example.datnv1.Entity.Orders.OrderDetail;
import com.example.datnv1.Entity.Orders.OrderDetailBatch;
import com.example.datnv1.Entity.Orders.ProductDetailOrder;
import com.example.datnv1.Entity.Product.Batch;
import com.example.datnv1.Entity.Product.Product;
import com.example.datnv1.Entity.Product.ProductDetail;
import com.example.datnv1.Entity.Product.ProductDetailBatch;
import com.example.datnv1.Enum.SellType;
import com.example.datnv1.Repository.OrderRepository.OrderDetailRepo;
import com.example.datnv1.Repository.OrderRepository.ProductDetailOrderRepo;
import com.example.datnv1.Repository.ProductRepository.OrderDetailBatchRepo;
import com.example.datnv1.Service.ProductService.BatchSevice;
import com.example.datnv1.Service.ProductService.ProductDetailService;
import com.example.datnv1.Service.ProductService.ProductSevice;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderDetailService {

    @Autowired
    OrderDetailRepo orderDetailRepo;
    @Autowired
    BatchSevice batchSevice;
    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    OrderDetailBatchRepo orderDetailBatchRepo;
    @Autowired
    ProductDetailOrderRepo productDetailOrderRepo;
    @Autowired
    ProductSevice productSevice;

    @Transactional
    public OrderDetail saveWhenScan(OrderDetailsReqDTO dto) {

        if(!dto.getBatches().isEmpty() && !dto.getProductDetails().isEmpty()) {
            throw new RuntimeException("Chỉ có thể mua theo cân hoặc sản phẩm chi tiết!");
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetailRepo.save(orderDetail);
        String productName = "";
        float price = 0;
        double quantity = 0;
        String type = SellType.PRODUCT_DETAIL.toString();
        if(!dto.getBatches().isEmpty()) {
            type = SellType.RETAIL.toString();
            for (OrderDetaiBatchReqDTO item : dto.getBatches()){
                Batch batch = batchSevice.getById(item.getBatchId());                                                                                                                                                if(productName.isEmpty()) {
                    productName = batch.getProduct().getName();
                    price = batch.getProduct().getPrice();
                }
                float availableStock = batch.getQuantityRetail();
                if(availableStock - item.getQuantity() < 0) {
                    throw new RuntimeException("Sản phẩm đã hết hàng");
                }
                batch.setQuantityRetail(availableStock - item.getQuantity());
                batch.setQuantity(batch.getQuantity() - item.getQuantity());
                batchSevice.batchSave(batch);
                quantity += item.getQuantity();
                orderDetailBatchRepo.save(OrderDetailBatch.builder()
                                .orderDetail(orderDetail)
                                .batch(batch)
                                .quantity(item.getQuantity())
                                .build()
                );
            }
        }
        if(!dto.getProductDetails().isEmpty()) {
            for (ProductDetailOrderReqDTO item : dto.getProductDetails()){
                ProductDetailBatch productDetailBatch = productDetailService
                        .getProductDetailBatchById(item.getProductDetailBatchId());
                if(productName.isEmpty()) {
                    productName =productDetailBatch.getBatch().getProduct().getName() + "-" + productDetailBatch.getProductDetail().getProductDetailName();
                    price = productDetailBatch.getProductDetail().getPrice();
                }
                quantity += item.getQuantity();
                long availableStock = productDetailBatch.getQuantity();
                if(availableStock - item.getQuantity() < 0) {
                    throw new RuntimeException("Sản phẩm đã hết hàng");
                }
                productDetailBatch.setQuantity(availableStock - item.getQuantity());
                Batch batch = productDetailBatch.getBatch();
                float weight = productDetailBatch.getProductDetail().getWeight();
                batch.setQuantity(batch.getQuantity() - item.getQuantity() * weight);
                batchSevice.batchSave(batch);
                productDetailOrderRepo.save(ProductDetailOrder.builder()
                                .orderDetail(orderDetail)
                                .productDetailBatch(productDetailBatch)
                                .quantity(item.getQuantity())
                                .build()
                );
            }
        }
        orderDetail.setProductName(productName);
        orderDetail.setQuantity(quantity);
        orderDetail.setPrice(price);
        orderDetail.setTotal((float)( price * quantity));
        orderDetail.setOrders(dto.getOrder());
        orderDetail.setType(type);
        return orderDetailRepo.save(orderDetail);
    }

    private void saveOrderDetailBatches(OrderDetail orderDetail, Map<Batch, Float> allocatedBatches) {
        for (Map.Entry<Batch, Float> entry : allocatedBatches.entrySet()) {
            OrderDetailBatch orderDetailBatch = new OrderDetailBatch();
            orderDetailBatch.setBatch(entry.getKey());
            orderDetailBatch.setOrderDetail(orderDetail);
            orderDetailBatch.setQuantity(entry.getValue());
            orderDetailBatchRepo.save(orderDetailBatch);
        }
    }

    private void saveProductDetailOrder(OrderDetail orderDetail, Map<ProductDetailBatch, Long> allocations) {
        List<ProductDetailOrder> productDetailOrderList = new ArrayList<>();
        for (Map.Entry<ProductDetailBatch, Long> entry : allocations.entrySet()) {
            ProductDetailBatch productDetailBatch = entry.getKey();
            long allocatedQuantity = entry.getValue();
            ProductDetailOrder productDetailOrder = new ProductDetailOrder();
            productDetailOrder.setOrderDetail(orderDetail);
            productDetailOrder.setProductDetailBatch(productDetailBatch);
            productDetailOrder.setQuantity(allocatedQuantity);
            productDetailOrderList.add(productDetailOrder);
        }
        // Lưu danh sách vào DB
        productDetailOrderRepo.saveAll(productDetailOrderList);
    }



    @Transactional
    public OrderDetail handleSaveOrderPending(OrderDetailsReqDTO dto) {
        SellType type = dto.getType();

        OrderDetail orderDetail = new OrderDetail();
        String productName = "";
        float price = 0;
        Float quantity = 0f;

        if(type == SellType.RETAIL) {
            Product p = productSevice.getById(dto.getProductInfo().getProductId());
            productName = p.getName();
            price = p.getPrice();
            quantity = dto.getProductInfo().getQuantity();
            orderDetail.setProduct(p);

            Map<Batch, Float> allocatedBatches = allocateStockFIFO(p.getId(), quantity);
            saveOrderDetailBatches(orderDetail, allocatedBatches);
        }else{
            ProductDetail productDetail = productDetailService.getByProductDetailId(dto.getProductInfo().getProductId());
            productName = productDetail.getProductDetailName();
            price = productDetail.getPrice();
            quantity = dto.getProductInfo().getQuantity();
            orderDetail.setProductDetail(productDetail);
            Map<ProductDetailBatch, Long> allocations = allocateStockFIFOForProductDetailBatch(
                    productDetail.getId(),
                    Math.round(quantity)
            );
            saveProductDetailOrder(orderDetail, allocations);
        }

        orderDetail.setProductName(productName);
        orderDetail.setQuantity(quantity);
        orderDetail.setPrice(price);
        orderDetail.setTotal((float)( price * quantity));
        orderDetail.setOrders(dto.getOrder());
        orderDetail.setType(type.toString());
        return orderDetailRepo.save(orderDetail);
    }


    @Transactional
    public void handleSaveOrderConfirm(OrderDetail orderDetail){
        String type = orderDetail.getType();
        if(type.equals(SellType.RETAIL.toString())){
            for (OrderDetailBatch item : orderDetail.getOrderDetailBatchList()){
                Batch batch = item.getBatch();
                batch.setQuantityRetail((float) (batch.getQuantityRetail() - item.getQuantity()));
                batch.setQuantity((float) (batch.getQuantity() - item.getQuantity()));
                batch.setReservedQuantity((float) (batch.getReservedQuantity() - item.getQuantity()));
                batchSevice.batchSave(batch);
            }
        }else{
            for (ProductDetailOrder item : orderDetail.getProductDetailOrderList()){
                ProductDetailBatch productDetailBatch = item.getProductDetailBatch();
                Batch batch =  productDetailBatch.getBatch();

                productDetailBatch.setQuantity(productDetailBatch.getQuantity() - item.getQuantity());
                productDetailBatch.setReservedQuantity(productDetailBatch.getReservedQuantity() - item.getQuantity());
                productDetailService.save(productDetailBatch);

                batch.setQuantity((float) (batch.getQuantity() - item.getQuantity() * productDetailBatch.getProductDetail().getWeight()));
                batchSevice.batchSave(batch);
            }
        }
    }

    @Transactional
    public Map<Batch, Float> allocateStockFIFO(Long productId, float requiredQuantity) {
        Map<Batch, Float> allocations = new LinkedHashMap<>();

        List<Batch> availableBatches = batchSevice.findAvailableBatchesByProduct(productId);
        float remainingQuantity = requiredQuantity;
        for (Batch batch : availableBatches) {
            float availableStock = batch.getQuantityRetail() - batch.getReservedQuantity();
            if (availableStock <= 0) continue;
            float allocated = Math.min(availableStock, remainingQuantity);
            batch.setReservedQuantity(batch.getReservedQuantity() + allocated);
            batchSevice.batchSave(batch);
            allocations.put(batch, allocated);
            remainingQuantity -= allocated;
            if (remainingQuantity <= 0) break;
        }
        if (remainingQuantity > 0) {
            throw new RuntimeException("Không đủ hàng trong kho!");
        }

        return allocations;
    }
    @Transactional
    public Map<ProductDetailBatch, Long> allocateStockFIFOForProductDetailBatch(Long productDetailId, long requiredQuantity) {
        Map<ProductDetailBatch, Long> allocations = new LinkedHashMap<>();
        List<ProductDetailBatch> availableBatches = productDetailService.findFirstByProductDetailAndAvailableStock(productDetailId);
        long remainingQuantity = requiredQuantity;
        for (ProductDetailBatch productDetailBatch : availableBatches) {
            long availableStock = productDetailBatch.getQuantity() - productDetailBatch.getReservedQuantity();
            if (availableStock <= 0) continue;
            long allocated = Math.min(availableStock, remainingQuantity);
            productDetailBatch.setReservedQuantity(productDetailBatch.getReservedQuantity() + allocated);
            productDetailService.save(productDetailBatch);
            allocations.put(productDetailBatch, allocated);
            remainingQuantity -= allocated;
            if (remainingQuantity <= 0) break;
        }
        if (remainingQuantity > 0) {
            throw new RuntimeException("Không đủ hàng trong kho!");
        }

        return allocations;
    }


}
