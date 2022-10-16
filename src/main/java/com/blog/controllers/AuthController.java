package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Security.JwtTokenHelper;
import com.blog.exception.InvalidUserAndPassword;
import com.blog.payload.JwtAuthRequest;
import com.blog.payload.JwtAuthResponse;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	// @Autowired
	// private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception
	// throws Exception
	{
		this.authenticate(request.getEmail(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		// response.setUser(this.mapper.map((User) userDetails, UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detal!!");
			throw new InvalidUserAndPassword("Email/Username and password does not matched!!");
		}
	}

	// register new user api

	// @PostMapping("/register")
	// public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto
	// userDto) {
	// UserDto registeredUser = this.userService.registerNewUser(userDto);
	// return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	// }

	// get loggedin user data
	// @Autowired
	// private UserRepo userRepo;
	// @Autowired
	// private ModelMapper mapper;

	// @GetMapping("/current-user/")
	// public ResponseEntity<UserDto> getUser(Principal principal) {
	// User user = this.userRepo.findByEmail(principal.getName()).get();
	// return new ResponseEntity<UserDto>(this.mapper.map(user, UserDto.class),
	// HttpStatus.OK);
	// }

}