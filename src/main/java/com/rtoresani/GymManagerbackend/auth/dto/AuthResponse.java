package com.rtoresani.GymManagerbackend.auth.dto;


import com.rtoresani.GymManagerbackend.models.user.ERole;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private Long userId;
    private String email;
    private ERole role;
}
