package com.app.backend.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        System.out.println(student);
        Optional<Student> studentOptional =
                studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id){
        boolean exist = studentRepository.existsById(id);
        if(exist){
            studentRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Student with id:" +
                    id + "does not exist");
        }
    }


    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        boolean exist = studentRepository.existsById(studentId);
        if (exist) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(()->new IllegalStateException(
                            "Student with id: " +
                            studentId + " does not exist"));
            if (name != null && !name.isEmpty()
                    && !Objects.equals(student.getName(), name)){
                student.setName(name);
            }

            if (email != null && !email.isEmpty()
                    && !Objects.equals(student.getEmail(), email)){
                Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
                if(studentOptional.isPresent()){
                    throw new IllegalStateException("email is taken");
                }
                student.setEmail(email);
            }
        } else {
            throw new IllegalStateException("Student with id: " +
                    studentId + " does not exist");
        }
    }
}
