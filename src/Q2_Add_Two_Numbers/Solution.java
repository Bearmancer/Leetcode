package Q2_Add_Two_Numbers;

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
