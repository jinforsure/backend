package com.example.pfe.Service;

import com.example.pfe.Dto.Benefit.RequestBenefit;
import com.example.pfe.Dto.Benefit.ResponseBenefit;
import com.example.pfe.Entities.Benefit;

import java.util.List;

public interface BenefitService {
    List<ResponseBenefit> getAllBenefits();
    boolean deleteBenefit(Long id);
    void createBenefit(RequestBenefit requestBenefit);
    Benefit updateBenefit(Long id, RequestBenefit requestBenefit);
    ResponseBenefit getBenefitById(Long id);
    ResponseBenefit getBenefitWithCategory(Long id);
}
