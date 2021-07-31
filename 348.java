class TicTacToe {
	int []rowCounter;
	int []colCounter;
	int leftDiag;
	int rightDiag;
	int size;

	TicTacToe (int n){
		rowCounter = new int[n];
		colCounter = new int[n];
		size = n;
	}

	public int nextMove(int row, int col, int player){
		int move = player ==1 ? 1 : -1;
		
		rowCounter[row] +=move;
		colCounter[col] +=move;
		
		if(row == col)
			leftDiag+=move;

		if(row == n-1-col)
			rightDiag+=move;

		if(rowCounter[row] == n || colCounter[col] == n || leftDiag ==n || rightDiag ==n)
			return 1;
		else if(rowCounter[row] == -n || colCounter[col] == -n || leftDiag ==-n || rightDiag ==-n)
			return 2;
		else 
			return 0;
		
	}
}
