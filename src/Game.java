import java.io.IOException;

public class Game {

    // portable newline
    private final static String NEWLINE = System.getProperty("line.separator");
  
    private Dungeon dungeon;     // the dungeon
    private char MONSTER;        // name of the monster (A - Z)
    private char ROGUE = '@';    // name of the rogue
    private int N;               // board dimension
    private Site monsterSite;    // location of monster
    private Site rogueSite;      // location of rogue
    private Monster monster;     // the monster
    private Rogue rogue;         // the rogue
    private boolean isGameOver = false; // status of game

    public Game(String fileName) throws IOException {
        char[][] board = Reader.readDungeon(fileName);
        N = board.length;
        initializeGame(board);
    }

    private void initializeGame(char[][] board) {
        dungeon = new Dungeon(board);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] >= 'A' && board[i][j] <= 'Z') {
                    MONSTER = board[i][j];
                    board[i][j] = '.';
                    monsterSite = new Site(i, j);
                }
                if (board[i][j] == ROGUE) {
                    board[i][j] = '.';
                    rogueSite = new Site(i, j);
                }
            }
        }
        monster = new Monster(this);
        rogue = new Rogue(this);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void step() {
        if (isGameOver) return;

        if (monsterSite.equals(rogueSite)) {
            isGameOver = true;
            return;
        }

        // Monster moves
        Site next = monster.move();
        if (dungeon.isLegalMove(monsterSite, next)) monsterSite = next;
        else throw new RuntimeException("Monster caught cheating");

        if (monsterSite.equals(rogueSite)) {
            isGameOver = true;
            return;
        }

        // Rogue moves
        next = rogue.move();
        if (dungeon.isLegalMove(rogueSite, next)) rogueSite = next;
        else throw new RuntimeException("Rogue caught cheating");

        if (monsterSite.equals(rogueSite)) {
            isGameOver = true;
        }
    }

    // return position of monster and rogue
    public Site getMonsterSite() { return monsterSite; }
    public Site getRogueSite()   { return rogueSite;   }
    public Dungeon getDungeon()  { return dungeon;     }


    // play until monster catches the rogue
    public void play() {
        for (int t = 1; true; t++) {
            System.out.println("Move " + t);
            System.out.println();

            // monster moves
            if (monsterSite.equals(rogueSite)) break;
            Site next = monster.move();
            if (dungeon.isLegalMove(monsterSite, next)) monsterSite = next;
            else throw new RuntimeException("Monster caught cheating");
            System.out.println(this);

            // rogue moves
            if (monsterSite.equals(rogueSite)) break;
            next = rogue.move();
            if (dungeon.isLegalMove(rogueSite, next)) rogueSite = next;
            else throw new RuntimeException("Rogue caught cheating");
            System.out.println(this);

//            // make process visible
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }

        System.out.println("Caught by monster");

    }


    // string representation of game state (inefficient because of Site and string concat)
    public String toString() {
        //use stringBuilder instead of string
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Site site = new Site(i, j);
                if (rogueSite.equals(monsterSite) && (rogueSite.equals(site))) s.append("* ");
                else if (rogueSite.equals(site)) s.append(ROGUE).append(" ");
                else if (monsterSite.equals(site)) s.append(MONSTER).append(" ");
                else if (dungeon.isRoom(site)) s.append(". ");
                else if (dungeon.isCorridor(site)) s.append("+ ");
                else if (dungeon.isRoom(site)) s.append(". ");
                else if (dungeon.isWall(site)) s.append("  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


    public static void main(String[] args) {
        try {
            Game game = new Game("dungeonA.txt");
            System.out.println(game);
            game.play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
