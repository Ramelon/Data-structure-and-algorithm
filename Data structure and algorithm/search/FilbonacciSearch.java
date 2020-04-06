package search;

import java.util.Arrays;

public class FilbonacciSearch {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 5, 8, 10, 50, 222, 1222 };
		// System.out.println(Arrays.toString(Filbonacci()));
		System.out.println(Search(arr, 1222));
	}

	// ����쳲���������
	public static int[] Filbonacci() {
		int[] k = new int[20];
		k[0] = 1;
		k[1] = 1;
		for (int i = 2; i < k.length; i++) {
			k[i] = k[i - 1] + k[i - 2];
		}
		return k;
	}

	// ����
	public static int Search(int[] arr, int searchValue) {
		int low = 0; // ���λ��
		int high = arr.length - 1; // �ұ�λ��
		int mid = 0; // �м���
		int k = 0; // 쳲���������
		int F[] = Filbonacci(); // 쳲���������
		// �ҵ���쳲�����������һ���Դ��ڲ��ұ���Ԫ�ظ�������F[k];
		while (high > F[k] - 1) {
			k++;
		}

		// ��ԭ���ұ���չΪ����ΪF[n](���Ҫ����Ԫ�أ��򲹳��ظ����һ��Ԫ�أ�ֱ������F[n]��Ԫ��)
		int[] temp = Arrays.copyOf(arr, F[k]); // ������Ȳ��������Զ���0 {0,1} 3�� {0,1,0} => {0,1,1}
		for (int i = high + 1; i < temp.length; i++) {
			temp[i] = arr[high];
		}

		while (low <= high) {
			mid = low + F[k - 1] - 1;
			if (searchValue > temp[mid]) {
				low = mid + 1;
				//F[k] = F[k-1] + F[k-2]
				//1 1 2 3 5 8 13 21 
				//1 2 3  ����  4 5 6 7 8
				k -= 2;
			} else if (searchValue < temp[mid]) {
				high = mid - 1;
				k -= 1;
			} else {
				//��Ҫȷ���������±�
				if (mid <= high) {
					return mid;
				} else {
					return high;
				}
			}
		}
		return -1;
	}
}
