package com.restservice.studentservices.Dao;

import com.restservice.studentservices.Model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String>
{
}
