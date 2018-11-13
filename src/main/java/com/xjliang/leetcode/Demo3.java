package com.xjliang.leetcode;


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Demo3 {

    public static void main(String[] args) {

    }
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
