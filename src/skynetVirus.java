import java.util.*;

/**
 * Sky net virus solution based on Breadth First Search
 * See {@link https://www.codingame.com/ide/43744749f2fe544b05a07aee8a7521d45976f5f}

 **/
class Player {

    private static Set<Integer> visited = new HashSet<Integer>();
    private static Set<Integer> gateways = new HashSet<Integer>();
    private static int N = 0;
    private static int[][] ADJACENCY_MATRIX;
    static String lastCommand = null;   
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt(); // the total number of nodes in the level, including the gateways
        ADJACENCY_MATRIX = new int[N][N];


        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways


        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            ADJACENCY_MATRIX[N1][N2] = 1;
            ADJACENCY_MATRIX[N2][N1] = 1;
        }

        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            gateways.add(EI);
        }
        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            Map<Integer, Queue<Integer>> queueNextLevel = new HashMap<Integer, Queue<Integer>>();
            queueNextLevel.put(SI, getChildren(SI));

            findFirstExit(queueNextLevel);
            System.out.println(lastCommand);
        }
    }

    private static Queue<Integer> getChildren(int x){
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < N; i++) {
            if (ADJACENCY_MATRIX[x][i] == 1) {
                queue.add(i);
            }
        }
        return queue;
    }

    public static void findFirstExit(Map<Integer, Queue<Integer>> level){
        Map<Integer, Queue<Integer>> nextLevel = new HashMap<Integer, Queue<Integer>>();
        for (Integer currentRoot: level.keySet()  ) {
            Queue<Integer> children = level.get(currentRoot);
            visited.add(currentRoot);
            while (!children.isEmpty()){
                int currentChildNode = children.remove();
                if (gateways.contains(currentChildNode)){
                    lastCommand = ""+currentRoot+" "+currentChildNode;
                    return;
                }
                nextLevel.put(currentChildNode, getChildren(currentChildNode));
            }
        }
        if (!nextLevel.isEmpty()) {
            findFirstExit(nextLevel);
        }
        return;
    }
}
