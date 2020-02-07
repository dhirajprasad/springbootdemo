package com.codelab.springBootDemo.serviceImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codelab.springBootDemo.entity.User;
import com.codelab.springBootDemo.service.UserService;

@Service@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
    private SessionFactory sessionFactory;
	@Override
	public void createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
        session.save(user);
	}

	@Override
	public List<User> getUser() {
		return null;
	}

	@Override
	public User findById(int id) {
		Session session = sessionFactory.getCurrentSession();
        User user=(User) session.get(User.class,id);
        return user;
	}

	@Override
	public User update(User val, int id) {
		Session session = sessionFactory.getCurrentSession();
        User user =(User)session.get(User.class, id);
        user.setCountry(val.getCountry());
        user.setName(val.getName());
        session.update(user);
        return user;
	}

}
