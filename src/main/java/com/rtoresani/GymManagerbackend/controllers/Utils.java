package com.rtoresani.GymManagerbackend.controllers;

import org.apache.coyote.BadRequestException;

public class Utils {

    public static void checkId(Long id, String entity) throws BadRequestException {
        if(id == null) throw new BadRequestException("El id del " + entity +" no puede ser nulo.");
        if(id < 1) throw new BadRequestException("El id del " + entity + " no puede ser negativo.");
    }
}
