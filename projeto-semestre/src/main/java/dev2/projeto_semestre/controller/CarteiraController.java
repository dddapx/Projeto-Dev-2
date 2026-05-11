package dev2.projeto_semestre.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev2.projeto_semestre.dto.CarteiraRequestDTO;
import dev2.projeto_semestre.dto.CarteiraResponseDTO;
import dev2.projeto_semestre.service.CarteiraService;

@RestController
@RequestMapping("/api/carteiras") 
public class CarteiraController {

    private final CarteiraService service;

    public CarteiraController(CarteiraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CarteiraResponseDTO> criar(@RequestBody CarteiraRequestDTO request) {
        CarteiraResponseDTO response = service.criarCarteira(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}