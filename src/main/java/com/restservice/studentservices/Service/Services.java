package com.restservice.studentservices.Service;

import com.restservice.studentservices.Dao.CourseRepository;
import com.restservice.studentservices.Dao.StudentRepository;
import com.restservice.studentservices.Model.Course;
import com.restservice.studentservices.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * All of the services this rest api provides
 */

@Service
public class Services
{
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    /**
     * Wiring this class to course and student repository
     * @param studentRepository a crud repository of student objects
     * @param courseRepository a crud repository of student objects
     */
    @Autowired
    public Services(StudentRepository studentRepository,
                    CourseRepository courseRepository)
    {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * Adds a student object to repository
     * @param student entity to add to the respective repository
     */
    public void addStudent(Student student)
    {
        studentRepository.save(student);
    }

    /**
     * Deletes a student object from the repository with the specified ID
     * @param studentID ID of the student to be removed from the repository
     */
    public void removeStudent(String studentID)
    {
        studentRepository.deleteById(studentID);
    }

    /**
     * Updates the specified object
     * @param student the object to be updated
     */
    public void updateStudent(Student student)
    {
        studentRepository.save(student);
    }

    /**
     *
     * @param studentID ID of the student to find
     * @return the student object with specified ID
     */
    public Student getStudent(String studentID)
    {
        return studentRepository.findById(studentID).orElse(null);
    }

    /**
     *
     * @return List of all students in the student repository
     */
    public List<Student> getAllStudents()
    {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    /**
     *
     * @param studentID ID of the student which the course will be added to
     * @param courseID ID of the course which will be added to the student
     */
    public void addCourseToStudent(String studentID, String courseID)
    {
        Student s = studentRepository.findById(studentID).orElse(null);
        Course c = courseRepository.findById(courseID).orElse(null);
        assert s != null;
        assert c != null;
        s.setCourse(c);
        studentRepository.save(s);
    }

    /**
     *
     * @param studentID ID of the student which the course will be removed from
     * @param courseID ID of the course which will be removed from the student
     */
    public void removeCourseFromStudent(String studentID, String courseID)
    {
        Student s = studentRepository.findById(studentID).orElse(null);
        Course c = courseRepository.findById(courseID).orElse(null);
        assert s != null;
        assert c != null;
        s.getCourses().remove(c);
        studentRepository.save(s);
    }


    /**
     *
     * @param studentID ID of the student whose course will be returned
     * @param courseID ID of the course which will be returned
     * @return The course with the specified ID if found, null otherwise
     */
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

    /**
     *
     * @param course adds this course to the course repository
     */
    public void addCourse(Course course)
    {
        courseRepository.save(course);
    }

    /**
     *
     * @param courseID removes the course with this id from the repository
     */
    public void removeCourse(String courseID)
    {
        courseRepository.deleteById(courseID);
    }

    /**
     *
     * @param course course object to be updated
     * @param courseID ID of the course to be updated
     */
    public void updateCourse(Course course, String courseID)
    {
        courseRepository.save(course);
    }

    /**
     *
     * @param courseID ID of the course to find in the repository
     * @return Course object with the specified ID
     */
    public Course getCourse(String courseID)
    {
        return courseRepository.findById(courseID).orElse(null);
    }

    /**
     *
     * @return List of all courses
     */
    public List<Course> getAllCourse()
    {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }
}
