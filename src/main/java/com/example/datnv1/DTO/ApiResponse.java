package com.example.datnv1.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Bỏ qua các giá trị null khi serialize JSON
public class ApiResponse<T> {

    private HttpStatus status;
    private String message;
    private T data;
    private LocalDateTime timestamp;
    private String error;

    // Phương thức tạo phản hồi thành công
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .status(HttpStatus.OK)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Phương thức tạo phản hồi lỗi
    public static ApiResponse<?> error(HttpStatus status, String message, String errorDetails) {
        return ApiResponse.builder()
                .status(status)
                .message(message)
                .error(errorDetails)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
