package io.zipcoder.persistenceapp;

import org.mariadb.jdbc.MySQLDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
@RestController
public class PersonService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value= "/people", method = RequestMethod.POST)
    public ResponseEntity createPerson(@RequestBody Person person){
            this.jdbcTemplate.execute(
                    "insert into PERSON (FIRST_NAME, LAST_NAME, MOBILE)" +
                            "values ('" + person.getFirstName() + "','" +
                            person.getLastName() + "','" + person.getMobileNumber() + "');");
            return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public ResponseEntity getAllPeople(){
        //this.jdbcTemplate.queryForList("SELECT * FROM people;");
        return new ResponseEntity(this.jdbcTemplate.queryForList("SELECT * FROM person;"),HttpStatus.OK);
    }
}
