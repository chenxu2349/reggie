package com.example.reggie.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class unCasual {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new LinkedList<>();
        int lens = s.length(), lenp = p.length();
        int left = 1, right = lenp;
        Map<Character, Integer> origin = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        //用两个窗口统计目标串的字符数和当前扫描串的字符数
        for(int i = 0; i < lenp; i++) origin.put(p.charAt(i), origin.getOrDefault(p.charAt(i), 0) + 1);
        for(int i = 0; i < lenp; i++) window.put(s.charAt(i), window.getOrDefault(s.charAt(i), 0) + 1);
        if(isValid(origin, window)) ans.add(0);
        for(int i = 1; i <= lens - lenp; i++) {
            window.put(s.charAt(left - 1), window.get(s.charAt(left - 1)) - 1);
            window.put(s.charAt(right), window.getOrDefault(s.charAt(right), 0) + 1);
            if(isValid(origin, window)) ans.add(i);
            left++; right++;
        }
        return ans;
    }

    public static boolean isValid(Map<Character, Integer> a, Map<Character, Integer> b) {
        for(char key : a.keySet()) {
            if(a.get(key) != b.get(key)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "cabcba";
        String p = "abc";
        List<Integer> anagrams = findAnagrams(s, p);
        for(int k : anagrams) System.out.println(k);
    }
}
