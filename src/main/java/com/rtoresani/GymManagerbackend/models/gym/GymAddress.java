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
@Table(name = "GYM_ADDRESSES")
public class GymAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String city;
    private Double postalCode;

    @OneToOne(mappedBy = "gymAddress")
    @JsonIgnore
    private GymData gymData;
}
