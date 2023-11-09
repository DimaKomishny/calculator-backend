package com.calculator.backend.services.impl;

import com.calculator.backend.entity.Equation;
import com.calculator.backend.repository.EquationRepository;
import com.calculator.backend.services.EquationValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EquationService {

    private EquationRepository equationRepo;
    private EquationValidator equationValidator;

    public List<Equation> getAllEquations() {
        return equationRepo.findAll();
    }

    public List<Equation> getEquationByRoot(double root) {
        return equationRepo.findByRoot(root);
    }

    public void save(Equation equation) {
        equationValidator.validate(equation);
        equationRepo.save(equation);
    }
}
