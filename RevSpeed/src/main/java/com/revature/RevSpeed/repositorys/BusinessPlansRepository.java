package com.revature.RevSpeed.repositorys;

import com.revature.RevSpeed.models.BusinessPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessPlansRepository extends JpaRepository<BusinessPlans, Long> {
}
