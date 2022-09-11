package com.example.reggie;

import java.util.*;

public class solution220911 {

    public static int mostFrequentEven(int[] nums) {
        int len = nums.length, ans = 0, max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++) {
            if(nums[i] % 2 == 0) map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        if(map.size() == 0) return -1;
        for(int key : map.keySet()) {
            if(map.get(key) > max) {
                max = map.get(key);
                ans = key;
            }
            if(map.get((key)) == max) ans = Math.min(key, ans);
        }
        return ans;
    }

    public static int partitionString(String s) {
        int len = s.length(), left = 0, right = 0, ans = 0;
        if(len == 1) return 1;
        if(len == 2) return s.charAt(0) == s.charAt(1) ? 2 : 1;

        Set<Character> window = new HashSet<>();
        while(left <= right && right <len) {
            char c = s.charAt(right);
            if(!window.contains(c)) {
                window.add(c);
                right++;
            } else {
                ans++;
                window = new HashSet<>();
                left = right;
                window.add(c);
                right++;
            }
        }
        if(window.size() > 0) ans++;
        return ans;
    }

    public static int minGroups(int[][] intervals) {
        int len = intervals.length, ans = 0;
        Set<Integer> set = new HashSet<>();
        //按照第一位升序，第一位相同则第二位升序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < len; i++) {
            //新开一个组
            if(!set.contains(i)) {
                set.add(i);
                ans++;
                int t = i;
                for (int j = i + 1; j < len; j++) {
                    int[] ti = intervals[t];
                    int[] tj = intervals[j];
                    //后面合法的一个区间
                    if (tj[0] > ti[1] && !set.contains(j)) {
                        set.add(j);
                        t = j;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //1.
        int[] arr = new int[]{8154,9139,8194,3346,5450,9190,133,8239,4606,8671,8412,6290};
        System.out.println(mostFrequentEven(arr));
        //2.
        System.out.println(partitionString("abcadfacc"));
        //3.
        int[][] intervals = new int[][]{{441459,446342},{801308,840640},{871890,963447},{228525,336985},{807945,946787},{479815,507766},{693292,944029},{751962,821744}};
        System.out.println("minGroups:" + minGroups(intervals));
    }
}
