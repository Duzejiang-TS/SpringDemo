package com.example.demomybatis.controller;

import com.example.demomybatis.bean.Department;
import com.example.demomybatis.bean.Employee;
import com.example.demomybatis.mapper.DepartmentMapper;
import com.example.demomybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Devin13 on 2018/8/10.
 * @version 1.0
 */
@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }

    @GetMapping("/dept")
    public Department insertDept(Department department){
        departmentMapper.insertDept(department);
        return department;
    }
    @GetMapping("/emp/{id}")
    public Employee  getEmp(@PathVariable("id") Integer id){
        return employeeMapper.getEmyById(id);
    }
}
