package com.calculator.backend.controller;

import com.calculator.backend.dto.EquationDto;
import com.calculator.backend.entity.Equation;
import com.calculator.backend.entity.Root;
import com.calculator.backend.facade.EquationFacade;
import com.calculator.backend.services.impl.EquationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/equation")
@AllArgsConstructor
public class EquationController {

    private EquationFacade equationFacade;

    @GetMapping
    public ResponseEntity<List<EquationDto>> getAll() {
        return new ResponseEntity<>(equationFacade.getAll(), HttpStatus.OK);
    }

    @GetMapping("/root/{root}")
    public ResponseEntity<List<EquationDto>> getByRoot(@PathVariable("root") Double root) {
        return new ResponseEntity<>(equationFacade.getByRoot(root), HttpStatus.OK);
    }

    @GetMapping("/root")
    public ResponseEntity<List<EquationDto>> getByRoot() {
        return new ResponseEntity<>(equationFacade.getWithOneRoot(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        equationFacade.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquationDto> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(equationFacade.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public void save(@RequestBody EquationDto equationDto) {
        equationFacade.save(equationDto);
    }
}
