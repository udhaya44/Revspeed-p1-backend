package com.revature.RevSpeed.repositorys;

import com.revature.RevSpeed.models.BroadbandPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BroadbandPlansRepository extends JpaRepository<BroadbandPlans,Long> {
    @Query("SELECT b FROM BroadbandPlans b JOIN FETCH b.ott")
    List<BroadbandPlans> findAllWithOTTPlatforms();
}
