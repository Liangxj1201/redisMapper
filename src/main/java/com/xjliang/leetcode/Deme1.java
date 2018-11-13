package com.xjliang.leetcode;

public class Deme1 {

    public static void main(String[] args) {
        int []num = {0,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(num));
    }

    public static int removeDuplicates(int [] nums){
        if(nums.length==0){
            return 0;
        }
        int number = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=nums[number]){
                number++;
                nums[number] = nums[i];
            }
        }
        return number;
    }
}
