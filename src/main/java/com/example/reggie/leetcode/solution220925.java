package com.example.reggie.leetcode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class solution220925 {

    public static int longestSubarray(int[] nums) {
        Calendar calendar = Calendar.getInstance();
        int len = nums.length, max = 0, left = 0, right = 1, ans = 1;
        if(len == 1) return 1;
        for(int k : nums) max = Math.max(max, k);
        while(left <= right && right < len) {
            if((nums[right] & nums[right - 1]) == max) {
                ans = Math.max(ans, right - left + 1);
            } else {
                left = right;
            }
            right++;
        }
        return ans;
    }

    public static List<Integer> goodIndices(int[] nums, int k) {
        int len = nums.length;
        List<Integer> downToUp = new ArrayList<>();
        List<Integer> ans = new LinkedList<>();
        if(k == 1) {
            for(int i = 1; i < len - 1; i++) ans.add(i);
            return ans;
        }
        for(int i = k; i < len - k; i++) {
            if(nums[i - 2] >= nums[i - 1] && nums[i + 2] >= nums[i + 1]) downToUp.add(i);
        }
        for(int i = 0; i < downToUp.size(); i++) {
            int index = downToUp.get(i);
            boolean leftValid = true, rightValid = true;
            for(int j = index - k; j < index - 1; j++) {
                if(nums[j] < nums[j + 1]) {leftValid = false; break;}
            }
            if(!leftValid) continue;
            for(int j = index + 2; j <= index + k; j++) {
                if(nums[j] < nums[j - 1]) {rightValid = false; break;}
            }
            if(rightValid)
                ans.add(index);
        }
        return ans;
    }

    public static void main(String[] args) {
        //2.
        int[] nums = new int[]{1,2,3,4};
        System.out.println(longestSubarray(nums));
        //3.
        System.out.println("好下标：");
        int[] nums1 = new int[]{878724,201541,179099,98437,35765,327555,475851,598885,849470,943442};
        int k = 4;
        for(int key : goodIndices(nums1, k)) System.out.println(key);
    }
}

