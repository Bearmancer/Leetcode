package Q_0023_Merge_K_Sorted_Lists;

/**
 * <h1><a href="https://leetcode.com/problems/merge-k-sorted-lists/">LeetCode #23: Merge k Sorted Lists</a></h1>
 * <p><b>Difficulty:</b> Hard &nbsp;|&nbsp; <b>Category:</b> Linked List</p>
 *
 * <h2>Problem</h2>
 * <p>You are given an array of <code>k</code> linked-lists <code>lists</code>, each linked-list is sorted in ascending order.</p>
 * <p><em>Merge all the linked-lists into one sorted linked-list and return it.</em></p>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> lists = [[1,4,5],[1,3,4],[2,6]]
 * <strong>Output:</strong> [1,1,2,3,4,4,5,6]
 * <strong>Explanation:</strong> The linked-lists are:
 * [
 *   1-&gt;4-&gt;5,
 *   1-&gt;3-&gt;4,
 *   2-&gt;6
 * ]
 * merging them into one sorted linked list:
 * 1-&gt;1-&gt;2-&gt;3-&gt;4-&gt;4-&gt;5-&gt;6
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> lists = []
 * <strong>Output:</strong> []
 * </pre>
 * <h3>Example 3:</h3>
 * <pre>
 * <strong>Input:</strong> lists = [[]]
 * <strong>Output:</strong> []
 * </pre>
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>k == lists.length</code></li>
 * <li><code>0 &lt;= k &lt;= 10<sup>4</sup></code></li>
 * <li><code>0 &lt;= lists[i].length &lt;= 500</code></li>
 * <li><code>-10<sup>4</sup> &lt;= lists[i][j] &lt;= 10<sup>4</sup></code></li>
 * <li><code>lists[i]</code> is sorted in <strong>ascending order</strong>.</li>
 * <li>The sum of <code>lists[i].length</code> will not exceed <code>10<sup>4</sup></code>.</li>
 * </ul>
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return null;
    }
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
