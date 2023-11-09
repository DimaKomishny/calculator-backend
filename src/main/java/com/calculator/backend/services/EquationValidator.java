package com.calculator.backend.services;

import com.calculator.backend.entity.Equation;

public interface EquationValidator {
    void validate(Equation equationBody);
}
