package com.rtoresani.GymManagerbackend.repositories.user;

import com.rtoresani.GymManagerbackend.models.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
