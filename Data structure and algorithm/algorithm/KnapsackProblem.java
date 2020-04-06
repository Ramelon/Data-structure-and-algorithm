package algorithm;

public class KnapsackProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] w = { 2, 3, 5, 7, 8 };
		int[] value = { 3, 4, 6, 8, 10 };
		int[] num = { 1, 2, 3, 4, 5 };
		int capacity = 12; // 5 5 2 8+8+3=19
		int n = w.length;
		// ZeroOneKnapsack(w, value, n, capacity);
		// completeKnapsack(w, value, n, capacity);
		moreKnapsack(w, value, n, capacity, num);
	}

	/**
	 * 
	 * @param w
	 *            ��Ʒ������
	 * @param value
	 *            ��Ʒ�ļ�ֵ
	 * @param n
	 *            ��Ʒ������
	 * @param capacity
	 *            ��Ʒ������
	 */
	public static void ZeroOneKnapsack(int[] w, int[] value, int n, int capacity) {
		// ��Ϊ��һ�е�һ���Ϊ0������Ҫ��1.
		// ��һ�к͵�һ�����Ƕ������õ���Ŀ����Ϊ�˸������01����
		int[][] v = new int[n + 1][capacity + 1];

		// ��¼·��
		int[][] path = new int[n + 1][capacity + 1];
		// ��Ϊv[0][j] �� v[i][0]������ Ĭ��Ϊ0
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < capacity + 1; j++) {
				if (w[i - 1] > j) {
					// �������������jС�ڵ�ǰ��Ʒ�����������i����Ʒ����װ��ɱ�����
					v[i][j] = v[i - 1][j];
				} else {
					// ��Ϊvalue�����0��ʼ������value[i-1],w[i-1]
					// v[i][j] = Math.max(v[i - 1][j], value[i - 1] + v[i - 1][j - w[i - 1]]);
					if (v[i - 1][j] < value[i - 1] + v[i - 1][j - w[i - 1]]) {
						v[i][j] = value[i - 1] + v[i - 1][j - w[i - 1]];
						path[i][j] = 1;
					} else {
						v[i][j] = v[i - 1][j];
					}
				}
			}
		}

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				if (v[i][j] < 10) {
					System.out.print(v[i][j] + "  ");
				} else {
					System.out.print(v[i][j] + " ");
				}
			}
			System.out.println();
		}

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				System.out.print(path[i][j] + "  ");
			}
			System.out.println();
		}

		int i = path.length - 1;
		int j = path[0].length - 1;
		while (i > 0 && j > 0) {
			if (path[i][j] == 1) {
				System.out.println("��Ʒ" + i + "���뱳��");
				// �����Ǻ�֮ǰһ�����ҷ��뱳�������Ʒ����ô�ҵ�����ҲҪ��Ӧ�ż��١�
				// ������˵������һֱΪ20�����˵�����٣���ô�ͻ��ظ���
				j -= w[i - 1];
			}
			i--;
		}
	}

	// 01������ÿ����Ʒֻ����һ��
	// ��ȫ������û����ʱ������һ����Ʒ�����ã�֪����������
	// ��ȫ���������ǵ�01��������Ϊʲô��
	// ��Ϊ��01���������ҵ�ǰ��Ʒ���Թ���ʱ����ô�ҽ�����Ƕ��Լ��� i-1�������������01������v[i - 1][j - w[i - 1]]
	// v[i][j] = Math.max(v[i - 1][j], value[i - 1] + v[i - 1][j - w[i - 1]]);
	// ��ȫ�������Լ���� �Լ��� i�������⣬v[i][j - w[i - 1]]
	public static void completeKnapsack(int[] w, int[] value, int n, int capacity) {
		// ��Ϊ��һ�е�һ���Ϊ0������Ҫ��1.
		// ��һ�к͵�һ�����Ƕ������õ���Ŀ����Ϊ�˸������01����
		int[][] v = new int[n + 1][capacity + 1];

		// ��¼·��
		int[][] path = new int[n + 1][capacity + 1];
		// ��Ϊv[0][j] �� v[i][0]������ Ĭ��Ϊ0
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < capacity + 1; j++) {
				if (w[i - 1] > j) {
					// �������������jС�ڵ�ǰ��Ʒ�����������i����Ʒ����װ��ɱ�����
					v[i][j] = v[i - 1][j];
				} else {
					// ��Ϊvalue�����0��ʼ������value[i-1],w[i-1]
					// v[i][j] = Math.max(v[i - 1][j], value[i] + v[i - 1][j - w[i - 1]]);
					if (v[i - 1][j] < value[i - 1] + v[i][j - w[i - 1]]) {
						v[i][j] = value[i - 1] + v[i][j - w[i - 1]];
						path[i][j] = 1;
					} else {
						v[i][j] = v[i - 1][j];
					}
				}
			}
		}

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				if (v[i][j] < 10) {
					System.out.print(v[i][j] + "  ");
				} else {
					System.out.print(v[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println("path·����");
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				System.out.print(path[i][j] + "  ");
			}
			System.out.println();
		}

		int i = path.length - 1;
		int j = path[0].length - 1;
		while (j > 0) {
			if (path[i][j] == 1) {
				// �����Ǻ�֮ǰһ�����ҷ��뱳�������Ʒ����ô�ҵ�����ҲҪ��Ӧ�ż��١�
				// ������˵������һֱΪ20�����˵�����٣���ô�ͻ��ظ���
				System.out.println("��Ʒ" + i + "���뱳��");
				if ((j - w[i - 1]) >= w[i - 1]) {
					System.out.println("��Ʒ" + i + "���뱳��");
					j -= w[i - 1];
				}
				j -= w[i - 1];
			}

			if (i == 1) {
				i = 1;
			} else {
				i--;
			}
		}
	}

	// ���ر�������������������Ƶģ����Ǹ�һ��num���� �涨����
	public static void moreKnapsack(int[] w, int[] value, int n, int capacity, int[] num) {
		// ��Ϊ��һ�е�һ���Ϊ0������Ҫ��1.
		// ��һ�к͵�һ�����Ƕ������õ���Ŀ����Ϊ�˸������01����
		int[][] v = new int[n + 1][capacity + 1];

		// ��¼·��
		int[][] path = new int[n + 1][capacity + 1];
		// ��Ϊv[0][j] �� v[i][0]������ Ĭ��Ϊ0
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < capacity + 1; j++) {
				if (w[i - 1] > j) {
					// �������������jС�ڵ�ǰ��Ʒ�����������i����Ʒ����װ��ɱ�����
					v[i][j] = v[i - 1][j];
				} else {
					// ���ر���������Ҫ֪����ǰ��Ʒ����ܹ��򼸴�
					// num[i - 1] �涨����Ʒ����ö���
					// j / w[i - 1] �ұ���������ö���
					int maxNumber = Math.min(num[i - 1], j / w[i - 1]);
					for (int k = 0; k < maxNumber + 1; k++) {
						if (v[i - 1][j] < k * value[i - 1] + v[i - 1][j - k * w[i - 1]]) {
							v[i][j] = k * value[i - 1] + v[i - 1][j - k * w[i - 1]];
							path[i][j] = 1;
						} else {
							v[i][j] = v[i - 1][j];
						}
					}
				}
			}
		}

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				if (v[i][j] < 10) {
					System.out.print(v[i][j] + "  ");
				} else {
					System.out.print(v[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println("path·����");
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				System.out.print(path[i][j] + "  ");
			}
			System.out.println();
		}

		int i = path.length - 1;
		int j = path[0].length - 1;
		while (j > 0) {
			if (path[i][j] == 1) {
				// �����Ǻ�֮ǰһ�����ҷ��뱳�������Ʒ����ô�ҵ�����ҲҪ��Ӧ�ż��١�
				// ������˵������һֱΪ20�����˵�����٣���ô�ͻ��ظ���
				System.out.println("��Ʒ" + i + "���뱳��");
				if ((j - w[i - 1]) >= w[i - 1]) {
					System.out.println("��Ʒ" + i + "���뱳��");
					j -= w[i - 1];
				}
				j -= w[i - 1];
			}

			if (i == 1) {
				i = 1;
			} else {
				i--;
			}
		}
	}
}

// 01������·��û������
// ��ȫ������·��������
// ���ر�����·����ȷ��
// �������˼����
// ���Ҳ����˼��Ϊʲô���ر�����ô���������Լ�����һ�¹�ʽ����ȷ���
