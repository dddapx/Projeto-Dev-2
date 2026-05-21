package dev2.projeto_semestre.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> tratarErrosGerais(RuntimeException ex) {
        Map<String, String> erro = new HashMap<>();
        
        erro.put("mensagem", ex.getMessage());
        
        // se for um erro de busca nos CRUDs, devolve o 404 not found
        if (ex.getMessage().toLowerCase().contains("não encontrad")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
        
        // para qualquer outra coisa, devolve 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}