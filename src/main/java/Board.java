public class Board {

    private int numRows;
    private int numCols;
    private int numMines;
    private Square[][] board;

    public Board(int rows, int cols, int mines){
        numRows = rows;
        numCols = cols;
        numMines = mines;
        board = new Square[numRows][numCols];

        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                board[i][j] = new Square(i, j);
            }
        }
    }

    public int getNumCols(){
        return numCols;
    }

    public int getNumRows(){
        return numRows;
    }

    public int getNumMines(){
        return numMines;
    }

    public Square getSquare();

    public void createBoard();

    public void createMines();

    public void setClues();
}
