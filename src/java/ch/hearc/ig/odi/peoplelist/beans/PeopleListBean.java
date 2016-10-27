/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.peoplelist.beans;

import ch.hearc.ig.odi.peoplelist.business.Person;
import ch.hearc.ig.odi.peoplelist.service.Services;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author dardan.kastrati
 */
@Named(value = "peopleListBean")
//@Dependent
@RequestScoped
public class PeopleListBean {
    private Person person = new Person();

    @Inject Services services;
    
    /**
     * Creates a new instance of PeopleListBean
     */
    public PeopleListBean() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    public List getPeopleList(){
        return services.getPeopleList();
    }
    
    public void addPerson(){
        services.savePerson(person.getGender(), person.getFirstName(), person.getLastName(), person.isMarried(), person.getBirthDate());
    }
}
