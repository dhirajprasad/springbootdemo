package com.codelab.springBootDemo.service;

import java.util.List;

import com.codelab.springBootDemo.entity.User;

public interface UserService {
	public void createUser(User user);
    public List<User> getUser(User user);
    public User findById(int id);
    public User update(User user, int id);

}
