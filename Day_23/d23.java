import java.util.*;

class Graph {

    public int[][] adjMatrix;
    public int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];
    }

    public void addEdge(int i, int j) {
        adjMatrix[i][j] = 1;
        adjMatrix[j][i] = 1;
    }
}



public class d23 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> edgeList = new ArrayList<>();
        HashMap<String, Integer> hm = new HashMap<>();
        HashMap<Integer, String> hm2 = new HashMap<>();
        long res = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) {
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
        for (int i = 0; i < g.numVertices; i++) {
            for (int j = i + 1; j < g.numVertices; j++) {
               for (int k = j + 1; k < g.numVertices; k++) {
                   if (g.adjMatrix[i][j] == 1 && g.adjMatrix[j][k] == 1 && g.adjMatrix[k][i] == 1) {
                       if (hm2.get(i).startsWith("t") || hm2.get(j).startsWith("t") || hm2.get(k).startsWith("t")) {
                           res += 1;
                       }
                   }
               }
            }
        }
        System.out.println(res);
        sc.close();
    }

   
}