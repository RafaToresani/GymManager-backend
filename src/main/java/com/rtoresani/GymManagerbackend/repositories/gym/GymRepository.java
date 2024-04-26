package com.rtoresani.GymManagerbackend.repositories.gym;

import com.rtoresani.GymManagerbackend.models.gym.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
    Boolean existsByTitle(String title);
}
