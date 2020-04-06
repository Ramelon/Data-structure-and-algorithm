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

		// ����һ��ͼ
		Graph graph = new Graph(vertexSize);
		// ����kruskalMinTree
		KruskalMinTree kruskalMinTree = new KruskalMinTree();
		// ��ʼ������
		int edgeNum = kruskalMinTree.createGraph(graph, vertexSize, vertexs, edges);
		// �������ǵ��ڽ�����
		kruskalMinTree.showGraph(graph);
		// ������ǵı�
		// System.out.println(Arrays.toString(kruskalMinTree.getEdges(edgeNum, vertexs,
		// edges)));
		kruskalMinTree.Kruskal(edgeNum, vertexs, edges);
	}

}

class KruskalMinTree {
	public int createGraph(Graph graph, int vertexSize, char[] vertexs, int[][] edges) {
		int edgeNum = 0;
		// ��ʼ�� vertexs edges
		for (int i = 0; i < vertexSize; i++) {
			graph.vertexs[i] = vertexs[i];
			for (int j = 0; j < vertexSize; j++) {
				graph.edges[i][j] = edges[i][j];
			}
		}

		// �ҵ����б���
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
	 *            ����Ķ���
	 * @param vertexs
	 * @return ��һ���ַ�������ҵ�������Ӧ���±꣬��֮������-1
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
	 *            �ߵ���Ŀ
	 * @param vertexs
	 * @param edges
	 * @return ����Edge [start=A, end=D, weight=5]
	 */
	public Edge[] getEdges(int edgeNum, char[] vertexs, int[][] edges) {
		int index = 0;
		// ��űߵ����� ����Edge [start=A, end=D, weight=5]��ʽ������еı�
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
	 *            ��Ӧ�յ������
	 * @param i
	 *            �����±�
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
	 *            �ߵ���Ŀ
	 * @param vertexs
	 * @param edges
	 */
	public void Kruskal(int edgeNum, char[] vertexs, int[][] edges) {
		// ����������е�����
		int index = 0;
		// ��Ž������
		Edge[] rets = new Edge[edgeNum];
		// ���ÿ����������С�������е��յ�
		int[] ends = new int[edgeNum];
		// �ߵ�����
		Edge[] edge = getEdges(edgeNum, vertexs, edges);
		System.out.println(Arrays.toString(edge));
		Arrays.sort(edge);
		// System.out.println(Arrays.toString(edge));
		// ����ж��Ƿ��γɻ�·
		for (int i = 0; i < edgeNum; i++) {
			// ��һ���ַ�������ҵ�������Ӧ���±꣬��֮������-1
			// �õ���һ���������ʼλ��
			int p1 = getPosition(edge[i].start, vertexs); // 
			// �õ���һ��������յ�λ��
			int p2 = getPosition(edge[i].end, vertexs); // 
			// �õ���һ���������ʼλ�õ��յ�
			int m = getEnd(ends, p1); // 
			// �õ���һ��������յ�λ�õ��յ�
			int n = getEnd(ends, p2); // 

			if (m != n) {
				ends[m] = n; // 
				rets[index++] = edge[i];
			}
		}
		System.out.println("��С������");
		for (int i = 0; i < index; i++) {
			System.out.println(rets[i]);
		}
	}
}

class Edge implements Comparable<Edge> {
	char start; // �ߵ���ʼλ��
	char end; // �ߵĽ���λ��
	int weight;// �ߵ�Ȩֵ

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