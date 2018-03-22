package com.mall.zhangheng.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @Column
    String count;

    @Column
    String name;

    @Column
    String detail;

    @Column
    String info;

    @Column
    String image;

    @Column
    String price;

    @Column
    String category;



}
