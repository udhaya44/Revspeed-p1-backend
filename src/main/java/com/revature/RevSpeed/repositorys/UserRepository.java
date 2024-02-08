package com.revature.RevSpeed.repositorys;

import com.revature.RevSpeed.models.Role;
import com.revature.RevSpeed.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {


   public Optional<User> findByEmail(String email);

  public void deleteByEmail(String id);
   User findByRole(Role role);

    @Query(value = "UPDATE User u SET u.is_broad_band_user=false WHERE u.user_id = :userId", nativeQuery = true)
    void deleteuser(@Param("userId") String userId);

    @Query(value = "SELECT usl.*, ut.* " +
            "FROM user_service_link usl " +
            "JOIN user_table ut ON usl.user_id = ut.user_id", nativeQuery = true)
    List<Map<String, Object>> getActiveSub();

//   User findByEmail(String email);

}
