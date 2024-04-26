package com.rtoresani.GymManagerbackend.dto;

import com.rtoresani.GymManagerbackend.dto.responses.ClientResponse;
import com.rtoresani.GymManagerbackend.dto.responses.GymResponse;
import com.rtoresani.GymManagerbackend.models.client.Client;
import com.rtoresani.GymManagerbackend.models.gym.Gym;

public class Mapper {

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

    public static ClientResponse mapToResponse(Client client){
        if(client == null) return null;
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
                .build();
    }
}
