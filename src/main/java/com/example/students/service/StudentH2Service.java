package com.example.students.service;

import com.example.students.repository.StudentRepository;

import com.example.students.modal.Student;

import java.util.*;

import com.example.students.modal.StudentRowMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;


@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getStudents() {
        List <Student> studentsList = db.query("select * from student", new StudentRowMapper());
        ArrayList <Student> students = new ArrayList<>(studentsList);
        return students;
    }
    
    @Override
    public Student getStudentById(int id) {

        try {
            Student student = db.queryForObject("select * from student where id = ?", new StudentRowMapper(),id);
            return student;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student addStudent(Student student) {
        db.update("insert into student(name,age) values (? ?)",student.getName(),student.getAge());

        Student savedStudent = db.queryForObject("select * from student where name = ? and age = ?", new StudentRowMapper(),student.getName(),student.getAge());
        return savedStudent;
    }


    @Override
    public Student updateStudent(int id, Student student) {
        
        if (student.getName() != null) {
            db.update("update student set name = ? where id = ?",student.getName(),id);
        }

        if (student.getAge() != 0) {
            db.update("update student set age = ? where id = ?",student.getAge(),id);
        }

        return getStudentById(id);
    }

    @Override
    public void deleteStudent(int id) {
        db.update("delete from student where id = ?",id);
    }
}
