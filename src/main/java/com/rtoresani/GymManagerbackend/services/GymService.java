package com.rtoresani.GymManagerbackend.services;


import com.rtoresani.GymManagerbackend.dto.Mapper;
import com.rtoresani.GymManagerbackend.dto.request.GymRequest;
import com.rtoresani.GymManagerbackend.dto.responses.GymResponse;
import com.rtoresani.GymManagerbackend.exceptions.ResourceAlreadyExistException;
import com.rtoresani.GymManagerbackend.exceptions.ResourceNotFoundException;
import com.rtoresani.GymManagerbackend.models.gym.Gym;
import com.rtoresani.GymManagerbackend.models.gym.GymAddress;
import com.rtoresani.GymManagerbackend.models.gym.GymData;
import com.rtoresani.GymManagerbackend.repositories.gym.GymAddressRepository;
import com.rtoresani.GymManagerbackend.repositories.gym.GymDataRepository;
import com.rtoresani.GymManagerbackend.repositories.gym.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GymService {
    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private GymAddressRepository gymAddressRepository;
    @Autowired
    private GymDataRepository gymDataRepository;


    // ============================== POST ==============================
    /*Crea y retorna un nuevo gimnasio*/
    public GymResponse createGym(GymRequest request){
            if(gymDataRepository.existsByEmail(request.getEmail())) throw new ResourceAlreadyExistException("El correo electrónico ingresado no está disponible.");
            if(gymRepository.existsByTitle(request.getTitle())) throw new ResourceAlreadyExistException("El nombre del gimnasio no está disponible");

            GymAddress address = createGymAddress(request);
            GymData gymData = createGymData(request, address);
            Gym gym = createGymEntity(request, gymData);

            return Mapper.mapToResponse(gym);

    }

    private GymAddress createGymAddress(GymRequest request) {
        GymAddress gymAddress = GymAddress
                .builder()
                .city(request.getCity())
                .street(request.getStreet())
                .number(request.getNumber())
                .postalCode(request.getPostalCode())
                .build();

        return gymAddressRepository.save(gymAddress);
    }

    private GymData createGymData(GymRequest request, GymAddress address) {
        GymData data = GymData
                .builder()
                .email(request.getEmail())
                .gymAddress(address)
                .phoneNumber(request.getPhoneNumber())
                .build();

        return gymDataRepository.save(data);
    }

    private Gym createGymEntity(GymRequest request, GymData gymData) {
        Gym gym = Gym
                .builder()
                .title(request.getTitle())
                .gymData(gymData)
                .isActive(true)
                //Attendence
                .build();
        return gymRepository.save(gym);
    }

    // ============================== GET ==============================
    /*Busca y retorna la lista completa de gimnasios*/
    public List<GymResponse> findAll(){
        List<Gym> gyms = this.gymRepository.findAll();
        if(gyms.isEmpty()) throw new ResourceNotFoundException("gimnasio");
        List<GymResponse> gymResponses = new ArrayList<>();
        for(Gym gym: gyms){
            gymResponses.add(Mapper.mapToResponse(gym));
        }
        return gymResponses;
    }

    /*Busca y retorna un gimnasio por id*/
    public GymResponse findById(Long id) {
        Optional<Gym> gym = this.gymRepository.findById(id);
        if(gym.isEmpty()) throw new ResourceNotFoundException("gimnasio", "id", id.toString());

        return Mapper.mapToResponse(gym.get());
    }

    // ============================== PUT ==============================
    /*
    * Busca y actualiza el objeto al completo.*/
    public GymResponse update(GymRequest request, Long id){
        Optional<Gym> gym = gymRepository.findById(id);
        if(gym.isEmpty()) throw new ResourceNotFoundException("gimnasio", "id", id.toString());

        Gym updated=gym.get();

        updated.setTitle(request.getTitle());
        updated.getGymData().setEmail(request.getEmail());
        updated.getGymData().setPhoneNumber(request.getPhoneNumber());
        updated.getGymData().getGymAddress().setCity(request.getCity());
        updated.getGymData().getGymAddress().setStreet(request.getStreet());
        updated.getGymData().getGymAddress().setNumber(request.getNumber());
        updated.getGymData().getGymAddress().setPostalCode(request.getPostalCode());

        return Mapper.mapToResponse(gymRepository.save(updated));
    }

    // ============================== PUT ==============================
    /*
    * Cambia el estado de activo a inactivo y visceversa*/
    public void toggleStatus(Long id) {
        Optional<Gym> gym = gymRepository.findById(id);
        if(gym.isEmpty()) throw new ResourceNotFoundException("gimnasio", "id", id.toString());

        gym.get().setIsActive(!gym.get().getIsActive());
        this.gymRepository.save(gym.get());
    }

    public void deleteById(Long id) {
        Optional<Gym> gym = gymRepository.findById(id);
        if(gym.isEmpty()) throw new ResourceNotFoundException("gimnasio", "id", id.toString());
        this.gymRepository.deleteById(id);
    }
}
