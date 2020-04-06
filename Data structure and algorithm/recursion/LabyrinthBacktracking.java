package recursion;

public class LabyrinthBacktracking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int map[][] = new int[8][8];
		// �����ϰ��
		for (int i = 0; i < 8; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
			map[i][0] = 1;
			map[i][7] = 1;
		}
		// �ϰ��
		map[2][5] = 1;
		map[3][5] = 1;
		map[3][6] = 1;

		// ��ʾ�Թ���ʼ״̬
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(map[i][j] + "  ");
			}
			System.out.println("");
		}
		
		findWay(map, 1, 1);
		
		System.out.println("---------------------");
		// ��ʾ�Թ�Ѱ·��״̬
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(map[i][j] + "  ");
			}
			System.out.println("");
		}
	}

	/**
	 * 
	 * @param map �Թ���ͼ
	 * @param x  ��ʼλ��x
	 * @param y  ��ʼλ��y
	 * @return
	 */
	// �����õݹ���������⡣
	public static boolean findWay(int[][] map, int x, int y) {
		if(map[6][6] == 2) {
			return	true;
		}else {
			// ˵����ǰλ����ͨ·
			if(map[x][y] == 0 ) {
				// �����ǰ����ͨ·�����Ǿ͸���2
				map[x][y] = 2;
				// �������Ƕ������ȼ������Թ�
				// �� -> �� -> �� -> ��
				// �� findWay(map, x - 1, y)
				// �� findWay(map, x, y + 1)
				// �� findWay(map, x + 1, y)
				// ��findWay(map, x, y - 1)
				if( findWay(map, x - 1, y) ) { // ��
					return true;
				}else if(findWay(map, x, y + 1)) { // ��
					return true;
				}else if(findWay(map, x + 1, y)) { // ��
					return true;
				}else if(findWay(map, x, y - 1)) { // ��
					return true;
				}else {// �������Ҿ���ͨ �������� �˴�����·
					map[x][y] = 3;
					return false;
				}
			}else { // ˵����ǰλ�ò���ͨ·
				return false;
			}
		}
	}
}
