package com.example.pfe.Service;

import com.example.pfe.Dto.Benefit.RequestRooms;
import com.example.pfe.Dto.Benefit.RequestRoomsUpdate;
import com.example.pfe.Dto.Benefit.ResponseRooms;
import com.example.pfe.Entities.Rooms;

import java.util.List;

public interface RoomsService {
    List<ResponseRooms> getAllRooms();
    Boolean deleteRooms(Long id);
    void createRooms(RequestRooms roomsRequest);
    Rooms updateRooms(Long id, RequestRoomsUpdate roomsRequest);
    ResponseRooms getRoomsById(Long id);
    // ResponseEmployee getEmployeeByIdwithTask(Long id);
}
