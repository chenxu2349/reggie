package com.example.reggie;

import java.util.*;

/**
 * @ClassName Solution220806
 * @Description
 * @Author chenxu
 * @Date 2022/8/6 22:36
 **/
public class Solution220806 {
    public static List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        int len1 = items1.length, len2 = items2.length;
        List<List<Integer>> ans = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<len1; i++) {
            map.put(items1[i][0], map.getOrDefault(items1[i][0], 0) + items1[i][1]);
        }
        for(int i = 0; i<len2; i++) {
            map.put(items2[i][0], map.getOrDefault(items2[i][0], 0) + items2[i][1]);
        }
        int[] value = new int[map.size()]; int index = 0;
        for(int key : map.keySet()) {value[index] = key; index++;}
        Arrays.sort(value);

        for(int i = 0; i<value.length; i++) {
            List<Integer> valid = new LinkedList<>();
            valid.add(value[i]); valid.add(map.get(value[i]));
            ans.add(valid);
        }
        return ans;
    }

    public static long countBadPairs(int[] nums) {
        int len = nums.length; long badEntry = 0;
        if(len == 1) return 0;
        for(int i = 0; i<len; i++) {
            for(int j = i+1; j<len; j++) {
                if(j - i != nums[j] - nums[i]) badEntry++;
            }
        }
        return badEntry;
    }

    public static int arithmeticTriplets(int[] nums, int diff) {
        int len = nums.length, ans = 0;
        for(int i = 0; i<len; i++) {
            for(int j = i+1; j<len; j++) {
                for(int k = j+1; k<len; k++) {
                    if(nums[k]-nums[j]==diff && nums[j]-nums[i]==diff) ans++;
                }
            }
        }
        return ans;
    }

    public static int longestIdealString(String s, int k) {
        int len = s.length(), ans = 0;
        if(len == 1) return 1;
        char[] chars = s.toCharArray();
        //dp[i]表示以chars[i]结尾的字符串最长理想序列长度
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        //记录以某字母结尾的最大长度
        Map<Character, Integer> map = new HashMap<>();
        map.keySet();
        for(int i = 0; i<len; i++) {
            char t = chars[i];
            for(int j = 0; j<=k; j++) {
                char t1 = (char)(t-j);
                char t2 = (char)(t+j);
                if(map.containsKey(t1)) dp[i] = Math.max(dp[i], map.get(t1)+1);
                if(map.containsKey(t2)) dp[i] = Math.max(dp[i], map.get(t2)+1);
            }
            map.put(t, dp[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] item1 = {{1,1},{4,5},{3,8}};
        int[][] item2 = {{3,1},{1,5}};
        int[] num = {86550,14905,56903,669,3990};
        int[] nums = {0,1,4,6,7,10};
        List<List<Integer>> lists = mergeSimilarItems(item1, item2);
        long kkk = countBadPairs(num);
        System.out.println(kkk);
        System.out.println(arithmeticTriplets(nums, 3));
        System.out.println(longestIdealString("acfgbd", 2));
    }
}
