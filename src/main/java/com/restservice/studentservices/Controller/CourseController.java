package com.restservice.studentservices.Controller;

import com.restservice.studentservices.Model.Course;
import com.restservice.studentservices.Service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController
{
    protected final Services courseService;

    @Autowired
    public CourseController(Services courseService)
    {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    public void addCourse(@RequestBody Course course)
    {
        courseService.addCourse(course);
    }

    @DeleteMapping("courses/{courseID}")
    public void removeCourse(@PathVariable String courseID)
    {
        courseService.removeCourse(courseID);
    }

    @PutMapping("/courses/{courseID}")
    public void updateCourse(@RequestBody Course course,
                             @PathVariable String courseID)
    {
        courseService.updateCourse(course,courseID);
    }

    @GetMapping("/courses/{courseID}")
    public Course getCourse(@PathVariable String courseID)
    {
        return courseService.getCourse(courseID);
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses()
    {
        return courseService.getAllCourse();
    }
}
