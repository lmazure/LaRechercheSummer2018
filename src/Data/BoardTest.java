package Data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void sizeIsCorrectlyManaged() {
        // --- arrange ---
        
        final Board board = new Board(17);
        
        // --- assert ---
        assertEquals(17, board.getSize());
    }
    
    @Test
    void cellContentGetterAndSetterAreCorrectlyManaged() {
        
        // --- arrange ---
        
        final Board board = new Board(7);
        
        // --- act ---
        
        board.setCellContent(1, 2, Board.BLUE);
        board.setCellContent(2, 1, Board.RED);

        // --- assert ---

        assertEquals(Board.BLUE, board.getCellContent(1, 2));
        assertEquals(Board.RED, board.getCellContent(2, 1));
    }

    @Test
    void uninitializedCellsAreEmpty() {

        // --- arrange ---
        
        final Board board = new Board(2);
        
        // --- assert ---

        assertEquals(Board.EMPTY, board.getCellContent(0, 0));
        assertEquals(Board.EMPTY, board.getCellContent(0, 1));
        assertEquals(Board.EMPTY, board.getCellContent(1, 0));
        assertEquals(Board.EMPTY, board.getCellContent(1, 1));
    }
}
