package org.ruqinhu.algorithm.leetcode;

import java.util.HashMap;

public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0) {
            return head;
        }
        ListNode first = head;
        HashMap<Integer, ListNode> map = new HashMap<>();
        int i = 1;
        map.put(i, first);
        while(first.next != null) {
            first = first.next;
            i++;
            map.put(i, first);
        }
        int rem = i - n + 1;
        ListNode frontListNode = map.get(rem - 1);
        if (frontListNode == null) {
            head = map.get(2);
            return head;
        }
        frontListNode.next = map.get(rem + 1);
        return head;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

}
