package com.rtoresani.GymManagerbackend.models.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENTS_ADDRESS")
public class ClientAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String city;
    private Double postalCode;

    @OneToOne(mappedBy = "clientAddress")
    @JsonIgnore
    private ClientData clientData;
}
