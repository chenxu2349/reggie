package com.example.reggie.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class solution221002 {

    public static int commonFactors(int a, int b) {
        int m = Math.min(a, b), ans = 0;
        for(int i = 1; i <= m; i++) {
            if(a % i == 0 && b % i == 0) ans++;
        }
        return ans;
    }

    public static int maxSum(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        for(int i = 0; i <= m - 3; i++) {
            for(int j = 0; j <= n - 3; j++) {
                int t = grid[i][j] + grid[i][j+1] +grid[i][j+2] +grid[i+1][j+1] +grid[i+2][j] +grid[i+2][j+1] +grid[i+2][j+2];
                ans = Math.max(ans, t);
            }
        }
        return ans;
    }

    public static int minimizeXor(int num1, int num2) {
        int count2 = countBiOne(num2), xor = Integer.MAX_VALUE, ans = 0;
        for(int i = 0; i < 1000000000; i++) {
            if(countBiOne(i) == count2) {
                if((i ^ num1) < xor) {
                    xor = (i ^ num1);
                    ans = i;
                }
            }
        }
        return ans;
    }

    public static int countBiOne(int a) {
        String str = Integer.toBinaryString(a);
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '1') count++;
        }
        return count;
    }

    public static int deleteString(String s) {
        int ans = 0;
        while(s.length() > 0) {
            boolean find = false;
            for(int i = 0; i < s.length() / 2; i++) {
                if(s.substring(0, i + 1).equals(s.substring(i + 1, 2*i + 2))) {
                    find = true;
                    s = s.substring(i + 1);
                    ans++;
                    break;
                }
            }
            if(!find) return ans + 1;
        }
        return ans;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length(), left = 0, right = 0, maxWord = 0;
        Set<String> set = new HashSet<>();
        for(String k : wordDict) {
            set.add(k);
            maxWord = Math.max(maxWord, k.length());
        }
        StringBuilder str = new StringBuilder();
        while(left <= right && right < len) {
            if(left == right) {
                if(set.contains(s.charAt(left))) {
                    right++;
                    left = right;
                } else {
                    str.append(s.charAt(right));
                    right++;
                }
            } else {
                str.append(s.charAt(right));
                if(set.contains(str.toString())) {
                    right++;
                    left = right;
                    str = new StringBuilder();
                } else right++;
            }
        }
        if(str.length() > 0 && !set.contains(str.toString())) return false;
        return true;
    }

    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length, max = 0;
        //dp[i][j]表示以(i,j)为右下角所能构成的最大正方形
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                //靠边时（第一行或第一列）最多只可能边长为1
                if(i == 0 || j == 0) {
                    dp[i][j] = (matrix[i][j] == '1') ? 1 : 0;
                    continue;
                }
                if(matrix[i][j] == '0') {
                    dp[i][j] = 0;
                    continue;
                }
                if(dp[i - 1][j - 1] == 0) {
                    dp[i][j] = (matrix[i][j] == '1') ? 1 : 0;
                    continue;
                } else {
                    //k是左上角存储的最大边长，只需从(i,j)向左向上扫描k长度看是否全为1
                    int k = dp[i - 1][j - 1];
                    boolean valid = true;
                    for(int x = i - 1; x >= i - k; x--) {
                        if(matrix[x][j] == '0') {
                            dp[i][j] = 1; valid = false; break;
                        }
                    }
                    if(!valid) continue;
                    for(int y = j - 1; y >= j - k; y--) {
                        if(matrix[i][y] == '0') {
                            dp[i][j] = 1; valid = false; break;
                        }
                    }
                    if(valid) dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        //dp数组中寻找最大值
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }

    public static void main(String[] args) {
        //1.
        System.out.println(commonFactors(1, 2));
        //2.
        int[][] grid = {{6,2,1,3}, {4,2,1,5}, {9,2,8,7}, {4,1,2,9}};
        System.out.println(maxSum(grid));
        //3.
        //System.out.println(minimizeXor(1, 12));
        //4.
        System.out.println(deleteString("aaabaab"));
        //5.
        List<String> wordDict = new ArrayList<>();
        wordDict.add("a");wordDict.add("b");
        System.out.println(wordBreak("ab", wordDict));
        //
        char[][] matrix = {{'0','0','0','1'},{'1','1','0','1'},{'1','1','1','1'},{'0','1','1','1'},{'0','1','1','1'}};
        System.out.println("最大正方形：" + maximalSquare(matrix));
    }
}
