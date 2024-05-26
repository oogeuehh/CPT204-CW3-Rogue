<<<<<<< HEAD
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
=======
import java.util.*;
>>>>>>> bf73991 (Initial commit)

public class Monster extends  Character{

    public Monster(Game game) {

        super(game);

    }

    @Override
    public Site move() {
        Site monster = game.getMonsterSite();
<<<<<<< HEAD
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

=======
        Site rogue = game.getRogueSite();
        List<Site> shortestNeighbors = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;

        //System.out.println("Monster current site: " + monster);
        //System.out.println("Rogue current site: " + rogue);

        // Find shortest distance to rogue and collect all neighbors with the same shortest distance
        for (Site neighbor : getNeighbors(monster)) {
            int distToRogue = getDistToSite(neighbor, rogue);
            //System.out.println("Neighbor: " + neighbor + ", Distance to Rogue: " + distToRogue);

            if (distToRogue < minDist) {
                minDist = distToRogue;
                shortestNeighbors.clear();
                shortestNeighbors.add(neighbor);
            } else if (distToRogue == minDist) {
                shortestNeighbors.add(neighbor);
            }
        }

       /* // Print the shortest path neighbors
        System.out.println("Longest path neighbors:");
        for (Site neighbor : shortestNeighbors) {
            System.out.println(neighbor);
        }
*/
        // Randomly choose one neighbor from the shortestNeighbors list
        Random random = new Random();
        Site bestMove = shortestNeighbors.get(random.nextInt(shortestNeighbors.size()));

        //System.out.println("Best move chosen: " + (bestMove != null ? bestMove : monster));
        return bestMove != null ? bestMove : monster;
    }


>>>>>>> bf73991 (Initial commit)
}
