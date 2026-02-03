package Q_206_Reverse_Linked_List;

import java.util.ArrayList;

class Solution {
    public ListNode reverseList(ListNode head) {
        return iterativeSolution(head);
    }

    private ListNode arrayListSolution(ListNode head) {
        var curr = head;

        if (head == null ) {
            return null;
        }

        ArrayList<ListNode> numbers = new ArrayList<>();

        while (curr != null) {
            numbers.add(curr);
            curr = curr.next;
        }

        var headNode = numbers.getLast();
        var currentNode = headNode;

        for (int i = numbers.size() - 2; i >= 0; i--) {
            ListNode newNode = new ListNode(numbers.get(i).val);
            currentNode.next = newNode;
            currentNode = newNode;
        }

        return headNode;
    }


    //region - Dharma
    private ListNode recursiveSolution(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        var newHead = recursiveSolution(head.next);
        insertLast(newHead, head);

        return newHead;
    }

    // Inserts node at the end of the linked list pointed by 'head'
    // assumes head is not  null
    private void insertLast(ListNode head, ListNode node) {
         var curr = head;

         while (curr.next != null) {
             curr = curr.next;
         }

         curr.next = node;
         node.next = null;
    }

    // Theta(n) time and Theta(1) space
    // Go through each node and make the next ptr point to the previous node
    private ListNode iterativeSolution(ListNode head) {
        // If the list has 0 or 1 elements, we can return the list itself
        // as no reversing is needed
        if (head == null || head.next == null) return head;

        // Iterate through each element in the list and reverse the pointers
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        // At this point, curr is null
        // and prev points to the last node which is the new head
        return prev;
    }
    //endregion

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

