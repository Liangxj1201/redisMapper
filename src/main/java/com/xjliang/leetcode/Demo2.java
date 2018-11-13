package com.xjliang.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Demo2 {
    public static void rotate(int[] nums, int k) {
        int [] nums2 = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            if(i+k<=nums.length-1){
                nums2[i+k]=nums[i];
            }else{
                nums2[(i+k)%(nums.length-1)-1] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums2));
    }

    public static void main(String[] args) {
        int [] nums = {1,2,3,4,5,6,7};
        int k=3;
        rotate(nums,k);
        System.out.println(Arrays.toString(nums));
        Map<String,String> map = new HashMap<>();
        map.put("pro","hello");
        map.put("Pro","hello2");
        System.out.println(map.get("pro"));
        System.out.println(map.get("Pro"));

    }
}
