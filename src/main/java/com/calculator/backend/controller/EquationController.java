package com.calculator.backend.controller;

import com.calculator.backend.services.impl.EquationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/equation")
@AllArgsConstructor
public class EquationController {

    private EquationService equationService;


    @GetMapping
    public String getAllEquation() {
        return equationService.getEquationByRoot(10).stream()
                .flatMap(e -> e.getRoots().stream())
                .map(r -> r.getRoot().toString())
                .collect(Collectors.joining());
    }

    @PostMapping
    public void save() {
        equationService.save(null);
    }
}
