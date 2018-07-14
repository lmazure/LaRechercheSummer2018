package Processor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Data.Board;

class CheckerTest extends Checker {

    @Test
    void testValid() {
        // --- arrange ---
        
        final Board board = TestHelper.buildFromString(
                "BRBRBRRRBBBRR" + 
                "RRBBBBRBBRRRB" + 
                "BRRBRBRRRRBBB" + 
                "BRBRRBBRBRRBR" + 
                "BBBBRRBBRRBRR" + 
                "RBRRRBRBBBBBR" + 
                "BRRBBBBBRBRRR" + 
                "BBRRBRRBBRRBB" + 
                "RBRBRRBRBBRRB" + 
                "RBBBBRRRRBRBR" + 
                "RRBRRRBBRBBBB" + 
                "RBRRBBBRRRBRB" + 
                "RRRBBRBBBRBBR");
        
        // --- assert ---

        assertNull(Checker.findError(board));
        assertTrue(Checker.isValid(board));
    }

    @Test
    void testInvalidLargeSquare() {
        // --- arrange ---
        
        final Board board = TestHelper.buildFromString(
                "RRBRBRRRBBBRR" + 
                "RRBBBBRBBRRRB" + 
                "BRRBRBRRRRBBB" + 
                "BRBRRBBRBRRBR" + 
                "BBBBRRBBRRBRR" + 
                "RBRRRBRBBBBBR" + 
                "BRRBBBBBRBRRR" + 
                "BBRRBRRBBRRBB" + 
                "RBRBRRBRBBRRB" + 
                "RBBBBRRRRBRBR" + 
                "RRBRRRBBRBBBB" + 
                "RBRRBBBRRRBRB" + 
                "RRRBBRBBBRBBR");
        
        // --- assert ---

        assertNotNull(Checker.findError(board));
        assertFalse(Checker.isValid(board));
    }
    
    @Test
    void testInvalidSmallSquareBottomLeft() {
        // --- arrange ---
        
        final Board board = TestHelper.buildFromString(
                "BRBRBRRRBBBRR" + 
                "RRBBBBRBBRRRB" + 
                "BRRBRBRRRRBBB" + 
                "BRBRRBBRBRRBR" + 
                "BBBBRRBBRRBRR" + 
                "RBRRRBRBBBBBR" + 
                "BRRBBBBBRBRRR" + 
                "BBRRBRRBBRRBB" + 
                "RBRBRRBRBBRRB" + 
                "RBBBBRRRRBRBR" + 
                "RRBRRRBBRBBBB" + 
                "RRRRBBBRRRBRB" + 
                "RRRBBRBBBRBBR");
        
        // --- assert ---

        assertNotNull(Checker.findError(board));
        assertFalse(Checker.isValid(board));
    }
    
    @Test
    void testInvalidSmallSquareTopLeft() {
        // --- arrange ---
        
        final Board board = TestHelper.buildFromString(
                "RRB" + 
                "RRB" + 
                "BRR");
        
        // --- assert ---

        assertNotNull(Checker.findError(board));
        assertFalse(Checker.isValid(board));
    }

    @Test
    void testInvalidSmallSquareTopRight() {
        // --- arrange ---
        
        final Board board = TestHelper.buildFromString(
                "BRR" + 
                "RRR" + 
                "BBB");
        
        // --- assert ---

        assertNotNull(Checker.findError(board));
        assertFalse(Checker.isValid(board));
    }
    
    @Test
    void testInvalidSmallSquareBottomRight() {
        // --- arrange ---
        
        final Board board = TestHelper.buildFromString(
                "BRBR" + 
                "BRBB" + 
                "RRBB" + 
                "RBBB");
        
        // --- assert ---

        assertNotNull(Checker.findError(board));
        assertFalse(Checker.isValid(board));
    }
}
