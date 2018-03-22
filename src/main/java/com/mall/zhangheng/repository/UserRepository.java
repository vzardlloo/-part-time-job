package com.mall.zhangheng.repository;


import com.mall.zhangheng.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{

     User findUserById(Integer id);
     User findUserByNameAndAndPassword(String name,String password);

}
