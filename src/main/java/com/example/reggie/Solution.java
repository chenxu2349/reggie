package com.example.reggie;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        Stack<Integer> stack = new Stack<>();
        // i, j是分别扫描push和pop序列的指针
        int j = 0;
        for(int i = 0; i < len; i++) {
            if(stack.empty()) stack.push(pushed[i]);
            else if(stack.peek() == popped[j]) {
                int p = stack.pop();
                j++; i--;
            }
            else stack.push(pushed[i]);
        }
        while(!stack.empty()) {
            int p = stack.pop();
            if(p == popped[j]) j++;
            else return false;
        }
        return true;
    }

    public static int[][] spiralMatrix(int m, int n, ListNode head) {
        return null;
    }

    public static void main(String[] args) {
        String tt = decodeMessage("the quick brown fox jumps over the lazy dog","vkbs bs t suepuv");
        System.out.println(tt);
        int[] pushed = new int[]{1,2,3,4,5};
        int[] poped = new int[]{4,5,3,2,1};
        System.out.println(validateStackSequences(pushed, poped));

        StringBuilder str = new StringBuilder();
        str.append(10);
        System.out.println(str);
        str.append("->" + 5);
        System.out.println("str:" + str);
    }


}