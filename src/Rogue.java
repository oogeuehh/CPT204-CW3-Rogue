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
        Site bestMove = null;
        int maxDist = -1;

        for (Site neighbor : neighbors) {
            int distToMonster = dfs(neighbor, monster, new HashSet<>());
            if (distToMonster > maxDist) {
                maxDist = distToMonster;
                bestMove = neighbor;
            }
        }

        return (bestMove != null) ? bestMove : rogue; // 如果找不到最佳移动位置，则保持原地
    }

    // DFS方法，计算到目标点的最远距离
    private int dfs(Site start, Site target, Set<Site> visited) {
        if (start.equals(target)) {
            return 0; // 如果当前位置是目标点，则距离为0
        }

        visited.add(start);
        int maxDist = -1;

        List<Site> neighbors = getNeighbors(start);
        for (Site neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                int distToTarget = dfs(neighbor, target, visited);
                maxDist = Math.max(maxDist, distToTarget + 1);
            }
        }

        visited.remove(start);
        return maxDist;
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
