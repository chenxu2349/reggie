package com.example.reggie.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LCCUP2022 {

    public static int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int len = temperatureA.length;
        int left = 0, right = 1, ans = 0;
        while(right < len) {
            int a = 0, b = 0;
            if(temperatureA[right] - temperatureA[right - 1] > 0) a = 1;
            else a = (temperatureA[right] - temperatureA[right - 1] < 0) ? -1 : 0;
            if(temperatureB[right] - temperatureB[right - 1] > 0) b = 1;
            else b = (temperatureB[right] - temperatureB[right - 1] < 0) ? -1 : 0;
            if(a == b) {
                ans = Math.max(ans, right - left);
            } else {
                left = right;
            }
            right++;
        }
        return ans;
    }

    public static int transportationHub(int[][] path) {
        int len = path.length, n = 0;
        //统计图的节点个数
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        for(int[] k : path) {
            int k0 = k[0], k1 = k[1];
            //对所有图节点重新编号
            if(map.get(k0) == null) {map.put(k0, n);map1.put(n, k0);n++;}
            if(map.get(k1) == null) {map.put(k1, n);map1.put(n, k1);n++;}
        }
        //上面循环执行完后n就是总节点数量
        int[][] count = new int[n][2]; //count[0]表示进入数 count[1]发出数
        for(int i = 0; i < len; i++) {
            int out = path[i][0], in = path[i][1];
            count[map.get(out)][1] += 1;
            count[map.get(in)][0] += 1;
        }
        for(int i = 0; i < n; i++) {
            if(count[i][0] == (n - 1) && count[i][1] == 0) return map1.get(i);
        }
        return -1;
    }

    public static void main(String[] args) {
        //1.
        int[] a = new int[]{5,10,16,-6,15,11,3};
        int[] b = new int[]{16,22,23,23,25,3,-16};
        System.out.println("最长气温连续变化趋势：" + temperatureTrend(a, b));
        //2.
        int[][] path = new int[][]{{2,4},{2,6},{4,6}};
        System.out.println("交通枢纽：" + transportationHub(path));
        //3.
        String[] plate = new String[]{"..W.", "w..O"};
        //4.
        //5.
    }
}
