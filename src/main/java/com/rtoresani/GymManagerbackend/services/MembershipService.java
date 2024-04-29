package com.rtoresani.GymManagerbackend.services;

import com.rtoresani.GymManagerbackend.dto.Mapper;
import com.rtoresani.GymManagerbackend.dto.request.MembershipRequest;
import com.rtoresani.GymManagerbackend.dto.responses.MembershipResponse;
import com.rtoresani.GymManagerbackend.exceptions.ResourceNotFoundException;
import com.rtoresani.GymManagerbackend.models.client.Client;
import com.rtoresani.GymManagerbackend.models.membership.Membership;
import com.rtoresani.GymManagerbackend.repositories.client.ClientRepository;
import com.rtoresani.GymManagerbackend.repositories.membership.MembershipRepository;
import com.rtoresani.GymManagerbackend.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MembershipRepository membershipRepository;

    @Scheduled(fixedRate = 86400000)
    public void updateMembershipStatus(){
        List<Membership> memberships = this.membershipRepository.findAll();
        for(Membership membership: memberships){
            if(membership.getEndingDate()!= null && membership.getEndingDate().isBefore(LocalDate.now())){
                membership.setStatus(false);
                this.membershipRepository.save(membership);
            }
        }
    }

    //// ============================== POST ==============================
    public MembershipResponse create(MembershipRequest request){
        Optional<Client> client = this.clientRepository.findById(request.getUserId());
        if(client.isEmpty()) throw new ResourceNotFoundException("cliente", "id", request.getUserId().toString());

        Membership membership = Membership
                .builder()
                .client(client.get())
                .startDate(LocalDate.now())
                .endingDate(request.getEndingDate())
                .status(true)
                .build();

        membership = this.membershipRepository.save(membership);
        client.get().getMemberships().add(membership);
        this.clientRepository.save(client.get());

        return Mapper.mapToResponse(membership);
    }


    //// ============================== GET ==============================

    /* FIND ALL*/
    public List<MembershipResponse> findAll() {
        List<Membership> memberships = this.membershipRepository.findAll();
        if(memberships.isEmpty()) throw new ResourceNotFoundException("membresías");

        List<MembershipResponse> responses = new ArrayList<>();
        for(Membership membership: memberships){
            responses.add(Mapper.mapToResponse(membership));
        }
        return responses;
    }

    /*FIND ALL BY CLIENT ID*/
    public List<MembershipResponse> findAllByClientId(Long id) {
        List<Membership> memberships = this.membershipRepository.findAllByClientId(id);
        if(memberships.isEmpty()) throw new ResourceNotFoundException("membresías");

        List<MembershipResponse> responses = new ArrayList<>();
        for(Membership membership: memberships){
            responses.add(Mapper.mapToResponse(membership));
        }
        return responses;
    }

    //// ============================== PUT ==============================
    /*Actualiza fecha de finalización de la membresía. Por si sucede que se equivoca el usuario al ingresarla. */
    public MembershipResponse updateMembership(MembershipRequest request, Long id){
        Optional<Membership> membership = this.membershipRepository.findById(id);
        if(membership.isEmpty()) throw new ResourceNotFoundException("membership", "id", id.toString());

        membership.get().setEndingDate(request.getEndingDate());
        return Mapper.mapToResponse(this.membershipRepository.save(membership.get()));
    }
}
