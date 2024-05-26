import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Rogue extends Character{

    public Rogue(Game game) {
        super(game);
    }

    @Override
    public Site move() {
        Site rogue = game.getRogueSite();
        Site monster = game.getMonsterSite();
        Site bestMove = rogue;
        int maxDist = -1;
        int minLoopDist = Integer.MAX_VALUE;

        List<Site> longestPathNeighbors = new ArrayList<>();

        for (Site neighbor : getNeighbors(rogue)) {
            int distToMonster = getDistToSite(neighbor, monster);
            int distToLoop = getDistToNearestLoop(neighbor);
            //System.out.println("Neighbor: " + neighbor + ", Distance to monster: " + distToMonster);

            if (distToMonster > maxDist || (distToMonster == maxDist && distToLoop < minLoopDist)) {
                maxDist = distToMonster;
                minLoopDist = distToLoop;
                longestPathNeighbors.clear();
                longestPathNeighbors.add(neighbor);
            } else if (distToMonster == maxDist && distToLoop == minLoopDist) {
                longestPathNeighbors.add(neighbor);
            }
        }
/*// Print the longest path neighbors
        System.out.println("Longest path neighbors:");
        for (Site neighbor : longestPathNeighbors) {
            System.out.println(neighbor);
        }*/

        // Randomly choose one neighbor from the longest path neighbors
        if (!longestPathNeighbors.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(longestPathNeighbors.size());
            bestMove = longestPathNeighbors.get(randomIndex);
        }

        //System.out.println("Best move chosen: " + (bestMove != null ? bestMove : monster));
        return bestMove;
    }

    private int getDistToNearestLoop(Site site) {
        // Placeholder for actual loop distance calculation
        return 0; // Assume 0 distance to loop as a placeholder
    }

}
