package Main;

import Data.Board;
import Processor.Solver;

public class Main {

    final private Solver a_solver;
    
    public static void main(String[] args) {

        final Main main = new Main();
        main.start();
    }
    
    private Main() {
        
        a_solver = new Solver(b -> this.handle(b));
    }
    
    private void start() {
        
        final Board board = new Board(1);
        board.setCellContent(0, 0, Board.BLUE);
        a_solver.generateLargerBoard(board);
        
        System.out.println("Done!");
    }
    
    private void handle(final Board board) {
        
        if (board.getSize() > 12) {
            System.out.println(board.getSize());
            System.out.println(board.dumpToString());
        }
        
        a_solver.generateLargerBoard(board);        
    }
}
