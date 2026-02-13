package Q_21_Merge_Two_Sorted_Linked_List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return iterative(list1, list2);
    }

    private ListNode recursive(ListNode list1, ListNode list2) {
        // 1 -> 3 -> 5
        // 2 -> 3 -> 4 -> 5

        // 1 -> merge(3 -> 5, 2 -> 3 -> 4 -> 5)

        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // 1 elements
        if (list1.val < list2.val) {
            ListNode newHead = list1;
            newHead.next = recursive(list1.next, list2);
            return newHead;
        } else {
            ListNode newHead = list2;
            newHead.next = recursive(list2.next, list1);
            return newHead;
        }
    }

    private ListNode iterative(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode tracker1 = list1;
        ListNode tracker2 = list2;
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;

        while (tracker1 != null && tracker2 != null) {
            int val1 = tracker1.val;
            int val2 = tracker2.val;

            if (val1 < val2) {
                tail.next = tracker1;
                ListNode next = tracker1.next;
                tracker1.next = null;
                tracker1 = next;
            } else {
                tail.next = tracker2;
                ListNode next = tracker2.next;
                tracker2.next = null;
                tracker2 = next;
            }

            tail = tail.next;
        }

        if (tracker1 == null) tail.next = tracker2;
        else if (tracker2 == null) tail.next = tracker1;

        return dummyHead.next;
    }

    public class ListNode {
        ListNode next;
        int val;

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