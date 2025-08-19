package com.lib.validadores.documents;

import org.junit.jupiter.api.Test;

public class CPFValidateTest {

    @Test
    void deveRetornarTrue_quandoCpfValido() {
        String cpf = "093.631.984-48";
        boolean result = CPFValidate.isCpfValid(cpf);
        assert(result);
    }

    @Test
    void deveRetornarFalse_quandoCpfInvalido() {
        String cpf = "093.631.984-49";
        boolean result = CPFValidate.isCpfValid(cpf);
        assert(!result);
    }
}
