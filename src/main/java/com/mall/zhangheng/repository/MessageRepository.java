package com.mall.zhangheng.repository;


import com.mall.zhangheng.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {


}
