package com.example.pfe.Controller;


import com.example.pfe.Dto.Benefit.RequestBenefit;
import com.example.pfe.Dto.Benefit.ResponseBenefit;
import com.example.pfe.Service.BenefitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/arsii/benefit")
@RequiredArgsConstructor
public class BenefitController {
    @Autowired
    private BenefitService benefitService;

    @GetMapping("")
    public ResponseEntity<List<ResponseBenefit>> getAllBenefits() {
        List<ResponseBenefit> benefits = benefitService.getAllBenefits();
        return  ResponseEntity.ok(benefits);
    }
    @PostMapping("")
    public ResponseEntity<Object> addBenefit(@RequestBody @Valid RequestBenefit request){
        benefitService.createBenefit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message","save success !")
        );
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseBenefit> getBenefitById(@PathVariable Long id){
        return  ResponseEntity.ok(benefitService.getBenefitById(id));
    }
    @GetMapping("{id}/equipments")
    public ResponseEntity<ResponseBenefit> getBenefitByIdwithEquipments(@PathVariable Long id){
        return  ResponseEntity.ok(benefitService.getBenefitWithCategory(id));
    }
    @GetMapping("{id}/rooms")
    public ResponseEntity<ResponseBenefit> getBenefitByIdwithRooms(@PathVariable Long id){
        return  ResponseEntity.ok(benefitService.getBenefitWithCategory(id));
    }
    @PutMapping(value ="{id}")
    public ResponseEntity<Object> updateBenefit(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid RequestBenefit request){
        benefitService.updateBenefit(id,request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                Collections.singletonMap("message","updated success !")
        );
    }
    @DeleteMapping(value ="{id}")
    public ResponseEntity<Object> deleteBenefit (@PathVariable Long id){
        boolean deleteBenefit = benefitService.deleteBenefit(id);
        if (deleteBenefit){
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Collections.singletonMap("message","deleted success !"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap("message","id does not exsist"));
    }
}
