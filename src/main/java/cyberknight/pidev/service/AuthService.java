package cyberknight.pidev.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cyberknight.pidev.repository.userRepository;
import cyberknight.pidev.config.JWTProvider;
import cyberknight.pidev.dto.LoginRequest;
import cyberknight.pidev.dto.RegisterRequest;
import cyberknight.pidev.dto.passwordChangeRequest;
import cyberknight.pidev.dto.userCredetialsEditRequest;
import cyberknight.pidev.exception.SendingMailException;
import cyberknight.pidev.exception.TokenException;
import cyberknight.pidev.model.notificationEmail;
import cyberknight.pidev.model.role;
import cyberknight.pidev.model.user;
import cyberknight.pidev.model.verificationToken;
import cyberknight.pidev.repository.*;

import static java.time.Instant.now;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

	private final userRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final verificationTokenRepository verificationTokenRepository;
	private final MailContentBuilder mailContentBuilder;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
//	private final JWTProvider jwtProvider;
	private final roleRepository roleee;

	@Transactional
	public String signup(RegisterRequest registerRequest) {
		user user = new user();
		if (userRepository.existsByUsername(registerRequest.getUsername())) {
			return "user name already exists";
		} else if (userRepository.existsByEmail(registerRequest.getEmail())) {
			return "Mail already exists";
		}
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setCreated(now());
		user.setEnabled(false);
		user.setRole(verifyUserRole());
		user.setMfa(false);
		user.setAdress(registerRequest.getAdress());
		user.setHours(registerRequest.getHours());
		user.setNumber(registerRequest.getNumber());
		user.setSalary(registerRequest.getSalary());
		user.setName(registerRequest.getName());
		user.setLastName(registerRequest.getLastName());
		/*
		 * if(user.isMfa()) { user.setSecret(totpManager.generateSecret()); }
		 */

		userRepository.save(user);

		String token = generateVerificationToken(user);
		String message = mailContentBuilder
				.build("Thank you for signing up to ConsommiTounsi, please click on the below url to activate your account : "
						+ "http://localhost:8080/accountVerification" + "/" + token);

		mailService.sendMail(new notificationEmail("Please Activate your account", user.getEmail(), message));
		return "Please activate your account";
	}

	private role verifyUserRole() {
		String r = "CLIENT";
		Optional<role> opRole = roleee.findByroleName(r);
		opRole.orElseThrow(() -> new TokenException("Role not found"));
		return opRole.get();
	}

	private String generateVerificationToken(user user) {
		String token = UUID.randomUUID().toString();
		verificationToken verificationToken = new verificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	public void verifyAccount(String token) {
		Optional<verificationToken> verificationTokenOptional = verificationTokenRepository.findBytoken(token);
		verificationTokenOptional.orElseThrow(() -> new TokenException("Invalid Token"));
		fetchUserAndEnable(verificationTokenOptional.get());
	}

	public String login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
//		String authenticationToken = jwtProvider.generateToken(authenticate);
//		System.out.println("\n" + authenticationToken);
		return loginRequest.getUsername();
	}

	@Transactional
	private void fetchUserAndEnable(verificationToken verificationToken) {
		long id = verificationToken.getUser().getUserId();
		user user = userRepository.findById(id).orElseThrow(() -> new SendingMailException(
				"User Not Found with id - " + verificationToken.getUser().getUsername()));
		user.setEnabled(true);
		userRepository.save(user);
	}

	public List<user> getUsers() {
		return userRepository.findAll();
	}

	public String editUserPassword(passwordChangeRequest changeRequest) {
		
//		if((changeRequest.getPassword()==null)&&(changeRequest.getPasswordCheck()==null))testing
//			return changeRequest.getPassword()+" "+changeRequest.getPasswordCheck() +" "+ changeRequest.toString(); //"password null";
		if (changeRequest.getPassword().equals(changeRequest.getPasswordCheck())) {
			
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		Optional<user> opUser = userRepository.findByUsername(username);
		opUser.orElseThrow(() -> new TokenException("User not found"));
		user user = new user();
		user = opUser.get();
		user.setPassword(encodePassword(changeRequest.getPassword()));
		userRepository.save(user);
		return "Password updated";
		}
		else return "Passwords don't match";

	}
	public String editUserCredentials(userCredetialsEditRequest changeRequest)
	{

			System.out.println(changeRequest);
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = "";
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			Optional<user> opUser = userRepository.findByUsername(username);
			opUser.orElseThrow(() -> new TokenException("User not found"));
			user user = new user();
			user = opUser.get();
			if(changeRequest.getAdress()!=null)
				user.setAdress(changeRequest.getAdress());
			if(changeRequest.getEmail()!=null)
				user.setEmail(changeRequest.getEmail());
			if(changeRequest.getNumber()!=null)
				user.setNumber(changeRequest.getNumber());
			if(changeRequest.getName()!=null)
				user.setName(changeRequest.getName());
			if(changeRequest.getLastName()!=null)
				user.setLastName(changeRequest.getLastName());
			
			userRepository.save(user);
			System.out.println(user.toString());
			//return "User Credentials Updated "+changeRequest.toString();
			return "User Credentials Updated ";

	}
}