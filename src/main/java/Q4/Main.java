package Q4;

import Utils.Reader;

import java.io.FileNotFoundException;
import java.util.*;

import static Q4.Main.Dijkstra.computePaths;
import static Q4.Main.Dijkstra.getShortestPathTo;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(Reader.getInstance().getFile("input1.txt"));

        Scanner sc = new Scanner(System.in);
        Integer nbPiste = sc.nextInt();
        Integer depart = sc.nextInt();
        Integer arrive = sc.nextInt();

        ArrayList<Vertex> vertices = new ArrayList<>();

        for (int i = 0; i < nbPiste; i++) {
            Vertex e = new Vertex(Integer.toString(i));
            vertices.add(e);
        }

        for (int i = 0; i < nbPiste; i++) {
            ArrayList<Edge> edges = new ArrayList<>();
            for (int j = 0; j < nbPiste; j++) {
                Double val = sc.nextDouble();
                if (i < j)
                {
                    edges.add(new Edge(vertices.get(j), val));
                }
            }
            vertices.get(i).adjacencies = edges.toArray(new Edge[edges.size()]);
        }

        computePaths(vertices.get(depart)); // run Dijkstra
        List<Vertex> path = getShortestPathTo(vertices.get(arrive));
        System.out.println((double)Math.round(path.get(path.size() - 1).minDistance * 1000) / 1000d);
    }


    static class Vertex implements Comparable<Vertex> {
        public final String name;
        public Edge[] adjacencies;
        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;
        public Vertex(String argName) { name = argName; }
        public String toString() { return name; }
        public int compareTo(Vertex other) {
            return Double.compare(minDistance, other.minDistance);
        }
    }

    static class Edge {
        public final Vertex target;
        public final double weight;
        public Edge(Vertex argTarget, double argWeight)
        { target = argTarget; weight = argWeight; }
    }

    public static class Dijkstra {
        public static void computePaths(Vertex source) {
            source.minDistance = 0.;
            PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
            vertexQueue.add(source);

            while (!vertexQueue.isEmpty()) {
                Vertex u = vertexQueue.poll();

                for (Edge e : u.adjacencies) {
                    Vertex v = e.target;
                    double weight = e.weight;
                    double distanceThroughU = 1 - (1 - u.minDistance) * (1 -weight); // proba

                    if (distanceThroughU < v.minDistance) {
                        vertexQueue.remove(v);
                        v.minDistance = distanceThroughU ;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                }
            }
        }

        public static List<Vertex> getShortestPathTo(Vertex target) {
            List<Vertex> path = new ArrayList<Vertex>();
            for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
                path.add(vertex);

            Collections.reverse(path);
            return path;
        }
    }
}


