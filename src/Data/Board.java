package Data;

public class Board {

    public enum Color {
        Empty,
        Red,
        Blue
    }
    
    final private int a_size;
    final private Color[] a_data;
    
    public Board(final int size) {
        
        // ASSERT size > 0
        
        a_size = size;
        a_data = new Color[size*size]; 
    }
    
    public void setCellContent(
            final int x,
            final int y,
            final Color value) {

        // ASSERT x >= 0 and x < a_size
        // ASSERT y >= 0 and y < a_size
        // ASSERT value == Color.Empty or value == Color.Red or value == Color.Blue   
        
        a_data[x + y * a_size] = value;
    }
    
    public Color getCellContent(
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
