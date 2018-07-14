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
    
    @Test
    void CopyConstructorBehavesCorrectly() {

        // --- arrange ---
        
        final Board board = new Board(3);
        board.setCellContent(0, 0, Board.BLUE);
        board.setCellContent(0, 1, Board.RED);
        //board.setCellContent(0, 2, Board.EMPTY);
        board.setCellContent(1, 0, Board.BLUE_IS_DISALLOWED);
        //board.setCellContent(1, 1, Board.EMPTY);
        board.setCellContent(1, 2, Board.RED);
        board.setCellContent(2, 0, Board.RED_IS_DISALLOWED);
        board.setCellContent(2, 1, Board.RED);
        board.setCellContent(2, 2, Board.BLUE);
        
        // --- act ---

        final Board generatedBoard = new Board(board);
        
        // --- assert ---
        
        assertEquals(3, generatedBoard.getSize());
        assertEquals(Board.BLUE, generatedBoard.getCellContent(0, 0));
        assertEquals(Board.RED, generatedBoard.getCellContent(0, 1));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(0, 2));
        assertEquals(Board.BLUE_IS_DISALLOWED, generatedBoard.getCellContent(1, 0));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(1, 1));
        assertEquals(Board.RED, generatedBoard.getCellContent(1, 2));
        assertEquals(Board.RED_IS_DISALLOWED, generatedBoard.getCellContent(2, 0));
        assertEquals(Board.RED, generatedBoard.getCellContent(2, 1));
        assertEquals(Board.BLUE, generatedBoard.getCellContent(2, 2));
    }

    @Test
    void largerBoardIsCorrectlyGenerated() {

        // --- arrange ---
        
        final Board board = new Board(3);
        board.setCellContent(0, 0, Board.BLUE);
        board.setCellContent(0, 1, Board.RED);
        //board.setCellContent(0, 2, Board.EMPTY);
        board.setCellContent(1, 0, Board.RED);
        //board.setCellContent(1, 1, Board.EMPTY);
        board.setCellContent(1, 2, Board.RED);
        //board.setCellContent(2, 0, Board.EMPTY);
        board.setCellContent(2, 1, Board.RED);
        board.setCellContent(2, 2, Board.BLUE);
        
        // --- act ---

        final Board generatedBoard = board.generateLargerBoard();
        
        // --- assert ---
        
        assertEquals(4, generatedBoard.getSize());
        assertEquals(Board.BLUE, generatedBoard.getCellContent(0, 0));
        assertEquals(Board.RED, generatedBoard.getCellContent(0, 1));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(0, 2));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(0, 3));
        assertEquals(Board.RED, generatedBoard.getCellContent(1, 0));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(1, 1));
        assertEquals(Board.RED, generatedBoard.getCellContent(1, 2));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(1, 3));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(2, 0));
        assertEquals(Board.RED, generatedBoard.getCellContent(2, 1));
        assertEquals(Board.BLUE, generatedBoard.getCellContent(2, 2));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(2, 3));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(3, 0));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(3, 1));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(3, 2));
        assertEquals(Board.EMPTY, generatedBoard.getCellContent(3, 3));
    }

    @Test
    void stringDumpIsCorrect() {

        // --- arrange ---
        
        final Board board = new Board(3);
        //board.setCellContent(0, 2, Board.EMPTY);
        board.setCellContent(1, 2, Board.RED);
        board.setCellContent(2, 2, Board.BLUE);
        board.setCellContent(0, 1, Board.RED);
        //board.setCellContent(1, 1, Board.EMPTY);
        board.setCellContent(2, 1, Board.RED);
        board.setCellContent(0, 0, Board.BLUE);
        board.setCellContent(1, 0, Board.RED);
        //board.setCellContent(2, 0, Board.EMPTY);        
        
        // --- assert ---
        
        assertEquals(" RB\nR R\nBR \n", board.dumpToString());
    }
}
