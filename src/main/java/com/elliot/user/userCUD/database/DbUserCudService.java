package com.elliot.user.userCUD.database;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.elliot.user.User;
import com.elliot.user.userCUD.api.UserCUDService;

public class DbUserCudService implements UserCUDService  {
 
	
	JdbcTemplate template;
	private DbUserCudService(){
		
	}
	
	public DbUserCudService(JdbcTemplate template){
		this.template = template;
	}
	
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		template.update(
			    "INSERT INTO users (username, password) VALUES (?, ?)",
			    user.getUsername(), user.getPassword()
			);
		return user;
	}

	@Override
	public User find(String id) {
		// TODO Auto-generated method stub
		User user = null;
		
		List<User> users = template.query(
                "SELECT userid, username, password FROM Users WHERE userid = ?", new Object[] { id },
                (rs, rowNum) -> new User(rs.getString("userid"), rs.getString("username"), rs.getString("password"))
        );
		
		if(users.size() == 1){
			user= users.get(0);
		}
		
		return user;
	}

	@Override
	public User findByUsername(String username) {
				User user = null;
				RowMapper rowMapper = (rs, rowNum) -> {

					

					String userId = rs.getString("userid");
					String userName = rs.getString("username");
					String password = rs.getString("password");
					User u = new User(userId, userName, password);
					return u;
					};
				List users = template.query(
		                "SELECT userid, username, password FROM Users WHERE username = ?", new Object[] { username },
		                rowMapper
		        );
				
				if(users.size() == 1){
					user= (User) users.get(0);
				}
				
				return user;
	}

}
