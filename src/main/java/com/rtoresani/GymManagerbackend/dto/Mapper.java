package com.rtoresani.GymManagerbackend.dto;

import com.rtoresani.GymManagerbackend.dto.responses.ClientResponse;
import com.rtoresani.GymManagerbackend.dto.responses.GymResponse;
import com.rtoresani.GymManagerbackend.dto.responses.MembershipResponse;
import com.rtoresani.GymManagerbackend.models.client.Client;
import com.rtoresani.GymManagerbackend.models.gym.Gym;
import com.rtoresani.GymManagerbackend.models.membership.Membership;
import com.rtoresani.GymManagerbackend.repositories.membership.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

public class Mapper {

    @Autowired
    private MembershipRepository membershipRepository;

    public static GymResponse mapToResponse(Gym gym){
        if(gym == null) return null;

       return GymResponse
                .builder()
                .id(gym.getId())
                .title(gym.getTitle())
                .phoneNumber(gym.getGymData().getPhoneNumber())
                .email(gym.getGymData().getEmail())
                .city(gym.getGymData().getGymAddress().getCity())
                .street(gym.getGymData().getGymAddress().getStreet())
                .number(gym.getGymData().getGymAddress().getNumber())
                .postalCode(gym.getGymData().getGymAddress().getPostalCode())
                .isActive(gym.getIsActive())
                .build();
    }

    public static ClientResponse mapToResponse(Client client, Membership membership){
        if(client == null) return null;

            Long membershipId = (membership != null) ? membership.getId() : -1;
            LocalDate endingDate = (membership != null) ? membership.getEndingDate() : LocalDate.now();
            Boolean status = (membership != null) ? membership.getStatus() : false;

        return ClientResponse
                .builder()
                .id(client.getId())
                .firstName(client.getClientData().getFirstName())
                .lastName(client.getClientData().getLastName())
                .dni(client.getClientData().getDni())
                .email(client.getClientData().getEmail())
                .phoneNumber(client.getClientData().getPhoneNumber())
                .city(client.getClientData().getClientAddress().getCity())
                .street(client.getClientData().getClientAddress().getStreet())
                .number(client.getClientData().getClientAddress().getNumber())
                .postalCode(client.getClientData().getClientAddress().getPostalCode())
                .membershipId(membershipId)
                .endingDate(endingDate)
                .status(status)
                .build();
    }

    public static MembershipResponse mapToResponse(Membership membership){
        if(membership == null) return null;
        return MembershipResponse
                .builder()
                .id(membership.getId())
                .userId(membership.getClient().getId())
                .starDate(membership.getStartDate())
                .endingDate(membership.getEndingDate())
                .status(membership.getStatus())
                .build();
    }
}
