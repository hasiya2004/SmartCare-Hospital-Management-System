package com.smartcare.hospital_api.repository;

import com.smartcare.hospital_api.entity.Room;
import com.smartcare.hospital_api.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAvailableTrue();

    List<Room> findByRoomTypeAndAvailableTrue(RoomType type);
}