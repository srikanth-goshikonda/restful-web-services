package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();
	private static int userCount =3;
	
	static {
		users.add(new User(1, "Srikanth","sg@gmail.com" ,new Date()));
		users.add(new User(2, "Naresh", "ng@gmail.com",new Date()));
		users.add(new User(3, "Suresh", "sg@gmail.com",new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User saveUser(User user) {
		if(user.getId() == null)
		{
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findUserById(int id)
	{
		for(User user : users) {
			if(user.getId() == id)
			{
				return user;
			}
		}
		return null;
	}

	public User deleteById(int userId) {
		// TODO Auto-generated method stub
		for(User user :users)
		{
			if(user.getId() == userId)
			{
				users.remove(user);
				return user;
			}
		}
		return null;
	}
}
