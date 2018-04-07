package io.zipcoder.persistenceapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RestController
public class PersonService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseEntity createPerson(@RequestBody Person person) {
        this.jdbcTemplate.execute(
                "insert into PERSON (FIRST_NAME, LAST_NAME, MOBILE)" +
                        "values ('" + person.getFirstName() + "','" +
                        person.getLastName() + "','" + person.getMobileNumber() + "');");
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public ResponseEntity getAllPeople() {
        return new ResponseEntity(this.jdbcTemplate.queryForList("SELECT * FROM person;"), HttpStatus.OK);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity getPersonById(@PathVariable long id) {
        return new ResponseEntity(this.jdbcTemplate.queryForList("SELECT * FROM PERSON WHERE ID =" +
                id + ";"), HttpStatus.OK);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public ResponseEntity updatePerson(@PathVariable long id) {
        Person person = (Person) this.jdbcTemplate.queryForList("SELECT * from person WHERE ID =" + id + ";").get(0);
        if (person != null) {
            this.jdbcTemplate.update("UPDATE PERSON (FIRST_NAME, LAST_NAME, MOBILE)" +
                    "VALUES ('" + person.getFirstName() +"'.'" + person.getLastName() + "','" +
            person.getMobileNumber()+"');");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePerson(@PathVariable long id){
        this.jdbcTemplate.execute("DELETE from Person where ID =" + id +";");
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/people/reverselookup/{mobileNumber}", method = RequestMethod.GET)
    public ResponseEntity getPersonByMobileNumber(@PathVariable String mobileNumber){
        return new ResponseEntity(this.jdbcTemplate.queryForList("SELECT * FROM PERSON WHERE MOBILE = '"
                + mobileNumber+"';"), HttpStatus.OK);
    }

    @RequestMapping(value = "/people/surname/{lastName}", method = RequestMethod.GET)
    public ResponseEntity getPeopleByLastName(@PathVariable String lastName){
        return new ResponseEntity(this.jdbcTemplate.queryForList("SELECT * FROM Person WHERE LAST_NAME ='" +
        lastName+ "';"), HttpStatus.OK);
    }

    @RequestMapping(value = "/people/surname", method = RequestMethod.GET)
    public ResponseEntity displayLastNames(){
        return new ResponseEntity(this.jdbcTemplate.queryForList("SELECT LAST_NAME FROM PERSON;"), HttpStatus.OK);
    }

    @RequestMapping(value = "/people/firstname/stats", method = RequestMethod.GET)
    public ResponseEntity firstNameFrequency(){
        return new ResponseEntity(this.jdbcTemplate.queryForList("SELECT FIRST_NAME, COUNT(FIRST_NAME)" +
                " FROM PERSON GROUP BY FIRST_NAME;"), HttpStatus.OK);
    }

}
