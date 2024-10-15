package erika.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import erika.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

