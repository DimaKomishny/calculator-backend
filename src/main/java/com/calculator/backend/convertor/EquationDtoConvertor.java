package com.calculator.backend.convertor;

import com.calculator.backend.dto.EquationDto;
import com.calculator.backend.entity.Equation;
import com.calculator.backend.entity.Root;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EquationDtoConvertor {

    public Equation convert(EquationDto dto) {
        return Equation.builder()
                .id(dto.getId() == null ? null : UUID.fromString(dto.getId()))
                .body(dto.getBody())
                .roots(dto.getRoots()
                        .stream()
                        .map(r -> Root.builder().value(r).build())
                        .collect(Collectors.toList()))
                .build();
    }
    public EquationDto convert(Equation equation) {
        return EquationDto.builder()
                .id(equation.getId().toString())
                .body(equation.getBody())
                .roots(equation.getRoots().stream().map(Root::getValue).collect(Collectors.toList()))
                .build();
    }
}
