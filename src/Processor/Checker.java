package Processor;

import Data.Board;

public class Checker {

    @FunctionalInterface
    private interface ErrorHandler<T> {
        T handle(Board board, int xError, int yError, int wError);
    }

    static public boolean isValid(final Board board) {
        
        final Boolean error = scan(board, (b, x, y, w) -> Boolean.TRUE);
        
        return (error == null);
    }
    
    static public String findError(final Board board) {
        
        return scan(board, Checker::buildErrorString);
    }

    static private <T> T scan(
            final Board board,
            final ErrorHandler<T> handler) {

        for (int x = 0; x < board.getSize() - 1; x++) {
            for (int y = 0; y < board.getSize() - 1; y++) {
                for (int i = 1; i < board.getSize() - Integer.max(x, y); i++) {
                    if ((board.getCellContent(x, y) == board.getCellContent(x + i, y)) &&
                       (board.getCellContent(x, y) == board.getCellContent(x, y + i)) &&
                       (board.getCellContent(x, y) == board.getCellContent(x + i, y + i))) {
                        return handler.handle(board, x, y, i);
                    }
                }
            }
        }
        
        return null;
    }
    
    static private String buildErrorString(
            final Board board,
            final int xError,
            final int yError,
            final int wError) {
        
        final StringBuilder builder = new StringBuilder(board.getSize() * (3 * board.getSize() + 1));
        
        for (int y = board.getSize() -1; y >= 0; y--) {
            for (int x = 0; x < board.getSize(); x++) {
                char highlight;
                if (((x == xError) && (y == yError)) ||
                    ((x == xError + wError) && (y == yError)) ||
                    ((x == xError) && (y == yError + wError)) ||
                    ((x == xError + wError) && (y == yError + wError))) {
                    highlight = '|';
                } else {
                    highlight = ' ';
                }
                char value;
                switch (board.getCellContent(x, y)) {
                    case Board.EMPTY : value = ' '; break;
                    case Board.RED : value = 'R'; break;
                    case Board.BLUE : value = 'B'; break;
                    default: throw new AssertionError();
                }
                builder.append(highlight);
                builder.append(value);
                builder.append(highlight);
            }
            builder.append('\n');
        }

        return builder.toString();
    }

}
