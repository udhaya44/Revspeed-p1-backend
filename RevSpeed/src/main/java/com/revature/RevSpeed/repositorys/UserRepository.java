package com.revature.RevSpeed.repositorys;

import com.revature.RevSpeed.models.Role;
import com.revature.RevSpeed.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

   public Optional<User> findByEmail(String email);


   User findByRole(Role role);

//   User findByEmail(String email);

}
