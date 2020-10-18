package com.it.test;

import com.it.dao.CustomerDao;
import com.it.dao.LinkManDao;
import com.it.domain.Customer;
import com.it.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        Customer customer = new Customer();
        customer.setCustName("bai");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("li");

        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd1(){
        Customer customer = new Customer();
        customer.setCustName("bai");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("li");

        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd2(){
        Customer customer = new Customer();
        customer.setCustName("bai");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("li");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd(){
        Customer customer = new Customer();
        customer.setCustName("bai1");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("li1");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeDelete(){
        Customer customer = customerDao.findOne(1l);
        customerDao.delete(customer);
    }
}
