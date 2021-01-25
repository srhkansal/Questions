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
