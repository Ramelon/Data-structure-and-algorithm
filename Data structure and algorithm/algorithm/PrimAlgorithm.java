package algorithm;

import java.util.Arrays;

public class PrimAlgorithm {
	public static void main(String[] args) {
		char[] vertexs= { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int vertexSize = vertexs.length;
		int[][] edges = { { 0, 7, 0, 5, 0, 0, 0 }, { 7, 0, 8, 9, 7, 0, 0 }, { 0, 8, 0, 0, 5, 0, 0 },
				{ 5, 9, 0, 0, 15, 6, 0 }, { 0, 7, 5, 15, 0, 8, 9 }, { 0, 0, 0, 6, 8, 0, 11 },
				{ 0, 0, 0, 0, 9, 11, 0 } };
		// ����һ��ͼ
		Graph graph = new Graph(vertexSize);
		// ��С������
		MinTree minTree = new MinTree();
		minTree.createGraph(graph, vertexSize, vertexs, edges);
		// ��ʾ���ǵ��ڽӾ���
		minTree.showGraph(graph);
		// ��ʾ·��
		minTree.prim(graph, 3);
	}
}

class MinTree {
	/**
	 * 
	 * @param graph
	 *            һ��ͼ����
	 * @param vertexSize
	 *            ͼ�ж������
	 * @param vertex
	 *            ͼ�ж����ֵ
	 * @param edges
	 *            ͼ��Ȩֵ(�ڽӾ���)
	 */
	public void createGraph(Graph graph, int vertexSize, char[] vertexs, int[][] edges) {
		// �൱���ֶ�����
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
	 *            һ��ͼ
	 * @param v
	 *            ��ǰ���ǿ�ʼ������±�
	 */
	public void prim(Graph graph, int v) {
		// ������ǵ�ǰ�����Ƿ񱻷���
		int visited[] = new int[graph.vertexSize];
		// ��ǰ����Ҫ����¼�ɷ���
		visited[v] = 1;
		// ���ڴ��δ�������Ķ��������ѱ������������СȨֵ�Ķ���
		int minV = -1;
		int minI = -1;
		// Ѱ����СȨֵ ����¼��Ĭ��Ϊ�����
		int minDistance = Integer.MAX_VALUE;
		// ѭ������k�����㣬���ú�֮ǰ��n-1�ζ�Ӧ
		for (int k = 1; k < graph.vertexSize; k++) {
			// ����Ҫȷ��ÿһ��������ͼ����̵�һ��·�� �� ��¼
			for (int i = 0; i < graph.vertexSize; i++) {
				for (int j = 0; j < graph.vertexSize; j++) {
					if(visited[i] == 1 && visited[j] == 0 && graph.edges[i][j] != 0 && graph.edges[i][j] < minDistance) {
						// �ҵ���ǰ��ͼ����С·������¼
						minDistance = graph.edges[i][j];
						// ��¼��С·�����±�
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
	int vertexSize; // ��Ŷ���ĸ���
	char[] vertexs; // ��Ŷ��������
	int[][] edges; // ���һ��Ȩֵ

	public Graph(int vertexSize) {
		// TODO Auto-generated constructor stub
		this.vertexSize = vertexSize;
		this.vertexs = new char[vertexSize];
		this.edges = new int[vertexSize][vertexSize];
	}
}