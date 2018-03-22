package com.mall.zhangheng.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @Column
    Integer id;
    @Column
    String  name;
    @Column
    String  password;
    @Column
    String  email;


}
