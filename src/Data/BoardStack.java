package Data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class BoardStack {

    final private int a_size;
    private Deque<Board> a_boards;
    final private Deque<File> a_files;
    
    public BoardStack(final int maximumNumberOfBoardsInMemory) {
        
        a_size = maximumNumberOfBoardsInMemory;
        a_boards = new ArrayDeque<Board>(a_size);
        a_files = new ArrayDeque<File>();
    }
    
    public void push(final Board board) throws IOException {
        
        synchronized (this) {
            if (a_boards.size() == a_size) {
                final File file = writeBoardsToFile(a_boards, a_files.size());
                a_files.push(file);
                a_boards.clear();
            }
            a_boards.push(board);
        }
    }
    
    public Board pop() throws IOException {
        
        synchronized (this) {
            if (a_boards.isEmpty()) {
                final File file = a_files.pop();
                retrieveBoardsFromFile(file, a_boards);
            }
            return a_boards.pop();            
        }
    }

    public boolean isEmpty() {
    
        return a_boards.isEmpty() && a_files.isEmpty();
    }
    
    static private File writeBoardsToFile(
            final Deque<Board> boards,
            final int fileNumber) throws IOException {
        
        final String prefix = String.format("%08d", fileNumber);
        final String suffix = ".boards";
        final File file = File.createTempFile(prefix, suffix);
        file.deleteOnExit();
        
        try ( final FileOutputStream s = new FileOutputStream(file);
              final DataOutputStream os = new DataOutputStream(s)) {
            os.writeInt(boards.size());
            while (!boards.isEmpty()) {
                boards.pop().writeToStream(os);
            }
        }
        
        return file;
    }

    private void retrieveBoardsFromFile(
            final File file,
            final Deque<Board> boards) throws IOException {
        
        assert boards.isEmpty();
        
        try ( final FileInputStream s = new FileInputStream(file);
              final DataInputStream is = new DataInputStream(s)) {
            final int size = is.readInt();
            for (int i = 0; i < size; i++) {
                boards.addLast(Board.generateFromStream(is));
            }
        }
    }
}
