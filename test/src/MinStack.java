package Q_155_Min_Stack_Old;

import java.util.Stack;

/**
 * LeetCode: https://leetcode.com/problems/min-stack/
 * NeetCode: https://neetcode.io/problems/min-stack
 * LeetCode #155: Min Stack
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * <p>
 * Implement the {@code MinStack} class:
 * <p>
 * - {@code MinStack()} initializes the stack object.
 * - {@code void push(int val)} pushes the element {@code val} onto the stack.
 * - {@code void pop()} removes the element on the top of the stack.
 * - {@code int top()} gets the top element of the stack.
 * - {@code int getMin()} retrieves the minimum element in the stack.
 * <p>
 * You must implement a solution with {@code O(1)} time complexity for each
 * function.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top(); // return 0
 * minStack.getMin(); // return -2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * - {@code -2^31 <= val <= 2^31 - 1}
 * - Methods {@code pop}, {@code top} and {@code getMin} operations will always
 * be called on non-empty stacks.
 * - At most {@code 3 * 10^4} calls will be made to {@code push}, {@code pop},
 * {@code top}, and {@code getMin}.
 */

class MinStack {
	Stack<Long> stack = new Stack<>();

	public MinStack() {
	}

	public void push(int val) {
		int min = (stack.isEmpty() || val < this.getMin()) ? val : this.getMin();

	}

	public void pop() {
		stack.pop();
	}

	public int top() {
		return (int) (stack.peek() & 0xFFFFFFFF);
	}

	public int getMin() {
		return (int) (stack.peek() >> 32);
	}
}