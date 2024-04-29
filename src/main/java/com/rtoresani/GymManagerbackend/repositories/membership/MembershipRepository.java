package com.rtoresani.GymManagerbackend.repositories.membership;

import com.rtoresani.GymManagerbackend.models.membership.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findAllByClientId(Long id);
    Optional<Membership> findFirstByClientIdAndStatusTrueAndEndingDateAfterOrderByEndingDateDesc(Long id,  LocalDate date);
    Optional<Membership> findFirstByClientIdAndStatusFalseAndEndingDateAfterOrderByEndingDateAsc(Long id,  LocalDate date);

}
