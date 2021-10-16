/**
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

Example 1:
Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]

Example 2:
Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]

Constraints:
|x| + |y| <= 300
**/

class Solution {//Minimum Knight Moves
    public int minKnightMoves(int x, int y) {
 
 		boolean [][]visited = new boolean[301][301];
 		Queue<int []> queue = new LinkedList<>();
 		queue.offer(new int[]{0,0,0});
 		visited[0][0] = true;

 		while(!queue.isEmpty()) {
 			int size = queue.size();
 			for(int i=0; i<size; ++i) {
 				int []cord = queue.poll();
 				int nx = cord[0];
 				int ny = cord[1];
 				int steps = cord[2];

 				if(x == nx && y == ny) return steps;
                
 				List<int []> nbrs = getNeighbors(nx, ny, steps, visited);
 				if(nbrs==null || nbrs.size()==0) continue;

 				for(int []nbr : nbrs) {
 					visited[nbr[0]][nbr[1]] = true;
 					queue.offer(new int[]{nbr[0], nbr[1], nbr[2]});
 				}
 			}
 		} 
 		return -1;      
    }

    private List<int []> getNeighbors(int x, int y, int steps, boolean [][]visited){
    	int []dx = {-2,-2, -1, -1, 1, 1, 2, 2};
    	int []dy = {1,-1, 2, -2, 2, -2, 1, -1};

    	List<int []> nbrs = new ArrayList<>();
    	for(int i=0; i<8; ++i) {
    		int nx = dx[i] + x;
    		int ny = dy[i] + y;
            if(nx < 0 || ny <0) continue;
    		if(visited[nx][ny]) continue;
    		nbrs.add(new int[]{nx, ny, steps+1});
    	}
        return nbrs;
    }
}
