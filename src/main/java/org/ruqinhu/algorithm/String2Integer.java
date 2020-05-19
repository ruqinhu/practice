package org.ruqinhu.algorithm;

public class String2Integer {

    public int myAtoi(String str) {
        StringBuilder sb = new StringBuilder();
        if (str.isEmpty()) {
            return 0;
        }
        boolean first = true;
        char[] params = str.toCharArray();
        for (char c : params) {
            if (Character.isWhitespace(c)) {
                if (first) {
                    continue;
                } else {
                    break;
                }
            }
            if (first && !(Character.isDigit(c) || (c == ('-') || c == ('+')))) {
                return 0;
            }
            if (first && (c == ('-') || c == ('+'))) {
                sb.append(c);
                first = false;
                continue;
            }
            if (Character.isDigit(c)) {
                sb.append(c);
                first = false;
                continue;
            }
            break;
        }
        if (sb.length() == 0) {
            return 0;
        }
        try {
            int ret = Integer.parseInt(sb.toString());
            return ret;
        } catch (NumberFormatException e) {
            if (sb.length() == 1) {
                return 0;
            }
            if (sb.charAt(0) == '-') {
                return Integer.MIN_VALUE;
            }
            if (sb.charAt(0) == '+') {
                return Integer.MAX_VALUE;
            }
            return Integer.MAX_VALUE;
        }
    }

}
