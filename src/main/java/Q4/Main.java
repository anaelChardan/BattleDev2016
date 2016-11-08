package Q4;

import Utils.Reader;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static List<Vertex> nodes;
    static List<Edge> edges;

    private static void addLane(String laneId, int sourceLocNo, int destLocNo, double duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(Reader.getInstance().getFile("Q4/input2.txt"));

        Scanner sc = new Scanner(System.in);

        final int nbSommets = sc.nextInt();
        final int sDepart = sc.nextInt();
        final int sArrive = sc.nextInt();


        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < nbSommets; i++) {
            nodes.add(new Vertex(i, "Node_" + i));
        }

        int sCurr = 0;
        while (sc.hasNext()) {
            for (int sLine = 0; sLine < nbSommets; sLine++) {
                final double avanlange = Double.parseDouble(sc.next());
                addLane("" + sCurr, sCurr, sLine, avanlange);
            }
            sCurr++;
        }

        Graph graph = new Graph(nodes, edges);
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.execute(nodes.get(sDepart));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(sArrive));

        int v[] = {sDepart};
        double aval[] = {1.0};
        for (Vertex vertex : path) {
            edges.stream().filter(edge -> (edge.source == nodes.get(v[0])) && (edge.destination == nodes.get(vertex.getId()))).findFirst().ifPresent(edge -> {
                aval[0] *= (1.0 - edge.getWeight());
            });
            v[0] = vertex.getId();
        }
        final DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        System.out.println(df.format(1.0 - aval[0]));
    }

    private static class Vertex {
        final private int id;
        final private String name;


        public Vertex(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ("" + id).hashCode();
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            if (!(id==other.id))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class Edge {
        private final String id;
        private final Vertex source;
        private final Vertex destination;
        private final double weight;

        public Edge(String id, Vertex source, Vertex destination, double weight) {
            this.id = id;
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public String getId() {
            return id;
        }

        public Vertex getDestination() {
            return destination;
        }

        public Vertex getSource() {
            return source;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return source + " " + destination;
        }
    }

    private static class Graph {
        private final List<Vertex> vertexes;
        private final List<Edge> edges;

        public Graph(List<Vertex> vertexes, List<Edge> edges) {
            this.vertexes = vertexes;
            this.edges = edges;
        }

        public List<Vertex> getVertexes() {
            return vertexes;
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }

    private static class Dijkstra {
        private final List<Vertex> nodes;
        private final List<Edge> edges;
        private Set<Vertex> settledNodes;
        private Set<Vertex> unSettledNodes;
        private Map<Vertex, Vertex> predecessors;
        private Map<Vertex, Double> distance;

        public Dijkstra(Graph graph) {
            // create a copy of the array so that we can operate on this array
            this.nodes = new ArrayList<Vertex>(graph.getVertexes());
            this.edges = new ArrayList<Edge>(graph.getEdges());
        }

        public void execute(Vertex source) {
            settledNodes = new HashSet<Vertex>();
            unSettledNodes = new HashSet<Vertex>();
            distance = new HashMap<>();
            predecessors = new HashMap<Vertex, Vertex>();
            distance.put(source, (double) 0);
            unSettledNodes.add(source);
            while (unSettledNodes.size() > 0) {
                Vertex node = getMinimum(unSettledNodes);
                settledNodes.add(node);
                unSettledNodes.remove(node);
                findMinimalDistances(node);
            }
        }

        private void findMinimalDistances(Vertex node) {
            List<Vertex> adjacentNodes = getNeighbors(node);
            for (Vertex target : adjacentNodes) {
                if (getShortestDistance(target) > getShortestDistance(node)
                        + getDistance(node, target)) {
                    distance.put(target, getShortestDistance(node)
                            + getDistance(node, target));
                    predecessors.put(target, node);
                    unSettledNodes.add(target);
                }
            }

        }

        private double getDistance(Vertex node, Vertex target) {
            for (Edge edge : edges) {
                if (edge.getSource().equals(node)
                        && edge.getDestination().equals(target)) {
                    return edge.getWeight();
                }
            }
            throw new RuntimeException("Should not happen");
        }

        private List<Vertex> getNeighbors(Vertex node) {
            List<Vertex> neighbors = new ArrayList<Vertex>();
            for (Edge edge : edges) {
                if (edge.getSource().equals(node)
                        && !isSettled(edge.getDestination())) {
                    neighbors.add(edge.getDestination());
                }
            }
            return neighbors;
        }

        private Vertex getMinimum(Set<Vertex> vertexes) {
            Vertex minimum = null;
            for (Vertex vertex : vertexes) {
                if (minimum == null) {
                    minimum = vertex;
                } else {
                    if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                        minimum = vertex;
                    }
                }
            }
            return minimum;
        }

        private boolean isSettled(Vertex vertex) {
            return settledNodes.contains(vertex);
        }

        private Double getShortestDistance(Vertex destination) {
            Double d = distance.get(destination);
            if (d == null) {
                return (double) Integer.MAX_VALUE;
            } else {
                return d;
            }
        }

        /*
         * This method returns the path from the source to the selected target and
         * NULL if no path exists
         */
        public LinkedList<Vertex> getPath(Vertex target) {
            LinkedList<Vertex> path = new LinkedList<Vertex>();
            Vertex step = target;
            // check if a path exists
            if (predecessors.get(step) == null) {
                return null;
            }
            path.add(step);
            while (predecessors.get(step) != null) {
                step = predecessors.get(step);
                path.add(step);
            }
            // Put it into the correct order
            Collections.reverse(path);
            return path;
        }
    }
}
