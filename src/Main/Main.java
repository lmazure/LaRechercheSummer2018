package Main;

import java.io.IOException;

import Data.Board;
import Data.BoardStack;

public class Main {

    final private BoardStack a_bordStack;
    
    public static void main(String[] args) {

        final Main main = new Main();
        main.start();
    }
    
    private Main() {
        
        a_bordStack = new BoardStack(10_000);
    }
    
    private void start() {
        
        final Board board = new Board(1);
        board.setCellContent(0, 0, Board.BLUE);
        try {
            a_bordStack.push(board);
        } catch (final IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        final int numberOfThreads = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < numberOfThreads; i++) {
            final Worker worker = new Worker(a_bordStack); 
            final Thread thread = new Thread(worker);
            thread.start();
        }
    }
}
