package com.example.pfe.Service;

import com.example.pfe.Dto.Benefit.RequestEquipments;
import com.example.pfe.Dto.Benefit.RequestEquipmentsUpdate;
import com.example.pfe.Dto.Benefit.ResponseEquipments;
import com.example.pfe.Entities.Equipments;

import java.util.List;

public interface EquipmentsService {
    List<ResponseEquipments> getAllEquipments();
    Boolean deleteEquipments(Long id);
    void createEquipments(RequestEquipments equipmentsRequest);
    Equipments updateEquipments(Long id, RequestEquipmentsUpdate equipmentsRequest);
    ResponseEquipments getEquipmentsById(Long id);
    //ResponseEquipments getEquipmentsByType(String type);
    // ResponseEmployee getEmployeeByIdwithTask(Long id);
}
