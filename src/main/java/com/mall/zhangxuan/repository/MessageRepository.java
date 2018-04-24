package com.mall.zhangxuan.repository;


import com.mall.zhangxuan.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {


}
