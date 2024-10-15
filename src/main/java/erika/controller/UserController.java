package erika.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import erika.entity.User;
import erika.service.UserService;

@RestController
	@RequestMapping("/api/users")
	public class UserController {
	    @Autowired
	    private UserService userService;

	    @PostMapping("/register")
	    public User register(@RequestBody User user) {
	        return userService.register(user);
	    }

	    @GetMapping("/{username}")
	    public User getUser(@PathVariable String username) {
	        return userService.findByUsername(username);
	    }
	}
}
