package search;

public class LinerSearch {
	public static void main(String[] args) {
		//������˳��
		int arr[] = {-1, 3, 7, 8, 9, 2, 5};
		int index = Search(arr, 5);
		if(index == -1) {
			System.out.println("û���ҵ�");
		}else {
			System.out.println("�±�Ϊ:" + index);
		}

	}
	
	/**
	 * 
	 * @param arr �����ҵ�����
	 * @param searchValue Ҫ���ҵ�ֵ
	 * @return
	 */
	public static int Search(int[] arr, int searchValue) {
		for(int i = 0; i < arr.length; i++) {
			//����ҵ�ֵ���򷵻ض�Ӧ���±�
			if(arr[i] == searchValue) {
				return i;
			}
		}
		//û���ҵ�������-1
		return -1;
	}
}	
