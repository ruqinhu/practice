package org.ruqinhu.algorithm.leetcode;

/**
 *
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 *
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 *
 * 优化的点:
 * 1.访问字符串中字符时，使用自增的 int 标志位即可，不需要声明一个临时数组
 * 2.判断是否越界： ans = ans * 10 + digit > Integer.MAX_VALUE  -->  ans > (Integer.MAX_VALUE - digit) / 10
 */
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
