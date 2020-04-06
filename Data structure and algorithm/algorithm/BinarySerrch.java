package algorithm;

public class BinarySerrch {
	public static void main(String[] args) {
		int arr[] = { 1, 5, 55, 61, 124, 165, 999 };
		int index = binarySearchDemo(arr, 111);
		if(index == -1) {
			System.out.println("δ�ҵ�����ҵ�ֵ");
		}else {
			System.out.println("�ҵ�:" + arr[index] + ",�±�Ϊ:" + index);
		}
	}

	// ���ȣ��������Ԫ���ǰ��������У������м�λ�ü�¼�Ĺؼ�������ҹؼ��ֱȽϣ����������ȣ�
	// ����ҳɹ������������м�λ�ü�¼����ֳ�ǰ���������ӱ�����м�λ�ü�¼�Ĺؼ��ִ��ڲ�
	// �ҹؼ��֣����һ������ǰһ�ӱ������һ�����Һ�һ�ӱ��ظ����Ϲ��̣�ֱ���ҵ���������
	// �ļ�¼��ʹ���ҳɹ�����ֱ���ӱ�����Ϊֹ����ʱ���Ҳ��ɹ���

	/**
	 * 
	 * @param arr
	 *            �����ҵ�����(����)
	 * @param searchValue
	 *            (Ҫ���ҵ�ֵ)
	 * @return ����ҵ������ض�Ӧ���±ꡣ���û�ҵ�������-1
	 */
	public static int binarySearchDemo(int[] arr, int searchValue) {
		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (searchValue == arr[mid]) {
				return mid;
			} else if (arr[mid] > searchValue) {
				// ������
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}
}
