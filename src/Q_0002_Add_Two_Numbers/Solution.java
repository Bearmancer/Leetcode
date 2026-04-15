package Q_0002_Add_Two_Numbers;

/**
 * LeetCode: https://leetcode.com/problems/add-two-numbers/
 * NeetCode: https://neetcode.io/problems/add-two-numbers
 * LeetCode #2: Add Two Numbers
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 * Example 1:
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 *
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 *
 * Constraints:
 *
 * - The number of nodes in each linked list is in the range {@code [1, 100]}.
 * - {@code 0 <= Node.val <= 9}
 * - It is guaranteed that the list represents a number that does not have leading zeros.
 */

public class Solution {
    // Theta(max(m, n)) time complexity
    // Theta(1) space complexity
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        var carryForward = 0;

        // Keep going forward as long as there is atleast 1 digit
        while (curr1 != null || curr2 != null) {
            // Get the digits
            // If a node is null, it means we reached its end and its digit can be taken as 0
            var digit1 = curr1 != null ? curr1.val : 0;
            var digit2 = curr2 != null ? curr2.val : 0;

            // Get the sum
            var sum = digit1 + digit2 + carryForward;

            // Calculate the sum digit and carry
            carryForward = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;

            // Update curr1 and curr2 based on whether they are null
            curr1 = curr1 != null ? curr1.next : null;
            curr2 = curr2 != null ? curr2.next : null;
        }

        // At this point if carry != 0, it needs to be added at the end of the list
        tail.next = carryForward == 1 ? new ListNode(1) : null;

        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
