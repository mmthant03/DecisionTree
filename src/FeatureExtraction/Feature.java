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

    /**
     * Constructor
     * @param board
     * @param winner
     */
    public Feature(int[][] board, int winner) {
        this.setBoard(board);
        this.winner = winner;
        // after setting up the board, will use that board to set up the feature
        // will go like this
        // this.bottomLeft = BottomLeft();
        // this.center = Center();
        this.setHigherChances();

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
        System.out.println("\nWinner piece : " + this.winner + "\n");
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

    public int getHigherChances() {
        return this.higherChances;
    }

    public void setHigherChances() {
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
        if(winChances[0] >= winChances[1] && winChances[0]>= winChances[2]) {
            this.higherChances = 0;
        } else if (winChances[1] > winChances[2]) {
            this.higherChances = 1;
        } else if (winChances[2] > winChances[1]) {
            this.higherChances = 2;
        }
    }

}
