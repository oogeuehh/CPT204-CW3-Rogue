import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Rogue extends Character{

    public Rogue(Game game) {
        super(game);
    }

    @Override
    public Site move() {
//        Site monster = game.getMonsterSite();
        Site rogue   = game.getRogueSite();
        Site monster = game.getMonsterSite();
        Site bestMove = rogue;
        int maxDist = -1;
        int minLoopDist = Integer.MAX_VALUE;

        for (Site neighbor : getNeighbors(rogue)) {
            int distToMonster = getDistToSite(neighbor, monster);
            int distToLoop = getDistToNearestLoop(neighbor); // Placeholder

            if (distToMonster > maxDist || (distToMonster == maxDist && distToLoop < minLoopDist)) {
                maxDist = distToMonster;
                minLoopDist = distToLoop;
                bestMove = neighbor;
            }
        }

        return bestMove;
    }

    private int getDistToNearestLoop(Site site) {
        // Placeholder for actual loop distance calculation
        return 0; // Assume 0 distance to loop as a placeholder
    }

}
