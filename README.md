# Spring - JdbcTemplate

## Query - Read values from one column & multiple rows

```java
jdbcTemplate.queryForList("SELECT NAME FROM EMPLOYEE", String.class);
```

## Query - Read values from multiple columns & multiple rows using RowMapper

```java
jdbcTemplate.query("SELECT * FROM EMPLOYEE", new EmployeeRowMapper());

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("ID"));
        employee.setName(resultSet.getString("NAME"));
        return employee;
    }
}
```

## Query - Read values from multiple columns & multiple rows using BeanPropertyRowMapper

```java
jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));

public class Employee {
    private Integer id;
    private String name;
    
    // Getters & Setters
}
```

## Query - Read only one row

```java
String sql = "SELECT * FROM EMPLOYEE WHERE ID = ?";
try {
    return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());
 } catch (EmptyResultDataAccessException e) {
    return null;
 }
```
