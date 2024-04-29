package com.rtoresani.GymManagerbackend.controllers;

import com.rtoresani.GymManagerbackend.dto.request.MembershipRequest;
import com.rtoresani.GymManagerbackend.dto.responses.MembershipResponse;
import com.rtoresani.GymManagerbackend.dto.responses.SuccessResponse;
import com.rtoresani.GymManagerbackend.services.MembershipService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"}, methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
@RequestMapping("/api/v1/memberships")
public class MembershipController {
    private String url = "/api/v1/memberships";

    @Autowired
    private MembershipService membershipService;

    //// ============================== POST ==============================
    /*Crea y retorna una nueva membresía.*/
    @PostMapping
    public ResponseEntity<SuccessResponse> create(@Valid @RequestBody MembershipRequest request, BindingResult bindingResult) throws BadRequestException {
        if(bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getFieldError().getDefaultMessage());

        MembershipResponse response = this.membershipService.create(request);

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("201")
                .message("Membresía creada satisfactoriamente.")
                .object(response)
                .url(url)
                .build(), HttpStatus.CREATED);
    }

    //// ============================== GET ==============================
    /*Retorna todas las membresías.*/
    @GetMapping
    public ResponseEntity<SuccessResponse> findAll(){
        List<MembershipResponse> responses = this.membershipService.findAll();

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Solicitud exitosa.")
                .object(responses)
                .url(url)
                .build(), HttpStatus.OK);
    }
    /*Retorna todas las membresías de un usuario en base a su id.*/
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> findAllByClientId(@PathVariable Long id) throws BadRequestException {
        Utils.checkId(id, "membresía");

        List<MembershipResponse> responses = this.membershipService.findAllByClientId(id);

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Solicitud exitosa.")
                .object(responses)
                .url(url+"/"+id)
                .build(), HttpStatus.OK);
    }

    //// ============================== PUT ==============================
    @PutMapping("/{membershipId}")
    public ResponseEntity<SuccessResponse> updateMembership(@PathVariable Long membershipId, @Valid @RequestBody MembershipRequest request) throws BadRequestException {
        Utils.checkId(membershipId, "membresía");

        MembershipResponse responses = this.membershipService.updateMembership(request, membershipId);

        return new ResponseEntity<>(SuccessResponse
                .builder()
                .statusCode("200")
                .message("Solicitud exitosa.")
                .object(responses)
                .url(url+"/"+membershipId)
                .build(), HttpStatus.OK);
    }
}
