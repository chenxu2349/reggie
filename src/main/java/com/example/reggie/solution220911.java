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

    //2736
    public static int maximumSwap(int num) {
        if(num < 10) return num;
        int copy = num, len = 0;
        List<Integer> list = new ArrayList<>();
        while(copy > 0) {
            int k = copy % 10;
            list.add(k);
            copy /= 10;
        }
        int[] elements = new int[list.size()];
        Collections.reverse(list);
        for(int i = 0; i < list.size(); i++) elements[i] = list.get(i);
        int[] copyNums = elements;
        return 0;
    }

    public static int check(int a) {
        a++;
        return a;
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int len = nums.length, sum = 0;
        for(int i = 0; i < len; i++) sum += nums[i];
        if(sum % k != 0) return false;
        int subSum = sum / k, left = 0, right = len - 1;
        Arrays.sort(nums);
        while(left < right) {
            if(nums[right] > subSum) return false;
            if(nums[right] == subSum) right--;
            if(nums[left] + nums[right] > subSum) return false;
            if(nums[left] + nums[right] == subSum) {left++; right--;}
            if(nums[left] + nums[right] < subSum) {
                int flag = left, newSum = nums[right];
                while(newSum < subSum) {
                    newSum += nums[flag];
                    flag++;
                }
                if(newSum == subSum) {right--; left = flag;}
                if(newSum > subSum) return false;
            }
        }
        return true;
    }

    public static String decodeString(String s) {
        int len = s.length(), subSum = 0, left = 0, right = 0;
        int leftK = 0, rightK = 0;  //记录左右括号数
        if(isPureChars(s)) return s;
        char[] arr = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < len; i++) {
            char c = arr[i];
            if(c >= 'a' && c <= 'z') ans.append(c);
            if(c >= '0' && c <= '9') {  //计算左括号之前的数字大小
                subSum = subSum * 10 + (c - '0');
            }
            //如果出现左括号，找到与之匹配的右括号，将中间的子字符串作为参数递归传给原函数
            if(c == '[') {
                left = i;
                leftK++;
                for(int j = i + 1; j < len; j++) {
                    if(arr[j] == '[') leftK++;
                    if(arr[j] == ']') {
                        rightK++;
                        if(leftK == rightK) {right = j; break;}
                    }
                }
                String decodeSubString = decodeString(s.substring(left + 1, right));
                leftK = 0; rightK = 0;
                //将递归获得的解码后字串拼接在答案后
                while(subSum > 0) {
                    ans.append(decodeSubString);
                    subSum--;
                }
                i = right;
            }
        }
        return new String(ans.toString());
    }

    public static boolean isPureChars(String s) {
        int len = s.length();
        for(int i = 0; i < len; i++) {
            if(s.charAt(i) < 'a' || s.charAt(i) > 'z') return false;
        }
        return true;
    }

    //map用于剪枝，记录已经搜索过的s2字符串，为什么记录s2，因为s1是基本的，而s2是多变的，记录s2防止重复计算
    //public static Map<String, Integer> map = new HashMap<>();
    public static int kSimilarity(String s1, String s2) {
        int len = s1.length(), subIndex = 0, k = 0;
        if (s1.equals(s2) || len == 1) return 0;
        if (map.get(s2) != null) return map.get(s2);
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            char c1 = str1[i];
            if (str1[i] != str2[i]) {
                for (int j = i; j < len; j++) {
                    char c2 = str2[j];
                    if (c2 == c1) {
                        //交换搜索到的合法字符
                        char t = str2[i];
                        str2[i] = str2[j];
                        str2[j] = t;
                        k++;
                        subIndex = i + 1;
                        //递归
                        String newS1 = new String(Arrays.copyOfRange(str1, subIndex, len));
                        String newS2 = new String(Arrays.copyOfRange(str2, subIndex, len));
                        k += kSimilarity(newS1, newS2);
                        list.add(k);
                        //恢复
                        k = 0;
                        t = str2[i];
                        str2[i] = str2[j];
                        str2[j] = t;
                    }
                }
                break;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int key : list) ans = Math.min(ans, key);
        //记录当前s2的最优值
        map.put(s2, ans);
        return ans;
    }

    static List<String> ans = new LinkedList<>();
    static List<List<String>> nums;
    static Map<String, Integer> map = new HashMap<>();
    public static List<String> letterCombinations(String digits) {
        int len = digits.length();
        if(len == 0) return ans;
        nums = new ArrayList<>();
        nums.add(null); nums.add(null); //0,1
        List<String> t = new LinkedList<>();t.add("a");t.add("b");t.add("c");nums.add(t); //2
        t = new LinkedList<>();t.add("d");t.add("e");t.add("f");nums.add(t);//3
        t = new LinkedList<>();t.add("g");t.add("h");t.add("i");nums.add(t);//4
        t = new LinkedList<>();t.add("j");t.add("k");t.add("l");nums.add(t);//5
        t = new LinkedList<>();t.add("m");t.add("n");t.add("o");nums.add(t);//6
        t = new LinkedList<>();t.add("p");t.add("q");t.add("r");t.add("s");nums.add(t);//7
        t = new LinkedList<>();t.add("t");t.add("u");t.add("v");nums.add(t);//8
        t = new LinkedList<>();t.add("w");t.add("x");t.add("y");t.add("z");nums.add(t);//9
        if(len == 1) return nums.get(digits.charAt(0) - '0');
        return dfs(digits);
    }

    public static List<String> dfs(String s) {
        int len = s.length();
        List<String> valid = new LinkedList<>();
        if(len == 0) return valid;
        if(len == 1) return nums.get(s.charAt(0) - '0');
        int num = s.charAt(0) - '0';
        for(String key : nums.get(num)) {
            StringBuilder str = new StringBuilder(key);
            List<String> sublist = dfs(s.substring(1, len));
            if(sublist.size() == 0) {
                if(true) {
                    map.put(str.toString(), 1);
                    ans.add(str.toString());
                    valid.add(str.toString());
                }
            }
            for(String key1 : sublist) {
                str.append(key1);
                //去重
                if(true) {
                    map.put(str.toString(), 1);
                    ans.add(str.toString());
                    valid.add(str.toString());
                    str = new StringBuilder(key);
                }
            }
        }
        return valid;
    }

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
//        //1.
//        int[] arr = new int[]{8154,9139,8194,3346,5450,9190,133,8239,4606,8671,8412,6290};
//        System.out.println(mostFrequentEven(arr));
//        //2.
//        System.out.println(partitionString("abcadfacc"));
//        //3.
//        int[][] intervals = new int[][]{{441459,446342},{801308,840640},{871890,963447},{228525,336985},{807945,946787},{479815,507766},{693292,944029},{751962,821744}};
//        System.out.println("minGroups:" + minGroups(intervals));
//        //
//        List<Integer> list1 = new LinkedList<>();
//        list1.add(2);list1.add(1);list1.add(1);
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(2);list2.add(1);list2.add(1);
//        System.out.println(list1.equals(list2));
//        //
//        System.out.println(maximumSwap(2736));
//        //
//        System.out.println("a:" + check(3));
//        //
//        int[] nums = new int[]{4,4,6,2,3,8,10,2,10,7};
//        System.out.println(canPartitionKSubsets(nums, 4));
//        //
//        System.out.print("字符串解码：");
//        System.out.println(decodeString("3[a]"));
        //
        //"baaabaabbbabbbabaaab"
        //"babbbbbaabaabaaaabba"
        //System.out.println("k相似度：" + kSimilarity("baaabaabbbcabbbabaab", "bcabbbbbabaabaaaabba"));

        //电话号码
        List<String> letterCombinations = letterCombinations("2322");
        for(String k : letterCombinations) System.out.println(k);


        long l2 = System.currentTimeMillis();
        System.out.println("运行时长:" + (l2-l1) + "ms");
    }
}





























