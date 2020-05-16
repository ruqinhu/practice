package org.ruqinhu.algorithm;

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
