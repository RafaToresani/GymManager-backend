package com.rtoresani.GymManagerbackend.controllers;

import com.rtoresani.GymManagerbackend.dto.request.GymRequest;
import com.rtoresani.GymManagerbackend.dto.responses.GymResponse;
import com.rtoresani.GymManagerbackend.dto.responses.SuccessResponse;

import com.rtoresani.GymManagerbackend.services.GymService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@CrossOrigin(origins = {"http://localhost:4200/"}, methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
@RequestMapping("/api/v1/gyms")
public class GymController {

    private final String url = "/api/v1/gyms";

    @Autowired
    private GymService service;

    // ============================== POST ==============================
    /*Crea un nuevo gimnasio*/
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SuccessResponse> create(@Valid @RequestBody GymRequest request, BindingResult bindingResult) throws BadRequestException {
        if(bindingResult.hasErrors()) throw new BadRequestException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());

        GymResponse gym = service.createGym(request);

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("201")
                .message("Gimnasio creado satisfactoriamente.")
                .object(gym)
                .url(url)
                .build(), HttpStatus.CREATED);
    }

    // ============================== GET ==============================
    /*Busca y retorna la lista de gimnasios.*/
    @GetMapping
    public ResponseEntity<SuccessResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<GymResponse> gyms= this.service.findAllPageable(page, size);

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Solicitud exitosa.")
                .object(gyms)
                .url(url)
                .build(), HttpStatus.OK);
    }

    /*Busca y retorna un gimnasio por id*/
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> findById(@PathVariable Long id) throws BadRequestException {
        Utils.checkId(id, "gimnasio");
        GymResponse gym = this.service.findById(id);


        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Solicitud exitosa.")
                .object(gym)
                .url(url+"/"+id)
                .build(), HttpStatus.OK);
    }

    // ============================== PUT ==============================
    /*Busca y actualiza los datos del gimnasio*/
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateGym(@PathVariable Long id, @Valid @RequestBody GymRequest gymRequest, BindingResult bindingResult) throws BadRequestException {
        if(bindingResult.hasErrors()) throw new BadRequestException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        Utils.checkId(id, "gimnasio");

        GymResponse gym = service.update(gymRequest, id);
        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Actualización exitosa.")
                .object(gym)
                .url(url+"/"+id)
                .build(), HttpStatus.OK);
    }

    // ============================== PATCH ==============================
    /*Cambia el estado del gimnasio.*/
    @PatchMapping("/toggleStatus/{id}")
    public ResponseEntity<SuccessResponse> toggleStatus(@PathVariable Long id) throws BadRequestException {
        Utils.checkId(id, "gimnasio");

        service.toggleStatus(id);
        return new ResponseEntity<>(SuccessResponse
                .builder()
                .message("Solicitud exitosa.")
                .url(url+"/toggleStatus/"+id)
                .build(), HttpStatus.OK);
    }

    // ============================== DELETE ==============================
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteById(@PathVariable Long id) throws BadRequestException{
        Utils.checkId(id, "gimnasio");
        service.deleteById(id);
        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Eliminación exitosa.")
                .url(url+"/"+id)
                .build(), HttpStatus.OK);
    }
}
