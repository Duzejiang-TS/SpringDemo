package com.example.demojpa.repository;

import com.example.demojpa.entiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Devin13 on 2018/8/11.
 * @version 1.0
 */
//继承JpaRepository完成对数据库的操作
public interface UserRepository extends JpaRepository<User,Integer> {//<要操作的实体类，这个实体类逐渐的类型>

}
