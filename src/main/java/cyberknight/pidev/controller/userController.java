package cyberknight.pidev.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cyberknight.pidev.dto.passwordChangeRequest;
import cyberknight.pidev.dto.userCredetialsEditRequest;
import cyberknight.pidev.model.user;
import cyberknight.pidev.service.AuthService;
import cyberknight.pidev.service.UserDetailServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class userController {
	private AuthService authService;
	private UserDetailServiceImpl userService;
	
	@GetMapping("getUsers")
	public List<user>getUsers()
	{
		return authService.getUsers();
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);

		}
		return "user: " + auth.getName() + " is logged out.";
	}
	@GetMapping("/profile")
	public String profile()
	{
		return userService.showCurrentUser();
	}
	@PostMapping("/editPassword")
	public String editPassword(@RequestBody passwordChangeRequest changeRequest)
	{
		return authService.editUserPassword(changeRequest);
	}
	@PostMapping("/editCredentials")
	public String editCredentials(@RequestBody userCredetialsEditRequest changeRequest)
	{
		return authService.editUserCredentials(changeRequest);
	}

}
