import java.util.ArrayList;
<<<<<<< HEAD
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

=======
import java.util.List;
import java.util.Random;
>>>>>>> bf73991 (Initial commit)
public class Rogue extends Character{

    public Rogue(Game game) {
        super(game);
    }

    @Override
    public Site move() {
<<<<<<< HEAD
//        Site monster = game.getMonsterSite();
        Site rogue   = game.getRogueSite();
=======
        Site rogue = game.getRogueSite();
>>>>>>> bf73991 (Initial commit)
        Site monster = game.getMonsterSite();
        Site bestMove = rogue;
        int maxDist = -1;
        int minLoopDist = Integer.MAX_VALUE;

<<<<<<< HEAD
        for (Site neighbor : getNeighbors(rogue)) {
            int distToMonster = getDistToSite(neighbor, monster);
            int distToLoop = getDistToNearestLoop(neighbor); // Placeholder
=======
        List<Site> longestPathNeighbors = new ArrayList<>();

        for (Site neighbor : getNeighbors(rogue)) {
            int distToMonster = getDistToSite(neighbor, monster);
            int distToLoop = getDistToNearestLoop(neighbor);
            //System.out.println("Neighbor: " + neighbor + ", Distance to monster: " + distToMonster);
>>>>>>> bf73991 (Initial commit)

            if (distToMonster > maxDist || (distToMonster == maxDist && distToLoop < minLoopDist)) {
                maxDist = distToMonster;
                minLoopDist = distToLoop;
<<<<<<< HEAD
                bestMove = neighbor;
            }
        }

=======
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
>>>>>>> bf73991 (Initial commit)
        return bestMove;
    }

    private int getDistToNearestLoop(Site site) {
        // Placeholder for actual loop distance calculation
        return 0; // Assume 0 distance to loop as a placeholder
    }

}
