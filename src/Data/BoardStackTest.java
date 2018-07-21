package Data;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class BoardStackTest {

    @Test
    void testInMemory() throws IOException {

        // --- arrange ---
        
        final Board board1 = TestHelper.buildFromString("RRRRRRRRR");
        final Board board2 = TestHelper.buildFromString("BRRRRRRRB");
        final Board board3 = TestHelper.buildFromString("RBBRRRBBR");
        final Board board4 = TestHelper.buildFromString("RRRBBBRRR");
        final BoardStack stack = new BoardStack(10);
        stack.push(board1);
        stack.push(board2);
        stack.push(board3);
        stack.push(board4);

        // --- assert ---

        assertEquals(board4, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(board3, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(board2, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(board1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testExceptionIfPopEmpty() throws IOException {

        // --- arrange ---
        
        final Board board1 = TestHelper.buildFromString("RRRRRRRRR");
        final Board board2 = TestHelper.buildFromString("BRRRRRRRB");
        final Board board3 = TestHelper.buildFromString("RBBRRRBBR");
        final Board board4 = TestHelper.buildFromString("RRRBBBRRR");
        final BoardStack stack = new BoardStack(10);
        stack.push(board1);
        stack.push(board2);
        stack.push(board3);
        stack.push(board4);

        // --- assert ---

        assertEquals(board4, stack.pop());
        assertEquals(board3, stack.pop());
        assertEquals(board2, stack.pop());
        assertEquals(board1, stack.pop());
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }


    @Test
    void testOutOfMemory() throws IOException {

        // --- arrange ---
        
        final Board board1 = TestHelper.buildFromString("RRRRRRRRR");
        final Board board2 = TestHelper.buildFromString("BRRRRRRRB");
        final Board board3 = TestHelper.buildFromString("RBBRRRBBR");
        final Board board4 = TestHelper.buildFromString("RRRBBBRRR");
        final Board board5 = TestHelper.buildFromString("BRRRRRRRR");
        final Board board6 = TestHelper.buildFromString("BRRRRRRRB");
        final Board board7 = TestHelper.buildFromString("BBBRRRBBR");
        final Board board8 = TestHelper.buildFromString("BRRBBBRRR");
        final Board board9 = TestHelper.buildFromString("RRRRRRRRB");
        final Board boardA = TestHelper.buildFromString("BRRRRRRRR");
        final Board boardB = TestHelper.buildFromString("RBBRRRBBB");
        final Board boardC = TestHelper.buildFromString("RRRBBBRRB");
        final BoardStack stack = new BoardStack(3);
        stack.push(board1);
        stack.push(board2);
        stack.push(board3);
        stack.push(board4);
        stack.push(board5);
        stack.push(board6);
        stack.push(board7);
        stack.push(board8);
        stack.push(board9);
        stack.push(boardA);
        stack.push(boardB);
        stack.push(boardC);

        // --- assert ---

        assertEquals(boardC, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(boardB, stack.pop());
        assertFalse(stack.isEmpty());
        assertFalse(stack.isEmpty());
        assertEquals(boardA, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(board9, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(board8, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(board7, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(board6, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(board5, stack.pop());
        assertFalse(stack.isEmpty());
        assertFalse(stack.isEmpty());
        assertEquals(board4, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(board3, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(board2, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(board1, stack.pop());
        assertTrue(stack.isEmpty());
    }
}
