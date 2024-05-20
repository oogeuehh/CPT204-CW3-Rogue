import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    protected Game game;
    protected Dungeon dungeon;
    protected int N;

    public Character(Game game) {
        this.game = game;
        this.dungeon = game.getDungeon();
        this.N = dungeon.size();
    }

    public abstract Site move();

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

    protected int getDistToSite(Site from, Site to) {
        return dungeon.dijkstra(from.i() * N + from.j(), to.i() * N + to.j());
    }
}
