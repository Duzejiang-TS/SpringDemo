package com.example.demomybatis.mapper;

import com.example.demomybatis.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Devin13 on 2018/8/10.
 * @version 1.0
 */
@Mapper
public interface EmployeeMapper {

    public Employee getEmyById(Integer id);

    public void insertEmp(Employee employee);
}
