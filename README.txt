This is the test project that is done by Oleg Minukhin for a specification
in "doc/Otus API Sample Problem.pdf".

To build the app you need to set an environment variable JAVA_HOME to path
to JDK 8 and M2_HOME to path to Maven.  To build the project and run all the test,
execute the following command:

mnv clean install

To run the service independently, execute the following command:

mvn exec:java

Then you will be able to make the following HTTP calls:

- http://localhost:8080/rs/student?first={firstName}&last={lastName}
    - Where {firstName} and {lastName} are first and last names to search for.
    Any of them or both could be skipped.
    - You can set Accept HTTP header parameter to either application/json or application/xml
- http://localhost:8080/rs/student/{id}
    - Where {id} is a student ID. If the service cannot find a user, it will
    return 404 HTTP code.
    - You can set Accept HTTP header parameter to either application/json or application/xml
