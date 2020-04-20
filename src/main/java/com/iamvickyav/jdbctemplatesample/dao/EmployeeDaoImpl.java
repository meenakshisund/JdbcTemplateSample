package com.iamvickyav.jdbctemplatesample.dao;

import com.iamvickyav.jdbctemplatesample.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;

@Repository
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
            // Using RowMapper
            Employee emp = jdbcTemplate.queryForObject(sql, arr, new EmployeeRowMapper());

            // Using lambda
            return jdbcTemplate.queryForObject(sql, arr, (ResultSet resultSet, int rowNum) -> {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("ID"));
                employee.setName(resultSet.getString("NAME"));
                return employee;
            });

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional
    public boolean deleteEmployees(Object...args) {
        deleteOne(args);
        insert();
        return true;
    }

    private void deleteOne(Object...args){
        String sql = "delete from EMPLOYEE where id=?";
        for (Object arg : args) {
            jdbcTemplate.update(sql, arg);
        }
    }

    private void insert() {
        jdbcTemplate.update("insert into EMPLOYEE(ID,NAME) values(?,?)", 1, "SACHIN");
    }
}