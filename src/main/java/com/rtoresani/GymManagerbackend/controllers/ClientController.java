package com.rtoresani.GymManagerbackend.controllers;

import com.rtoresani.GymManagerbackend.dto.request.ClientRequest;
import com.rtoresani.GymManagerbackend.dto.responses.ClientResponse;
import com.rtoresani.GymManagerbackend.dto.responses.GymResponse;
import com.rtoresani.GymManagerbackend.dto.responses.SuccessResponse;
import com.rtoresani.GymManagerbackend.models.client.Client;
import com.rtoresani.GymManagerbackend.services.ClientService;
import jakarta.validation.Valid;
import jdk.jshell.execution.Util;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"}, methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
@RequestMapping("/api/v1/clients")
public class ClientController {

    private String url = "/api/v1/clients";

    @Autowired
    private ClientService clientService;

    // ============================== POST ==============================
    /*Crea y retorna un nuevo cliente*/
    @PostMapping
    public ResponseEntity<SuccessResponse> create(@Valid @RequestBody ClientRequest request, BindingResult bindingResult) throws BadRequestException {
        if(bindingResult.hasErrors()) throw new BadRequestException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());

        ClientResponse response = clientService.create(request);

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("201")
                .message("Cliente creado satisfactoriamente.")
                .object(response)
                .url(url)
                .build(), HttpStatus.CREATED);
    }

    // ============================== GET ==============================
    /*Busca y retorna la lista de clientes paginada.*/
    @GetMapping
    public ResponseEntity<SuccessResponse> findAllPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<ClientResponse> clients = this.clientService.findAllPageable(page, size);

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Solicitud exitosa.")
                .object(clients)
                .url(url+"?page="+page+"&size="+size)
                .build(), HttpStatus.CREATED);
    }

    /*Busca y retorna un cliente por id*/
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> findById(@PathVariable Long id) throws BadRequestException {
        Utils.checkId(id, "cliente");
        ClientResponse response = this.clientService.findById(id);

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Solicitud exitosa.")
                .object(response)
                .url(url+"/"+id)
                .build(), HttpStatus.CREATED);
    }

    /*Busca y retorna un cliente por id*/
    @GetMapping("/findByDni/{dni}")
    public ResponseEntity<SuccessResponse> findByDni(@PathVariable String dni) throws BadRequestException {
        ClientResponse response = this.clientService.findByDni(dni);

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Solicitud exitosa.")
                .object(response)
                .url(url+"/findByDni/"+dni)
                .build(), HttpStatus.CREATED);
    }

    // ============================== PATCH ==============================
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateClient(@PathVariable Long id, @Valid @RequestBody ClientRequest request, BindingResult bindingResult) throws BadRequestException {
        Utils.checkId(id, "cliente");

        ClientResponse response = this.clientService.updateClient(request, id);

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Actualización de cliente exitosa.")
                .object(response)
                .url(url+"/"+id)
                .build(), HttpStatus.OK);
    }

    // ============================== DELETE ==============================
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteClient(@PathVariable Long id) throws BadRequestException {
        Utils.checkId(id, "cliente");

        this.clientService.deleteById(id);

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Eliminación de cliente exitosa.")
                .object(null)
                .url(url+"/"+id)
                .build(), HttpStatus.OK);
    }
}
