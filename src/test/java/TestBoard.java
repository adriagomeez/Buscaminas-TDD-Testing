import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {

    Board board;

    protected void setUp(){
        board = new Board( 16, 11, 5);
    }

    @Test
    void testGetNumRows(){
        assertEquals(board.getNumRows(), 16);
    }

    @Test
    void testGetNumCols(){
        assertEquals(board.getNumCols(), 11);
    }

    @Test
    void testGetNumMines(){
        assertEquals(board.getNumMines(), 5);
    }

    @Test
    void testConstructor(){
        Board boardConstruct = new Board(23, 15, 7);
        assertEquals(boardConstruct.getNumRows(), 23);
        assertEquals(boardConstruct.getNumCols(), 15);
        assertEquals(boardConstruct.getNumMines(), 7);

    }
}
