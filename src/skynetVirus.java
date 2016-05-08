import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private static Set<Integer> visited = new HashSet<Integer>();
    private static Set<Integer> gateways = new HashSet<Integer>();
    static int N = 0;
    static int[][] GM;
    static String lastCommand = null;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt(); // the total number of nodes in the level, including the gateways
        GM = new int[N][N];


        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways


        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            GM[N1][N2] = 1;
            GM[N2][N1] = 1;
        }

        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            gateways.add(EI);
        }

        System.err.println(gateways.iterator().next());
        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

            // Write an action using System.out.println()


            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            Map<Integer, Queue<Integer>> queueNextLevel = new HashMap<Integer, Queue<Integer>>();
            queueNextLevel.put(SI, getChildren(SI));

            findFirstExit(queueNextLevel);
            System.out.println(lastCommand);
        }
    }

    private static Queue<Integer> getChildren(int x){
        Queue<Integer> set = new LinkedList<Integer>();
        for (int i = 0; i < N; i++) {
            if (GM[x][i] == 1) {
                set.add(i);
            }
        }
        return set;
    }

    public static String findFirstExit(Map<Integer, Queue<Integer>> level){
        Map<Integer, Queue<Integer>> nextLevel = new HashMap<Integer, Queue<Integer>>();
        for (Integer currentRoot: level.keySet()  ) {
            Queue<Integer> children = level.get(currentRoot);
            visited.add(currentRoot);
            while (!children.isEmpty()){
                int currentChildNode = children.remove();
                if (gateways.contains(currentChildNode)){
                    lastCommand = ""+currentRoot+" "+currentChildNode;
                    return ""+currentRoot+" "+currentChildNode;
                }
                nextLevel.put(currentChildNode, getChildren(currentChildNode));
            }
        }
        if (!nextLevel.isEmpty()) {
            findFirstExit(nextLevel);
        }
        return null;
    }
}