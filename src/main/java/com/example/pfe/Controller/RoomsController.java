package com.example.pfe.Controller;

import com.example.pfe.Dto.Benefit.RequestRooms;
import com.example.pfe.Dto.Benefit.RequestRoomsUpdate;
import com.example.pfe.Dto.Benefit.ResponseRooms;
import com.example.pfe.Service.RoomsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/arsii/rooms")
@RequiredArgsConstructor
public class RoomsController {
    @Autowired
    private RoomsService roomsService;

    @GetMapping("")
    public ResponseEntity<List<ResponseRooms>> getAllRooms(){
        List<ResponseRooms> rooms = roomsService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
    @PostMapping("")
    public ResponseEntity<Object> addRooms(@RequestBody @Valid RequestRooms request){
        roomsService.createRooms(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message","save success !")
        );
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseRooms> getRoomsById(@PathVariable Long id){
        return  ResponseEntity.ok(roomsService.getRoomsById(id));
    }
    @PutMapping(value ="{id}")
    public ResponseEntity<Object> updateRooms(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid RequestRoomsUpdate request){
        roomsService.updateRooms(id,request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                Collections.singletonMap("message","updated success !")
        );
    }
    @DeleteMapping(value ="{id}")
    public ResponseEntity<Object> deleteRooms (@PathVariable Long id){
        boolean deleteRooms = roomsService.deleteRooms(id);
        if (deleteRooms){
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Collections.singletonMap("message","deleted success !"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap("message","id does not exsist"));
    }

}
