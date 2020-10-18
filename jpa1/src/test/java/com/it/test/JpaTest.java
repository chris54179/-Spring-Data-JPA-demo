package com.it.test;

import com.it.domain.Customer;
import com.it.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {
    @Test
    public void testSave(){
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
//
//        EntityManager em = factory.createEntityManager();

        EntityManager em = JpaUtils.getEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer customer = new Customer();
        customer.setCustName("小明");
        customer.setCustIndustry("test");

        em.persist(customer);

        tx.commit();
        em.close();
//        factory.close();
    }
    @Test
    public void testFind(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Customer customer = entityManager.find(Customer.class, 1l );
        System.out.println(customer);
        tx.commit();
        entityManager.close();
    }
    @Test
    public void testReference(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Customer customer = entityManager.getReference(Customer.class, 1l );
        System.out.println(customer);
        tx.commit();
        entityManager.close();
    }

    @Test
    public void testRemove(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Customer customer = entityManager.find(Customer.class, 1l);

        entityManager.remove(customer);

        tx.commit();
        entityManager.close();
    }

    @Test
    public void testUpdate(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Customer customer = entityManager.find(Customer.class, 2l);
        customer.setCustIndustry("gg");

        entityManager.merge(customer);

        tx.commit();
        entityManager.close();
    }
}
