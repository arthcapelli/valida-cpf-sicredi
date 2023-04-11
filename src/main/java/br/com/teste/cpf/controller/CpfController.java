package br.com.teste.cpf.controller;

import br.com.teste.cpf.representation.response.CpfResponse;
import br.com.teste.cpf.service.CpfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping()
public class CpfController {

    @Autowired
    CpfService cpfService;

    @GetMapping("{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CpfResponse> consultaCpf(@PathVariable String cpf) {
        return Optional.ofNullable(cpfService.consultaCpf(cpf))
                .map(cpfResponse -> ResponseEntity.ok().body(cpfResponse))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
