package com.mall.zhangheng.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String  score;
    @Column
    private String  location;
    @Column
    private String  phone;
    @Column
    private String  email;
    @Column
    private String  name;
    @Column
    private String  password;


}
