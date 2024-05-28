import java.util.*;

public class Rogue extends Character {

    public Rogue(Game game) {
        super(game);
    }

    @Override
    public Site move() {
        Site rogue = game.getRogueSite();
        Site monster = game.getMonsterSite();

        List<Site> neighbors = getNeighbors(rogue);
        List<Site> farthestNeighbors = new ArrayList<>();
        int maxDist = -1;

        for (Site neighbor : neighbors) {
            int distToMonster =  bfs(neighbor, monster);
            if (distToMonster > maxDist) {
                maxDist = distToMonster;
                farthestNeighbors.clear();
                farthestNeighbors.add(neighbor);
            } else if (distToMonster == maxDist) {
                farthestNeighbors.add(neighbor);
            }
        }

        if (farthestNeighbors.isEmpty()) {
            return rogue;
        }

        Random random = new Random();
        Site bestMove = farthestNeighbors.get(random.nextInt(farthestNeighbors.size()));

        return bestMove;
    }

    // BFS method to find the shortest path distance
    private int bfs(Site start, Site target) {
        Queue<Site> queue = new LinkedList<>();
        Set<Site> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Site current = queue.poll();
                if (current.equals(target)) {
                    return distance;
                }
                for (Site neighbor : getNeighbors(current)) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            distance++;
        }

        return Integer.MAX_VALUE; // If the target is not reachable
    }

    @Override
    protected List<Site> getNeighbors(Site site) {
        List<Site> neighbors = new ArrayList<>();
        int[] directions = {-1, 0, 1};

        for (int i : directions) {
            for (int j : directions) {
                if (i != 0 || j != 0) {
                    Site neighbor = new Site(site.i() + i, site.j() + j);
                    if (dungeon.isLegalMove(site, neighbor)) {
                        neighbors.add(neighbor);
                    }
                }
            }
        }

        return neighbors;
    }
}

