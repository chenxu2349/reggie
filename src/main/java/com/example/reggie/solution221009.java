package com.example.reggie;

import java.util.Stack;

public class solution221009 {

    public static int hardestWorker(int n, int[][] logs) {
        int len = logs.length, longest = 0, ans = -1;
        if(len == 1) return logs[0][0];
        for(int i = 0; i < len; i++) {
            int time = i == 0 ? logs[0][1] : logs[i][1] - logs[i - 1][1];
            if(time > longest) {
                longest = time;
                ans = logs[i][0];
            }
            if(time == longest) ans = Math.min(ans, logs[i][0]);
        }
        return ans;
    }

    public static int[] findArray(int[] pref) {
        int len = pref.length;
        if(len == 1) return pref;
        int[] ans = new int[len];
        ans[0] = pref[0];
        for(int i = 1; i < len; i++) {
            ans[i] = pref[i] ^ pref[i - 1];
        }
        return ans;
    }

    public static String robotWithString(String s) {
        int len = s.length(), smallestIndex = 0,count = 0;
        char smallestChar = s.charAt(smallestIndex);
        if(len == 1) return s;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(c < s.charAt(smallestIndex)) {
                smallestIndex = i;
                smallestChar = c;
            }
        }
        Stack<Character> ss = new Stack<>();
        Stack<Character> tt = new Stack<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < len; i++) {
            ss.push(s.charAt(len - 1 - i));
            if(s.charAt(i) == smallestChar) count++;
        }
        while(count > 0) {
            while(ss.peek() != smallestChar) tt.push(ss.pop());
            ans.append(ss.pop());
            count--;
        }
        while(!ss.isEmpty()) {
            if(ss.peek() < tt.peek()) ans.append(ss.pop());
            else tt.push(ss.pop());
        }
        while(!tt.isEmpty()) ans.append(tt.pop());
        return ans.toString();
    }

    public static void main(String[] args) {
        //1.
        int[][] logs = new int[][]{{0,3},{2,5},{0,9},{1,15}};
        System.out.println(hardestWorker(10, logs));
        //2.
        int[] pref = new int[]{1,3,0,4};
        for(int k : findArray(pref)) System.out.print(k + " ");
        //3.
        System.out.println(" ");
        System.out.println(robotWithString("bac"));
    }
}
