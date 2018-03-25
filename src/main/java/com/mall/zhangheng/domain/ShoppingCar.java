package com.mall.zhangheng.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shopingcar")
public class ShoppingCar {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer id;

    @Column
    String cid;

    @Column
    String pid;

    @Column
    String cname;

    @Column
    String pname;

    @Column
    String value;

    @Column
    String payment;

}
