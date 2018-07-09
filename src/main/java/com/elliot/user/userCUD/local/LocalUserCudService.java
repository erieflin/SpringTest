package com.elliot.user.userCUD.local;

import java.util.HashMap;

import com.elliot.user.User;
import com.elliot.user.userCUD.api.UserCUDService;

public class LocalUserCudService implements UserCUDService
{
	public LocalUserCudService(){
		
	}
	private static final HashMap <String, User> users = new HashMap<String, User>();
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return users.put(user.getId(), user);
	}

	@Override
	public User find(String id) {
		// TODO Auto-generated method stub
		return users.get(id);
	}

	@Override
	public User findByUsername(String username) {
		User foundUser = null;
		for(User user: this.users.values()){
			if(user.getUsername().trim().equalsIgnoreCase(username.trim())){
				foundUser= user;
			}
		}
		return foundUser;
	}

}
