package com.rtoresani.GymManagerbackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientRequest {
    @NotBlank(message = "El nombre del cliente es requerido.")
    private String firstName;
    @NotBlank(message = "El apellido del cliente es requerido.")
    private String lastName;
    @NotBlank(message = "El apellido del cliente es requerido.")
    private String dni;
    @Email(message = "El correo electrónico es requerido.")
    private String email;
    @NotBlank(message = "El campo ciudad es requerido.")
    private String city;
    @NotBlank(message = "El campo calle es requerido.")
    private String street;
    @NotBlank(message = "El campo altura es requerido.")
    private String number;
    @NotNull(message = "El campo código postal es requerido.")
    private Double postalCode;
    @NotBlank(message = "El número de teléfono no puede estar vacío.")
    private String phoneNumber;
}
