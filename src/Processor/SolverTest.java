package Processor;

import org.junit.jupiter.api.Disabled;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Data.Board;

class SolverTest {


    @Test
    void testSmallest() {

        // --- arrange ---
        
        final Board board = new Board(1);
        board.setCellContent(0, 0, Board.RED);
        
        // --- assert ---
        
        Solver.generateLargerBoard(board);
    }
    
    @Test
    @Disabled
    void test() {

        // --- arrange ---
        
        final Board board = new Board(3);
        board.setCellContent(0, 0, Board.RED);
        board.setCellContent(1, 0, Board.RED);
        board.setCellContent(2, 0, Board.BLUE);
        board.setCellContent(0, 1, Board.RED);
        board.setCellContent(1, 1, Board.BLUE);
        board.setCellContent(2, 1, Board.BLUE);
        board.setCellContent(0, 2, Board.RED);
        board.setCellContent(1, 2, Board.BLUE);
        board.setCellContent(2, 2, Board.RED);        
        
        // --- assert ---
        
        Solver.generateLargerBoard(board);
    }
}
