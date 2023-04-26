package com.demo.service;

import com.demo.entity.Student;
import com.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    public List<Student> getStudents(){
       return studentRepository.findAll();
    }


    public Student updateStudent(int id, Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isEmpty())
            throw new RuntimeException("Student Not found with id: " + id);

        Student s = new Student();
        s.setId(student.getId());
        s.setFirstName(student.getFirstName());
        s.setLastName(student.getLastName());
        s.setCourse(student.getCourse());
        s.setCountry(student.getCountry());
        return studentRepository.saveAndFlush(s);

    }

    public String deleteStudent(int id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isEmpty())
            return "Student Not found with id: " + id;
        else {
            studentRepository.delete(existingStudent.get());
            return "Student deleted";
        }
    }
}
