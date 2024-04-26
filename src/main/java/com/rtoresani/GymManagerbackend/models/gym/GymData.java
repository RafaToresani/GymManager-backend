package com.rtoresani.GymManagerbackend.models.gym;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GYMS_DATA")
public class GymData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "gym_address_id")
    private GymAddress gymAddress;

    @OneToOne(mappedBy = "gymData")
    @JsonIgnore
    private Gym gym;
}
