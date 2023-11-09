package com.calculator.backend.services.impl;

import com.calculator.backend.entity.Equation;
import com.calculator.backend.repository.EquationRepository;
import com.calculator.backend.services.EquationValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EquationService {

    private EquationRepository equationRepo;
    private EquationValidator equationValidator;

    public List<Equation> getAll() {
        return equationRepo.findAll();
    }

    public List<Equation> getEquationByRoot(double root) {
        return equationRepo.findByRoot(root);
    }

    public void save(Equation equation) {
        equationValidator.validate(equation);
        equationRepo.save(equation);
    }

    public Equation getById(UUID id) {
        return equationRepo.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Equation with id %s does not exist", id)));
    }

    public void delete(UUID id) {
        equationRepo.deleteById(id);
    }

    public List<Equation> getWithOneRoot() {
        return equationRepo.findWithOneRoot();
    }
}
