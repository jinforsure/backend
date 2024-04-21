package com.example.pfe.ServiceImpl;

import com.example.pfe.Dto.Benefit.RequestRooms;
import com.example.pfe.Dto.Benefit.RequestRoomsUpdate;
import com.example.pfe.Dto.Benefit.ResponseRooms;
import com.example.pfe.Entities.Benefit;
import com.example.pfe.Entities.Rooms;
import com.example.pfe.Repository.BenefitRepository;
import com.example.pfe.Repository.RoomsRepository;
import com.example.pfe.Service.RoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService {
    @Autowired
    private RoomsRepository roomsRepository;
    @Autowired
    private BenefitRepository benefitRepository;

    @Override
    public List<ResponseRooms> getAllRooms() {
        List<Rooms> rooms = roomsRepository.findAll();
        List<ResponseRooms> userFormated = new ArrayList<>();
        for (Rooms rooms1 : rooms){
            ResponseRooms member = ResponseRooms.makeRooms(rooms1);
            userFormated.add(member);
        }
        return userFormated;
    }

    @Override
    public void createRooms(RequestRooms roomsRequest) {
        Benefit benefit = benefitRepository.findById(roomsRequest.getBenefitId()).orElseThrow();
        Rooms rooms = Rooms.builder()
                .name(roomsRequest.getName())
                .type(roomsRequest.getType())
                .location(roomsRequest.getLocation())
                .maintenance_status(roomsRequest.getMaintenance_status())
                .capacity(roomsRequest.getCapacity())
                .benefit(benefit)
                .build();
        roomsRepository.save(rooms);
    }

    @Override
    public ResponseRooms getRoomsById(Long id) {
        Optional<Rooms> rooms = roomsRepository.findById(id);
        return ResponseRooms.makeRooms((rooms.get()));
    }

    @Override
    public Rooms updateRooms(Long id, RequestRoomsUpdate roomsRequest) {
        Rooms rooms = roomsRepository.findById(id).orElseThrow();
        if (roomsRequest.getCapacity() != 0){
            rooms.setCapacity(roomsRequest.getCapacity());
        }
        if (roomsRequest.getMaintenance_status() != null){
            rooms.setMaintenance_status(roomsRequest.getMaintenance_status());
        }
        if (roomsRequest.getBenefitId() != null){
            Benefit benefit = benefitRepository.getById(roomsRequest.getBenefitId());
            rooms.setBenefit(benefit);
        }
        return roomsRepository.save(rooms);
    }

    @Override
    public Boolean deleteRooms(Long id) {
        if (!roomsRepository.existsById(id)) {
            return false;
        }
        roomsRepository.deleteById(id);
        return true;
    }
}
