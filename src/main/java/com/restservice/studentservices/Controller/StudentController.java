package com.restservice.studentservices.Controller;

import com.restservice.studentservices.Model.Course;
import com.restservice.studentservices.Model.Student;
import com.restservice.studentservices.Service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final Services services;

    @Autowired
    public StudentController(Services services) {
        this.services = services;
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) {
        services.addStudent(student);
    }

    @GetMapping("/students/{studentID}")
    public Student getStudent(@PathVariable String studentID)
    {
        return services.getStudent(studentID);
    }

    @DeleteMapping("/students/{studentID}")
    public void removeStudent(@PathVariable String studentID) {
        services.removeStudent(studentID);
    }

    @PutMapping("/students")
    public void updateStudent(@RequestBody Student student)
    {
        services.updateStudent(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return services.getAllStudents();
    }

    @PostMapping("/students/{studentID}/{courseID}")
    public void addCourseToStudent(@PathVariable String studentID,
                                   @PathVariable String courseID)
    {
        services.addCourseToStudent(studentID, courseID);
    }

    @GetMapping("/students/{studentID}")
    public List<Course> getStudentCourses(@PathVariable String studentID) {
        return services.getStudentCourses(studentID);
    }

    @GetMapping("student/{studentID}/{courseID}")
    public Course getStudentCourse(@PathVariable String studentID,
                                   @PathVariable String courseID)
    {
        return services.getStudentCourse(studentID, courseID);
    }
}
