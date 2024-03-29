package com.test.mvp.server.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Version;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    
    @Version
    private Integer version;
    
    private String  firstName;
    private String  lastName;
    private String  emailId;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getVersion() {
        return version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
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
    
    public String getEmailId() {
        return emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public void persist() {
        EntityManager em = EMF.get().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(this);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static Person findPerson(Long id) {
        EntityManager em = EMF.get().createEntityManager();
        try {
            Person person = em.find(Person.class, id);
            // force to get all the employees
            return person;
        } finally {
            em.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static Integer listAllCount() {
        EntityManager em = EMF.get().createEntityManager();
        try {
            List<Person> list = em.createQuery("select o from Person o").getResultList();
            // force to get all the employees
            return list.size();
        } finally {
            em.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<Person> listAll() {
        EntityManager em = EMF.get().createEntityManager();
        try {
            List<Person> list = em.createQuery("select o from Person o").getResultList();
            // force to get all the employees
            list.size();
            return list;
        } finally {
            em.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<Person> findUserEntries(int firstResult, int maxResults) {
        EntityManager em = EMF.get().createEntityManager();
        try {
            Query query = em.createQuery("select o from Person o");
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResults);
            
            List<Person> list = query.getResultList();
            // force to get all the employees
            list.size();
            return list;
        } finally {
            em.close();
        }
    }
}
