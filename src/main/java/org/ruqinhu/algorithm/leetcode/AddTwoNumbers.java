package org.ruqinhu.algorithm.leetcode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 优化的点:
 * 1.可以不抽出共通的循环逻辑，而是都在控制位移的大循环中处理
 *
 */
public class AddTwoNumbers {

    class Solution {
        public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
            ListNode first = null;
            ListNode ret = null;
            boolean flag = false;
            boolean isFirst = true;
            if (l1 == null || l2 == null) {
                return l1==null?l2:l1;
            }
            while (l1 != null && l2 != null) {
                if (isFirst) {
                    ret = new ListNode(0);
                    first = ret;
                    isFirst = false;
                }
                if (flag) {
                    ret.val += 1;
                    flag = false;
                }
                ret.val += l1.val;
                ret.val += l2.val;
                l1 = l1.next;
                l2 = l2.next;

                if (ret.val > 9) {
                    ret.val = ret.val % 10;
                    flag = true;
                }

                if(l1 != null && l2 != null) {
                    ret.next = new ListNode(0);
                    ret = ret.next;
                }

            }

            if (l1 == null && l2 == null) {
                if (flag) {
                    ret.next = new ListNode(1);
                    ret = ret.next;
                }
                return first;
            }


            while (l1 != null) {
                ret.next = new ListNode(0);
                ret = ret.next;

                ret.val = l1.val;
                l1 = l1.next;

                if (flag) {
                    ret.val += 1;
                    flag = false;
                }

                if (ret.val > 9) {
                    flag = true;
                    ret.val = ret.val % 10;
                }
            }
            while (l2 != null) {
                ret.next = new ListNode(0);
                ret = ret.next;

                ret.val = l2.val;
                l2 = l2.next;

                if (flag) {
                    ret.val += 1;
                    flag = false;
                }

                if (ret.val > 9) {
                    flag = true;
                    ret.val = ret.val % 10;
                }
            }


            if (flag) {
                ret.next = new ListNode(1);
            }

            return first;
        }



        

        class ListNode {
            int val;
            ListNode next;

            ListNode(int x) {
                val = x;
            }
        }
    }
}
