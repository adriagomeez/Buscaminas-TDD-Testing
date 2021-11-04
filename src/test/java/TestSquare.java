import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSquare {

    Square square;

    protected void setUp(){
        square = new Square(4,3);
    }

    @Test
    void testGetRow(){
        assertEquals(square.getRow(), 4);
    }

    @Test
    void testGetCol(){
        assertEquals(square.getCol(), 3);
    }

    @Test
    void testIsMine(){
        assertFalse(square.isMine());
    }

    @Test
    void testSetMine(){
        square.setMine();
        assertTrue(square.isMine());
    }

    @Test
    void testIsOpen(){
        assertFalse(square.isOpen());
    }

    @Test
    void testSetOpen(){
        square.setOpen();
        assertTrue(square.isOpen());
    }

    @Test
    void testConstructor(){
        Square squareConstruct = new Square(5,7);
        assertEquals(squareConstruct.getRow(), 5);
        assertEquals(squareConstruct.getCol(), 7);
        assertFalse(squareConstruct.isMine());
        assertFalse(squareConstruct.isOpen());
    }
}
