package app.disney.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.disney.DTO.AppUserDto;
import app.disney.service.IUserService;


@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/createUser")
	private ResponseEntity<?> createUser() {

		AppUserDto userDto = new AppUserDto();

		return new ResponseEntity<>(userDto, HttpStatus.OK);

	}
	
	@PostMapping("/saveUser")
	private ResponseEntity<?> saveUser(@RequestBody @Valid AppUserDto newUser, BindingResult result){
		
		if (result.hasErrors()) return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(userService.saveUser(newUser),HttpStatus.CREATED);
		
	}
	
	
	
	
	

}
