package com.rtoresani.GymManagerbackend.repositories.user;

import com.rtoresani.GymManagerbackend.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
}
