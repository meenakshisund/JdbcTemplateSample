package com.iamvickyav.jdbctemplatesample.service;

import com.iamvickyav.jdbctemplatesample.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public boolean deleteEmployees(Object...args) {
        return employeeDao.deleteEmployees(args);
    }
}