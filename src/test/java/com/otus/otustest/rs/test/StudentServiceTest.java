package com.otus.otustest.rs.test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.otus.otustest.rs.SearchResult;
import com.otus.otustest.rs.StudentResult;
import com.otus.otustest.rs.StudentService;

public class StudentServiceTest
        extends JerseyTest {

    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(StudentService.class);
    }

    @Test
    public void testGetAllStudentsJson() {
        Response response = target("/student").request(MediaType.APPLICATION_JSON).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertEquals("The number of students should be: ", 13, searchResult.getStudent().size());
    }

    @Test
    public void testGetAllStudentsXml() {
        Response response = target("/student").request(MediaType.APPLICATION_XML).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_XML, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertEquals("The number of students should be: ", 13, searchResult.getStudent().size());
    }

    @Test
    public void testStudentsByFirstNameJson() {
        Response response = target("/student").queryParam("first", "John").request(MediaType.APPLICATION_JSON).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertEquals("The number of students should be: ", 1, searchResult.getStudent().size());
        assertTrue("Students should have correct first name", searchResult.getStudent().stream().allMatch(s-> s.getFirstName().equals("John")));
    }

    @Test
    public void testStudentsByFirstNameXml() {
        Response response = target("/student").queryParam("first", "John").request(MediaType.APPLICATION_XML).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_XML, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertEquals("The number of students should be: ", 1, searchResult.getStudent().size());
        assertTrue("Students should have correct first name", searchResult.getStudent().stream().allMatch(s-> s.getFirstName().equals("John")));
    }

    @Test
    public void testStudentsByFirstNameNotFoundJson() {
        Response response = target("/student").queryParam("first", "Aaa").request(MediaType.APPLICATION_JSON).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertEquals("The number of students should be: ", 0, searchResult.getStudent().size());
    }

    @Test
    public void testStudentsByFirstNameNotFoundXml() {
        Response response = target("/student").queryParam("first", "Aaa").request(MediaType.APPLICATION_XML).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_XML, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertNull("There should be no students", searchResult.getStudent());
    }

    @Test
    public void testStudentsByLastNameJson() {
        Response response = target("/student").queryParam("last", "Smith").request(MediaType.APPLICATION_JSON).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertEquals("The number of students should be: ", 2, searchResult.getStudent().size());
        assertTrue("Students should have correct last name", searchResult.getStudent().stream().allMatch(s-> s.getLastName().equals("Smith")));
    }

    @Test
    public void testStudentsByLastNameXml() {
        Response response = target("/student").queryParam("last", "Smith").request(MediaType.APPLICATION_XML).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_XML, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertEquals("The number of students should be: ", 2, searchResult.getStudent().size());
        assertTrue("Students should have correct last name", searchResult.getStudent().stream().allMatch(s-> s.getLastName().equals("Smith")));
    }

    @Test
    public void testStudentsByLastNameNotFoundJson() {
        Response response = target("/student").queryParam("last", "Aaa").request(MediaType.APPLICATION_JSON).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertEquals("The number of students should be: ", 0, searchResult.getStudent().size());
    }

    @Test
    public void testStudentsByLastNameNotFoundXml() {
        Response response = target("/student").queryParam("last", "Aaa").request(MediaType.APPLICATION_XML).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_XML, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertNull("There should be no students", searchResult.getStudent());
    }

    @Test
    public void testStudentsByFirstAndLastNameJson() {
        Response response = target("/student").queryParam("first", "Jane").queryParam("last", "Smith").request(MediaType.APPLICATION_JSON).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertEquals("The number of students should be: ", 1, searchResult.getStudent().size());
        assertTrue("Students should have correct last name", searchResult.getStudent().stream().allMatch(s-> s.getFirstName().equals("Jane") && s.getLastName().equals("Smith")));
    }

    @Test
    public void testStudentsByFistAndLastNameXml() {
        Response response = target("/student").queryParam("first", "Jane").queryParam("last", "Smith").request(MediaType.APPLICATION_XML).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_XML, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertEquals("The number of students should be: ", 1, searchResult.getStudent().size());
        assertTrue("Students should have correct last name", searchResult.getStudent().stream().allMatch(s-> s.getFirstName().equals("Jane") && s.getLastName().equals("Smith")));
    }

    @Test
    public void testStudentsByFistAndLastNameNotFoundJson() {
        Response response = target("/student").queryParam("fist", "Aaa").queryParam("last", "Aaa").request(MediaType.APPLICATION_JSON).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertEquals("The number of students should be: ", 0, searchResult.getStudent().size());
    }

    @Test
    public void testStudentsByFistAndLastNameNotFoundXml() {
        Response response = target("/student").queryParam("first", "Aaa").queryParam("last", "Aaa").request(MediaType.APPLICATION_XML).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_XML, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        SearchResult searchResult = response.readEntity(SearchResult.class);
        assertNull("There should be no students", searchResult.getStudent());
    }

    @Test
    public void testStudentDetailsFor0Json() {
        Response response = target("/student/0").request(MediaType.APPLICATION_JSON).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        StudentResult searchResult = response.readEntity(StudentResult.class);
        assertEquals(0, searchResult.getId());
        assertEquals("John", searchResult.getFirstName());
        assertEquals("Smith", searchResult.getLastName());
        assertEquals("johnsmith@mailinator.com", searchResult.getEmailAddress());
        assertEquals(3.1666666666666665, searchResult.getAverageGrade(), 0);
        assertEquals(6, searchResult.getStudentClass().size());
    }

    @Test
    public void testStudentDetailsFor0Xml() {
        Response response = target("/student/0").request(MediaType.APPLICATION_XML).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_XML, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        StudentResult searchResult = response.readEntity(StudentResult.class);
        assertEquals(0, searchResult.getId());
        assertEquals("John", searchResult.getFirstName());
        assertEquals("Smith", searchResult.getLastName());
        assertEquals("johnsmith@mailinator.com", searchResult.getEmailAddress());
        assertEquals(3.1666666666666665, searchResult.getAverageGrade(), 0);
        assertEquals(6, searchResult.getStudentClass().size());
    }

    @Test
    public void testStudentDetailsFor1Json() {
        Response response = target("/student/1").request(MediaType.APPLICATION_JSON).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        StudentResult searchResult = response.readEntity(StudentResult.class);
        assertEquals(1, searchResult.getId());
        assertEquals("Jane", searchResult.getFirstName());
        assertEquals("Smith", searchResult.getLastName());
        assertEquals("janesmith@mailinator.com", searchResult.getEmailAddress());
        assertEquals(2.75, searchResult.getAverageGrade(), 0);
        assertEquals(6, searchResult.getStudentClass().size());
    }

    @Test
    public void testStudentDetailsFor1Xml() {
        Response response = target("/student/1").request(MediaType.APPLICATION_XML).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_XML, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        StudentResult searchResult = response.readEntity(StudentResult.class);
        assertEquals(1, searchResult.getId());
        assertEquals("Jane", searchResult.getFirstName());
        assertEquals("Smith", searchResult.getLastName());
        assertEquals("janesmith@mailinator.com", searchResult.getEmailAddress());
        assertEquals(2.75, searchResult.getAverageGrade(), 0);
        assertEquals(6, searchResult.getStudentClass().size());
    }

    @Test
    public void testStudentDetailsFor12Json() {
        Response response = target("/student/12").request(MediaType.APPLICATION_JSON).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        StudentResult searchResult = response.readEntity(StudentResult.class);
        assertEquals(12, searchResult.getId());
        assertEquals("Jose", searchResult.getFirstName());
        assertEquals("Santiago", searchResult.getLastName());
        assertEquals("jsantiago@mailinator.com", searchResult.getEmailAddress());
        assertEquals(3, searchResult.getAverageGrade(), 0);
        assertEquals(6, searchResult.getStudentClass().size());
    }

    @Test
    public void testStudentDetailsFor12Xml() {
        Response response = target("/student/12").request(MediaType.APPLICATION_XML).get();
        assertEquals("Http Response should be: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_XML, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        StudentResult searchResult = response.readEntity(StudentResult.class);
        assertEquals(12, searchResult.getId());
        assertEquals("Jose", searchResult.getFirstName());
        assertEquals("Santiago", searchResult.getLastName());
        assertEquals("jsantiago@mailinator.com", searchResult.getEmailAddress());
        assertEquals(3, searchResult.getAverageGrade(), 0);
        assertEquals(6, searchResult.getStudentClass().size());
    }

    @Test
    public void testStudentDetailsNotFoundJson() {
        Response response = target("/student/13").request(MediaType.APPLICATION_JSON).get();
        assertEquals("Http Response should be: ", Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStudentDetailsNotFoundXml() {
        Response response = target("/student/13").request(MediaType.APPLICATION_XML).get();
        assertEquals("Http Response should be: ", Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

}
