package com.example.reggie;

import java.util.*;

public class solution220904 {

    public static boolean checkDistances(String s, int[] distance) {
        int slen = s.length(), dlen = distance.length;
        //二维数组letter记录字符串中字母出现两次的下标
        int[][] letter = new int[26][2];
        for (int i = 0; i < 26; i++) {
            int[] arr = letter[i];
            Arrays.fill(arr, -1);
        }
        for(int i = 0; i<slen; i++) {
            char c = s.charAt(i);
            int d = c - 'a';
            if(letter[d][0] == -1) letter[d][0] = i;
            else letter[d][1] = i;
        }
        for(int i = 0; i<dlen; i++) {
            if(letter[i][0] != -1) {
                if(distance[i] != (letter[i][1] - letter[i][0] - 1)) return false;
            }
        }
        return true;
    }

    static long ans = 0;
    public static int numberOfWays(int startPos, int endPos, int k) {
        if(Math.abs(endPos - startPos) == k) return 1;
        backtrack(startPos, endPos, k);
        return (int)ans%1000000007;
    }

    public static void backtrack(int startPos, int endPos, int k) {
        if(k == 0) {
            if(startPos == endPos) ans++;
            return;
        }
        int s1 = startPos+1, s2 = startPos-1;
        //向右走一步
        backtrack(startPos + 1, endPos, k - 1);
        //向左走一步
        backtrack(startPos - 1, endPos, k - 1);
    }

    public static int[][] merge(int[][] intervals) {
        int len = intervals.length;
        //Arrays.sort(intervals, (a,b) -> (a[1] - b[1]));
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[1]==o2[1]) return o1[0]-o2[0];
//                return o1[1]-o2[1];
//            }
//        });
        Arrays.sort(intervals, (a,b) -> (a[0] - b[0]));
        for (int[] k : intervals) System.out.println(Arrays.toString(k));
        System.out.println("合并后的数组:");
        List<int[]> ans = new ArrayList<int[]>();
        int[] pre = intervals[0];
        for(int i = 1; i < len; i++) {
            if(intervals[i][0] <= pre[1] && intervals[i][1] >= pre[1]) {
                pre[1] = intervals[i][1];
            }
            if(intervals[i][0] > pre[1]) {
                ans.add(new int[]{pre[0], pre[1]});
                pre = intervals[i];
            }
        }
        ans.add(new int[]{pre[0], pre[1]});
        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        //1.
        String s = "abaccb";
        int[] distance = new int[]{1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println(checkDistances(s, distance));
        //2.
        System.out.println("方法数：" + numberOfWays(1,1000,999));
        //3.
        int a = 1&8;
        System.out.println(a == 0);
        //56.
        int[][] nums = new int[][]{{1,3},{2,6},{5,10},{8,9},{7,9}};

        for (int[] k : merge(nums)) System.out.println(Arrays.toString(k));

        Set<Integer> set = new HashSet<>();
        set.add(1);set.add(2);set.add(3);set.add(1);
        System.out.println(set.contains(4));

        String[] ss = new String[]{"111", "222", "333"};
        System.out.println(ss[0].equals("112"));
    }
}
