package com.example.students.modal;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    public Student mapRow(ResultSet rs, int rowNum ) throws SQLException {
        return new Student (
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("age"));
    }
}
