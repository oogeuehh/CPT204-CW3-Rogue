import java.util.ArrayList;
import java.lang.Character;

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
        isRoom = new boolean[N][N];
        isCorridor = new boolean[N][N];
        adjMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char cell = board[i][j];
                if (cell == '.') {
                    isRoom[i][j] = true;
                } else if (cell == '+') {
                    isCorridor[i][j] = true;
                } else if (Character.isUpperCase(cell) || cell == '@') { // Uppercase letters for monsters, '@' for rogue
                    isRoom[i][j] = true; // or isCorridor[i][j] = true; based on your game rules
                }
                //System.out.println("Cell (" + i + ", " + j + "): " + cell + ", isRoom: " + isRoom[i][j] + ", isCorridor: " + isCorridor[i][j]);
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

            for(Map.Entry<Integer, Integer> entry : waitingMap.entrySet()){
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
    public boolean isRoom(Site v) {
        int i = v.i();
        int j = v.j();
        boolean result = i >= 0 && j >= 0 && i < N && j < N && isRoom[i][j];
        //System.out.println("isRoom check for (" + i + ", " + j + "): " + result);
        return result;
    }

    public boolean isCorridor(Site v) {
        int i = v.i();
        int j = v.j();
        boolean result = i >= 0 && j >= 0 && i < N && j < N && isCorridor[i][j];
        //System.out.println("isCorridor check for (" + i + ", " + j + "): " + result);
        return result;
    }


    // does v correspond to a wall site?
    public boolean isWall(Site v) {
        boolean result = (!isRoom(v) && !isCorridor(v));
        //System.out.println("isWall check for " + v + ": " + result);
        return result;
    }

    // does v-w correspond to a legal move?
    public boolean isLegalMove(Site v, Site w) {
        int i1 = v.i();
        int j1 = v.j();
        int i2 = w.i();
        int j2 = w.j();

       /* System.out.println("Checking move from " + v + " to " + w);
        System.out.println("i1: " + i1 + ", j1: " + j1);
        System.out.println("i2: " + i2 + ", j2: " + j2);
        System.out.println("N: " + N);*/

        if (i1 < 0 || j1 < 0 || i1 >= N || j1 >= N) {
            //System.out.println("Position out of bounds for v: " + v);
            return false;
        }
        if (i2 < 0 || j2 < 0 || i2 >= N || j2 >= N) {
            //System.out.println("Position out of bounds for w: " + w);
            return false;
        }
        if (isWall(v) || isWall(w)) {
            //System.out.println("Wall check failed for v: " + v + " or w: " + w);
            return false;
        }
        if (Math.abs(i1 - i2) > 1) {
            //System.out.println("Move distance check failed for rows v: " + v + " and w: " + w);
            return false;
        }
        if (Math.abs(j1 - j2) > 1) {
            //System.out.println("Move distance check failed for columns v: " + v + " and w: " + w);
            return false;
        }
        if (isRoom(v) && isRoom(w)) {
            //System.out.println("Legal move from room to room for v: " + v + " and w: " + w);
            return true;
        }
        if (i1 == i2) {
            //System.out.println("Legal move in the same row for v: " + v + " and w: " + w);
            return true;
        }
        if (j1 == j2) {
            //System.out.println("Legal move in the same column for v: " + v + " and w: " + w);
            return true;
        }

        //System.out.println("Move not allowed for v: " + v + " and w: " + w);
        return false;
    }



}
