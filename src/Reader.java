import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
<<<<<<< HEAD
    private static final String DUNGEON_PATH = "src/Dungeons/";
=======
    private static final String DUNGEON_PATH = "Dungeons/";
>>>>>>> bf73991 (Initial commit)

    public static char[][] readDungeon(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(DUNGEON_PATH + fileName));
        int N = Integer.parseInt(reader.readLine());
        char[][] board = new char[N][N];

<<<<<<< HEAD
=======
        //System.out.println("Reading dungeon of size: " + N);

>>>>>>> bf73991 (Initial commit)
        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(2 * j);
<<<<<<< HEAD
=======
                //System.out.println("Read cell (" + i + ", " + j + "): " + board[i][j]);
>>>>>>> bf73991 (Initial commit)
            }
        }

        reader.close();
        return board;
    }
<<<<<<< HEAD
=======

>>>>>>> bf73991 (Initial commit)
}
