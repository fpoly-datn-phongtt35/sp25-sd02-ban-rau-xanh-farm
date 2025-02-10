package com.example.datnv1.Entity.Promotion;

import com.example.datnv1.Entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "promotion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Promotion extends BaseEntity {
    @Column
    private String code;

    @Column
    private String name;

    @Column
    private Long value;

    @Column
    private Integer type;

    @Column
    private String note;

    @Column
    private LocalDateTime startAt;

    @Column
    private LocalDateTime endAt;


}
