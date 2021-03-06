package com.iamvickyav.jdbctemplatesample.dao;

import com.iamvickyav.jdbctemplatesample.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Integer> getListOfIds();

    List<String> getListOfNames();

    List<Employee> getAllEmployees();

    List<Employee> getAllEmployeesUsingBean();

    Employee getEmployeeById(Integer id, String name);

    public boolean deleteEmployees(Object...args);
}
