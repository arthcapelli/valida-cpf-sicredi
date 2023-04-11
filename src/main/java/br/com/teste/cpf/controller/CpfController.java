package br.com.teste.cpf.controller;

import br.com.teste.cpf.service.CpfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class CpfController {

    @Autowired
    CpfService cpfService;

    @GetMapping("{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public boolean consultaCpf(@PathVariable String cpf) {
        return cpfService.consultaCpf(cpf);
    }
}
