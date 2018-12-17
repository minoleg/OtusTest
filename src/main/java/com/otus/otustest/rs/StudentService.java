package com.otus.otustest.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.otus.otustest.model.DataStore;
import com.otus.otustest.model.Student;

@Path("student")
public class StudentService {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public SearchResult search(@QueryParam("first") String first, @QueryParam("last") String last) {
        List<Student> studentList = DataStore.getInstance().findStudentByFirstAndLast(first, last);
        return new SearchResult(studentList);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public StudentResult search(@PathParam("id") int id) {
        Student student = DataStore.getInstance().getStudent(id);
        if (student == null) {
            throw new NotFoundException("Customer with id " + id + " was not found");
        } else {
            return new StudentResult(student);
        }
    }

}
