package algorithm;

import java.util.Arrays;

public class DijkstraAlgothrim {
	public static void main(String[] args) {
		int min = 32767;
		char[] vertexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int[][] matrix = { { 0, 7, min, 5, min, min, min }, { 7, 0, 8, 9, 7, min, min },
				{ min, 8, 0, min, 5, min, min }, { 5, 9, min, 0, 15, 6, min }, { min, 7, 5, 15, 0, 8, 9 },
				{ min, min, min, 6, 8, 0, 11 }, { min, min, min, min, 9, 11, 0 } };
		Graph2 graph2 = new Graph2(vertexs, matrix);
		graph2.showGraph();

		Dijkstra dijkstra = new Dijkstra();
		dijkstra.DijkstraDemo(vertexs, matrix, 1);
	}
}

class Dijkstra {
	/**
	 * 功能:查找最短路径
	 * 
	 * @param vertexs
	 *            顶点集合
	 * @param matrix
	 *            边权值集合
	 * @param start
	 *            原点的起始位置
	 */
	public void DijkstraDemo(char[] vertexs, int[][] matrix, int start) {
		// 顶点个数
		int n = matrix.length;
		// 保存起始点(原点)到各个顶点的最短路径
		int[] bis = new int[n];
		// 标记当前顶点是否被访问
		int[] isVisited = new int[n];
		// 记录最短路径的顶点
		String[] path = new String[n];
		// 初始化标记路径，我们假设一开始原点可以走过所有的路径。
		// [B-->A, B-->B, B-->C, B-->D, B-->E, B-->F, B-->G]
		for (int i = 0; i < n; i++) {
			path[i] = new String(vertexs[start] + "-->" + vertexs[i]);
		}
		// 第一个顶点已经被访问，且路径为0;
		bis[start] = 0;
		isVisited[start] = 1;
		// 这里我们要执行n-1次对于所以顶点来说，因为传进来的原点，自己访问自己为0，也就是访问除原点外的所有顶点的最短路径
		for (int i = 1; i < n; i++) {
			// 当前尾部的路径下标
			int u = -1;
			// 寻找最小的权值，记录，默认是无穷大
			int minDis = Integer.MAX_VALUE;
			// 找到当前原点到所有顶点中的最短的路径，并记录下标，这里的下标就是当前尾部的路径下标
			for (int j = 0; j < n; j++) {
				if (isVisited[j] == 0 && matrix[start][j] < minDis) {
					minDis = matrix[start][j];
					u = j;
				}
			}
			System.out.println("minDis="+minDis);
			System.out.println("u="+u);
			bis[u] = minDis;
			isVisited[u] = 1;

			// 现在我们以u为中介(中间点),找到最短路径w(u,v),此时就能得到我们的w(s,v)的最短路径
			for (int k = 0; k < n; k++) {
				// dis[u] + matrix[u][i] < matrix[start][i]
				// 如果存在一条w(u,v)的边,那么s到v的一条新路径为dis[u] + w(u,v);
				// 如果这个值比当前的dis(v)小那么交替。
				if (isVisited[k] == 0 && matrix[start][u] + matrix[u][k] < matrix[start][k]) {
					matrix[start][k] = matrix[start][u] + matrix[u][k];
					path[k] = path[u] + "-->" + vertexs[k];
				}
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.println(path[i] + "的最短路径为:" + bis[i]);
		}
	}
}

class Graph2 {
	private char[] vertexts; // 存放顶点
	private int[][] matrix; // 邻接数组(矩阵)

	public Graph2(char[] vertexs, int[][] matrix) {
		// TODO Auto-generated constructor stub
		this.vertexts = vertexs;
		this.matrix = matrix;
	}

	public void showGraph() {
		for (int[] m : matrix) {
			System.out.println(Arrays.toString(m));
		}
	}
}
