package Q19_Remove_Nth_Node_From_Linked_List;

import java.util.ArrayList;

class Solution {
    private static int getSize(ListNode head) {
        var size = 0;
        var curr = head;

        while (curr != null) {
            size++;
            curr = curr.next;
        }

        return size;
    }

    // assume that 1 <= i <= size - 1
    // deletes the node at index 'i' in the list
    private static void deleteNode(ListNode head, int i) {
        // jump i-1 times to reach the node previous to the node which we need to delete
        ListNode curr = head;
        for (int j = 0; j < i - 1; j++) {
            curr = curr.next;
        }

        // Delete the node after curr
        curr.next = curr.next.next;
    }

    private static ListNode arrayListSolution(ListNode head, int n) {
        var curr = head;
        ArrayList<ListNode> values = new ArrayList<>();

        if (n == 0 && head.next == null) {
            return null;
        }

        while (curr != null) {
            values.add(curr);
            curr = curr.next;
        }

        values.get(values.size() - n - 1).next = values.get(values.size() - n + 1);

        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int k) {
        int size = getSize(head);

        // if k == size then the head node must be deleted
        if (k == size) return head.next;

        // find the index of the node to be deleted
        int i = size - k;

        // delete the node at index 'i' (i >= 1 => head won't change)
        deleteNode(head, i);
        return head;
    }

    public class ListNode {
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
