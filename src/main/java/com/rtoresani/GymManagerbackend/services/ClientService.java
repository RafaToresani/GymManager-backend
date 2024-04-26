package com.rtoresani.GymManagerbackend.services;

import com.rtoresani.GymManagerbackend.dto.Mapper;
import com.rtoresani.GymManagerbackend.dto.request.ClientRequest;
import com.rtoresani.GymManagerbackend.dto.responses.ClientResponse;
import com.rtoresani.GymManagerbackend.dto.responses.GymResponse;
import com.rtoresani.GymManagerbackend.exceptions.ResourceAlreadyExistException;
import com.rtoresani.GymManagerbackend.exceptions.ResourceNotFoundException;
import com.rtoresani.GymManagerbackend.models.client.Client;
import com.rtoresani.GymManagerbackend.models.client.ClientAddress;
import com.rtoresani.GymManagerbackend.models.client.ClientData;
import com.rtoresani.GymManagerbackend.repositories.client.ClientAddressRepository;
import com.rtoresani.GymManagerbackend.repositories.client.ClientDataRepository;
import com.rtoresani.GymManagerbackend.repositories.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientDataRepository clientDataRepository;
    @Autowired
    private ClientAddressRepository clientAddressRepository;


    // ============================== POST ==============================
    /*Crea y retorna un nuevo cliente*/
    public ClientResponse create(ClientRequest request){
        if(clientDataRepository.existsByEmail(request.getEmail())) throw new ResourceAlreadyExistException("El correo electrónico ingresado no está disponible.");
        if(clientDataRepository.existsByDni(request.getDni())) throw new ResourceAlreadyExistException("El dni ingresado ya pertenece a otro cliente.");

        ClientAddress address = createClientAddress(request);
        ClientData data = createClientData(request, address);
        Client client = createClientEntity(request, data);

        return Mapper.mapToResponse(client);
    }

    private Client createClientEntity(ClientRequest request, ClientData data) {
        Client client = Client
                .builder()
                .clientData(data)
                .attendances(new HashSet<>())
                .memberships(new HashSet<>())
                .build();

        return clientRepository.save(client);
    }

    private ClientData createClientData(ClientRequest request, ClientAddress address) {
        ClientData clientData = ClientData
                .builder()
                .clientAddress(address)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dni(request.getDni())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
        return clientDataRepository.save(clientData);
    }

    private ClientAddress createClientAddress(ClientRequest request) {
        ClientAddress clientAddress = ClientAddress
                .builder()
                .city(request.getCity())
                .street(request.getStreet())
                .number(request.getNumber())
                .postalCode(request.getPostalCode())
                .build();
        return clientAddressRepository.save(clientAddress);
    }


    // ============================== GET ==============================
    /*Busca y retorna todos los clientes paginados.*/
    public Page<ClientResponse> findAllPageable(int page, int size){
        Page<Client> clients = this.clientRepository.findAll(PageRequest.of(page, size));

        if(clients.isEmpty()) throw new ResourceNotFoundException("cliente");

        List<ClientResponse> clientResponses = new ArrayList<>();
        for (Client client : clients.getContent()) {
            clientResponses.add(Mapper.mapToResponse(client));
        }

        return new PageImpl<>(clientResponses, clients.getPageable(), clients.getTotalElements());
    }

    /*Busca y retorna un cliente en base a su id*/
    public ClientResponse findById(Long id) {
        Optional<Client> client = this.clientRepository.findById(id);
        if(client.isEmpty()) throw new ResourceNotFoundException("cliente", "id", id.toString());

        return Mapper.mapToResponse(client.get());
    }

    /*Busca y retorna un cliente en base a su dni*/
    public ClientResponse findByDni(String dni){
        Optional<Client> client = this.clientRepository.findByClientDataDni(dni);
        if(client.isEmpty()) throw new ResourceNotFoundException("cliente", "dni", dni);

        return Mapper.mapToResponse(client.get());
    }

    // ============================== PATCH ==============================
    /*Busca y actualiza los datos de un cliente.*/
    public ClientResponse updateClient(ClientRequest request, Long id){
        Optional<Client> client = this.clientRepository.findById(id);
        if(client.isEmpty()) throw new ResourceNotFoundException("cliente", "id", id.toString());

        Client updated = client.get();
        updated.getClientData().setDni(request.getDni());
        updated.getClientData().setEmail(request.getEmail());
        updated.getClientData().setFirstName(request.getFirstName());
        updated.getClientData().setLastName(request.getLastName());
        updated.getClientData().setPhoneNumber(request.getPhoneNumber());
        updated.getClientData().getClientAddress().setCity(request.getCity());
        updated.getClientData().getClientAddress().setStreet(request.getStreet());
        updated.getClientData().getClientAddress().setNumber(request.getNumber());
        updated.getClientData().getClientAddress().setPostalCode(request.getPostalCode());

        return Mapper.mapToResponse(this.clientRepository.save(updated));
    }


    public void deleteById(Long id) {
        Optional<Client> client = this.clientRepository.findById(id);
        if(client.isEmpty()) throw new ResourceNotFoundException("cliente", "id", id.toString());
        this.clientRepository.deleteById(id);
    }
}
