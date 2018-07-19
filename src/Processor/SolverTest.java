package Processor;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Data.Board;

public class SolverTest {


    @Test
    void testSmallest() {

        // --- arrange ---
        
        final Board board = TestHelper.buildFromString("B");
        final Set<Board> set = new HashSet<Board>(8);
        
        // --- act ---

        final Solver solver = new Solver(b -> set.add(b));
        solver.generateLargerBoard(board);

        // --- assert ---

        assertEquals(5 , set.size());
        assertTrue(set.contains(TestHelper.buildFromString("BBBR")) ^ set.contains(TestHelper.buildFromString("RBBB")));
        assertTrue(set.contains(TestHelper.buildFromString("BRBR")) ^ set.contains(TestHelper.buildFromString("RRBB")));
        assertTrue(set.contains(TestHelper.buildFromString("BRBB")));
        assertTrue(set.contains(TestHelper.buildFromString("RBBR")));
        assertTrue(set.contains(TestHelper.buildFromString("RRBR")));
    }
    
    @Test
    void test() {

        final Board board = TestHelper.buildFromString("BRBB");
        
        // --- act ---

        final Solver solver = new Solver(b -> assertTrue(Checker.isValid(b)));
        solver.generateLargerBoard(board);
    }
}
