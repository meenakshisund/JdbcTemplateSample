package com.iamvickyav.jdbctemplatesample.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public boolean deleteEmployees(Integer id1, Integer id2) {
        return employeeDao.deleteEmployees(id1, id2);
    }
}