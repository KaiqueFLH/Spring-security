package com.usercrud.usercrud.User.Repository;

import com.usercrud.usercrud.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserDetailsENTITY_Username(String username);
}
