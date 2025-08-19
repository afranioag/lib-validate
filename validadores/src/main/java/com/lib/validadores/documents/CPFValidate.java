package com.lib.validadores.documents;


public class CPFValidate {

    public static boolean isCpfValid(String s) {
        if(isBlank(s)) return false;
        s = removeCharacters(s);

        if(s.length() != 11) return false;

        try {
            String base = s.substring(0, 9);
            int dv1 = calcularDigitoVerificador(base, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2});
            int dv2 = calcularDigitoVerificador(base + dv1, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2});

            // Compara os dígitos calculados com os dígitos informados no CPF
            return s.equals(base + dv1 + dv2);
        } catch (IllegalArgumentException e) {
            // Se algum caractere na base for inválido para o cálculo
            return false;
        }
    }

    /**
     * Calcula um dígito verificador (0-9) usando o algoritmo Módulo 11.
     */
    private static int calcularDigitoVerificador(String base, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < base.length(); i++) {
            soma += charToValue(base.charAt(i)) * pesos[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    /**
     * Converte um caractere alfanumérico para seu valor numérico.
     * '0'-'9' -> 0-9
     * 'A'-'Z' -> 10-35
     */
    private static int charToValue(char c) {
        if (Character.isDigit(c)) {
            return c - '0';
        } else if (Character.isLetter(c)) {
            return Character.toUpperCase(c) - 'A' + 10;
        }
        throw new IllegalArgumentException("Caractere inválido na base do documento: " + c);
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String removeCharacters(String s) {
        return s.replaceAll("[^0-9]", "").trim();
    }
}
