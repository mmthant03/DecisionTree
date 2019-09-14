package FeatureExtraction;

public class Feature {
    int rows = 6;
    int columns = 7;
    public int[][] board = new int[rows][columns];
    private int winner;
    private int bottomLeft;
    private int center;
    private int bottomRight;

    public Feature(int[][] board, int winner) {
        this.setBoard(board);
        this.winner = winner;
        // after setting up the board, will use that board to set up the feature
        // will go like this
        // this.bottomLeft = BottomLeft();
        // this.center = Center();

    }

    public void display()
    {
        for (int i=rows-1; i>=0; i--)
        {
            for (int j = 0; j < columns; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setBoard(int[][] newBoard) {
        for(int i=0; i<this.rows; i++) {
            for (int j=0; j < this.columns; j++) {
                this.board[i][j] = newBoard[i][j];
            }
        }
    }



}
