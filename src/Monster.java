import java.util.*;

public class Monster extends  Character{
    private Map<Site, Map<Site, Integer>> distanceCache;// storage path cache

    public Monster(Game game) {

        super(game);
        distanceCache = new HashMap<>();

    }

    /**
     * Monster's move strategy: Find the shortest path to the rogue using cached distances.
     *
     * @return the next site for the monster to move to
     */
    @Override
    public Site move() {
        Site monster = game.getMonsterSite();
        Site rogue = game.getRogueSite();
        List<Site> shortestNeighbors = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;

        // Find shortest distance to rogue and collect all neighbors with the same shortest distance
        for (Site neighbor : getNeighbors(monster)) {
            int distToRogue = getDistToSiteCached(neighbor, rogue);

            if (distToRogue < minDist) {
                minDist = distToRogue;
                shortestNeighbors.clear();
                shortestNeighbors.add(neighbor);
            } else if (distToRogue == minDist) {
                shortestNeighbors.add(neighbor);
            }
        }

        // Randomly choose one neighbor from the shortestNeighbors list
        Random random = new Random();
        Site bestMove = shortestNeighbors.get(random.nextInt(shortestNeighbors.size()));

        return bestMove != null ? bestMove : monster;
    }

    /**
     * Get the cached distance between two sites, computing it if necessary.
     *
     * @param from the starting site
     * @param to the destination site
     * @return the distance between the two sites
     */
    private int getDistToSiteCached(Site from, Site to) {
        distanceCache.putIfAbsent(from, new HashMap<>());
        Map<Site, Integer> fromCache = distanceCache.get(from);

        if(!fromCache.containsKey(to)) {
            int distance = dungeon.dijkstra(from.i()*N + from.j(), to.i()*N + to.j());
            fromCache.put(to, distance);
        }

        return fromCache.get(to);
    }


}
