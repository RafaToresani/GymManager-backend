package com.rtoresani.GymManagerbackend.dto.responses;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String city;
    private String street;
    private String number;
    private Double postalCode;
    private String phoneNumber;

    private Long membershipId;
    private LocalDate endingDate;
    private Boolean status;
}
