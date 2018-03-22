package com.mall.zhangheng.service;

import com.mall.zhangheng.domain.User;
import com.mall.zhangheng.exception.ErrorException;
import com.mall.zhangheng.kit.StringKit;
import com.mall.zhangheng.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User getUserById(Integer id){
        return repository.findUserById(id);
    }

    public User addUser(User user){
      return repository.save(user);
    }

    public User updateUser(User user){
        return repository.saveAndFlush(user);
    }


    public void deleteUserById(User user){
        repository.delete(user);
    }

    public User getUserByNameAndPassword(String name,String password){
        if (!StringKit.isNotBlank(name,password)){
            throw new ErrorException("this param can not be empty");
        }
        return repository.findUserByNameAndAndPassword(name, password);
    }


}
