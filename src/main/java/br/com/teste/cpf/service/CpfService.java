package br.com.teste.cpf.service;

import br.com.teste.cpf.domain.Status;
import br.com.teste.cpf.mapper.CpfMapper;
import br.com.teste.cpf.representation.response.CpfResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.regex.Pattern;

@Service
public class CpfService {

    @Autowired
    private CpfMapper cpfMapper;

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}");

    public CpfResponse consultaCpf(String cpf) {

        if (!verificaCpfValido(cpf)) {
            return null;
        }

        return randomizaRetorno();
    }

    public static boolean verificaCpfValido(String cpf) {
        if (cpf == null || !CPF_PATTERN.matcher(cpf).matches()) {
            return false;
        }

        cpf = cpf.replaceAll("[.-]", "");

        int sum = 0;
        int weight = 10;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum += digit * weight;
            weight--;
        }

        int firstVerifier = 11 - (sum % 11);
        if (firstVerifier > 9) {
            firstVerifier = 0;
        }

        sum = 0;
        weight = 11;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum += digit * weight;
            weight--;
        }
        sum += firstVerifier * 2;

        int secondVerifier = 11 - (sum % 11);
        if (secondVerifier > 9) {
            secondVerifier = 0;
        }

        return cpf.charAt(9) - '0' == firstVerifier && cpf.charAt(10) - '0' == secondVerifier;
    }

    public CpfResponse randomizaRetorno() {
        Random random = new Random();
        int resultado = random.nextInt(2);

        if (resultado == 0) {
            return cpfMapper.toResponse(Status.ABLE_TO_VOTE.toString());
        }
        return cpfMapper.toResponse(Status.UNABLE_TO_VOTE.toString());

    }

}
