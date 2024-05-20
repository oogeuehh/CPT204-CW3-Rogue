import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Monster extends  Character{

    public Monster(Game game) {

        super(game);

    }

    @Override
    public Site move() {
        Site monster = game.getMonsterSite();
        Site rogue   = game.getRogueSite();
        Site bestMove = monster;
        int minDist = Integer.MAX_VALUE;

        for (Site neighbor : getNeighbors(monster)) {
            int distToRogue = getDistToSite(neighbor, rogue);

            if (distToRogue < minDist) {
                minDist = distToRogue;
                bestMove = neighbor;
            }
        }

        return bestMove != null ? bestMove : monster;
    }

}
