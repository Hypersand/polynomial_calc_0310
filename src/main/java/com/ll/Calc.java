package com.ll;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static int run(String exp) {
        if (!exp.contains(" ")) return Integer.parseInt(exp);

        boolean hasBracket = exp.contains("(") && exp.contains(")");
        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
        boolean needToCompound = needToMulti && needToPlus;

        if (hasBracket) {
            exp = exp.substring(1, exp.length() - 1);
        }

        if (needToCompound) {
            String newExp = "";
            String[] bits = exp.split(" \\+ ");
            for (String bit : bits) {
                newExp += run(bit) + " + ";
            }

            return run(newExp);
        }

        if (needToPlus) {
            exp = exp.replaceAll("- ", "+ -");

            String[] bits = exp.split(" \\+ ");

            int sum = 0;

            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }

            return sum;
        }

        if (needToMulti) {
            String[] bits = exp.split(" \\* ");

            int sum = 1;

            for (int i = 0; i < bits.length; i++) {
                sum *= Integer.parseInt(bits[i]);
            }

            return sum;
        }

        throw new RuntimeException("올바른 계산식이 아닙니다.");
    }

}
