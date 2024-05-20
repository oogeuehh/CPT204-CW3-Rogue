import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Dungeon {
    private boolean[][] isRoom;        // is v-w a room site?
    private boolean[][] isCorridor;    // is v-w a corridor site?
    private int N;                     // dimension of dungeon

    //adjacency list -- hash table -- arrayList
    private HashMap<Integer, ArrayList<Integer>> adjMap;

    // initialize a new dungeon based on the given board
    public Dungeon(char[][] board) {
        N = board.length;
        isRoom     = new boolean[N][N];
        isCorridor = new boolean[N][N];
        adjMap     = new HashMap<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if      (board[i][j] == '.') isRoom[i][j] = true;
                else if (board[i][j] == '+') isCorridor[i][j] = true;
            }
        }

        // traverse each node of the dungeon
        // Check the point to the surrounding grid is valid or not
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                // get the center nodeID
                int nodeID = i*N + j;

                // traverse the surrounding
                for(int offsetI = -1; offsetI <= 1; offsetI++) {
                    for(int offsetJ = -1; offsetJ <= 1; offsetJ++){
                        //original point
                        if(offsetI == 0 && offsetJ == 0) continue;

                        int newI = i + offsetI;
                        int newJ = j + offsetJ;
                        if(newI < 0 && newI >= N && newJ < 0 && newJ >= N) continue;

                        // get the surrounding nodeID
                        int gridNodeID = newI * N + newJ;

                        Site center = new Site(i,j);
                        Site gridNode = new Site(newI,newJ);

                        // move valid, add to adjMap
                        if(isLegalMove(center, gridNode)) {
                            adjMap.putIfAbsent(nodeID,new ArrayList<>());
                            adjMap.get(nodeID).add(gridNodeID);
                        }
                    }
                }
            }
        }
//        System.out.println(adjMap);
    }

    // dijkstra algorithm
    // 用nodeID不用site是因为site还要转化成nodeID
    public int dijkstra(int startNodeID, int endNodeID){
        // create two map to store set of nodes, nodeID, shortest dist
        HashMap<Integer, Integer> distMap = new HashMap<>();
        HashMap<Integer, Integer> waitingMap = new HashMap<>();
        //initialization, put node into waitingMap
        waitingMap.put(startNodeID, 0);


        //iteration, until waitingMap is null
        while(!waitingMap.isEmpty()){
            // calculate minimum dist of waitingMap
            int minNodeID = -1;
            int minDist = Integer.MAX_VALUE;
            for(Map.Entry<Integer, Integer> entry:waitingMap.entrySet()){
                int thisNodeID = entry.getKey();
                int thisDist = entry.getValue();

                // check whether the value is less than minDist
                if(thisDist<minDist){
                    minNodeID = thisNodeID;
                    minDist = thisDist;
                }
            }

            // take the minDist
            waitingMap.remove(minNodeID);
            // update distMap
            distMap.put(minNodeID, minDist);


            // check the reaching points
            for(int neighbourNodeID: adjMap.getOrDefault(minNodeID, new ArrayList<>())){
                if(distMap.containsKey(neighbourNodeID)) continue;

                int newDist = minDist + 1;
                if(!waitingMap.containsKey(neighbourNodeID) || newDist < waitingMap.get(neighbourNodeID)) {
                    waitingMap.put(neighbourNodeID, newDist);
                }
            }
        }

        return distMap.getOrDefault(endNodeID, -1);

    }

    // return dimension of dungeon
    public int size() { return N; }

    // does v correspond to a corridor site? 
    public boolean isCorridor(Site v) {
        int i = v.i();
        int j = v.j();
        return i >= 0 && j >= 0 && i < N && j < N && isCorridor[i][j];
    }

    // does v correspond to a room site?
    public boolean isRoom(Site v) {
        int i = v.i();
        int j = v.j();
        return i >= 0 && j >= 0 && i < N && j < N && isRoom[i][j];
    }

    // does v correspond to a wall site?
    public boolean isWall(Site v) {
        return (!isRoom(v) && !isCorridor(v));
    }

    // does v-w correspond to a legal move?
    public boolean isLegalMove(Site v, Site w) {
        int i1 = v.i();
        int j1 = v.j();
        int i2 = w.i();
        int j2 = w.j();
        if (i1 < 0 || j1 < 0 || i1 >= N || j1 >= N) return false;
        if (i2 < 0 || j2 < 0 || i2 >= N || j2 >= N) return false;
        if (isWall(v) || isWall(w)) return false;
        if (Math.abs(i1 - i2) > 1)  return false;
        if (Math.abs(j1 - j2) > 1)  return false;
        if (isRoom(v) && isRoom(w)) return true;
        if (i1 == i2)               return true;
        if (j1 == j2)               return true;

        return false;
    }

}
