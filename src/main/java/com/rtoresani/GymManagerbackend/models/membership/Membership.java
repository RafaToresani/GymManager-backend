package com.rtoresani.GymManagerbackend.models.membership;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rtoresani.GymManagerbackend.models.client.Client;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBERSHIPS")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    private LocalDate startDate;
    private LocalDate endingDate;
    private Boolean status;
}
