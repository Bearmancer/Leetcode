package Q_0002_Add_Two_Numbers;

/**
 * <h1><a href="https://leetcode.com/problems/add-two-numbers/">LeetCode #2: Add Two Numbers</a></h1>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> Linked List, Math, Recursion</p>
 *
 * <h2>Problem</h2>
 * <p>You are given two <strong>non-empty</strong> linked lists representing two non-negative integers. The digits are stored in <strong>reverse order</strong>, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.</p>
 * <p>You may assume the two numbers do not contain any leading zero, except the number 0 itself.</p>
 *
 * <h2>Examples</h2>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> l1 = [2,4,3], l2 = [5,6,4]
 * <strong>Output:</strong> [7,0,8]
 * <strong>Explanation:</strong> 342 + 465 = 807.
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> l1 = [0], l2 = [0]
 * <strong>Output:</strong> [0]
 * </pre>
 * <h3>Example 3:</h3>
 * <pre>
 * <strong>Input:</strong> l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * <strong>Output:</strong> [8,9,9,9,0,0,0,1]
 * </pre>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in each linked list is in the range <code>[1, 100]</code>.</li>
 *   <li><code>0 &lt;= Node.val &lt;= 9</code></li>
 *   <li>It is guaranteed that the list represents a number that does not have leading zeros.</li>
 * </ul>
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
