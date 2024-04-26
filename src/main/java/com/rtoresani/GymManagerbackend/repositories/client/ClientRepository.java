package com.rtoresani.GymManagerbackend.repositories.client;

import com.rtoresani.GymManagerbackend.models.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Page<Client> findAll(Pageable pageable);


    Optional<Client> findByClientDataDni(String dni);
}
