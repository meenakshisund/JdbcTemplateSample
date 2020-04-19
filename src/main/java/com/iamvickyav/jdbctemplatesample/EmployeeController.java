package com.iamvickyav.jdbctemplatesample;

import com.iamvickyav.jdbctemplatesample.dao.EmployeeDao;
import com.iamvickyav.jdbctemplatesample.dao.EmployeeService;
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
    Employee getAnEmployee(@PathVariable Integer id, @PathVariable String name) {
        return employeeDao.getEmployeeById(id, name);
    }

    @RequestMapping("/all")
    List<Employee> getAllEmployee() {
        return employeeDao.getAllEmployees();
    }

    @RequestMapping("/all/bean")
    List<Employee> getAllEmployeeUsingBean() {
        return employeeDao.getAllEmployeesUsingBean();
    }

    @RequestMapping("/all/id")
    List<Integer> getAllIds() {
       return employeeDao.getListOfIds();
    }

    @RequestMapping("/all/name")
    List<String> getAllNames() {
        return employeeDao.getListOfNames();
    }

    @RequestMapping(value = "/one/{id1}/{id2}", method = RequestMethod.DELETE)
    boolean deleteEmployees(@PathVariable Integer id1, @PathVariable Integer id2) {
        return employeeService.deleteEmployees(id1, id2);
    }
}