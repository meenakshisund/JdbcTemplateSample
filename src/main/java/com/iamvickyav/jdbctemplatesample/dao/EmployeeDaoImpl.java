package com.iamvickyav.jdbctemplatesample.dao;

import com.iamvickyav.jdbctemplatesample.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Integer> getListOfIds() {
        return jdbcTemplate.queryForList("SELECT ID FROM EMPLOYEE", Integer.class);
    }

    public List<String> getListOfNames() {
        return jdbcTemplate.queryForList("SELECT NAME FROM EMPLOYEE", String.class);
    }

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEE", new EmployeeRowMapper());
    }

    public List<Employee> getAllEmployeesUsingBean() {
        String sql = "SELECT * FROM EMPLOYEE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee getEmployeeById(Integer id, String name) {
        String sql = "SELECT * FROM EMPLOYEE WHERE ID = ? AND LOWER(NAME) = ?";
        Object[] arr = new Object[]{id, name};

        try {
            return jdbcTemplate.queryForObject(sql, arr, new EmployeeRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional(rollbackFor = {DataAccessException.class})
    public boolean deleteEmployees(Integer id1, Integer id2) {
        String sql = "DELETE FROM EMPLOYEE WHERE ID = ?";
        Object[] arr1 = new Object[]{id1};
        Object[] arr2 = new Object[]{"hello"};

        try {
            int deleteOne = jdbcTemplate.update(sql, arr1);
            int deleteTwo = jdbcTemplate.update(sql, arr2);
            if (deleteOne > 0 && deleteTwo > 0)
                return true;
        } catch (DataAccessException e) {
            throw e;
        }
        return false;
    }
}