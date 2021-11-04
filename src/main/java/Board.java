public class Board {

    private int numRows;
    private int numCols;
    private int numMines;
    private Square[][] board;

    public Board(int rows, int cols, int mines){

    }

    public int getNumCols();

    public int getNumRows();

    public int getNumMines();

    public void createBoard();

    public void createMines();

    public void setClues();
}
