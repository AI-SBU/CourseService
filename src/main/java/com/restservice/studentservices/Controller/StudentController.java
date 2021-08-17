package com.restservice.studentservices.Controller;

import com.restservice.studentservices.Exceptoions.EmptyRepositoryException;
import com.restservice.studentservices.Exceptoions.EntityNotFoundException;
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
    public Student getStudent(@PathVariable String studentID){
        Student student = services.getStudent(studentID);
        if(student == null) throw new EntityNotFoundException("Student with ID: "  + studentID + " not found");
        return student;
    }

    @DeleteMapping("/students/{studentID}")
    public void removeStudent(@PathVariable String studentID)
    {
        if(services.getStudent(studentID) == null)
            throw new EntityNotFoundException("Student with ID: "  + studentID + " not found");
        services.removeStudent(studentID);
    }

    @PutMapping("/students")
    public void updateStudent(@RequestBody Student student)
    {
        Student s = services.getAllStudents()
                .stream()
                .filter(student1 -> student1.getId().equals(student.getId()))
                .findFirst()
                .orElse(null);
        if(s == null)
            throw new EntityNotFoundException("Student with ID " + student.getId() + " not found");
        services.updateStudent(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents()
    {
        if(services.getAllStudents().isEmpty())
            throw new EmptyRepositoryException("No students found in repository");
        return services.getAllStudents();
    }


    @PutMapping("/students/add/{studentID}/{courseID}")
    public void addCourseToStudent(@PathVariable String studentID,
                                   @PathVariable String courseID)
    {
        if(services.getStudent(studentID) == null)
            throw new EntityNotFoundException("Student with ID " + studentID + " not found");
        if(services.getCourse(courseID) == null)
            throw new EntityNotFoundException("Course with ID " + courseID + " not found");
        services.addCourseToStudent(studentID, courseID);
    }

    @PutMapping("/students/remove/{studentID}/{courseID}")
    public void removeCourseFromStudent(@PathVariable String studentID,
                                        @PathVariable String courseID)
    {
        if((services.getStudent(studentID) == null) ||
                (services.getCourse(courseID) == null))
            throw new EntityNotFoundException("Student with ID " + studentID + " not found");
        services.removeCourseFromStudent(studentID, courseID);
    }

    @GetMapping("student/{studentID}/{courseID}")
    public Course getStudentCourse(@PathVariable String studentID,
                                   @PathVariable String courseID)
    {
        if(services.getStudent(studentID) == null)
            throw new EntityNotFoundException("Student with ID " + studentID + " not found");
        Course c = services.getStudent(studentID)
                .getCourses()
                .stream()
                .filter(course -> course.getId().equals(courseID))
                .findFirst()
                .orElse(null);
        if(c == null)
            throw new EntityNotFoundException("Course with ID " + courseID + " not found");
        return services.getStudentCourse(studentID, courseID);
    }
}
