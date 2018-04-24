package com.mall.zhangxuan.repository;


import com.mall.zhangxuan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{

     User findUserById(Integer id);
     User findUserByNameAndAndPassword(String name,String password);

}
