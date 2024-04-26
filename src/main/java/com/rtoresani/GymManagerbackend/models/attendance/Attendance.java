package com.rtoresani.GymManagerbackend.models.attendance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rtoresani.GymManagerbackend.models.client.Client;
import com.rtoresani.GymManagerbackend.models.gym.Gym;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ATTENDANCES")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    @JsonIgnore
    private Gym gym;

    private LocalDateTime dateTime;
}
