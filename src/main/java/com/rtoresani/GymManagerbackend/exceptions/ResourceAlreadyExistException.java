package com.rtoresani.GymManagerbackend.exceptions;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ResourceAlreadyExistException extends RuntimeException{
    private String message;
    public ResourceAlreadyExistException(String message){
        super(message);
        this.message = message;
    }
}
