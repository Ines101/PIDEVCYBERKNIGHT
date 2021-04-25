package cyberknight.pidev.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cyberknight.pidev.dto.passwordChangeRequest;
import cyberknight.pidev.exception.TokenException;
import cyberknight.pidev.model.user;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
	private final cyberknight.pidev.repository.userRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		Optional<user> userOptional = userRepository.findByUsername(username);
		user user = userOptional
				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : " + username));
		// System.out.println(getAuthorities(user.getRole().getRoleName()));//test

		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true,
				getAuthorities(user.getRole().getRoleName()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return singletonList(new SimpleGrantedAuthority(role));
	}

	public String showCurrentUser() {
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
		return user.toString();
		
	}



}