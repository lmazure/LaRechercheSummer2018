package Processor;

import java.util.function.Consumer;

import Data.Board;

public class Solver {
    
    final private Consumer<Board> a_consumer;
    
    public Solver(final Consumer<Board> consumer) {
        
        a_consumer = consumer;
    }

    public void generateLargerBoard(final Board board) {

        final Board b = board.generateLargerBoard();
        
        analyzeIndexColumn(b, 0, Board.RED, Board.RED_IS_DISALLOWED, Board.BLUE_IS_DISALLOWED);
        analyzeIndexColumn(b, 0, Board.BLUE, Board.BLUE_IS_DISALLOWED, Board.RED_IS_DISALLOWED);
    }
 
   
    private void analyzeIndexColumn(
            final Board board,
            final int index,
            final int color,
            final int colorDisallowedFlag,
            final int otherColorDisallowedFlag) {
        
        final Board b = new Board(board);
        final int x = b.getSize() - 1;
        final int y = index;
        
        assert (b.getCellContent(x, y) != colorDisallowedFlag) || (b.getCellContent(x, y) != otherColorDisallowedFlag);

        if (board.getCellContent(x, y) == colorDisallowedFlag) {
            // the cell cannot have this color
            return;
        }
        
        b.setCellContent(x, y, color);
        
        for (int i = 1; i < b.getSize() - index - 1; i++) {
            
            if ( (b.getCellContent(x - i, y + i) == color) &&
                 (b.getCellContent(x - i, y) == color)) {
                if (b.getCellContent(x, y + i) == otherColorDisallowedFlag) {
                    // impossible: the two colors are disallowed
                    return;
                }
                b.setCellContent(x, y + i, colorDisallowedFlag);
            }
        }

        analyzeIndexRow(b, index, Board.RED, Board.RED_IS_DISALLOWED, Board.BLUE_IS_DISALLOWED);
        analyzeIndexRow(b, index, Board.BLUE, Board.BLUE_IS_DISALLOWED, Board.RED_IS_DISALLOWED);
    }
    
    private void analyzeIndexRow(
            final Board board,
            final int index,
            final int color,
            final int colorDisallowedFlag,
            final int otherColorDisallowedFlag) {
        
        final Board b = new Board(board);
        final int x = index;
        final int y = b.getSize() - 1;

        assert (b.getCellContent(x, y) != colorDisallowedFlag) || (b.getCellContent(x, y) != otherColorDisallowedFlag);

        if (board.getCellContent(x, y) == colorDisallowedFlag) {
            // the cell cannot have this color
            return;
        }
        
        b.setCellContent(x, y, color);
        
        for (int i = 1; i < b.getSize() - index; i++) {
            
            if ( (b.getCellContent(x + i, y - i) == color) &&
                 (b.getCellContent(x, y - i) == color)) {
                if (b.getCellContent(x + i, y) == otherColorDisallowedFlag) {
                    // impossible : the two colors are disallowed
                    return;
                }
                b.setCellContent(x + i, y, colorDisallowedFlag);
            }
        }

        if (index < b.getSize() - 2) {
            analyzeIndexColumn(b, index + 1, Board.RED, Board.RED_IS_DISALLOWED, Board.BLUE_IS_DISALLOWED);
            analyzeIndexColumn(b, index + 1, Board.BLUE, Board.BLUE_IS_DISALLOWED, Board.RED_IS_DISALLOWED);            
        } else {
            analyzeFinalCell(b, Board.RED, Board.RED_IS_DISALLOWED, Board.BLUE_IS_DISALLOWED);
            analyzeFinalCell(b, Board.BLUE, Board.BLUE_IS_DISALLOWED, Board.RED_IS_DISALLOWED);
        }
    }
    
    private void analyzeFinalCell(
            final Board board,
            final int color,
            final int colorDisallowedFlag,
            final int otherColorDisallowedFlag) {
     
        final Board b = new Board(board);
        final int z = b.getSize() - 1;
        
        assert (b.getCellContent(z, z) != colorDisallowedFlag) || (b.getCellContent(z, z) != otherColorDisallowedFlag);
                
        if (b.getCellContent(z, z) != colorDisallowedFlag) {
            b.setCellContent(z, z, color);
            a_consumer.accept(b);
        }
    }
    
    /*static private void dumpBoard(final Board board) {
        
        assert Checker.isValid(board);
        
        if (board.getSize() > 6) {
            System.out.println(board.getSize());
            System.out.println(board.dumpToString());
            System.out.println("------------------------");
        }
        
        generateLargerBoard(board);
    }*/
}
