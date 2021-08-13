package com.restservice.studentservices.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student
{
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String academicStanding;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> courses;

    public Student(){}

    public Student(String id, String firstName, String lastName,
                   String academicStanding)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicStanding = academicStanding;
        this.courses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getacademicStanding() {
        return academicStanding;
    }

    public void setacademicStanding(String academicStanding) {
        this.academicStanding = academicStanding;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public String toString()
    {
        return "\nID: " + this.id +
                "\nFirst Name: " + this.firstName +
                "\nLast Name: " + this.lastName +
                "\nacademicStanding: " + this.academicStanding +
                "\nCourses Enrolled: " + this.courses.toString();
    }
}
