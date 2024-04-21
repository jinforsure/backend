package com.example.pfe.Controller;

import com.example.pfe.Dto.Benefit.RequestEquipments;
import com.example.pfe.Dto.Benefit.RequestEquipmentsUpdate;
import com.example.pfe.Dto.Benefit.ResponseEquipments;
import com.example.pfe.Service.EquipmentsService;
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
@RequestMapping("api/arsii/equipments")
@RequiredArgsConstructor
public class EquipmentsController {
    @Autowired
    private EquipmentsService equipmentsService;

    @GetMapping("")
    public ResponseEntity<List<ResponseEquipments>> getAllEquipments(){
        List<ResponseEquipments> equipments = equipmentsService.getAllEquipments();
        return ResponseEntity.ok(equipments);
    }
    @PostMapping("")
    public ResponseEntity<Object> addEquipments(@RequestBody @Valid RequestEquipments request){
        equipmentsService.createEquipments(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message","save success !")
        );
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseEquipments> getEquipmentsById(@PathVariable Long id){
        return  ResponseEntity.ok(equipmentsService.getEquipmentsById(id));
    }

    @PutMapping(value ="{id}")
    public ResponseEntity<Object> updateEquipments(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid RequestEquipmentsUpdate request){
        equipmentsService.updateEquipments(id,request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                Collections.singletonMap("message","updated success !")
        );
    }
    @DeleteMapping(value ="{id}")
    public ResponseEntity<Object> deleteEquipments (@PathVariable Long id){
        boolean deleteEquipments = equipmentsService.deleteEquipments(id);
        if (deleteEquipments){
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Collections.singletonMap("message","deleted success !"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap("message","id does not exsist"));
    }


}
