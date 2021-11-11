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

    public Square getSquare(int row, int col){
        if( row >= 0 && row < numRows && col >= 0 && col <numCols)
            return board[row][col];

        return null;
    }

    public void createBoard();

    public void createMines();

    public void setClues(int row, int col){
        for (int i = 0; i < 8; i++){
            int auxRow = row;
            int auxCol = col;
            switch (i){
                case 0://topLeft
                    auxRow--;
                    auxCol--;
                    break;
                case 1://top
                    auxRow--;
                    break;
                case 2://topRight
                    auxRow--;
                    auxCol++;
                    break;
                case 3://right
                    auxCol++;
                    break;
                case 4://bottomRight
                    auxRow++;
                    auxCol++;
                    break;
                case 5://bottom
                    auxRow++;
                    break;
                case 6://bottomLeft
                    auxRow++;
                    auxCol--;
                    break;
                case 7://left
                    auxCol--;
                    break;
            }
            if( auxRow >= 0 && auxRow < numRows && auxCol >= 0 && auxCol <numCols)
                board[auxRow][auxCol].increaseNearMines();
        }
    }
}
