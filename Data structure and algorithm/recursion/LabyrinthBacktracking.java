package recursion;

public class LabyrinthBacktracking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int map[][] = new int[8][8];
		// 生成障碍物。
		for (int i = 0; i < 8; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
			map[i][0] = 1;
			map[i][7] = 1;
		}
		// 障碍物。
		map[2][5] = 1;
		map[3][5] = 1;
		map[3][6] = 1;

		// 显示迷宫初始状态
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(map[i][j] + "  ");
			}
			System.out.println("");
		}
		
		findWay(map, 1, 1);
		
		System.out.println("---------------------");
		// 显示迷宫寻路后状态
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(map[i][j] + "  ");
			}
			System.out.println("");
		}
	}

	/**
	 * 
	 * @param map 迷宫地图
	 * @param x  起始位置x
	 * @param y  起始位置y
	 * @return
	 */
	// 我们用递归解决这个问题。
	public static boolean findWay(int[][] map, int x, int y) {
		if(map[6][6] == 2) {
			return	true;
		}else {
			// 说明当前位置是通路
			if(map[x][y] == 0 ) {
				// 如果当前点是通路，我们就复制2
				map[x][y] = 2;
				// 按照我们定的优先级来走迷宫
				// 上 -> 右 -> 下 -> 左
				// 上 findWay(map, x - 1, y)
				// 右 findWay(map, x, y + 1)
				// 下 findWay(map, x + 1, y)
				// 左findWay(map, x, y - 1)
				if( findWay(map, x - 1, y) ) { // 上
					return true;
				}else if(findWay(map, x, y + 1)) { // 右
					return true;
				}else if(findWay(map, x + 1, y)) { // 下
					return true;
				}else if(findWay(map, x, y - 1)) { // 左
					return true;
				}else {// 上下左右均不通 则给出标记 此处是死路
					map[x][y] = 3;
					return false;
				}
			}else { // 说明当前位置不是通路
				return false;
			}
		}
	}
}
