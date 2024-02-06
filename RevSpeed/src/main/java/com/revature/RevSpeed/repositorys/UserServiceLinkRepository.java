package com.revature.RevSpeed.repositorys;

import com.revature.RevSpeed.dto.UserActivePlanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.RevSpeed.models.UserServiceLink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserServiceLinkRepository extends JpaRepository<UserServiceLink,Long> {

    @Query("SELECT usl FROM UserServiceLink usl " +
            "LEFT JOIN FETCH usl.broadbandPlans AS broadband " +
            "LEFT JOIN FETCH broadband.ott AS ott " +
            "WHERE usl.user.id = :userId")
    List<UserServiceLink> findUserServiceDetailsByUserId(String userId);

//    List<UserServiceLink> findByUserIdAndBroadbandActive(String userId, Boolean broadbandActive);
}
