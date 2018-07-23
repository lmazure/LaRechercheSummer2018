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
    private int a_currentRecord;
    //private int a_nb;
    
    public BoardStack(final int maximumNumberOfBoardsInMemory) {
        
        a_size = maximumNumberOfBoardsInMemory;
        a_boards = new ArrayDeque<Board>(a_size);
        a_files = new ArrayDeque<File>();
        a_currentRecord = 0;
        //a_nb = 0;
    }
    
    synchronized public void push(final Board board) throws IOException {

        //a_nb++;
        //System.out.println("push " + a_boards.size() + " " + board.getSize() + " " + a_nb);

        if (board.getSize() >= a_currentRecord) {
            System.out.println(board.getSize());
            System.out.println(board.dumpToString());
            a_currentRecord = board.getSize();
        }

        if (a_boards.size() == a_size) {
            final File file = writeBoardsToFile(a_boards, a_files.size());
            a_files.push(file);
            a_boards.clear();
        }
        a_boards.push(board);
    }
    
    synchronized public Board pop() throws IOException {

        //System.out.println("pop " + a_boards.size());
        
        if (a_boards.isEmpty()) {
            final File file = a_files.pop();
            retrieveBoardsFromFile(file, a_boards);
        }
        return a_boards.pop();            
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
        //System.out.println("Created file " + file.getAbsolutePath());
        
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

        //System.out.println("Retrieved file " + file.getAbsolutePath());
        
        try ( final FileInputStream s = new FileInputStream(file);
              final DataInputStream is = new DataInputStream(s)) {
            final int size = is.readInt();
            for (int i = 0; i < size; i++) {
                boards.addLast(Board.generateFromStream(is));
            }
        }
    }
}
