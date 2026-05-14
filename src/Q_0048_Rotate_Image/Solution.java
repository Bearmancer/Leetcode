package Q_0048_Rotate_Image;

import java.util.Arrays;

class Solution {
	/**
	 * <h2><a href="https://leetcode.com/problems/rotate-image/">LeetCode #48: Rotate
	 * Image</a></h2>
	 * <p><b>Difficulty:</b> Medium &nbsp;|&nbsp; <b>Category:</b> Math & Geometry</p>
	 * <p><b>NeetCode:</b> <a
	 * href="https://neetcode.io/problems/rotate-image">neetcode.io/problems/rotate-image</a></p>
	 *
	 * <h3>Problem</h3>
	 * <p>You are given an <code>n x n</code> 2D <code>matrix</code> representing an image, rotate
	 * the image by <strong>90</strong> degrees (clockwise).</p>
	 * <p>You have to rotate the image <a href="https://en.wikipedia.org/wiki/In-place_algorithm"
	 * target="_blank"><strong>in-place</strong></a>, which means you have to modify the input 2D
	 * matrix directly. <strong>DO NOT</strong> allocate another 2D matrix and do the rotation.</p>
	 * <p> </p>
	 * <p><strong class="example">Example 1:</strong></p>
	 * <img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg" style="width:
	 * 500px; height: 188px;"/>
	 * <pre>
	 * <strong>Input:</strong> matrix = [[1,2,3],[4,5,6],[7,8,9]]
	 * <strong>Output:</strong> [[7,4,1],[8,5,2],[9,6,3]]
	 * </pre>
	 * <p><strong class="example">Example 2:</strong></p>
	 * <img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg" style="width:
	 * 500px; height: 201px;"/>
	 * <pre>
	 * <strong>Input:</strong> matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
	 * <strong>Output:</strong> [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
	 * </pre>
	 * <p> </p>
	 * <p><strong>Constraints:</strong></p>
	 * <ul>
	 * <li><code>n == matrix.length == matrix[i].length</code></li>
	 * <li><code>1 &lt;= n &lt;= 20</code></li>
	 * <li><code>-1000 &lt;= matrix[i][j] &lt;= 1000</code></li>
	 * </ul>
	 */
	static void main() {
		int[][] matrix = new int[][]{{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}};
		int[][] matrixOdd = new int[][]{{15, 13, 2}, {14, 3, 4}, {12, 6, 8}};
		Solution s = new Solution();
		print2d(matrix);
		System.out.println("=====");
		s.rotate(matrix);
		print2d(matrix);

		System.out.println("===");
		print2d(matrixOdd);
		s.rotate(matrixOdd);
		System.out.println("====");
		print2d(matrixOdd);
	}

	private static void print2d(int[][] matrix) {
		for (int[] row : matrix) System.out.println(Arrays.toString(row));
	}

	public void rotate(int[][] matrix) {
		flipVertically(matrix);
		transpose(matrix);
	}

	private void transpose(int[][] matrix) {
		for (int r = 1 ; r < matrix.length ; r++) {
			for (int c = 0 ; c < r ; c++) {
				int temp = matrix[r][c];
				matrix[r][c] = matrix[c][r];
				matrix[c][r] = temp;
			}
		}
	}

	private void flipVertically(int[][] matrix) {
		int n = matrix.length;

		int startRow = 0;
		int endRow = n - 1;

		while (startRow <= endRow) {
			swapRows(matrix, startRow, endRow);
			startRow++;
			endRow--;
		}
	}

	private void swapRows(int[][] matrix, int row1, int row2) {
		for (int c = 0 ; c < matrix.length ; c++) {
			int temp = matrix[row1][c];
			matrix[row1][c] = matrix[row2][c];
			matrix[row2][c] = temp;
		}
	}

	public void rotateBrute(int[][] matrix) {
		int lastCol = matrix.length - 1;
		int lastRow = matrix.length - 1;

		// stage 1 - corners
		// step 1 -> (0,0) > lastind of row 1 (0,n-1)

		// store upper left as temp
		// upper left is safe to be overriden by lower left
		int temp = matrix[0][0];
		matrix[0][0] = matrix[lastRow][0];

		// lower left can be overwritten
		matrix[lastRow][0] = matrix[lastRow][lastCol];

		// lower right can be overwritten
		matrix[lastRow][lastCol] = matrix[0][lastCol];

		// upper right gets temp/upper left was reassigned
		matrix[0][lastCol] = temp;
	}
}