package com.rtoresani.GymManagerbackend.dto;

import com.rtoresani.GymManagerbackend.dto.responses.GymResponse;
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
}
