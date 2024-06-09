package com.example.pfe.ServiceImpl;

import com.example.pfe.Dto.Benefit.RequestEquipments;
import com.example.pfe.Dto.Benefit.RequestEquipmentsUpdate;
import com.example.pfe.Dto.Benefit.ResponseEquipments;
import com.example.pfe.Entities.Benefit;
import com.example.pfe.Entities.Equipments;
import com.example.pfe.Repository.BenefitRepository;
import com.example.pfe.Repository.EquipmentsRepository;
import com.example.pfe.Service.EquipmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentsServiceImpl implements EquipmentsService {
    @Autowired
    private EquipmentsRepository equipmentsRepository;
    @Autowired
    private BenefitRepository benefitRepository;

    @Override
    public List<ResponseEquipments> getAllEquipments() {
        List<Equipments> equipments = equipmentsRepository.findAll();
        List<ResponseEquipments> userFormated = new ArrayList<>();
        for (Equipments equipments1 : equipments){
            ResponseEquipments member = ResponseEquipments.makeEquipments(equipments1);
            userFormated.add(member);
        }
        return userFormated;
    }

    @Override
    public void createEquipments(RequestEquipments equipmentsRequest) {
        Benefit benefit = benefitRepository.findById(equipmentsRequest.getBenefitId()).orElseThrow();
        equipmentsRequest.setCategory("Equipments");
        Equipments equipments = Equipments.builder()
                .name(equipmentsRequest.getName())
                .type(equipmentsRequest.getType())
                .manufactuer(equipmentsRequest.getManufactuer())
                .model(equipmentsRequest.getModel())
                .quantity(equipmentsRequest.getQuantity())
                .price(equipmentsRequest.getPrice())
                .maintenance_status(equipmentsRequest.getMaintenance_status())
                .state(equipmentsRequest.getState())
                .category(equipmentsRequest.getCategory())
                .benefit(benefit)
                .build();
        equipmentsRepository.save(equipments);
    }

    @Override
    public ResponseEquipments getEquipmentsById(Long id) {
        Optional<Equipments> equipments = equipmentsRepository.findById(id);
        return ResponseEquipments.makeEquipments((equipments.get()));
    }


    @Override
    public Equipments updateEquipments(Long id, RequestEquipmentsUpdate equipmentsRequest) {
        Equipments equipments = equipmentsRepository.findById(id).orElseThrow();
        if (equipmentsRequest.getQuantity() != 0){
            equipments.setQuantity(equipmentsRequest.getQuantity());
        }
        if (equipmentsRequest.getPrice() != 0){
            equipments.setPrice(equipmentsRequest.getPrice());
        }
        if (equipmentsRequest.getMaintenance_status() != null){
            equipments.setMaintenance_status(equipmentsRequest.getMaintenance_status());
        }
        if (equipmentsRequest.getState() != null){
            equipments.setState(equipmentsRequest.getState());
        }


        return equipmentsRepository.save(equipments);
    }

    @Override
    public Boolean deleteEquipments(Long id) {
        if (!equipmentsRepository.existsById(id)) {
            return false;
        }
        equipmentsRepository.deleteById(id);
        return true;
    }
}
