package com.it;

import com.it.dao.CustomerDao;
import com.it.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSpec() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = criteriaBuilder.equal(custName, "heima");

                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    @Test
    public void testSpec2() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");

                Predicate p1 = criteriaBuilder.equal(custName, "heima");
                Predicate p2 = criteriaBuilder.equal(custIndustry, "zz");

                Predicate and = criteriaBuilder.and(p1, p2);
                return and;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }
    @Test
    public void testSpec3() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Path<Object> custName = root.get("custName");
                Predicate like = criteriaBuilder.like(custName.as(String.class), "小%");

                return like;
            }
        };

        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> list = customerDao.findAll(spec,sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test
    public void testSpec4() {
        Specification spec = null;
        Pageable pageable = new PageRequest(0, 2);
        Page<Customer> page = customerDao.findAll(null, pageable);

        System.out.println(page.getContent());
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());

    }
}