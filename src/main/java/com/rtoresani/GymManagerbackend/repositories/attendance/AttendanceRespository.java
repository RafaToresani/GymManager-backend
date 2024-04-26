package com.rtoresani.GymManagerbackend.repositories.attendance;

import com.rtoresani.GymManagerbackend.models.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRespository extends JpaRepository<Attendance, Long> {
}
