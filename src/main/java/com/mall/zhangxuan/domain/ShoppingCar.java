package com.mall.zhangxuan.domain;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
