package algorithm;

import java.util.Arrays;

public class KruskalAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] vertexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int vertexSize = vertexs.length;
		int[][] edges = { { 0, 7, 0, 5, 0, 0, 0 }, { 7, 0, 8, 9, 7, 0, 0 }, { 0, 8, 0, 0, 5, 0, 0 },
				{ 5, 9, 0, 0, 15, 6, 0 }, { 0, 7, 5, 15, 0, 8, 9 }, { 0, 0, 0, 6, 8, 0, 11 },
				{ 0, 0, 0, 0, 9, 11, 0 } };

		// 创建一个图
		Graph graph = new Graph(vertexSize);
		// 定义kruskalMinTree
		KruskalMinTree kruskalMinTree = new KruskalMinTree();
		// 初始化参数
		int edgeNum = kruskalMinTree.createGraph(graph, vertexSize, vertexs, edges);
		// 遍历我们的邻接数组
		kruskalMinTree.showGraph(graph);
		// 输出我们的边
		// System.out.println(Arrays.toString(kruskalMinTree.getEdges(edgeNum, vertexs,
		// edges)));
		kruskalMinTree.Kruskal(edgeNum, vertexs, edges);
	}

}

class KruskalMinTree {
	public int createGraph(Graph graph, int vertexSize, char[] vertexs, int[][] edges) {
		int edgeNum = 0;
		// 初始化 vertexs edges
		for (int i = 0; i < vertexSize; i++) {
			graph.vertexs[i] = vertexs[i];
			for (int j = 0; j < vertexSize; j++) {
				graph.edges[i][j] = edges[i][j];
			}
		}

		// 找到所有边数
		for (int i = 0; i < vertexSize; i++) {
			for (int j = i + 1; j < vertexSize; j++) {
				if (edges[i][j] != 0) {
					edgeNum++;
				}
			}
		}
		return edgeNum;
	}

	public void showGraph(Graph graph) {
		for (int[] edge : graph.edges) {
			System.out.println(Arrays.toString(edge));
		}
	}

	/**
	 * 
	 * @param ch
	 *            传入的顶点
	 * @param vertexs
	 * @return 给一个字符，如果找到返回相应的下标，反之，返回-1
	 */
	public int getPosition(char ch, char[] vertexs) {
		for (int i = 0; i < vertexs.length; i++) {
			if (vertexs[i] == ch) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param edgeNum
	 *            边的数目
	 * @param vertexs
	 * @param edges
	 * @return 返回Edge [start=A, end=D, weight=5]
	 */
	public Edge[] getEdges(int edgeNum, char[] vertexs, int[][] edges) {
		int index = 0;
		// 存放边的数组 ，以Edge [start=A, end=D, weight=5]形式存放所有的边
		Edge[] edge = new Edge[edgeNum];
		for (int i = 0; i < vertexs.length; i++) {
			for (int j = i + 1; j < vertexs.length; j++) {
				if (edges[i][j] != 0) {
					edge[index++] = new Edge(vertexs[i], vertexs[j], edges[i][j]);
				}
			}
		}
		return edge;
	}

	/**
	 * 
	 * @param ends
	 *            对应终点的数组
	 * @param i
	 *            起点的下标
	 * @return
	 */
	public int getEnd(int[] ends, int i) {
		while (ends[i] != 0) {
			i = ends[i];
		}
		return i;
	}

	/**
	 * 
	 * @param edgeNum
	 *            边的数目
	 * @param vertexs
	 * @param edges
	 */
	public void Kruskal(int edgeNum, char[] vertexs, int[][] edges) {
		// 当做结果序列的索引
		int index = 0;
		// 存放结果数组
		Edge[] rets = new Edge[edgeNum];
		// 存放每个顶点在最小生成树中的终点
		int[] ends = new int[edgeNum];
		// 边的序列
		Edge[] edge = getEdges(edgeNum, vertexs, edges);
		System.out.println(Arrays.toString(edge));
		Arrays.sort(edge);
		// System.out.println(Arrays.toString(edge));
		// 如何判断是否形成回路
		for (int i = 0; i < edgeNum; i++) {
			// 给一个字符，如果找到返回相应的下标，反之，返回-1
			// 拿到第一个顶点的起始位置
			int p1 = getPosition(edge[i].start, vertexs); // 
			// 拿到第一个顶点的终点位置
			int p2 = getPosition(edge[i].end, vertexs); // 
			// 拿到第一个顶点的起始位置的终点
			int m = getEnd(ends, p1); // 
			// 拿到第一个顶点的终点位置的终点
			int n = getEnd(ends, p2); // 

			if (m != n) {
				ends[m] = n; // 
				rets[index++] = edge[i];
			}
		}
		System.out.println("最小生成树");
		for (int i = 0; i < index; i++) {
			System.out.println(rets[i]);
		}
	}
}

class Edge implements Comparable<Edge> {
	char start; // 边的起始位置
	char end; // 边的结束位置
	int weight;// 边的权值

	public Edge(char start, char end, int weight) {
		// TODO Auto-generated constructor stub
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight;
	}

	@Override
	public String toString() {
		return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]\n";
	}
}