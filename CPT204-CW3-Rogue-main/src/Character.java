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

        //System.out.println("Current site: " + site);

        for (int i : directions) {
            for (int j : directions) {
                if (i != 0 || j != 0) {
                    Site neighbor = new Site(site.i() + i, site.j() + j);
                    //System.out.println("Checking neighbor: " + neighbor);
                    boolean legalMove = dungeon.isLegalMove(site, neighbor);
                    //System.out.println("Neighbor: " + neighbor + " is " + (legalMove ? "legal" : "not legal"));
                    if (legalMove) {
                        neighbors.add(neighbor);
                    }
                }
            }
        }

        //System.out.println("Neighbors found: " + neighbors);
        return neighbors;
    }



    protected int getDistToSite(Site from, Site to) {
        int start = from.i() * N + from.j();
        int end = to.i() * N + to.j();
        //System.out.println("Calculating distance from " + from + " (ID: " + start + ") to " + to + " (ID: " + end + ")");
        int distance = dungeon.dijkstra(start, end);
       // System.out.println("Distance calculated: " + distance);
        return distance;
    }


}
