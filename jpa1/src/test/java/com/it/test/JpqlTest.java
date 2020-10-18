package com.it.test;

import com.it.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import com.it.domain.Customer;

public class JpqlTest {
    @Test
    public void testFindAll(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin() ;

        String jpql = "from Customer";

        Query query = em.createQuery(jpql);

        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }

        tx.commit();
        em.close();
    }

    @Test
    public void testOrders(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "from Customer order by custId desc ";

        Query query = em.createQuery(jpql);

        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }

        tx.commit();
        em.close();
    }
    @Test
    public void testCount(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "select count(custId) from Customer ";

        Query query = em.createQuery(jpql);

        Object result = query.getSingleResult();
        System.out.println(result);

        tx.commit();
        em.close();
    }
    @Test
    public void testPaged(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = " from Customer ";

        Query query = em.createQuery(jpql);

        query.setFirstResult(0);
        query.setMaxResults(2);



        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }

        tx.commit();
        em.close();
    }
    @Test
    public void testCondition(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = " from Customer where custName like ?";

        Query query = em.createQuery(jpql);

        query.setParameter(1, "小%");


        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }

        tx.commit();
        em.close();
    }
}
