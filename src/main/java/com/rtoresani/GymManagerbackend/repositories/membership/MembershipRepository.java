package com.rtoresani.GymManagerbackend.repositories.membership;

import com.rtoresani.GymManagerbackend.models.membership.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
