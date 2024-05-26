import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private static final String DUNGEON_PATH = "src/Dungeons/";

    public static char[][] readDungeon(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(DUNGEON_PATH + fileName));
        int N = Integer.parseInt(reader.readLine());
        char[][] board = new char[N][N];

        //System.out.println("Reading dungeon of size: " + N);

        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(2 * j);
                //System.out.println("Read cell (" + i + ", " + j + "): " + board[i][j]);
            }
        }

        reader.close();
        return board;
    }

}
