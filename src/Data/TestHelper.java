package Data;

public class TestHelper {

    static public Board buildFromString(final String str) {
        
        final int size = (int) Math.round(Math.sqrt(str.length()));
        
        assert (str.length() == (size * size));
        
        final Board board = new Board(size);
        
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                final char c = str.charAt(x + (size - y - 1) * size);
                assert ((c == 'B') || (c == 'R'));
                board.setCellContent(x, y, (c == 'B') ? Board.BLUE : Board.RED);
            }
        }
        
        return board;
    }
}
