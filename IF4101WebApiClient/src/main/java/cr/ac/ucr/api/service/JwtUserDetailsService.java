package cr.ac.ucr.api.service;

import cr.ac.ucr.api.model.Client;
import cr.ac.ucr.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ClientRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Client aux = null;
		aux = repository.loadByName(username);

		if (aux!=null) {
			return new User(aux.getEmail(), aux.getPassword(),
					new ArrayList<>());
		} else {

			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}