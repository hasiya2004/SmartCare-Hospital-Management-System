package com.smartcare.hospital_api.controller;

import com.smartcare.hospital_api.entity.Room;
import com.smartcare.hospital_api.enums.RoomType;
import com.smartcare.hospital_api.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public List<Room> getAll(@RequestParam(required = false) Boolean available,
                             @RequestParam(required = false) RoomType type) {
        List<Room> rooms;

        if (available != null && available) {
            rooms = (type != null) ? roomService.getAvailableRoomsByType(type) : roomService.getAvailableRooms();
        } else {
            rooms = roomService.getAllRooms();
            if (available != null && !available) {
                rooms = rooms.stream().filter(r -> !r.isAvailable()).toList();
            }
        }

        return rooms;
    }

    @GetMapping("/{id}")
    public Room getById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public ResponseEntity<Room> create(@Valid @RequestBody Room room) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(room));
    }

    @PutMapping("/{id}")
    public Room update(@PathVariable Long id, @Valid @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}