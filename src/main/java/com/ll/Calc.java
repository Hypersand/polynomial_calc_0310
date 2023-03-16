package com.ll;

public class Calc {

    public static int run(String exp) {
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
        boolean needToCompound = needToMulti && needToPlus;

        if (hasBracket(exp)) {
            int start = 0;
            int end = 0;

            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '(') {
                    start = i;
                    continue;
                }
                if (exp.charAt(i) == ')') {
                    end = i;
                    break;
                }
            }
            String subExp1 = exp.substring(0, start + 1);
            String subExp2 = exp.substring(start + 1, end);
            String subExp3 = exp.substring(end, exp.length());
            System.out.println(subExp1);
            System.out.println(subExp2);

            if (subExp1.contains("-")) {
                return run("-" + run(subExp2));
            }

            if (end == exp.length() - 1) {
                if (subExp1.contains("+") || subExp1.contains("*")) {
                    exp = subExp1.substring(0,subExp1.length()-1) + run(subExp2);
                    return run(exp);
                }

            }

            if (subExp3.contains("+")||subExp3.contains("*")) {
                exp = run(subExp2) + subExp3.substring(1, subExp3.length());
                return run(exp);
            }

            exp = subExp2;
            return run(exp);
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

    private static boolean hasBracket(String exp) {
        return exp.contains("(") && exp.contains(")");
    }

}
