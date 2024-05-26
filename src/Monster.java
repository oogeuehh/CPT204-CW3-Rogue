import java.util.*;

public class Monster extends  Character{

    public Monster(Game game) {

        super(game);

    }

    @Override
    public Site move() {
        Site monster = game.getMonsterSite();
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


}