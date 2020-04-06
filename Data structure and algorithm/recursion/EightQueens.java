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
	// 生命a数组，数组里记录每一行皇后的位置，范围：0-7
	public int[] a = new int[max];
	// 用来计数，多少满足条件。
	public int count = 0;
	
	public void putQueens(int n) {
		// 如果n == max 说明我已经放置了最后一个女王，即结束，return
		if (n == max) {
			print();
			return;
		}

		for (int i = 0; i < max; i++) {
			boolean flag = true;
			a[n] = i;
			// 判断是否冲突.
			// 因为是for循环遍历所以同一行不考虑
			// 同一列 a[j] == a[n]
			// 斜线 Math.abs(n - j) == Math.abs(a[n] - a[j])
			for (int j = 0; j < n; j++) { //不冲突
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
