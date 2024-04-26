package com.rtoresani.GymManagerbackend.repositories.client;

import com.rtoresani.GymManagerbackend.models.client.ClientData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDataRepository extends JpaRepository<ClientData, Long> {
    boolean existsByDni(String dni);

    boolean existsByEmail(String email);
}
