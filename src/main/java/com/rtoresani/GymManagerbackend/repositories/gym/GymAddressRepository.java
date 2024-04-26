package com.rtoresani.GymManagerbackend.repositories.gym;

import com.rtoresani.GymManagerbackend.models.gym.GymAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymAddressRepository extends JpaRepository<GymAddress, Long> {
}
