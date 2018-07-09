package com.elliot.user.userCUD.api;

import com.elliot.user.User;

public interface UserCUDService {

	  User save(User user);

	  User find(String id);

	  User findByUsername(String username);
}
