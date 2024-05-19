import java.util.LinkedList;
import java.util.Queue;

public class Monster {
    private Game game;
    private Dungeon dungeon;
    private int N;
    private Site[] vertics;

    public Monster(Game game) {
        this.game    = game;
        this.dungeon = game.getDungeon();
        this.N       = dungeon.size();
    }


    public Site move() {
        Site monster = game.getMonsterSite();
        Site rogue   = game.getRogueSite();
        Site move    = null;

        return rogue;
    }

}
