package Main;

import java.io.IOException;
import java.util.NoSuchElementException;

import Data.Board;
import Data.BoardStack;
import Processor.Solver;

public class Worker implements Runnable {

    final private BoardStack a_stack;
    final private Solver a_solver;
    
    public Worker(final BoardStack stack) {
    
        a_stack = stack;
        a_solver = new Solver((board) -> handleFoundBoard(board));
    }
    
    @Override
    public void run() {

        while (true) {
            Board board;
            try {
                board = a_stack.pop();
                a_solver.generateLargerBoard(board);
            } catch (final NoSuchElementException e) {
                // the stack is empty, wait a little and retry
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                    System.exit(1);
                }
            } catch (final IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private void handleFoundBoard(final Board board) {
        
        try {
            a_stack.push(board);
        } catch (final IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
