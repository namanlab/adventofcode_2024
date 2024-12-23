import java.util.*;

class Graph {

    public int[][] adjMatrix;
    public int numVertices;
    public int[] clique;
    public int[] maxClique;
    public int maxCliqueSize;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];
        clique = new int[numVertices];
        maxClique = new int[numVertices];
        maxCliqueSize = 0;
    }

    public void addEdge(int i, int j) {
        adjMatrix[i][j] = 1;
        adjMatrix[j][i] = 1;
    }

    public boolean isClique(int b) {
        for (int i = 0; i < b; i++) {
            for (int j = i + 1; j < b; j++) {
                if (adjMatrix[clique[i]][clique[j]] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void findMaxClique(int start, int l) {
        for (int j = start; j < numVertices; j++) {
            clique[l] = j;
            if (isClique(l + 1)) {
                if (l + 1 > maxCliqueSize) {
                    maxCliqueSize = l + 1;
                    System.arraycopy(clique, 0, maxClique, 0, maxCliqueSize);
                }
                findMaxClique(j + 1, l + 1);
            }
        }
    }
}

public class d23p2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> edgeList = new ArrayList<>();
        HashMap<String, Integer> hm = new HashMap<>();
        HashMap<Integer, String> hm2 = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            String[] parts = line.split("-");
            String from = parts[0];
            String to = parts[1];
            if (!hm.containsKey(from)) {
                hm.put(from, hm.size());
            }
            if (!hm2.containsKey(hm.get(from))) {
                hm2.put(hm.get(from), from);
            }
            if (!hm.containsKey(to)) {
                hm.put(to, hm.size());
            }
            if (!hm2.containsKey(hm.get(to))) {
                hm2.put(hm.get(to), to);
            }
            edgeList.add(new int[] {hm.get(from), hm.get(to)});
        }
        Graph g = new Graph(hm.size());
        for (int[] edge : edgeList) {
            g.addEdge(edge[0], edge[1]);
        }

        g.findMaxClique(0, 0);
        System.out.println(g.maxCliqueSize);
        List<String> cliqueVertices = new ArrayList<>();
        for (int i = 0; i < g.maxCliqueSize; i++) {
            cliqueVertices.add(hm2.get(g.maxClique[i]));
        }
        Collections.sort(cliqueVertices);
        for (String vertex : cliqueVertices) {
            System.out.print(vertex + ",");
        }
        sc.close();
    }
}