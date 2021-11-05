import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {

    Board board;

    protected void setUp(){
        board = new Board( 7, 8, 3);
    }

    @Test
    void testGetNumRows(){
        assertEquals(board.getNumRows(), 7);
    }

    @Test
    void testGetNumCols(){
        assertEquals(board.getNumCols(), 8);
    }

    @Test
    void testGetNumMines(){
        assertEquals(board.getNumMines(), 3);
    }

    @Test
    void testConstructor(){
        Board boardConstruct = new Board(23, 15, 7);
        assertEquals(boardConstruct.getNumRows(), 23);
        assertEquals(boardConstruct.getNumCols(), 15);
        assertEquals(boardConstruct.getNumMines(), 7);

    }

    @ParameterizedTest
    @CsvSource(value = {"5, 5", "2, 1", "6, 3", "3, 4", "1, 2", "4, 6", //interiors
            "0, 0", "0, 1", "0, 2", "0, 3", "0, 4", "0, 5", "0, 6", "0, 7", //frontera primera fila
            "1, 7", "2, 7", "3, 7", "4, 7", "5, 7", "6, 7", //frontera ultima columna
            "6, 0", "6, 1", "6, 2", "6, 3", "6, 4", "6, 5", "6, 6", //frontera ultima fila
            "1, 0", "2, 0", "3, 0", "4, 0", "5, 0"}) //frontera primera columna
    void testGetSquareInterior(int row, int col){
        Square testSquere = board.getSquare(row, col);
        assertEquals(testSquere.getRow(), row);
        assertEquals(testSquere.getCol(), col);
    }

    @ParameterizedTest
    @CsvSource(value = {"0, -1", "-1, 0", "-1, -1", "-1, 4", "-4, 6",//Esquina superior izquierda y arriba
    "0, 8", "-1, 7", "-1, 8", "4, 8", "4, 10",//Esquina superior derecha y derecha
    "6, 8", "7, 7", "7, 8", "7, 4", "9, 4",//Esquina inferior derecha y abajo
    "7, 0", "6, -1", "7, -1", "4, -1", "4, -4"})//Esquina inferior izquierda y izquierda
    void testGetSquareExterior(int row, int col){
        Square testSquere = board.getSquare(row, col);
        assertNull(testSquere);

    }

    @ParameterizedTest
    @CsvSource(value = {"5, 5, 7, 5", "8, 3, 6, 4"})
    void testSetCluesInterior(int row1, int col1, int row2, int col2){
        board
    }
}
