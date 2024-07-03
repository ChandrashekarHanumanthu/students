package com.example.students.repository;

import com.example.students.modal.Student;

import java.util.*;

public interface StudentRepository {
    ArrayList<Student> getStudents();

    Student getStudentById(int id);

    Student addStudent(Student student);

    Student updateStudent(int studentId, Student student);

    void deleteStudent(int id);
}
