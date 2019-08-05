package com.iamvickyav.jdbctemplatesample.dao;

import com.iamvickyav.jdbctemplatesample.Employee;
import com.iamvickyav.jdbctemplatesample.dao.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Integer> getListOfIds() {
        return jdbcTemplate.queryForList("SELECT ID FROM EMPLOYEE", Integer.class);
    }

    public List<String> getListOfNames(){
        return jdbcTemplate.queryForList("SELECT NAME FROM EMPLOYEE", String.class);
    }

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEE", new EmployeeRowMapper());
    }

    public List<Employee> getAllEmployeesUsingBean() {
        String sql = "SELECT * FROM EMPLOYEE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee getEmployeeById(Integer id) {
        String sql = "SELECT * FROM EMPLOYEE WHERE ID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
