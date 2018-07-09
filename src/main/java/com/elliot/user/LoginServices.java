package com.elliot.user;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elliot.jwt.JWTTools;
import com.elliot.restTemplates.restResponse.BaseResponse;
import com.elliot.user.userCUD.api.UserCUDService;
import com.elliot.user.userCUD.database.DbUserCudService;

import io.jsonwebtoken.JwtBuilder;

@RestController
public class LoginServices {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	 private static final String template = "Hello, %s!";

	    @RequestMapping("/greeting")
	    public BaseResponse greeting(@RequestParam(value="name", defaultValue="World") String name) {
	        return new BaseResponse(0,
	                            String.format(template, name));
	    }

	
	@RequestMapping(value = "/auth/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest request) {
        String jwt = "";
        UserCUDService service = new DbUserCudService(this.jdbcTemplate);
        User user = service.findByUsername(request.getUsername());
        if(user!= null){
        	if(user.getPassword().trim().equalsIgnoreCase(request.getPassword())){
        		JwtBuilder jwtBuilder = JWTTools.initJWTBuilder();
        		jwtBuilder = JWTTools.addClaim("userID", user.getId(), jwtBuilder);
        		jwt = JWTTools.compactJwt(jwtBuilder);
        		return new ResponseEntity<BaseResponse> (new BaseResponse(Long.parseLong(user.getId()),
                        jwt), HttpStatus.OK);
        	}
        	
        }
        return new ResponseEntity<BaseResponse> (new BaseResponse(-1,
                "Invalid username or password"), HttpStatus.BAD_REQUEST);
        
    }
	
	@RequestMapping(value = "/auth/register")
    public ResponseEntity register(@RequestBody AuthenticationRequest request) {
		UserCUDService service = new DbUserCudService(this.jdbcTemplate);
        User user = new User(-1+"", request.getUsername(), request.getPassword());
        service.save(user);
        return new ResponseEntity<> (
                "user added", HttpStatus.OK);
        
    }
}