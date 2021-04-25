package cyberknight.pidev.controller;

import cyberknight.pidev.dto.LoginRequest;
import cyberknight.pidev.dto.RegisterRequest;
import cyberknight.pidev.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class authController {

	private final AuthService authService;

	@PostMapping("/signup")
	public String signup(@RequestBody RegisterRequest registerRequest) {
		
		//System.out.println("Added");
		return authService.signup(registerRequest);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		return new ResponseEntity<>(authService.login(loginRequest)+" is Logged in",OK);
	}

	@GetMapping("/accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token) {
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Activated Successully", OK);

	}

//	@GetMapping("/hello")
//	public String hello() {
//		return "hello world";
//	}


}
