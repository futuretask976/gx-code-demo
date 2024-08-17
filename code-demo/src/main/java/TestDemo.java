public class TestDemo {
    public static void main(String args[]) {
        // System.out.println(minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
        int[] arr = new int[]{0, 0, 0, 0, 0, 0};
        System.out.println(15 % 16);
    }

    public static int minPathSum(int[][] grid) {
        int rtn = calcMinPathSum(grid, 2, 2);
        return rtn;
    }

    public static int calcMinPathSum(int[][] grid, int currPosX, int currPosY) {
        if (currPosX == 0 && currPosY == 0) {
            // 原点
            return grid[0][0];
        } else if (currPosX == 2 && currPosY == 0) {
            // 右上角
            return grid[currPosX][currPosY] + calcMinPathSum(grid, currPosX - 1, currPosY);
        } else if (currPosX == 2 && currPosY == 2) {
            // 右下角
            int up = calcMinPathSum(grid, currPosX, currPosY - 1);
            int left = calcMinPathSum(grid, currPosX - 1, currPosY);
            return grid[currPosX][currPosY] + Math.min(up, left);
        } else if (currPosX == 0 && currPosY == 2) {
            // 左下角
            return grid[currPosX][currPosY] + calcMinPathSum(grid, currPosX, currPosY - 1);
        } else if (currPosX > 0 && currPosY == 0) {
            // 上中点
            int left = calcMinPathSum(grid, currPosX - 1, currPosY);
            int down = calcMinPathSum(grid, currPosX, currPosY + 1);
            return grid[currPosX][currPosY] + Math.min(left, down);
        } else if (currPosX == 2 && currPosY > 0) {
            // 右中点
            int up = calcMinPathSum(grid, currPosX, currPosY - 1);
            int left = calcMinPathSum(grid, currPosX - 1, currPosY);
            return grid[currPosX][currPosY] + Math.min(up, left);
        } else if (currPosX > 0 && currPosY > 2) {
            // 下中点
            int up = calcMinPathSum(grid, currPosX, currPosY - 1);
            int left = calcMinPathSum(grid, currPosX - 1, currPosY);
            return grid[currPosX][currPosY] + Math.min(up, left);
        } else if (currPosX == 0 && currPosY > 0) {
            // 左中点
            int up = calcMinPathSum(grid, currPosX, currPosY - 1);
            int right = calcMinPathSum(grid, currPosX + 1, currPosY);
            return grid[currPosX][currPosY] + Math.min(up, right);
        } else if (currPosX == 1 && currPosY == 1) {
            // 中点
            int up = calcMinPathSum(grid, currPosX, currPosY - 1);
            int right = calcMinPathSum(grid, currPosX + 1, currPosY);
            int left = calcMinPathSum(grid, currPosX - 1, currPosY);
            int minVal = Math.min(Math.min(up, left), right);
            return grid[currPosX][currPosY] + minVal;
        }
        throw new IllegalArgumentException();
    }
}



