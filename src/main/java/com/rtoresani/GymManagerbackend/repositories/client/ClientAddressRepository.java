package com.rtoresani.GymManagerbackend.repositories.client;

import com.rtoresani.GymManagerbackend.models.client.ClientAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAddressRepository extends JpaRepository<ClientAddress, Long> {
}
