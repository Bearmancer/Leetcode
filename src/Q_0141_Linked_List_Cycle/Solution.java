package Q_0141_Linked_List_Cycle;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * LeetCode: https://leetcode.com/problems/linked-list-cycle/
 * NeetCode: https://neetcode.io/problems/linked-list-cycle
 * LeetCode #141: Linked List Cycle
 *
 * Given {@code head}, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the {@code next} pointer. Internally, {@code pos} is used to denote the index of the node that tail's {@code next} pointer is connected to. Note that {@code pos} is not passed as a parameter.
 *
 * Return {@code true} if there is a cycle in the linked list. Otherwise, return {@code false}.
 *
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 *
 * Example 2:
 *
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 *
 * Example 3:
 *
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 *
 *
 * Constraints:
 *
 * - The number of the nodes in the list is in the range {@code [0, 10^4]}.
 * - {@code -10^5 <= Node.val <= 10^5}
 * - {@code pos} is {@code -1} or a valid index in the linked-list.
 *
 *
 * Follow up: Can you solve it using {@code O(1)} (i.e. constant) memory?
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

    static void main() {
        System.out.println(twoPointerSolution(new ListNode(1)));
    }

    // space complexity - big theta(1)
    // time complexity - big theta(n)
    private static boolean twoPointerSolution(ListNode head) {
        var slowTracker = head;
        var fastTracker = head;

        while (fastTracker != null && fastTracker.next != null) {
            slowTracker = slowTracker.next;
            fastTracker = fastTracker.next.next;

            if (slowTracker == fastTracker) {
                return true;
            }
        }

        return false;
    }

    // time complexity - big theta(n) on average, Theta(n^2) in the worst case
    // space complexity - big theta (n)
    private static boolean hashSetSolution(ListNode head) {
        HashSet<ListNode> nodeHashSet = new HashSet<>();

        var curr = head;
        while (curr != null) {
            // contains() runs in Theta(1) on average and Theta(n) in the worst case
            if (nodeHashSet.contains(curr)) return true;

            nodeHashSet.add(curr);
            curr = curr.next;
        }

        return false;
    }

    // time complexity - big theta(n^2)
    // space complexity - big theta(n)
    private static boolean arrayListSolution(ListNode head) {
        var curr = head;
        ArrayList<ListNode> values = new ArrayList<>();

        while (curr != null) {
            if (containsReferenceEqual(curr, values)) {
                return true;
            }
            values.add(curr);
            curr = curr.next;
        }

        return false;
    }

    private static boolean containsReferenceEqual(ListNode node, ArrayList<ListNode> values) {
        for (ListNode currentNode : values) {
            if (currentNode == node) return true;
        }

        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}