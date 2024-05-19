import java.util.LinkedList;
import java.util.Queue;

public class Rogue {
    private Game game;
    private Dungeon dungeon;
    private int N;
    private boolean[][] marked;
    private Site[][]edgeTo;
    private Site start;

    public Rogue(Game game) {
        this.game    = game;
        this.dungeon = game.getDungeon();
        this.N       = dungeon.size();
        this.marked = new boolean[N][N];
        this.edgeTo = new Site[N][N];
        this.start = game.getRogueSite();
    }

    public Site move() {
        Site monster = game.getMonsterSite();
        Site rogue   = game.getRogueSite();
        Site move    = null;

        return monster;
    }

}
