package Data;

public class Board {

    final static public int EMPTY = 0;
    final static public int RED = 0;
    final static public int BLUE = 0;
    
    final private int a_size;
    final private int[] a_data;
    
    public Board(final int size) {
        
        // ASSERT size > 0
        
        a_size = size;
        a_data = new int[size*size]; 
    }
    
    public int getSize() {
        
        return a_size;
    }
    
    public void setCellContent(
            final int x,
            final int y,
            final int value) {

        // ASSERT x >= 0 and x < a_size
        // ASSERT y >= 0 and y < a_size
        // ASSERT value == Empty or value == Red or value == Blue   
        
        a_data[x + y * a_size] = value;
    }
    
    public int getCellContent(
            final int x,
            final int y) {

        // ASSERT x >= 0 and x < a_size
        // ASSERT y >= 0 and y < a_size
        
        return a_data[x + y * a_size];
    }
    
    // TODO implement
    // toString
    // fromString
    // copy from smaller size
    // rotation + symmetry
}
