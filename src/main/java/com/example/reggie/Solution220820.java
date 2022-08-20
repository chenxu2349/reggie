package com.example.reggie;

/**
 * @ClassName Solution220820
 * @Description
 * @Author chenxu
 * @Date 2022/8/20 22:34
 **/
public class Solution220820 {

    public static int minimumRecolors(String blocks, int k) {
        char[] chars = blocks.toCharArray();
        //计算初始窗口内的黑白方块数量
        int white = 0, black = 0, ans = 0;
        for(int i = 0; i<k; i++) {
            if(chars[i] == 'W') white++;
            else black++;
        }
        ans = white;
        //设置左右指针，滑动窗口，统计窗口内白方块数量，取最小值返回
        int left = 0, right = k - 1;
        while(right < chars.length - 1) {
            if(chars[left] == 'W') white--;
            else black--;
            if(chars[right + 1] == 'W') white++;
            else black++;
            ans = white < ans ? white : ans;
            left++; right++;
        }
        return ans;
    }

    public static int secondsToRemoveOccurrences(String s) {
        char[] chars = s.toCharArray();
        if(chars.length == 1) return 0;
        boolean find = false;
        for(int i = 0; i<chars.length-1; i++) {
            if(chars[i] == '0' && chars[i+1] == '1') {
                find = true;
                chars[i] = '1'; chars[i+1] = '0';
                i++;
            }
        }
        if(!find) return 0;
        int seconds = 1;
        seconds += secondsToRemoveOccurrences(new String(chars));
        return seconds;
    }

    public static String shiftingLetters(String s, int[][] shifts) {
        char[] chars = s.toCharArray();
        for(int i = 0; i<shifts.length; i++) {
            int start = shifts[i][0], end = shifts[i][1], direction = shifts[i][2];
            for(int j = start; j<=end; j++) {
                if(direction == 1) {
                    if(chars[j] == 'z') chars[j] = 'a';
                    else chars[j]++;
                }else {
                    if(chars[j] == 'a') chars[j] = 'z';
                    else chars[j]--;
                }
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        int x = minimumRecolors("WBBWBWBWBWWBWBW", 5);
        System.out.println(x);
        int y = secondsToRemoveOccurrences("11010010101110");
        System.out.println(y);
        int[][] arr = new int[][]{{0,0,0},{1,1,1}};
        System.out.println(shiftingLetters("dztz", arr));
    }
}
