package com.rtoresani.GymManagerbackend.repositories.gym;

import com.rtoresani.GymManagerbackend.models.gym.GymData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymDataRepository extends JpaRepository<GymData, Long> {
    Boolean existsByEmail(String email);
}
