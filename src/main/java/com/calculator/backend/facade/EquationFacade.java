package com.calculator.backend.facade;

import com.calculator.backend.convertor.EquationDtoConvertor;
import com.calculator.backend.dto.EquationDto;
import com.calculator.backend.services.impl.EquationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EquationFacade {
    private EquationService equationService;
    private EquationDtoConvertor equationDtoConvertor;

    public void save(EquationDto equationDto) {
        equationService.save(equationDtoConvertor.convert(equationDto));
    }

    public EquationDto getById(String id) {
        return equationDtoConvertor.convert(equationService.getById(UUID.fromString(id)));
    }

    public List<EquationDto> getAll() {
        return equationService.getAll().stream()
                .map(equationDtoConvertor::convert).collect(Collectors.toList());
    }

    public List<EquationDto> getByRoot(Double root) {
        return equationService.getEquationByRoot(root).stream()
                .map(equationDtoConvertor::convert).collect(Collectors.toList());
    }

    public void delete(String id) {
        equationService.delete(UUID.fromString(id));
    }

    public List<EquationDto> getWithOneRoot() {
        return equationService.getWithOneRoot().stream()
                .map(equationDtoConvertor::convert).collect(Collectors.toList());
    }
}
