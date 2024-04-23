package com.rtoresani.GymManagerbackend.auth.jwt.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Ingrese una contraseña.")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres.")
    private String password;
    @Email(message = "Ingrese un correo electrónico válido.")
    @NotBlank(message = "El correo electrónico es requerido")
    private String email;
    @NotBlank(message = "El campo nombre es requerido.")
    private String firstName;
    @NotBlank(message = "El campo apellido es requerido.")
    private String lastName;
    @NotBlank(message = "El campo ciudad es requerido.")
    private String city;
    @NotBlank(message = "El campo calle es requerido.")
    private String street;
    @NotBlank(message = "El campo altura es requerido.")
    private String numberStreet;
    @NotNull(message = "El campo código postal es requerido.")
    private Double postalCode;
    @NotBlank(message = "El número de teléfono no puede estar vacío.")
    private String phoneNumber;
}
