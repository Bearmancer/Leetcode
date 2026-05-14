package Q_0207_Course_Schedule;

/**
 * <h1><a href="https://leetcode.com/problems/course-schedule/">LeetCode #207: Course Schedule</a></h1>
 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> Graphs</p>
 *
 * <h2>Problem</h2>
 * <p>There are a total of <code>numCourses</code> courses you have to take, labeled from <code>0</code> to <code>numCourses - 1</code>. You are given an array <code>prerequisites</code> where <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that you <strong>must</strong> take course <code>b<sub>i</sub></code> first if you want to take course <code>a<sub>i</sub></code>.</p>
 * <ul>
 * <li>For example, the pair <code>[0, 1]</code>, indicates that to take course <code>0</code> you have to first take course <code>1</code>.</li>
 * </ul>
 * <p>Return <code>true</code> if you can finish all courses. Otherwise, return <code>false</code>.</p>
 * <h3>Example 1:</h3>
 * <pre>
 * <strong>Input:</strong> numCourses = 2, prerequisites = [[1,0]]
 * <strong>Output:</strong> true
 * <strong>Explanation:</strong> There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * </pre>
 * <h3>Example 2:</h3>
 * <pre>
 * <strong>Input:</strong> numCourses = 2, prerequisites = [[1,0],[0,1]]
 * <strong>Output:</strong> false
 * <strong>Explanation:</strong> There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * </pre>
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>1 &lt;= numCourses &lt;= 2000</code></li>
 * <li><code>0 &lt;= prerequisites.length &lt;= 5000</code></li>
 * <li><code>prerequisites[i].length == 2</code></li>
 * <li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
 * <li>All the pairs prerequisites[i] are <strong>unique</strong>.</li>
 * </ul>
 */

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
            return false;
    }
}
