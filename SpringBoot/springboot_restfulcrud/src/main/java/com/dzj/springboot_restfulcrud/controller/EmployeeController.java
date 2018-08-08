package com.dzj.springboot_restfulcrud.controller;

import com.dzj.springboot_restfulcrud.dao.EmployeeDao;
import com.dzj.springboot_restfulcrud.entities.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author Devin13 on 2018/8/7.
 * @version 1.0
 */
@Controller
public class EmployeeController {

    @Resource
    EmployeeDao employeeDao;

    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();

        model.addAttribute("emps",employees);
        return "emp/list";
    }
}
