package com.example.reggie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Solution220710
 * @Description
 * @Author chenxu
 * @Date 2022/7/10 10:29
 **/
public class Solution220710 {

    public static int fillCups(int[] amount) {
        int sum = amount[0] + amount[1] + amount[2];
        Arrays.sort(amount);
        int min = amount[0], midium = amount[1], max = amount[2];
        if(max >= sum-max) return max;
        if(min+midium-max==2) return max+1;
        //轮番消耗
        for(int i = 0; i<max; i++) {
            if(i%2==0) {
                if(midium!=0) midium--;
                else min--;
            }
            else {
                if(min!=0) min--;
                else midium--;
            }
        }
        if(min==0 || midium==0) return max+min+midium;
        else return max+Math.max(midium, min);
    }

    public static void main(String[] args) {
        int[] amount = new int[]{4,2,4};
        Map<Character, Integer> need, window = new HashMap<>();
        System.out.println(fillCups(amount));
        String s = "love";
        System.out.println(s != "love");
        return;
    }
}
