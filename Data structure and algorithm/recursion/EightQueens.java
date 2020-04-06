package recursion;

public class EightQueens {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quees quees = new Quees();
		quees.putQueens(0);
		System.out.println(quees.getCount());
	}

}

class Quees {

	public int max = 8;
	// ����a���飬�������¼ÿһ�лʺ��λ�ã���Χ��0-7
	public int[] a = new int[max];
	// ������������������������
	public int count = 0;
	
	public void putQueens(int n) {
		// ���n == max ˵�����Ѿ����������һ��Ů������������return
		if (n == max) {
			print();
			return;
		}

		for (int i = 0; i < max; i++) {
			boolean flag = true;
			a[n] = i;
			// �ж��Ƿ��ͻ.
			// ��Ϊ��forѭ����������ͬһ�в�����
			// ͬһ�� a[j] == a[n]
			// б�� Math.abs(n - j) == Math.abs(a[n] - a[j])
			for (int j = 0; j < n; j++) { //����ͻ
				if( a[j] == a[n] ||  Math.abs(n - j) == Math.abs(a[n] - a[j]) ) {
					flag = false;
				}
			}
			if(flag) {
				putQueens(n+1);
			}
		}
	}
	
	public void print() {
		count++;
		for(int i = 0;i < 8; i++) {
			System.out.print(a[i] + "  ");
		}
		System.out.println("");
	}
	
	public int getCount() {
		return count;
	}
}
