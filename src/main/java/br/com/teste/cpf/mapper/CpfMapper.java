package br.com.teste.cpf.mapper;

import br.com.teste.cpf.representation.response.CpfResponse;
import org.springframework.stereotype.Component;

@Component
public class CpfMapper {

    public CpfResponse toResponse(String mensagem) {
        return CpfResponse.builder()
                .status(mensagem)
                .build();
    }
}
