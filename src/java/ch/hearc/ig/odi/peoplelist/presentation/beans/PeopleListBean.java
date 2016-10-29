/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.peoplelist.presentation.beans;

import ch.hearc.ig.odi.peoplelist.business.Person;
import ch.hearc.ig.odi.peoplelist.service.Services;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author dardan.kastrati
 */
@Named(value = "peopleListBean")
//@Dependent
@RequestScoped
public class PeopleListBean {
    private static final Logger LOG = Logger.getLogger(PeopleListBean.class.getName());
    
    private Person person = new Person();

    @Inject
    Services services;

    /**
     * Creates a new instance of PeopleListBean
     */
    public PeopleListBean() {
        LOG.setLevel(Level.ALL);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List getPeopleList() {
        return services.getPeopleList();
    }

    public void addPerson() {
        services.savePerson(person.getGender(), person.getFirstName(), person.getLastName(), person.isMarried(), person.getBirthDate());
    }

    public void delPerson() {
        try {
            services.deletePerson(person);

            // Redirection de la page
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
            //LOG.log(Level.SEVERE, "Une erreur s'est produite : " + e.getLocalizedMessage());
        }
    }
}
