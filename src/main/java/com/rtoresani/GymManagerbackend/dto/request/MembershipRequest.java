package com.rtoresani.GymManagerbackend.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MembershipRequest {
    @Positive(message = "El id del usuario no puede ser negativo")
    private Long userId;
    @NotNull(message = "La fecha de finalización no puede estar vacía.")
    private LocalDate endingDate;
}
