package com.rtoresani.GymManagerbackend.dto.responses;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GymResponse {
    private Long id;
    private String title;
    private String email;
    private String city;
    private String street;
    private String number;
    private Double postalCode;
    private String phoneNumber;
    private Boolean isActive;
}
