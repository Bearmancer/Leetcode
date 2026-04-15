package Q_0143_Reorder_List;

import java.util.ArrayList;

/**
 * LeetCode: https://leetcode.com/problems/reorder-list/
 * NeetCode: https://neetcode.io/problems/reorder-list
 * LeetCode #143: Reorder List
 * You are given the head of a singly linked-list. The list can be represented as:
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 */

public class Solution {
/**
 * Implementation Notes (Standardized Deep-Dive)
 * =============================================
 *
 * Design rationale
 * ----------------
 * This solution prioritizes correctness first, then readability, then optimization.
 * The algorithmic strategy is encoded directly in the method-level control flow
 * and helper abstractions below.
 *
 * Pros
 * ----
 * - Clear step-by-step control flow and predictable behavior.
 * - Uses standard Java data structures and idiomatic patterns.
 * - Easy to validate with examples and edge-case reasoning.
 *
 * Cons
 * ----
 * - May not be the asymptotically optimal variant for every input shape.
 * - Can trade some runtime efficiency for readability and maintainability.
 * - Data-structure overhead (boxing/object metadata) may affect large inputs.
 *
 * Time complexity
 * ---------------
 * Let n be input size and m be auxiliary structure size where applicable.
 * Complexity depends on the selected control-flow path and operations below.
 * The dominant terms come from loops, recursion depth, sorting/heap operations,
 * and map/set lookups used in the implemented path.
 *
 * Space complexity
 * ----------------
 * Auxiliary space depends on helper data structures and recursion stack depth
 * used by this implementation path.
 *
 * Overhead discussion
 * -------------------
 * - Object allocation and collection growth can contribute constant-factor costs.
 * - Hash-based and tree-based containers trade memory for operational speed.
 * - Intermediate containers improve clarity but can add temporary allocation cost.
 *
 * Future optimizations
 * --------------------
 * - Replace full-order operations with top-k or partial-order variants when possible.
 * - Reduce temporary allocations by reusing buffers/containers.
 * - Consider primitive-specialized structures for hot paths to reduce boxing overhead.
 *
 * ASCII execution model
 * ---------------------
 * Input -> Preprocess -> Core Transform -> Optional Ordering/Selection -> Output
 *
 *   [raw input]
 *        |
 *        v
 *   [normalize/prepare]
 *        |
 *        v
 *   [main algorithm steps]
 *        |
 *        v
 *   [construct result]
 */

    /**
     * assume that list has least 3 elements
     * if list has an even number of elements, both left and right lists have equal number of nodes after split
     * if list has an odd number of elements, left list has one more element than the right list
     */
    private static ListNode splitAndReturnRightHead(ListNode head) {
        var slow = head;
        var fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split the list after slow and return slow.next as the rightHead
        var rightHead = slow.next;
        slow.next = null;
        return rightHead;
    }

    /**
     * reverses the list and returns the new head
     * assume that there is least one node in the list
     */
    private static ListNode reverse(ListNode head) {
        if (head.next == null) return head;

        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }

    /**
     * merges the left and right lists by alternating the nodes between the left and right lists
     * either left list has one more element than right list
     * or both have same number of nodes
     * The resulting list would still have the leftHead as its head
     */
    private static void mergeAlternatively(ListNode leftHead, ListNode rightHead) {
        var leftCurr = leftHead;
        var rightCurr = rightHead;
        var dummy = new ListNode();
        var tail = dummy;

        while (leftCurr != null && rightCurr != null) {
            // remove leftCurr node from the left list and add it at the end of the new list
            tail.next = leftCurr;
            tail = tail.next;
            leftCurr = leftCurr.next;
            tail.next = null;

            // remove rightCurr node from the right list and add it at the end of the new list
            tail.next = rightCurr;
            tail = tail.next;
            rightCurr = rightCurr.next;
            tail.next = null;
        }

        if (leftCurr != null) {
            tail.next = leftCurr;
            leftCurr.next = null;
        }
    }

    /**
     * this solution is incorrect because we can't get the nodes at a particular index
     * because we are reordering the list and changing the indices
     */
    private static void indexSolution(ListNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        int size = getSize(head);
        var dummy = new ListNode();
        var tail = dummy;

        var straightIndex = 0;
        var reverseIndex = size - 1;

        while (straightIndex < size / 2) {
            // Add node at index straightIndex at the end of the new list
            tail.next = getNode(head, straightIndex);
            tail = tail.next;

            // Add node at index reverseIndex at the end of the new list
            tail.next = getNode(head, reverseIndex);
            tail = tail.next;

            straightIndex++;
            reverseIndex--;
        }

        if (size % 2 == 1) {
            tail.next = getNode(head, straightIndex);
            tail = tail.next;

        }

        tail.next = null;


    }

    private static int getSize(ListNode head) {
        ListNode curr = head;
        int size = 0;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }

    private static ListNode getNode(ListNode head, int index) {
        ListNode curr = head;
        // jump index-1 number of times to reach the node at index 'index'
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public void reorderList(ListNode head) {
        twoPointerSolution(head);

    }

    //region Two-Pointer Solution
    // Theta(n) time
    // Theta(1) space
    private void twoPointerSolution(ListNode head) {
        if (head.next == null || head.next.next == null) return;

        // split the list into 2 parts
        ListNode leftHead = head;
        ListNode rightHead = splitAndReturnRightHead(head);

        // Reverse right list
        rightHead = reverse(rightHead);

        // Merge left and right lists alternatively
        mergeAlternatively(leftHead, rightHead);
    }
    //endregion

    //region Array List Solution

    /**
     * Theta(n) time complexity
     * Theta(n) space complexity
     */
    private void arrayListSolution(ListNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }

        var allNodes = new ArrayList<ListNode>();
        var curr = head;

        while (curr != null) {
            allNodes.add(curr);
            curr = curr.next;
        }

        var size = allNodes.size();
        var dummy = new ListNode();
        var tail = dummy;

        var straightIndex = 0;
        var reverseIndex = size - 1;

        while (straightIndex < size / 2) {
            // Add node at index straightIndex at the end of the new list
            tail.next = allNodes.get(straightIndex);
            tail = tail.next;

            // Add node at index reverseIndex at the end of the new list
            tail.next = allNodes.get(reverseIndex);
            tail = tail.next;

            straightIndex++;
            reverseIndex--;
        }

        if (size % 2 == 1) {
            tail.next = allNodes.get(straightIndex);
            tail = tail.next;

        }

        tail.next = null;
    }
    //endregion

    //region ListNode
    public static class ListNode {
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
    //endregion
}
