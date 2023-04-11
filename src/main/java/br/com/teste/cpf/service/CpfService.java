package br.com.teste.cpf.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CpfService {

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}");

    public boolean consultaCpf(String cpf) {

        return verificaCpfValido(cpf);
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

}
