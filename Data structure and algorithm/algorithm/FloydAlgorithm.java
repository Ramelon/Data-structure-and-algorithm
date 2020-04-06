package algorithm;

import java.util.Arrays;

public class FloydAlgorithm {
	public static void main(String[] args) {
		int min = 32767;
		char[] vertexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int[][] matrix = { { 0, 7, min, 5, min, min, min }, { 7, 0, 8, 9, 7, min, min },
				{ min, 8, 0, min, 5, min, min }, { 5, 9, min, 0, 15, 6, min }, { min, 7, 5, 15, 0, 8, 9 },
				{ min, min, min, 6, 8, 0, 11 }, { min, min, min, min, 9, 11, 0 } };

		Grapg3 grapg3 = new Grapg3(vertexs, matrix);
		
		
		FloydDemo floydDemo = new FloydDemo();
		floydDemo.floyd(matrix);
		grapg3.showGraph();
	}
}

class FloydDemo {
	public void floyd(int[][] matrix) {
		// 遍历{ 'A', 'B', 'C', 'D', 'E', 'F', 'G' } 把其中每一个顶点当做中间节点
		for (int k = 0; k < matrix.length; k++) {
			// 矩阵，二维数据 对应的是matrix[i][j]
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					// G[i][j] = min( G[i][j], G[i][k]+G[k][j] )
					int temp = matrix[i][k] + matrix[k][j];
					if(temp < matrix[i][j]) {
						matrix[i][j] = temp;
					}
				}
			}
		}
	}
}

class Grapg3 {
	private char[] vertexs;
	private int[][] matrix;

	public Grapg3(char[] vertexs, int[][] matrix) {
		// TODO Auto-generated constructor stub
		this.vertexs = vertexs;
		this.matrix = matrix;
	}

	public void showGraph() {
		for (int[] m : matrix) {
			System.out.println(Arrays.toString(m));
		}
	}
}