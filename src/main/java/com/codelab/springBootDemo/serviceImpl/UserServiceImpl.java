package com.codelab.springBootDemo.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codelab.springBootDemo.entity.User;
import com.codelab.springBootDemo.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		/* int x= 10/0; */
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUser(User user) {
		Query query = sessionFactory.getCurrentSession().createQuery("from User where name = :username");
		query.setParameter("name", user.getName());
		return query.list();
	}

	@Override
	@Transactional
	public User findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		return user;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public User update(User val, int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		user.setCountry(val.getCountry());
		user.setName(val.getName());
		session.update(user);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new org.springframework.security.core.userdetails.User("admin", "$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu",
				getAuthority());
	}
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
	}

}
