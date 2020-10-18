package com.it.test;

import com.it.dao.CustomerDao;
import com.it.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(2l);
        System.out.println(customer);
    }
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setCustName("丁丁");
        customer.setCustAddress("zz");
        customerDao.save(customer);
    }
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustId(8l);
        customer.setCustName("丁丁2");
        customerDao.save(customer);
    }
    @Test
    public void testUpdate2(){
        Customer customer = customerDao.findOne(8l);
        customer.setCustId(8l);
        customer.setCustName("丁丁3");
        customerDao.save(customer);
    }
    @Test
    public void testDelete(){
        customerDao.delete(8l);
    }
    @Test
    public void testFindAll(){
        List<Customer> all = customerDao.findAll();
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }

    @Test
    public void testExists(){
        boolean exists = customerDao.exists(8L);
        System.out.println(exists);
    }
    @Test
    @Transactional
    public void testGeton(){
        Customer customer = customerDao.getOne(7L);
        System.out.println(customer);
    }
}
