package com.restservice.studentservices.Service;

import com.restservice.studentservices.Dao.CourseRepository;
import com.restservice.studentservices.Dao.StudentRepository;
import com.restservice.studentservices.Model.Course;
import com.restservice.studentservices.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Services
{
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public Services(StudentRepository studentRepository,
                    CourseRepository courseRepository)
    {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    public void addStudent(Student student)
    {
        studentRepository.save(student);
    }

    public void removeStudent(String studentID)
    {
        studentRepository.deleteById(studentID);
    }

    public void updateStudent(Student student)
    {
        studentRepository.save(student);
    }

    public Student getStudent(String studentID)
    {
        return studentRepository.findById(studentID).orElse(null);
    }

    public List<Student> getAllStudents()
    {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    public void addCourseToStudent(String studentID, String courseID)
    {
        Student s = studentRepository.findById(studentID).orElse(null);
        Course c = courseRepository.findById(courseID).orElse(null);
        assert s != null;
        assert c != null;
        s.setCourse(c);
        studentRepository.save(s);
    }

    public void removeCourseFromStudent(String studentID, String courseID)
    {
        Student s = studentRepository.findById(studentID).orElse(null);
        Course c = courseRepository.findById(courseID).orElse(null);
        assert s != null;
        assert c != null;
        s.getCourses().remove(c);
        studentRepository.save(s);
    }


    public Course getStudentCourse(String studentID, String courseID)
    {
        Student s = studentRepository.findById(studentID).orElse(null);
        if(s == null) return null;

        return  s.getCourses()
                .stream()
                .filter(course -> course.getId().equals(studentID))
                .findFirst()
                .orElse(null);
    }

    public void addCourse(Course course)
    {
        courseRepository.save(course);
    }

    public void removeCourse(String courseID)
    {
        courseRepository.deleteById(courseID);
    }

    public void updateCourse(Course course, String courseID)
    {
        courseRepository.save(course);
    }

    public Course getCourse(String courseID)
    {
        return courseRepository.findById(courseID).orElse(null);
    }

    public List<Course> getAllCourse()
    {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }
}
