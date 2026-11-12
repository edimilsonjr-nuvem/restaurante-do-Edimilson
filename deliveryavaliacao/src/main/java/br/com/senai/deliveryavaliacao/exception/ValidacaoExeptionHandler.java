package br.com.senai.deliveryavaliacao.exception;

import java.util.LinkedHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;


@RestControllerAdvice
public class ValidacaoExeptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarValidacao(
            MethodArgumentNotValidException erro) {

        Map<String, String> mensagens = new LinkedHashMap<>();

        erro.getBindingResult().getFieldErrors().forEach(campo ->
                mensagens.put(campo.getField(),
                        campo.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(mensagens);
    }
}
