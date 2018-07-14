package Data;

import java.util.Arrays;

public class Board {

    final static public int EMPTY = 0;
    final static public int RED = 1;
    final static public int BLUE = 2;
    final static public int RED_IS_DISALLOWED = 4;
    final static public int BLUE_IS_DISALLOWED = 8;
    
    final private int a_size;
    final private int[] a_data;
    
    public Board(final int size) {
        
        assert (size > 0);
        
        a_size = size;
        a_data = new int[size*size]; 
    }
    
    public Board(final Board board) {

        final int size = board.getSize();
        
        a_size = size;
        a_data = Arrays.copyOf(board.a_data, size*size);
    }
    
    public Board generateLargerBoard() {
    
        final int newBoardSize = a_size + 1;
        final Board b = new Board(newBoardSize);
        
        for (int x = 0; x < a_size; x++) {
            for (int y = 0; y < a_size; y++) {
                b.a_data[x + y * newBoardSize] = a_data[x + y * a_size]; //TODO optimize with Array.Copy
            }
        }
        
        return b;
    }
    
    public int getSize() {
        
        return a_size;
    }
    
    public void setCellContent(
            final int x,
            final int y,
            final int value) {

        //assert !((x == 3) && (y == 3) && ((value == RED) || (value == BLUE))) : "trying to set value " + value + " in the last cell"; //TODO remove this line
        assert (x >= 0) && (x < a_size);
        assert (y >= 0) && (y < a_size);
        assert (value == RED) || (value == BLUE) || (value == RED_IS_DISALLOWED) || (value == BLUE_IS_DISALLOWED): "invalid value: " + value  + " (cell: [" + x + "," + y + "])";
        assert (a_data[x + y * a_size] == EMPTY) || (a_data[x + y * a_size] == RED_IS_DISALLOWED) || (a_data[x + y * a_size] == BLUE_IS_DISALLOWED) : "the cell already contains: " + a_data[x + y * a_size] + " (cell: [" + x + "," + y + "], value: " + value + ")";
        assert !((a_data[x + y * a_size] == RED_IS_DISALLOWED) && (value == RED)): "trying to set a cell to red while this is not allowed  (cell: [" + x + "," + y + "])";
        assert !((a_data[x + y * a_size] == BLUE_IS_DISALLOWED) && (value == RED_IS_DISALLOWED)): "trying to disallow blue in a cell while red is already disallowed (cell: [" + x + "," + y + "])";
        assert !((a_data[x + y * a_size] == RED_IS_DISALLOWED) && (value == BLUE_IS_DISALLOWED)): "trying to disallow red in a cell while blue is already disallowed (cell: [" + x + "," + y + "])";
        
        a_data[x + y * a_size] = value;
    }
    
    public int getCellContent(
            final int x,
            final int y) {

        assert (x >= 0) && (x < a_size);
        assert (y >= 0) && (y < a_size);
        
        return a_data[x + y * a_size];
    }
    
    public String dumpToString() {
        
        final StringBuilder builder = new StringBuilder(a_size * (a_size + 1));
        
        for (int y = a_size -1; y >= 0; y--) {
            for (int x = 0; x < a_size; x++) {
                char c;
                switch (a_data[x + y * a_size]) {
                    case EMPTY : c = ' '; break;
                    case RED : c = 'R'; break;
                    case BLUE : c = 'B'; break;
                    case RED_IS_DISALLOWED : c = 'r'; break;
                    case BLUE_IS_DISALLOWED : c = 'b'; break;
                    default: throw new IllegalStateException(); // TODO find a better exception
                }
                builder.append(c);
            }
            builder.append('\n');
        }
        
        assert (builder.length() == a_size * (a_size + 1));
        
        return builder.toString();
    }
    
    // TODO implement
    // toString
    // fromString
    // copy from smaller size
    // rotation + symmetry
}
