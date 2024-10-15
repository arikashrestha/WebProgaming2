package erika.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import erika.entity.User;
import erika.repo.UserRepository;

@Service
	public class UserService {
	    @Autowired
	    private UserRepository userRepository;

	    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	    public User register(User user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.save(user);
	    }

	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }
	}
}
