package com.it.test;

import com.it.dao.RoleDao;
import com.it.dao.UserDao;
import com.it.domain.Role;
import com.it.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        User user = new User();
        user.setUserName("u");

        Role role = new Role();
        role.setRoleName("ro");

        user.getRoles().add(role);

        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd(){
        User user = new User();
        user.setUserName("u");

        Role role = new Role();
        role.setRoleName("ro");

        user.getRoles().add(role);

        role.getUsers().add(user);

        userDao.save(user);
//        roleDao.save(role);
    }


    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeRemove(){
        User user = userDao.findOne(1l);
        userDao.delete(user);
    }
}
