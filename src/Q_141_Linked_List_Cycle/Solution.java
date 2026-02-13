package Q_141_Linked_List_Cycle;

import java.util.ArrayList;
import java.util.HashSet;

public class Solution {
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