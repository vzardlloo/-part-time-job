package com.mall.zhangheng.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "message")
@Entity
@Data
public class Message {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String title;

    @Column
    String content;


}
