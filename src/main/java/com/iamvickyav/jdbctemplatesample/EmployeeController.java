package com.iamvickyav.jdbctemplatesample;

import com.iamvickyav.jdbctemplatesample.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeDao dao;

    @RequestMapping("/one/{id}")
    Employee getAnEmployee(@PathVariable Integer id) {
        return dao.getEmployeeById(id);
    }

    @RequestMapping("/all")
    List<Employee> getAllEmployee() {
        return dao.getAllEmployees();
    }

    @RequestMapping("/all/bean")
    List<Employee> getAllEmployeeUsingBean() {
        return dao.getAllEmployeesUsingBean();
    }

    @RequestMapping("/all/id")
    List<Integer> getAllIds() {
       return dao.getListOfIds();
    }

    @RequestMapping("/all/name")
    List<String> getAllNames() {
        return dao.getListOfNames();
    }
}
