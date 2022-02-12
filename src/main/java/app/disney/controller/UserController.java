package app.disney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import app.disney.security.IUserService;

@RestController
public class UserController {
	
	
	@Autowired
	private IUserService userService;

}
