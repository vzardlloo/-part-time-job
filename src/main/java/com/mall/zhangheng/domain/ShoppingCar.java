package com.mall.zhangheng.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "shopingcar")
public class ShoppingCar {

    @Id
    @Column
    Integer id;

    @Column
    Integer cid;

    @Column
    Integer pid;


}
