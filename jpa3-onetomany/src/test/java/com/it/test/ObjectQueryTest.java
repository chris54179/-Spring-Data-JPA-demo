package com.it.test;

import com.it.dao.CustomerDao;
import com.it.dao.LinkManDao;
import com.it.domain.Customer;
import com.it.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    public void testQuery1(){
        Customer customer = customerDao.getOne(1l);
        Set<LinkMan> linkMans = customer.getLinkMans();
        for (LinkMan linkMan : linkMans) {
            System.out.println(linkMan);
        }
    }
    @Test
    @Transactional
    public void testQuery2(){
        Customer customer = customerDao.findOne(1l);
        Set<LinkMan> linkMans = customer.getLinkMans();

        System.out.println("gg");
        System.out.println(linkMans.size());
    }

    @Test
    @Transactional
    public void testQuery3(){
        LinkMan linkMan = linkManDao.findOne(2l);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);

    }
}
