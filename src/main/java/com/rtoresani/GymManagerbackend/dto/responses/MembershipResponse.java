package com.rtoresani.GymManagerbackend.dto.responses;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MembershipResponse {
    private Long id;
    private Long userId;
    private LocalDate starDate;
    private LocalDate endingDate;
    private Boolean status;
}
