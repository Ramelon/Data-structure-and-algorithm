package algorithm;

import java.util.Arrays;

public class PrimAlgorithm {
	public static void main(String[] args) {
		char[] vertexs= { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int vertexSize = vertexs.length;
		int[][] edges = { { 0, 7, 0, 5, 0, 0, 0 }, { 7, 0, 8, 9, 7, 0, 0 }, { 0, 8, 0, 0, 5, 0, 0 },
				{ 5, 9, 0, 0, 15, 6, 0 }, { 0, 7, 5, 15, 0, 8, 9 }, { 0, 0, 0, 6, 8, 0, 11 },
				{ 0, 0, 0, 0, 9, 11, 0 } };
		// 生成一个图
		Graph graph = new Graph(vertexSize);
		// 最小生成树
		MinTree minTree = new MinTree();
		minTree.createGraph(graph, vertexSize, vertexs, edges);
		// 显示我们的邻接矩阵
		minTree.showGraph(graph);
		// 显示路径
		minTree.prim(graph, 3);
	}
}

class MinTree {
	/**
	 * 
	 * @param graph
	 *            一个图对象
	 * @param vertexSize
	 *            图中顶点个数
	 * @param vertex
	 *            图中顶点的值
	 * @param edges
	 *            图的权值(邻接矩阵)
	 */
	public void createGraph(Graph graph, int vertexSize, char[] vertexs, int[][] edges) {
		// 相当于手动创建
		for (int i = 0; i < vertexSize; i++) {
			graph.vertexs[i] = vertexs[i];
			for (int j = 0; j < vertexSize; j++) {
				graph.edges[i][j] = edges[i][j];
			}
		}
	}

	public void showGraph(Graph graph) {
		for (int[] edge : graph.edges) {
			System.out.println(Arrays.toString(edge));
		}
	}

	/**
	 * 
	 * @param graph
	 *            一个图
	 * @param v
	 *            当前我们开始顶点的下标
	 */
	public void prim(Graph graph, int v) {
		// 用来标记当前顶点是否被访问
		int visited[] = new int[graph.vertexSize];
		// 当前顶点要被记录成访问
		visited[v] = 1;
		// 用于存放未被遍历的顶点中与已被遍历顶点的最小权值的顶点
		int minV = -1;
		int minI = -1;
		// 寻找最小权值 并记录，默认为无穷大
		int minDistance = Integer.MAX_VALUE;
		// 循环遍历k个顶点，正好和之前的n-1次对应
		for (int k = 1; k < graph.vertexSize; k++) {
			// 我们要确定每一次生成子图中最短的一条路径 并 记录
			for (int i = 0; i < graph.vertexSize; i++) {
				for (int j = 0; j < graph.vertexSize; j++) {
					if(visited[i] == 1 && visited[j] == 0 && graph.edges[i][j] != 0 && graph.edges[i][j] < minDistance) {
						// 找到当前子图中最小路径并记录
						minDistance = graph.edges[i][j];
						// 记录最小路径的下标
						minV = i;
						minI = j;
					}
				}
			}
			System.out.println(graph.vertexs[minV] + "," + graph.vertexs[minI] + " " + minDistance);
			visited[minI] = 1;
			minDistance = Integer.MAX_VALUE;
		}
	}
}

class Graph {
	int vertexSize; // 存放顶点的个数
	char[] vertexs; // 存放顶点的数据
	int[][] edges; // 存放一个权值

	public Graph(int vertexSize) {
		// TODO Auto-generated constructor stub
		this.vertexSize = vertexSize;
		this.vertexs = new char[vertexSize];
		this.edges = new int[vertexSize][vertexSize];
	}
}