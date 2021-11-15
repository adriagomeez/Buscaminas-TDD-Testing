import MockObjects.MockRandomNumber;
import Models.Board;
import Models.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {

    Board board;

    @BeforeEach
    protected void setUp(){
        board = new Board( 7, 8, 1);
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
        assertEquals(board.getNumMines(), 1);
    }

    @Test
    void testConstructor(){
        Board boardConstruct = new Board(23, 15, 7);
        assertEquals(boardConstruct.getNumRows(), 23);
        assertEquals(boardConstruct.getNumCols(), 15);
        assertEquals(boardConstruct.getNumMines(), 7);

    }

    @Test
    void testGetInstance(){
        Board b1 = Board.getInstance(4, 4, 2);
        Board b2 = Board.getInstance(4, 5, 1);
        assertEquals(b1, b2);
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
    @CsvSource(value = {"-1, -1", "-1, 0", "-1, 1", "-1, 2", "-1, 3", "-1, 4", "-1, 5", "-1, 6", "-1, 7", "-1, 8", "-3, 4", "-4, 6",//frontera primera fila y valors aislados
    "-1, 8", "0, 8", "1, 8", "2, 8", "3, 8", "4, 8", "5, 8", "6, 8", "7, 8", "2, 9", "4, 10",//frontera columna derecha y valores aislados
    "7, -1", "7, 0", "7, 1", "7, 2", "7, 3", "7, 4", "7, 5", "7, 6", "7, 7",  "9, 4", "10, 6",//frontera ultima fila y valores aislados
    "1, -1", "2, -1", "3, -1", "4, -1", "5, -1", "6, -1", "4, -5", "6, -4"})//frontera columna izquierda y valores aislados
    void testGetSquareExterior(int row, int col){
        Square testSquere = board.getSquare(row, col);
        assertNull(testSquere);

    }

    @ParameterizedTest
    @CsvSource(value = {"4, 5", "3, 5", "2, 4", "4, 3"})
    void testSetCluesInterior(int row, int col){
        board.setClues(row, col);
        //casillas con pista
        assertEquals(board.getSquare(row-1, col).getNearMines(), 1);
        assertEquals(board.getSquare(row-1, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col-1).getNearMines(), 1);
        assertEquals(board.getSquare(row, col-1).getNearMines(), 1);
        assertEquals(board.getSquare(row-1, col-1).getNearMines(), 1);
        //casillas sin pista
        assertEquals(board.getSquare(row+2, col).getNearMines(), 0);
        assertEquals(board.getSquare(row, col+2).getNearMines(), 0);
        assertEquals(board.getSquare(row, col-2).getNearMines(), 0);
        assertEquals(board.getSquare(row-2, col).getNearMines(), 0);
    }

    @ParameterizedTest
    @CsvSource(value = {"5, 5, 3, 5"})
    void testSetCluesDosMinas(int row1, int col1, int row2, int col2){
        board.setClues(row1, col1);
        //una mina
        assertEquals(board.getSquare(row1-1, col1).getNearMines(), 1);
        assertEquals(board.getSquare(row1-1, col1+1).getNearMines(), 1);
        assertEquals(board.getSquare(row1, col1+1).getNearMines(), 1);
        assertEquals(board.getSquare(row1+1, col1+1).getNearMines(), 1);
        assertEquals(board.getSquare(row1+1, col1).getNearMines(), 1);
        assertEquals(board.getSquare(row1+1, col1-1).getNearMines(), 1);
        assertEquals(board.getSquare(row1, col1-1).getNearMines(), 1);
        assertEquals(board.getSquare(row1-1, col1-1).getNearMines(), 1);

        board.setClues(row2, col2);
        //dos minas
        assertEquals(board.getSquare(row1-1, col1).getNearMines(), 2);
        assertEquals(board.getSquare(row1-1, col1+1).getNearMines(), 2);
        assertEquals(board.getSquare(row1, col1+1).getNearMines(), 1);
        assertEquals(board.getSquare(row1+1, col1+1).getNearMines(), 1);
        assertEquals(board.getSquare(row1+1, col1).getNearMines(), 1);
        assertEquals(board.getSquare(row1+1, col1-1).getNearMines(), 1);
        assertEquals(board.getSquare(row1, col1-1).getNearMines(), 1);
        assertEquals(board.getSquare(row1-1, col1-1).getNearMines(), 2);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 0", "2, 0", "3, 0", "4, 0", "5, 0"})
    void testSetCluesLeftBorder(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row-1, col).getNearMines(), 1);
        assertEquals(board.getSquare(row-1, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col).getNearMines(), 1);

    }

    @ParameterizedTest
    @CsvSource(value = {"0, 1", "0, 2", "0, 3", "0, 4", "0, 5", "0, 6"})
    void testSetCluesTopBorder(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col-1).getNearMines(), 1);
        assertEquals(board.getSquare(row, col-1).getNearMines(), 1);

    }

    @ParameterizedTest
    @CsvSource(value = {"1, 7", "2, 7", "3, 7", "4, 7", "5, 7"})
    void testSetCluesRightBorder(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row-1, col).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col-1).getNearMines(), 1);
        assertEquals(board.getSquare(row, col-1).getNearMines(), 1);
        assertEquals(board.getSquare(row-1, col-1).getNearMines(), 1);

    }

    @ParameterizedTest
    @CsvSource(value = {"6, 1", "6, 2", "6, 3", "6, 4", "6, 5", "6, 6"})
    void testSetCluesBottomBorder(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row-1, col).getNearMines(), 1);
        assertEquals(board.getSquare(row-1, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row, col-1).getNearMines(), 1);
        assertEquals(board.getSquare(row-1, col-1).getNearMines(), 1);

    }

    @ParameterizedTest
    @CsvSource(value = {"0, 0"})
    void testSetCluesTopLeftCorner(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col).getNearMines(), 1);

    }

    @ParameterizedTest
    @CsvSource(value = {"0, 7"})
    void testSetCluesTopRightCorner(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row+1, col).getNearMines(), 1);
        assertEquals(board.getSquare(row+1, col-1).getNearMines(), 1);
        assertEquals(board.getSquare(row, col-1).getNearMines(), 1);

    }

    @ParameterizedTest
    @CsvSource(value = {"6, 7"})
    void testSetCluesBottomRightCorner(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row-1, col).getNearMines(), 1);
        assertEquals(board.getSquare(row, col-1).getNearMines(), 1);
        assertEquals(board.getSquare(row-1, col-1).getNearMines(), 1);

    }

    @ParameterizedTest
    @CsvSource(value = {"6, 0"})
    void testSetCluesBottomLeftCorner(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row-1, col).getNearMines(), 1);
        assertEquals(board.getSquare(row-1, col+1).getNearMines(), 1);
        assertEquals(board.getSquare(row, col+1).getNearMines(), 1);

    }

    @ParameterizedTest
    @CsvSource(value = {"1, -1", "2, -1", "3, -1", "4, -1", "5, -1"})
    void testSetCluesLeftBorderExterior(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row-1, col+1).getNearMines(), 0);
        assertEquals(board.getSquare(row, col+1).getNearMines(), 0);
        assertEquals(board.getSquare(row+1, col+1).getNearMines(), 0);

    }

    @ParameterizedTest
    @CsvSource(value = {"-1, 1", "-1, 2", "-1, 3", "-1, 4", "-1, 5", "-1, 6"})
    void testSetCluesTopBorderExterior(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row+1, col+1).getNearMines(), 0);
        assertEquals(board.getSquare(row+1, col).getNearMines(), 0);
        assertEquals(board.getSquare(row+1, col-1).getNearMines(), 0);

    }

    @ParameterizedTest
    @CsvSource(value = {"1, 8", "2, 8", "3, 8", "4, 8", "5, 8"})
    void testSetCluesRightBorderExterior(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row+1, col-1).getNearMines(), 0);
        assertEquals(board.getSquare(row, col-1).getNearMines(), 0);
        assertEquals(board.getSquare(row-1, col-1).getNearMines(), 0);

    }

    @ParameterizedTest
    @CsvSource(value = {"7, 1", "7, 2", "7, 3", "7, 4", "7, 5", "7, 6"})
    void testSetCluesBottomBorderExterior(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(row-1, col+1).getNearMines(), 0);
        assertEquals(board.getSquare(row-1, col).getNearMines(), 0);
        assertEquals(board.getSquare(row-1, col-1).getNearMines(), 0);

    }

    @ParameterizedTest
    @CsvSource(value = {"-1, 0", "-1, -1", "0, -1"})
    void testSetCluesTopLeftCornerExterior(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(0, 0).getNearMines(), 0);

    }

    @ParameterizedTest
    @CsvSource(value = {"-1, 7", "-1, 8", "0, 8"})
    void testSetCluesTopRightCornerExterior(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(0, 7).getNearMines(), 0);

    }

    @ParameterizedTest
    @CsvSource(value = {"6, 8", "7, 8", "7, 7"})
    void testSetCluesBottomRightCornerExterior(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(6, 7).getNearMines(), 0);

    }

    @ParameterizedTest
    @CsvSource(value = {"6, -1", "7, -1", "7, 0"})
    void testSetCluesBottomLeftCornerExterior(int row, int col){
        board.setClues(row, col);

        assertEquals(board.getSquare(6, 0).getNearMines(), 0);

    }

    @ParameterizedTest
    @CsvSource(value = {"2, 3", "4, 1", "5, 6", "1, 7", "3, 4",
            "0, 0", "0, 1", "0, 2", "0, 3", "0, 4", "0, 5", "0, 6", "0, 7", //frontera primera fila
            "1, 7", "2, 7", "3, 7", "4, 7", "5, 7", "6, 7", //frontera ultima columna
            "6, 0", "6, 1", "6, 2", "6, 3", "6, 4", "6, 5", "6, 6", //frontera ultima fila
            "1, 0", "2, 0", "3, 0", "4, 0", "5, 0"}) //frontera primera columna
    void testCreateMinesInteriorYBorde(int row, int col){
        MockRandomNumber mockRandomNumber = new MockRandomNumber();

        mockRandomNumber.setNum(row);
        mockRandomNumber.setNum(col);

        board.createMines(mockRandomNumber);

        assertTrue(board.getSquare(row, col).isMine());

    }

    @ParameterizedTest
    @CsvSource(value = {"-1, -1", "-1, 0", "-1, 1", "-1, 2", "-1, 3", "-1, 4", "-1, 5", "-1, 6", "-1, 7", "-1, 8", "-3, 4", "-4, 6",//frontera primera fila y valors aislados
            "-1, 8", "0, 8", "1, 8", "2, 8", "3, 8", "4, 8", "5, 8", "6, 8", "7, 8", "2, 9", "4, 10",//frontera columna derecha y valores aislados
            "7, -1", "7, 0", "7, 1", "7, 2", "7, 3", "7, 4", "7, 5", "7, 6", "7, 7",  "9, 4", "10, 6",//frontera ultima fila y valores aislados
            "1, -1", "2, -1", "3, -1", "4, -1", "5, -1", "6, -1", "4, -5", "6, -4"})//frontera columna izquierda y valores aislados
    void testCreateMinesExteriorYBordeExterior(int row, int col){
        MockRandomNumber mockRandomNumber = new MockRandomNumber();

        mockRandomNumber.setNum(row);
        mockRandomNumber.setNum(col);

        mockRandomNumber.setNum(1);
        mockRandomNumber.setNum(1);

        board.createMines(mockRandomNumber);

        assertTrue(board.getSquare(1, 1).isMine());

    }

    @Test
    void testCreateMinesVariasMinas(){
        Board b = new Board(4, 4, 3);
        MockRandomNumber mockRandomNumber = new MockRandomNumber();
        //mina 1
        mockRandomNumber.setNum(2);
        mockRandomNumber.setNum(2);
        //mina 2
        mockRandomNumber.setNum(3);
        mockRandomNumber.setNum(1);
        //mina 3
        mockRandomNumber.setNum(0);
        mockRandomNumber.setNum(2);

        b.createMines(mockRandomNumber);

        assertTrue(b.getSquare(2, 2).isMine());
        assertTrue(b.getSquare(3, 1).isMine());
        assertTrue(b.getSquare(0, 2).isMine());
    }

    @Test
    void testCreateMinesMinasRepetidas(){
        Board b = new Board(4, 4, 3);
        MockRandomNumber mockRandomNumber = new MockRandomNumber();
        //mina 1
        mockRandomNumber.setNum(1);
        mockRandomNumber.setNum(1);
        //mina 2
        mockRandomNumber.setNum(2);
        mockRandomNumber.setNum(1);
        //mina repetida
        mockRandomNumber.setNum(2);
        mockRandomNumber.setNum(1);
        //mina 3
        mockRandomNumber.setNum(3);
        mockRandomNumber.setNum(2);

        b.createMines(mockRandomNumber);

        assertTrue(b.getSquare(1, 1).isMine());
        assertTrue(b.getSquare(2, 1).isMine());
        assertTrue(b.getSquare(3, 2).isMine());
    }

    @Test
    void testSelectSquareConPistaZero(){
        //creacion del tablero
        Board tablero = new Board(5, 5, 3);
        MockRandomNumber mockRandomNumber = new MockRandomNumber();
        //mina 1
        mockRandomNumber.setNum(2);
        mockRandomNumber.setNum(2);
        //mina 2
        mockRandomNumber.setNum(2);
        mockRandomNumber.setNum(4);
        //mina 3
        mockRandomNumber.setNum(4);
        mockRandomNumber.setNum(0);

        tablero.createMines(mockRandomNumber);
        //square 1
        boolean resultado = tablero.selectSquare(4, 3);
        assertFalse(resultado);
        //casillas abiertas
        assertTrue(tablero.getSquare(4, 2).isOpen());
        assertTrue(tablero.getSquare(4, 3).isOpen());
        assertTrue(tablero.getSquare(4, 4).isOpen());
        assertTrue(tablero.getSquare(4, 1).isOpen());
        assertTrue(tablero.getSquare(3, 1).isOpen());
        assertTrue(tablero.getSquare(3, 2).isOpen());
        assertTrue(tablero.getSquare(3, 3).isOpen());
        assertTrue(tablero.getSquare(3, 4).isOpen());
        //casillas frontera con las abiertas pero cerradas
        assertFalse(tablero.getSquare(4, 0).isOpen());
        assertFalse(tablero.getSquare(3, 0).isOpen());
        assertFalse(tablero.getSquare(2, 0).isOpen());
        assertFalse(tablero.getSquare(2, 1).isOpen());
        assertFalse(tablero.getSquare(2, 2).isOpen());
        assertFalse(tablero.getSquare(2, 3).isOpen());
        assertFalse(tablero.getSquare(2, 4).isOpen());

        //square 2
        resultado = tablero.selectSquare(0, 0);
        assertFalse(resultado);
        //casillas abiertas
        assertTrue(tablero.getSquare(0, 0).isOpen());
        assertTrue(tablero.getSquare(0, 1).isOpen());
        assertTrue(tablero.getSquare(0, 2).isOpen());
        assertTrue(tablero.getSquare(0, 3).isOpen());
        assertTrue(tablero.getSquare(0, 4).isOpen());
        assertTrue(tablero.getSquare(1, 0).isOpen());
        assertTrue(tablero.getSquare(2, 0).isOpen());
    }

    @Test
    void testSelectSquareConPistaDiferenteZero(){
        //creacion del tablero
        Board tablero = new Board(5, 5, 3);
        MockRandomNumber mockRandomNumber = new MockRandomNumber();
        //mina 1
        mockRandomNumber.setNum(2);
        mockRandomNumber.setNum(2);
        //mina 2
        mockRandomNumber.setNum(2);
        mockRandomNumber.setNum(4);
        //mina 3
        mockRandomNumber.setNum(4);
        mockRandomNumber.setNum(0);

        tablero.createMines(mockRandomNumber);
        //square 1
        boolean resultado = tablero.selectSquare(1, 3);
        assertFalse(resultado);
        //casilla abierta
        assertTrue(tablero.getSquare(1, 3).isOpen());
        //casillas frontera con las abiertas pero cerradas
        assertFalse(tablero.getSquare(0, 2).isOpen());
        assertFalse(tablero.getSquare(0, 3).isOpen());
        assertFalse(tablero.getSquare(0, 4).isOpen());
        assertFalse(tablero.getSquare(1, 4).isOpen());
        assertFalse(tablero.getSquare(2, 4).isOpen());
        assertFalse(tablero.getSquare(2, 3).isOpen());
        assertFalse(tablero.getSquare(2, 2).isOpen());
        assertFalse(tablero.getSquare(1, 2).isOpen());

        //square 2
        resultado = tablero.selectSquare(3, 1);
        assertFalse(resultado);
        //casilla abierta
        assertTrue(tablero.getSquare(3, 1).isOpen());
        //casillas frontera con las abiertas pero cerradas
        assertFalse(tablero.getSquare(2, 0).isOpen());
        assertFalse(tablero.getSquare(2, 1).isOpen());
        assertFalse(tablero.getSquare(2, 2).isOpen());
        assertFalse(tablero.getSquare(3, 2).isOpen());
        assertFalse(tablero.getSquare(4, 2).isOpen());
        assertFalse(tablero.getSquare(4, 1).isOpen());
        assertFalse(tablero.getSquare(4, 0).isOpen());
        assertFalse(tablero.getSquare(3, 0).isOpen());
    }

    @Test
    void testSelectSquareCasillaAbierta(){
        //creacion del tablero
        Board tablero = new Board(5, 5, 3);
        MockRandomNumber mockRandomNumber = new MockRandomNumber();
        //mina 1
        mockRandomNumber.setNum(2);
        mockRandomNumber.setNum(2);
        //mina 2
        mockRandomNumber.setNum(2);
        mockRandomNumber.setNum(4);
        //mina 3
        mockRandomNumber.setNum(4);
        mockRandomNumber.setNum(0);

        tablero.createMines(mockRandomNumber);
        //abrir casilla
        boolean resultado = tablero.selectSquare(4, 3);
        //seleccionar misma casilla
        resultado = tablero.selectSquare(4, 3);
        assertFalse(resultado);
        //casillas abiertas siguen abiertas
        assertTrue(tablero.getSquare(4, 2).isOpen());
        assertTrue(tablero.getSquare(4, 3).isOpen());
        assertTrue(tablero.getSquare(4, 4).isOpen());
        assertTrue(tablero.getSquare(4, 1).isOpen());
        assertTrue(tablero.getSquare(3, 1).isOpen());
        assertTrue(tablero.getSquare(3, 2).isOpen());
        assertTrue(tablero.getSquare(3, 3).isOpen());
        assertTrue(tablero.getSquare(3, 4).isOpen());
        //casillas frontera con las abiertas pero cerradas siguen cerradas
        assertFalse(tablero.getSquare(4, 0).isOpen());
        assertFalse(tablero.getSquare(3, 0).isOpen());
        assertFalse(tablero.getSquare(2, 0).isOpen());
        assertFalse(tablero.getSquare(2, 1).isOpen());
        assertFalse(tablero.getSquare(2, 2).isOpen());
        assertFalse(tablero.getSquare(2, 3).isOpen());
        assertFalse(tablero.getSquare(2, 4).isOpen());
    }

    @Test
    void testSelectSquareConMina(){
        //creacion del tablero
        Board tablero = new Board(5, 5, 3);
        MockRandomNumber mockRandomNumber = new MockRandomNumber();
        //mina 1
        mockRandomNumber.setNum(2);
        mockRandomNumber.setNum(2);
        //mina 2
        mockRandomNumber.setNum(2);
        mockRandomNumber.setNum(4);
        //mina 3
        mockRandomNumber.setNum(4);
        mockRandomNumber.setNum(0);

        tablero.createMines(mockRandomNumber);
        //abrir casilla
        boolean resultado = tablero.selectSquare(2, 2);
        assertTrue(resultado);
        //casilla abierta
        assertTrue(tablero.getSquare(2, 2).isOpen());
        //casillas frontera con la abierta pero cerradas
        assertFalse(tablero.getSquare(1, 1).isOpen());
        assertFalse(tablero.getSquare(1, 2).isOpen());
        assertFalse(tablero.getSquare(1, 3).isOpen());
        assertFalse(tablero.getSquare(2, 3).isOpen());
        assertFalse(tablero.getSquare(3, 3).isOpen());
        assertFalse(tablero.getSquare(3, 2).isOpen());
        assertFalse(tablero.getSquare(3, 1).isOpen());
        assertFalse(tablero.getSquare(2, 1).isOpen());
    }


}
