package com.rtoresani.GymManagerbackend.dto.responses;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private LocalDateTime dateTime;
    private String message;
    private String url;
}
