package com.iamvickyav.jdbctemplatesample.controller;

import com.iamvickyav.jdbctemplatesample.dao.EmployeeDao;
import com.iamvickyav.jdbctemplatesample.model.Employee;
import com.iamvickyav.jdbctemplatesample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/one/{id}/{name}")
    Employee getEmployee(@PathVariable Integer id, @PathVariable String name) {
        return employeeDao.getEmployeeById(id, name);
    }

    @RequestMapping("/all")
    List<Employee> getEmployees() {
        return employeeDao.getAllEmployees();
    }

    @RequestMapping("/all/bean")
    List<Employee> getEmployeeUsingBean() {
        return employeeDao.getAllEmployeesUsingBean();
    }

    @RequestMapping("/all/id")
    List<Integer> getIds() {
       return employeeDao.getListOfIds();
    }

    @RequestMapping("/all/name")
    List<String> getNames() {
        return employeeDao.getListOfNames();
    }

    @RequestMapping(value = "/employees/{id1}/{id2}", method = RequestMethod.DELETE)
    boolean deleteEmployees(@PathVariable Object id1, @PathVariable Object id2) {
        return employeeService.deleteEmployees(id1, id2);
    }
}