package com.example.reggie;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static String decodeMessage(String key, String message) {
        int keyLen = key.length();
        int messageLen = message.length();
        char letter = 'a';
        char[] ans = new char[messageLen];
        Map<Character, Character> map = new HashMap<>();
        for(int i = 0; i<keyLen; i++) {
            if(map.get(key.charAt(i))==null && key.charAt(i)!=' ') {
                map.put(key.charAt(i),letter);
                letter++;
            }
            if(map.size()==26) break;
        }
        for(int i = 0; i<messageLen; i++) {
            if(map.get(message.charAt(i))!=null) {
                ans[i] = map.get(message.charAt(i));
            }
            else ans[i]=' ';
        }
        return new String(ans);
    }

    public static int[][] spiralMatrix(int m, int n, ListNode head) {
        return null;
    }

    public static void main(String[] args) {
        String tt = decodeMessage("the quick brown fox jumps over the lazy dog","vkbs bs t suepuv");
        System.out.println(tt);

    }


}