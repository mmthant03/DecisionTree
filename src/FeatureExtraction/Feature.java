package FeatureExtraction;

public class Feature {
    int rows = 6;
    int columns = 7;
    public int[][] board = new int[rows][columns];
    private int winner;
    private int bottomLeft;
    private int center;
    private int bottomRight;
    private int higherChances;
    private int controlCent2;

    /**
     * Constructor
     * @param board
     * @param winner
     */
    public Feature(int[][] board, int winner) {
        this.setBoard(board);
        this.winner = winner;
        this.bottomLeft = getBottomLeft();
        this.center = centerHolder();
        this.bottomRight = getBottomRight();
        this.higherChances = higherChancesHolder();
        this.controlCent2 = centerTwo();

    }

    /**
     * Display the board
     */
    public void display() {
        System.out.println("\n=====================================\n");
        for (int i=rows-1; i>=0; i--) {
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\nWinner piece   : " + this.winner);
        System.out.println("\nBottom Left    : " + this.bottomLeft);
        System.out.println("\nCenter         : " + this.center);
        System.out.println("\nBottom Right   : " + this.bottomRight);
        System.out.println("\nHigher Chances : " + this.higherChances);
        System.out.println();
    }

    /**
     * Setter method for board variable
     * @param newBoard
     */
    public void setBoard(int[][] newBoard) {
        for(int i=0; i<this.rows; i++) {
            for (int j=0; j < this.columns; j++) {
                this.board[i][j] = newBoard[i][j];
            }
        }
    }
    
    /**
     * returns value of bottom left position of board.
     */
    public int getBottomLeft() {
    	return board[0][0];
    }

    /**
     * returns value of bottom left position of board.
     */
    public int getBottomRight() {
        return board[0][6];
    }

    /**
     * returns player who has more pieces in center columns.
     */
    public int getCenter() {
        return this.center;
    }
    
    /**
     * return which player has the most pieces in the center columns of the board
     */
    public int centerHolder() {
    	int i = 2;
    	int j;
    	int p1Count = 0;
    	int p2Count = 0;
    	int check = 0;
    	for(i = 2; i <= 4; i++) {
    		for(j = 0; j <= 5; j++) {
    			check = board[j][i];
    			if(check == 1) {
    				p1Count++;
    			} else if(check == 2) {
    				p2Count++;
    			}
    		}
    	}
    	if(p1Count > p2Count) {
    		return 1;
    	} else if(p1Count < p2Count) {
    		return 2;
    	} else {
    		return 0;
    	}
    }

    /**
     * getter function for winner variable.
     */
    public int getWinner() {
    	return winner;
    }

    /**
     * returns player who has more higher chances to win
     */
    public int getHigherChances() {
        return this.higherChances;
    }

    /**
     * returns which player controls the center 2 positions
     */
    public int centerTwo() {
    	int count1 = 0;
    	int count2 = 0;
    	int centPos1 = board[2][3];
    	int centPos2 = board[3][3];
    	
    	if(centPos1 == 1) {
    		count1++;
    	} else if(centPos1 == 2) {
    		count2++;
    	}
    	
    	if(centPos2 == 1) {
    		count1++;
    	} else if(centPos2 == 2) {
    		count2++;
    	}
    	
    	if(count1 > count2) {
    		return 1;
    	} else if (count1 < count2) {
    		return 2;
    	} else {
    		return 0;
    	}
    }

    /**
     * return which player has more chances to win
     */
    public int higherChancesHolder() {
        // set the evaluation table
        int[][] evaluationTable =   {{3, 4, 5, 7, 5, 4, 3},
                                    {4, 6, 8, 10, 8, 6, 4},
                                    {5, 8, 11, 13, 11, 8, 5},
                                    {5, 8, 11, 13, 11, 8, 5},
                                    {4, 6, 8, 10, 8, 6, 4},
                                    {3, 4, 5, 7, 5, 4, 3}};
        // set an array that corresponds to pieces i.e 0,1, and 2
        int[] winChances = new int[3];
        // adds up the chances according to evaluation table
        for(int i=0;i<this.rows;i++) {
            for (int j=0;j<this.columns;j++) {
                winChances[this.board[i][j]] += evaluationTable[i][j];
            }
        }
        // compare the chances and return the piece which show higher chances
        if (winChances[1] > winChances[2]) {
            return 1;
        } else if (winChances[2] > winChances[1]) {
            return 2;
        } else {
            return 0;
        }
    }

}
