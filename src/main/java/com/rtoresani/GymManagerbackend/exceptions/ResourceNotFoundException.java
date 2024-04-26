package com.rtoresani.GymManagerbackend.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    private String entityName;
    private String resourceName;
    private String resourceValue;

    public ResourceNotFoundException(String entityName, String resourceName, String resourceValue) {
        super("El " + entityName + " con " + resourceName + ": " + resourceValue + " no existe.");
        this.entityName = entityName;
        this.resourceName = resourceName;
        this.resourceValue = resourceValue;
    }

    public ResourceNotFoundException(String entityName) {
        super("No se han encontrado recursos del tipo: " + entityName);
        this.entityName = entityName;
    }
}
