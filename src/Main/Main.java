package Main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Data.Board;
import Processor.Solver;

public class Main {

    final private Solver a_solver;
    final private ExecutorService a_executorService;
    
    public static void main(String[] args) {

        final Main main = new Main();
        main.start();
    }
    
    private Main() {
        
        a_solver = new Solver(b -> this.handle(b));
        a_executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }
    
    private void start() {
        
        final Board board = new Board(1);
        board.setCellContent(0, 0, Board.BLUE);
        handle(board);
    }
    
    private void handle(final Board board) {
               
        if (board.getSize() > 12) {
            System.out.println(board.getSize());
            System.out.println(board.dumpToString());
        }
        
        //System.out.println("new job");
        a_executorService.execute(() -> a_solver.generateLargerBoard(board));
        //a_solver.generateLargerBoard(board);
    }
}
