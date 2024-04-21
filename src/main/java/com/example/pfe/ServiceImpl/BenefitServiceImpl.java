package com.example.pfe.ServiceImpl;

import com.example.pfe.Dto.Benefit.RequestBenefit;
import com.example.pfe.Dto.Benefit.ResponseBenefit;
import com.example.pfe.Entities.Benefit;
import com.example.pfe.Repository.BenefitRepository;
import com.example.pfe.Service.BenefitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BenefitServiceImpl implements BenefitService {
    @Autowired
    private BenefitRepository benefitRepository;
    @Override
    public List<ResponseBenefit> getAllBenefits() {
        List<Benefit> benefits = benefitRepository.findAll();
        List<ResponseBenefit> benefitFormated = new ArrayList<>();
        for (Benefit benefit: benefits) {
            ResponseBenefit benefit1 = ResponseBenefit.makeBenefit(benefit);
            benefitFormated.add(benefit1);
        }
        return benefitFormated;
    }

    @Override
    public boolean deleteBenefit(Long id) {
        if (!benefitRepository.existsById(id)) {
            return false;
        }
        benefitRepository.deleteById(id);
        return true;
    }

    @Override
    public void createBenefit(RequestBenefit requestBenefit) {
        Benefit benefit = Benefit.builder()
                .category(requestBenefit.getCategory())
                .build();
        benefitRepository.save(benefit);

    }

    @Override
    public Benefit updateBenefit(Long id, RequestBenefit requestBenefit) {
            Benefit benefit = benefitRepository.findById(id).orElseThrow();
            if (requestBenefit.getCategory() != null){
                benefit.setCategory(requestBenefit.getCategory());
            }
            return benefitRepository.save(benefit);
    }

    @Override
    public ResponseBenefit getBenefitById(Long id) {
        Optional<Benefit> benefit = benefitRepository.findById(id);
        return ResponseBenefit.makeBenefit((benefit.get()));
    }

    @Override
    public ResponseBenefit getBenefitWithCategory(Long id) {
        Optional<Benefit> benefit = benefitRepository.findById(id);
        return ResponseBenefit.makeBenefitList((benefit.get()));
    }
}
