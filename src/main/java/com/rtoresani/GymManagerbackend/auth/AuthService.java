package com.rtoresani.GymManagerbackend.auth;


import com.rtoresani.GymManagerbackend.auth.dto.AuthResponse;
import com.rtoresani.GymManagerbackend.auth.dto.LoginRequest;
import com.rtoresani.GymManagerbackend.auth.dto.RegisterRequest;
import com.rtoresani.GymManagerbackend.auth.jwt.JwtService;
import com.rtoresani.GymManagerbackend.exceptions.ResourceAlreadyExistException;
import com.rtoresani.GymManagerbackend.models.ERole;
import com.rtoresani.GymManagerbackend.models.User;
import com.rtoresani.GymManagerbackend.models.UserAddress;
import com.rtoresani.GymManagerbackend.models.UserData;
import com.rtoresani.GymManagerbackend.repositories.user.UserAddressRepository;
import com.rtoresani.GymManagerbackend.repositories.user.UserDataRepository;
import com.rtoresani.GymManagerbackend.repositories.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserAddressRepository addressRepository;
    @Autowired
    private UserDataRepository userDataRepository;

    public Optional<AuthResponse> register(@Valid RegisterRequest request) {

        if(repository.existsByEmail(request.getEmail())) throw new ResourceAlreadyExistException("El email ya está en uso.");
        User user = createUser(request);
        String token = jwtService.getToken(user, user.getAuthorities());

        return Optional.of(AuthResponse.builder()
                .token(token)
                .role(user.getRole())
                .userId(user.getId())
                .email(user.getEmail())
                .build());

    }

    private User createUser(RegisterRequest request){
        UserAddress userAddress = createUserAddress(request);
        UserData userData = createUserData(request, userAddress);
        User user = createUserEntity(request, userData);
        return repository.save(user);
    }

    private User createUserEntity(RegisterRequest request, UserData userData) {

        User user = User.builder()
                .userData(userData)
                .role(ERole.MANAGER)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        if(existUsers()) user.setRole(ERole.ADMIN);

        return user;
    }

    private UserData createUserData(RegisterRequest request, UserAddress userAddress) {

        return userDataRepository.save(UserData
                .builder()
                .address(userAddress)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build());
    }

    private UserAddress createUserAddress(RegisterRequest request) {
        return addressRepository.save(UserAddress.builder()
                .city(request.getCity())
                .street(request.getStreet())
                .number(request.getNumberStreet())
                .postalCode(request.getPostalCode())
                .build());
    }

    public Optional<AuthResponse> login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user, user.getAuthorities());

        return Optional.of(AuthResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .token(token)
                .build());

    }

    private Boolean existUsers(){
        return repository.findAll().isEmpty();
    }
}
