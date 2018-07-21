package Data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Board {

    final static public byte EMPTY = 0;
    final static public byte RED = 1;
    final static public byte BLUE = 2;
    final static public byte RED_IS_DISALLOWED = 4;
    final static public byte BLUE_IS_DISALLOWED = 8;
    
    final private int a_size;
    final private byte[] a_data;
    
    public Board(final int size) {
        
        assert (size > 0);
        
        a_size = size;
        a_data = new byte[size*size]; 
    }
    
    public Board(final Board board) {

        final int size = board.getSize();
        
        a_size = size;
        a_data = Arrays.copyOf(board.a_data, size*size);
    }
    
    public Board generateLargerBoard() {
    
        final int newBoardSize = a_size + 1;
        final Board b = new Board(newBoardSize);
        
        for (int y = 0; y < a_size; y++) {
            for (int x = 0; x < a_size; x++) {
                b.a_data[x + y * newBoardSize] = a_data[x + y * a_size]; //TODO optimize with Array.Copy
            }
        }
        
        return b;
    }

    public Board generateSymetricBoard() {
        
        final Board b = new Board(a_size);
        
        for (int y = 0; y < a_size; y++) {
            for (int x = 0; x < a_size; x++) {
                b.a_data[y + x * a_size] = a_data[x + y * a_size];
            }
        }
        
        return b;
    }

    static public Board generateFromStream(final DataInputStream stream) throws IOException {

        final int size = stream.readInt();
        assert (size > 0);
        final Board b = new Board(size);
        
        final int nbReadBytes = stream.read(b.a_data);
        assert (nbReadBytes == b.a_size * b.a_size);
        
        return b;
    }
    
    @Override
    public boolean equals(final Object other) {
        
        if (this == other) return true;
        
        if (!(other instanceof Board)) return false;
        
        final Board o = (Board) other;

        if (a_size != o.a_size) return false;
        
        for (int y = 0; y < a_size; y++) {
            for (int x = 0; x < a_size; x++) {
                if (a_data[x + y * a_size] != o.a_data[x + y * a_size]) return false;
            }
        }
        
        return true;
    }
    
    @Override
    public int hashCode() {
        
        return Arrays.hashCode(a_data);
    }
    
    public int getSize() {
        
        return a_size;
    }
    
    public void setCellContent(
            final int x,
            final int y,
            final byte value) {

        assert (x >= 0) && (x < a_size);
        assert (y >= 0) && (y < a_size);
        assert (value == RED) || (value == BLUE) || (value == RED_IS_DISALLOWED) || (value == BLUE_IS_DISALLOWED): "invalid value: " + value  + " (cell: [" + x + "," + y + "])";
        assert (a_data[x + y * a_size] == EMPTY) || (a_data[x + y * a_size] == RED_IS_DISALLOWED) || (a_data[x + y * a_size] == BLUE_IS_DISALLOWED) : "the cell already contains: " + a_data[x + y * a_size] + " (cell: [" + x + "," + y + "], value: " + value + ")";
        assert !((a_data[x + y * a_size] == RED_IS_DISALLOWED) && (value == RED)): "trying to set a cell to red while this is not allowed  (cell: [" + x + "," + y + "])";
        assert !((a_data[x + y * a_size] == BLUE_IS_DISALLOWED) && (value == RED_IS_DISALLOWED)): "trying to disallow blue in a cell while red is already disallowed (cell: [" + x + "," + y + "])";
        assert !((a_data[x + y * a_size] == RED_IS_DISALLOWED) && (value == BLUE_IS_DISALLOWED)): "trying to disallow red in a cell while blue is already disallowed (cell: [" + x + "," + y + "])";
        
        a_data[x + y * a_size] = value;
    }
    
    public byte getCellContent(
            final int x,
            final int y) {

        assert (x >= 0) && (x < a_size);
        assert (y >= 0) && (y < a_size);
        
        return a_data[x + y * a_size];
    }
    
    public boolean isSymetric() {

        for (int y = 1; y < a_size; y++) {
            for (int x = 0; x < y - 1 ; x++) {
                if (a_data[y + x * a_size] != a_data[x + y * a_size]) return false;
            }
        }

        return true;
    }

    public void writeToStream(final DataOutputStream stream) throws IOException {
        
        stream.writeInt(a_size);
        stream.write(a_data);
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
                    default: throw new AssertionError();
                }
                builder.append(c);
            }
            builder.append('\n');
        }
        
        assert (builder.length() == a_size * (a_size + 1));
        
        return builder.toString();
    }
}
