package org.ruqinhu.algorithm.leetcode;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 */
public class LetterCombinationsOfPhoneNumber {

    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return Collections.EMPTY_LIST;
        }
        List<String> retList = new ArrayList<>();
        String[] strs = digits.split("");
        for (String str:strs) {
            retList = getStrList(str, retList);
        }
        return retList;
    }

    private List<String> getStrList(String str, List<String> letterCombinations) {
        if (letterCombinations.isEmpty()) {
            return Arrays.asList(phone.get(str).split(""));
        }
        String[] strs = phone.get(str).split("");
        List<String> retList = new ArrayList<>();
        for (String ss:letterCombinations) {
            for (String s:strs) {
                retList.add(ss + s);
            }
        }
        return retList;
    }

//    public static void main(String[] args) {
//        String example = "23";
//        LetterCombinationsOfPhoneNumber l = new LetterCombinationsOfPhoneNumber();
//        l.letterCombinations(example);
//    }

}
