package com.rtoresani.GymManagerbackend.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Ingrese un email válido")
    private String email;
    @NotBlank(message = "Ingrese una contraseña.")
    private String password;
}
