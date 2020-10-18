package com.it.test;

import com.it.dao.CustomerDao;
import com.it.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne(){
        Customer customer = customerDao.findJpql("丁丁2");
        System.out.println(customer);
    }
    @Test
    public void testFindCustNameAndId(){
//        Customer customer = customerDao.findCustNameAndId("丁丁2", 7L);
        Customer customer = customerDao.findCustNameAndId( 7L,"丁丁2");
        System.out.println(customer);
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdate(){
        customerDao.updateCustomer(7l, "heima");
    }

    @Test
    public void testFindSql(){
        List<Object[]> list = customerDao.findSql("丁%");
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testNaming(){
        Customer customer = customerDao.findByCustName("heima");
        System.out.println(customer);
    }

    @Test
    public void testNamingLike(){
        List<Customer> list = customerDao.findByCustNameLike("小明%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void testFindByCustNameLikeAnAndCustIndustry(){
        Customer customer = customerDao.findByCustNameLikeAndCustIndustry("小%", "gg");
        System.out.println(customer);
    }
}
