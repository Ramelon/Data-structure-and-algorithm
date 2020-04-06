package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

	// �洢����
	private ArrayList<String> vertexList;
	// ������Ҫһ����ά���飬 �洢��Ӧ���ڽӾ���
	private int[][] edges;
	// �ߵĸ���
	private int numofEdges;
	// �жϽ���Ƿ񱻷��ʹ�
	private static boolean[] isVisited;
	
	public static void main(String[] args) {
		int n = 8;
		Graph graph = new Graph(n);

		// ��Ӷ���
		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.insertVertex("D");
		graph.insertVertex("E");
		graph.insertVertex("F");
		graph.insertVertex("G");
		graph.insertVertex("H");
		// ��ӱ�
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
		
		
		System.out.println("������ȱ���");
		graph.dfs();
		System.out.println("\n������ȱ���");
		graph.bfs();
	}

	// n: ����
	public Graph(int n) {
		// TODO Auto-generated constructor stub
		vertexList = new ArrayList<String>();
		edges = new int[n][n];
		numofEdges = 0;
		isVisited = new boolean[n];
	}

	// �����Ӷ���
	public void insertVertex(String vertxt) {
		vertexList.add(vertxt);
	}

	// �����
	// e1,e2 �ߵ��±�
	// weight ���������ߵ�Ȩֵ 0�Ļ������ޱߣ� 1 �б�
	public void insertEdges(int e1, int e2, int weight) {
		edges[e1][e2] = weight;
		edges[e2][e1] = weight;
		numofEdges++;
	}

	// ���ؽ��ĸ���
	public int getNumOfVertex() {
		return vertexList.size();
	}

	// ���رߵĸ���
	public int getNumOfEdges() {
		return numofEdges;
	}

	// ���ڽӾ��� ��ȡ��Ӧ�±��ֵ
	public int getWeight(int e1, int e2) {
		return edges[e1][e2];
	}

	public String getValueByIndex(int index) {
		return vertexList.get(index);
	}

	// ��ʾ���ǵľ���
	public void showGraph() {
		for (int[] e : edges) {
			System.out.println(Arrays.toString(e));
		}
	}

	// ��ȡ��һ���ڽӵ���±�
	// ���Լ��ж�����Ҫ��һ��������������һ���ڽӵ㣬ͬʱ���Ƿ��������±�
	public int getFirstNeighbor(int index) {
		for (int i = 0; i < vertexList.size(); i++) {
			if (edges[index][i] > 0) {
				return i;
			}
		}
		return -1;
	}

	// ����ǰһ���ڽӽڵ���±� ����ȡ��һ���ڽӽ����±�
	// e1. e2 ��������
	public int getNextNeighbor(int e1, int e2) {
		for (int i = e2 + 1; i < vertexList.size(); i++) {
			if (edges[e1][i] > 0) {
				return i;
			}
		}
		return -1;
	}

	// ������ȱ�����ʵ�֡�
	// ��1�����ʶ���v��
	// ��2�����δ�v��δ�����ʵ��ڽӵ�w��������ͼ����������ȱ�����ֱ��ͼ�к�v��·��
	// ��ͨ�Ķ��㶼�����ʣ�
	// ��3������ʱͼ�����ж���δ�����ʣ����һ��δ�����ʵĶ�����������½��������
	// �ȱ�����ֱ��ͼ�����ж���������ʹ�Ϊֹ��
	public void dfs(boolean[] isVisited, int index) {
		System.out.print(getValueByIndex(index) + "->");
		// ����������ǰ��㣬˵�����Ѿ����ʹ�
		isVisited[index] = true;
		// ��ȡ�ڽӵ���±�
		int w = getFirstNeighbor(index);
		while (w != -1) {
			if (!isVisited[w]) {
				dfs(isVisited, w);
			}
			w = getNextNeighbor(index, w);
		}
	}

	// ��3������ʱͼ�����ж���δ�����ʣ����һ��δ�����ʵĶ�����������½��������
	// �ȱ�����ֱ��ͼ�����ж���������ʹ�Ϊֹ��
	// ����dfs
	public void dfs() {
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]) {
				dfs(isVisited, i);
			}
		}
	}

	// ������ȱ���
	// 1����ͼ��ĳ������v����������v��
	// 2�����v����У������зǿ�ʱ������ִ�У������㷨������
	// ��ʱ���а������ǵ�ǰ���������ڽӽ�㡣
	// 3�������У�ȡ�ö�ͷ���u�������ڽӵ�w�����ڽӵ㲻���ڡ�
	// �ظ�����һ���ڽӵ㡣ֱ������Ϊ�ա�
	// 4�����ڽӵ�w���ڣ������ڽӵ�w��������ѷ��ʣ����w��
	// ���С��ظ�����3��
	public void bfs(boolean[] isVisited, int index) {
		// ȡ�ö�ͷ���u
		int headIndex;
		// �ڽӵ�w
		int w;
		// ������Ҫ������ģ�����
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		// �����ǰ���
		System.out.print(getValueByIndex(index) + "->");

		// ����Ѿ������ʣ�����Ҫ���
		isVisited[index] = true;
		// �����±�
		linkedList.add(index);

		// �����зǿ�ʱ������ִ�У������㷨������
		while (!linkedList.isEmpty()) {
			// ��ȡͷ����±�
			headIndex = linkedList.removeFirst();
			// ��ȡ�ڽӵ��±�
			w = getFirstNeighbor(headIndex);
			// 4�����ڽӵ�w���ڣ������ڽӵ�w��������ѷ��ʣ����w��
			// ���С��ظ�����3��
			while (!isVisited[w]) {
				System.out.print(getValueByIndex(w) + "->");
				isVisited[w] = true;
				linkedList.add(w);
			}
			// �ҵ���һ���ڽӵ�Ҫ������
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
