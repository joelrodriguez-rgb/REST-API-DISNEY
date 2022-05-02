package app.disney.ports.input.rs.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.disney.domain.model.AppRole;
import app.disney.domain.model.AppUser;

@RequestMapping("/auth")
@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/createUser")
	private ResponseEntity<?> createUser() {

		AppUserDto userDto = new AppUserDto();

		return new ResponseEntity<>(userDto, HttpStatus.OK);

	}

	@PostMapping("/register")
	private ResponseEntity<?> saveUser(	@RequestBody @Valid AppUserDto newUser,
										BindingResult result) {

		if (result.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(userService.saveUser(newUser), HttpStatus.CREATED);

	}

	@GetMapping("/token/refresh")
	public void refreshToken(	HttpServletRequest request,
								HttpServletResponse response) throws IOException {

		String authorizationHeader = request.getHeader(AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

			try {

				String refresh_token = authorizationHeader.substring("Bearer ".length());

				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

				JWTVerifier jwtVerifier = JWT.require(algorithm).build();

				DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);

				String username = decodedJWT.getSubject();

				AppUser user = userService.getUser(username);

				String acces_token = JWT.create().withSubject(user.getUserName())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURI().toString())
						.withClaim("roles", user.getRoles().stream().map(AppRole::getName)
								.collect(Collectors.toList()))
						.sign(algorithm);

				Map<String, String> tokens = new HashMap<String, String>();
				tokens.put("acces_token", acces_token);
				tokens.put("refresh_token", refresh_token);

				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);

			} catch (Exception ex) {

				response.setHeader("error", ex.getMessage());
				response.setStatus(FORBIDDEN.value());

				Map<String, String> error = new HashMap<String, String>();
				error.put("error_message", ex.getMessage());

				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);

			}

		} else {
			throw new RuntimeException("Refresh token is missing");

		}

	}

}
