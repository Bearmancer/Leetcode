package Q_0021_Merge_Two_Sorted_Lists;

/**
 * LeetCode: https://leetcode.com/problems/merge-two-sorted-lists/
 * NeetCode: https://neetcode.io/problems/merge-two-sorted-lists
 * LeetCode #21: Merge Two Sorted Lists
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