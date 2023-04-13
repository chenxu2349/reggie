package com.example.reggie.leetcode;

import java.util.*;

public class solution221016 {

    public static int findMaxK(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int k : nums) set.add(k);
        Arrays.sort(nums);
        int len = nums.length;
        for(int i = len - 1; i >= 0; i--) {
            if(nums[i] > 0 && set.contains(-nums[i])) return nums[i];
        }
        return -1;
    }

    public static int countDistinctIntegers(int[] nums) {
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        for(int k : nums) {
            set.add(k);
            int s = 0;
            while(k > 0) {
                s = s * 10 + (k % 10);
                k /= 10;
            }
            set.add(s);
        }
        return set.size();
    }

    public static boolean sumOfNumberAndReverse(int num) {
        if(num == 0) return true;
        for(int i = 0; i <= num; i++) {
            int k = i;
            int rev = 0;
            while(k > 0) {
                rev = rev * 10 + (k % 10);
                k /= 10;
            }
            if(i + rev == num) return true;
        }
        return false;
    }

    public static long countSubarrays(int[] nums, int minK, int maxK) {
        int len = nums.length;
        return 0;
    }

    public static int totalFruit(int[] fruits) {
        List<Integer> basket = new ArrayList<>();
        int len = fruits.length, i = 0, index = 0, count = 0, ans = 0;
        while(i < len) {
            if(basket.size() == 0) {
                basket.add(fruits[i]);
                count++;
                i++;
            } else if(basket.size() == 1) {
                if(basket.get(0) != fruits[i]) basket.add(fruits[i]);
                count++;
                i++;
            } else {
                if(basket.get(0) == fruits[i] || basket.get(1) == fruits[i]) {
                    count++;
                    i++;
                } else {
                    ans = Math.max(ans, count);
                    int kind = fruits[i - 1];
                    for(int j = i - 1; j >= 0; j--) {
                        if(fruits[j] != kind) {
                            i = j + 1;
                            break;
                        }
                    }
                    count = 0;
                    basket.clear();
                }
            }
        }
        ans = Math.max(ans, count);
        return ans;
    }

    public static int beautySum(String s) {
        int len = s.length(), sum = 0;
        for(int i = 0; i < len; i++) {
            Map<Character, Integer> map = new HashMap<>();
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for(int j = i; j < len; j++) {
                char c = s.charAt(j);
                map.put(c, map.getOrDefault(c, 0) + 1);
                max = Math.max(max, map.get(c));
                min = Math.min(min, map.get(c));
                if(map.size() == 1) sum += 0;
                else sum += (max - min);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        //1.
        System.out.println("ANSWER 1:");
        int[] nums = new int[]{-10,8,6,7,-2,-3};
        System.out.println(findMaxK(nums));
        //2.
        int[] nums1 = new int[]{6,9,13,55,22,18,230,10,1000,1005,1054,1066};
        System.out.println("ANSWER 2:");
        System.out.println(countDistinctIntegers(nums1));
        //3.
        System.out.println("ANSWER 3:");
        System.out.println(sumOfNumberAndReverse(181));
        //fruit
        int[] fruits = new int[]{3,3,3,1,2,1,1,2,3,3,4};
        System.out.println(totalFruit(fruits));
        //
        System.out.println(beautySum("spps"));
    }
}
