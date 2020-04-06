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
	 * ����:�������·��
	 * 
	 * @param vertexs
	 *            ���㼯��
	 * @param matrix
	 *            ��Ȩֵ����
	 * @param start
	 *            ԭ�����ʼλ��
	 */
	public void DijkstraDemo(char[] vertexs, int[][] matrix, int start) {
		// �������
		int n = matrix.length;
		// ������ʼ��(ԭ��)��������������·��
		int[] bis = new int[n];
		// ��ǵ�ǰ�����Ƿ񱻷���
		int[] isVisited = new int[n];
		// ��¼���·���Ķ���
		String[] path = new String[n];
		// ��ʼ�����·�������Ǽ���һ��ʼԭ������߹����е�·����
		// [B-->A, B-->B, B-->C, B-->D, B-->E, B-->F, B-->G]
		for (int i = 0; i < n; i++) {
			path[i] = new String(vertexs[start] + "-->" + vertexs[i]);
		}
		// ��һ�������Ѿ������ʣ���·��Ϊ0;
		bis[start] = 0;
		isVisited[start] = 1;
		// ��������Ҫִ��n-1�ζ������Զ�����˵����Ϊ��������ԭ�㣬�Լ������Լ�Ϊ0��Ҳ���Ƿ��ʳ�ԭ��������ж�������·��
		for (int i = 1; i < n; i++) {
			// ��ǰβ����·���±�
			int u = -1;
			// Ѱ����С��Ȩֵ����¼��Ĭ���������
			int minDis = Integer.MAX_VALUE;
			// �ҵ���ǰԭ�㵽���ж����е���̵�·��������¼�±꣬������±���ǵ�ǰβ����·���±�
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

			// ����������uΪ�н�(�м��),�ҵ����·��w(u,v),��ʱ���ܵõ����ǵ�w(s,v)�����·��
			for (int k = 0; k < n; k++) {
				// dis[u] + matrix[u][i] < matrix[start][i]
				// �������һ��w(u,v)�ı�,��ôs��v��һ����·��Ϊdis[u] + w(u,v);
				// ������ֵ�ȵ�ǰ��dis(v)С��ô���档
				if (isVisited[k] == 0 && matrix[start][u] + matrix[u][k] < matrix[start][k]) {
					matrix[start][k] = matrix[start][u] + matrix[u][k];
					path[k] = path[u] + "-->" + vertexs[k];
				}
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.println(path[i] + "�����·��Ϊ:" + bis[i]);
		}
	}
}

class Graph2 {
	private char[] vertexts; // ��Ŷ���
	private int[][] matrix; // �ڽ�����(����)

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
