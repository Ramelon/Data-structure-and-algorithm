package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

	// 存储顶点
	private ArrayList<String> vertexList;
	// 我们需要一个二维数组， 存储对应的邻接矩阵
	private int[][] edges;
	// 边的个数
	private int numofEdges;
	// 判断结点是否被访问过
	private static boolean[] isVisited;
	
	public static void main(String[] args) {
		int n = 8;
		Graph graph = new Graph(n);

		// 添加顶点
		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.insertVertex("D");
		graph.insertVertex("E");
		graph.insertVertex("F");
		graph.insertVertex("G");
		graph.insertVertex("H");
		// 添加边
//		graph.insertEdges(0, 1, 1);
//		graph.insertEdges(0, 2, 1);
//		graph.insertEdges(0, 3, 1);
//		graph.insertEdges(1, 4, 1);
//		graph.insertEdges(2, 4, 1);
//		graph.insertEdges(3, 4, 1);
		graph.insertEdges(0, 1, 1);
		graph.insertEdges(0, 2, 1);
		graph.insertEdges(1, 3, 1);
		graph.insertEdges(1, 4, 1);
		graph.insertEdges(2, 5, 1);
		graph.insertEdges(2, 6, 1);
		graph.insertEdges(3, 7, 1);
		graph.insertEdges(4, 7, 1);
		graph.showGraph();
		
		
		System.out.println("深度优先遍历");
		graph.dfs();
		System.out.println("\n广度优先遍历");
		graph.bfs();
	}

	// n: 边数
	public Graph(int n) {
		// TODO Auto-generated constructor stub
		vertexList = new ArrayList<String>();
		edges = new int[n][n];
		numofEdges = 0;
		isVisited = new boolean[n];
	}

	// 如何添加顶点
	public void insertVertex(String vertxt) {
		vertexList.add(vertxt);
	}

	// 插入边
	// e1,e2 边的下标
	// weight 用来当做边的权值 0的话代表无边， 1 有边
	public void insertEdges(int e1, int e2, int weight) {
		edges[e1][e2] = weight;
		edges[e2][e1] = weight;
		numofEdges++;
	}

	// 返回结点的个数
	public int getNumOfVertex() {
		return vertexList.size();
	}

	// 返回边的个数
	public int getNumOfEdges() {
		return numofEdges;
	}

	// 把邻接矩阵 获取对应下标的值
	public int getWeight(int e1, int e2) {
		return edges[e1][e2];
	}

	public String getValueByIndex(int index) {
		return vertexList.get(index);
	}

	// 显示我们的矩阵。
	public void showGraph() {
		for (int[] e : edges) {
			System.out.println(Arrays.toString(e));
		}
	}

	// 获取第一个邻接点的下标
	// 我自己判断我想要那一个顶点来当做第一个邻接点，同时我们返回他的下标
	public int getFirstNeighbor(int index) {
		for (int i = 0; i < vertexList.size(); i++) {
			if (edges[index][i] > 0) {
				return i;
			}
		}
		return -1;
	}

	// 根据前一个邻接节点的下标 来获取下一个邻接结点的下标
	// e1. e2 他是坐标
	public int getNextNeighbor(int e1, int e2) {
		for (int i = e2 + 1; i < vertexList.size(); i++) {
			if (edges[e1][i] > 0) {
				return i;
			}
		}
		return -1;
	}

	// 深度优先遍历的实现。
	// （1）访问顶点v；
	// （2）依次从v的未被访问的邻接点w出发，对图进行深度优先遍历；直至图中和v有路径
	// 相通的顶点都被访问；
	// （3）若此时图中尚有顶点未被访问，则从一个未被访问的顶点出发，重新进行深度优
	// 先遍历，直到图中所有顶点均被访问过为止。
	public void dfs(boolean[] isVisited, int index) {
		System.out.print(getValueByIndex(index) + "->");
		// 如果我输出当前结点，说明我已经访问过
		isVisited[index] = true;
		// 获取邻接点的下标
		int w = getFirstNeighbor(index);
		while (w != -1) {
			if (!isVisited[w]) {
				dfs(isVisited, w);
			}
			w = getNextNeighbor(index, w);
		}
	}

	// （3）若此时图中尚有顶点未被访问，则从一个未被访问的顶点出发，重新进行深度优
	// 先遍历，直到图中所有顶点均被访问过为止。
	// 重载dfs
	public void dfs() {
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]) {
				dfs(isVisited, i);
			}
		}
	}

	// 广度优先遍历
	// 1、从图中某个顶点v出发，访问v。
	// 2、结点v入队列，当队列非空时，继续执行，否则算法结束。
	// 此时队列包含的是当前结点的所有邻接结点。
	// 3、出队列，取得队头结点u。查找邻接点w，若邻接点不存在。
	// 重复找下一个邻接点。直到队列为空。
	// 4、若邻接点w存在，访问邻接点w，并标记已访问，结点w入
	// 队列。重复步骤3。
	public void bfs(boolean[] isVisited, int index) {
		// 取得队头结点u
		int headIndex;
		// 邻接点w
		int w;
		// 我们需要队列来模拟操作
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		// 输出当前结点
		System.out.print(getValueByIndex(index) + "->");

		// 结点已经被访问，现在要标记
		isVisited[index] = true;
		// 存入下标
		linkedList.add(index);

		// 当队列非空时，继续执行，否则算法结束。
		while (!linkedList.isEmpty()) {
			// 获取头结点下标
			headIndex = linkedList.removeFirst();
			// 获取邻接点下标
			w = getFirstNeighbor(headIndex);
			// 4、若邻接点w存在，访问邻接点w，并标记已访问，结点w入
			// 队列。重复步骤3。
			while (!isVisited[w]) {
				System.out.print(getValueByIndex(w) + "->");
				isVisited[w] = true;
				linkedList.add(w);
			}
			// 我的下一个邻接点要出来。
			w = getNextNeighbor(headIndex, w);
		}
	}

	public void bfs() {
		isVisited = new boolean[getNumOfVertex()];
		for (int i = 0; i < getNumOfVertex(); i++) {
			if(!isVisited[i]) {	
				bfs(isVisited,i);
			}
		}
	}
}
