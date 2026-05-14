package Q_0206_Reverse_Linked_List;

import java.util.ArrayList;

/**
 * <h1><a href="https://leetcode.com/problems/reverse-linked-list/">LeetCode #206: Reverse Linked List</a></h1>
 * <p><b>Difficulty:</b> Easy &nbsp;|&nbsp; <b>Category:</b> Linked List, Recursion</p>
 *
 * <h2>Problem</h2>
 * <p>Given the <code>head</code> of a singly linked list, reverse the list, and return <em>the reversed list</em>.</p>
 *
 * <h2>Examples</h2>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> head = [1,2,3,4,5]
 * <strong>Output:</strong> [5,4,3,2,1]
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> head = [1,2]
 * <strong>Output:</strong> [2,1]
 * </pre>
 * <h3>Example 3:</h3>
 * <pre>
 * <strong>Input:</strong> head = []
 * <strong>Output:</strong> []
 * </pre>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>The number of nodes in the list is the range <code>[0, 5000]</code>.</li>
 *   <li><code>-5000 &lt;= Node.val &lt;= 5000</code></li>
 * </ul>
 *
 * <h2>Follow-up</h2>
 * <p>A linked list can be reversed either iteratively or recursively. Could you implement both?</p>
 */

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

