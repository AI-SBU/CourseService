package com.restservice.studentservices.Dao;

import com.restservice.studentservices.Model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String>
{

}
