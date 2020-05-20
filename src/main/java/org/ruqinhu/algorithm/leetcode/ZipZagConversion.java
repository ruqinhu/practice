package org.ruqinhu.algorithm.leetcode;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 *
 * 优化的点：
 * 1.思路问题，不该使用二维数组，图形化只是为了方便理解，不一定就需要按照图形来解体
 * 2.寻找循行规律，此处行号一直有规律的在变化，所以可以使用行号作为突破口
 */
public class ZipZagConversion {

        public String convert(String s, int numRows) {
            if (s.length() == 0) {
                return "";
            }
            if (numRows == 1) {
                return s;
            }
            int rowNums = (s.length() / (numRows + numRows - 2)) * (numRows -1);
            if ((s.length() % (numRows + numRows - 2)) > numRows) {
                rowNums = rowNums +  (s.length() % (numRows + numRows - 2)) % numRows + 1;
            } else {
                rowNums ++;
            }
            char[][] rm = new char[rowNums][numRows];
            int i = 0;
            int row = 0;
            int col = 0;
            while (i < s.length()) {
                if (row == 0 || row % (numRows - 1) == 0) {
                    while (col < numRows) {
                            rm[row][col] = s.charAt(i++);
                            col++;
                            if (i > s.length() - 1) {
                                break;
                            }
                    }
                    col = col -2;
                    row++;
                } else {
                    rm[row][col] = s.charAt(i++);
                    row++;
                    col--;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int x = 0;x < numRows;x++){
                for (int y = 0;y < rowNums;y++) {
                    char c = rm[y][x];
                    if (c != '\0') {
                        sb.append(c);
                    }
                }
            }
            return sb.toString();
        }

}
