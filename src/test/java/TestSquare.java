import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSquare {

    Square square;

    protected void setUp(){
        square = new Square(4,3);
    }

    @Test
    void testGetRow(){
        assertTrue(square.getRow() == 4);
    }

    @Test
    void testGetCol(){
        assertTrue(square.getCol() == 3);
    }

    @Test
    void testConstructor(){
        Square squareConstruct = new Square(5,7);
        assertTrue(squareConstruct.getRow() == 5);
        assertTrue(squareConstruct.getCol() == 7);
    }
}
