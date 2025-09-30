package com.dmslob.tasks.graph.dijkstra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.PriorityQueue;

public class DijkstraAll {

    public static void main(String[] args) {

        GraphNode A = new GraphNode("A");
        GraphNode B = new GraphNode("B");
        GraphNode C = new GraphNode("C");
        GraphNode D = new GraphNode("D");
        GraphNode E = new GraphNode("E");
        GraphNode F = new GraphNode("F");

        A.AddOutgoingEdge(B, 5);
        A.AddOutgoingEdge(C, 6);
        B.AddOutgoingEdge(C, 3);
        B.AddOutgoingEdge(E, 2);
        B.AddOutgoingEdge(F, 10);
        B.AddOutgoingEdge(D, 7);
        C.AddOutgoingEdge(E, 7);
        E.AddOutgoingEdge(F, 4);
        D.AddOutgoingEdge(F, 2);

        LocalGraph myGraph = new LocalGraph(A);
        LocalDijkstra dAlg = new LocalDijkstra(myGraph);
        dAlg.go();
        dAlg.PrintStatusOfPriorityQ();

        p("Node  Cost");
        myGraph.print();
    }

    private static void p(String s) {
        System.out.println(s);
    }
}

class GraphConstants {
    static final Integer INFINITY = 999999;
}

final class Edge {

    private GraphNode node;
    private Integer cost;

    Edge(GraphNode nodeTo, Integer cost) {
        this.node = nodeTo;
        this.cost = cost;
    }

    GraphNode getNode() {
        return node;
    }

    Integer getCost() {
        return cost;
    }
}

class GraphNode implements Comparable<GraphNode> {

    private static int nodeCount = 0;

    private ArrayList<Edge> outGoingEdges = new ArrayList<Edge>();
    private String val;
    private Integer ID;
    private boolean visited;
    private Integer distance = GraphConstants.INFINITY;

    GraphNode(String value) {
        this.init(value);
    }

    public GraphNode() {
        this.init("");
    }

    private void init(String nodeVal) {
        this.val = nodeVal;
        this.ID = GraphNode.nodeCount++;
        this.visited = false;
    }

    public void print() {
        System.out.println("Node from GraphNode " + this.val + " Found " + getDistance());
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    void AddOutgoingEdge(GraphNode node, Integer cost) {
        this.outGoingEdges.add(new Edge(node, cost));
    }

    ArrayList<Edge> getOutGoingEdges() {
        return outGoingEdges;
    }

    String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    Integer getID() {
        return ID;
    }

    @Override
    public int compareTo(GraphNode arg0) {
        return this.distance.compareTo(arg0.getDistance());
    }

    Integer getDistance() {
        return distance;
    }

    void setDistance(Integer distance) {
        this.distance = distance;
    }
}

class LocalGraph {

    private GraphNode startNode;
    private ArrayList<GraphNode> allNodes = new ArrayList<GraphNode>();
    private ArrayList<Integer> visitedNodes = new ArrayList<Integer>();

    LocalGraph(GraphNode startNode) {
        this.startNode = startNode;
        this.discoverGraph();
    }

    GraphNode getStartNode() {
        return this.startNode;
    }

    public ArrayList<GraphNode> getAllNodes() {
        return this.allNodes;
    }

    /**
     * Breath First Search traversal of the Graph
     */
    private void discoverGraph() {
        allNodes.add(this.startNode);
        visit(startNode);
        for (Edge e : this.startNode.getOutGoingEdges()) {
            if (!isVisited(e.getNode())) {
                bfs(e.getNode());
            }
        }
    }

    private void bfs(GraphNode n) {
        visit(n);
        this.allNodes.add(n);
        for (Edge e : n.getOutGoingEdges()) {
            if (!isVisited(e.getNode())) {
                bfs(e.getNode());
            }
        }
    }

    private boolean isVisited(GraphNode n) {
        return visitedNodes.contains(n.getID());
    }

    private void visit(GraphNode n) {
        this.visitedNodes.add(n.getID());
    }

    public void print() {
        for (GraphNode n : this.allNodes) {
            System.out.println(n.getVal() + " --- " + n.getDistance());
        }
    }
}

class GraphNodePriorityQueue {

    private PriorityQueue<GraphNode> pQueue = new PriorityQueue<GraphNode>();

    GraphNodePriorityQueue() {
    }

    public void add(GraphNode n) {
        pQueue.add(n);
    }

    public void add(Collection<GraphNode> nodeCollection) {
        this.pQueue.addAll(nodeCollection);
    }

    Boolean hasMore() {
        return !this.pQueue.isEmpty();
    }

    public GraphNode remove() {
        return this.pQueue.remove();
    }

    /**
     * Removes desired graph node, then inserts into appropriate slot
     *
     * @param distance
     */
    void updateGraphNodeDistance(GraphNode n) {
        this.pQueue.remove(n);
        this.pQueue.add(n);
    }

    void PrintContents() {
        //it appears that order is preserved when items are removed from the collections
        ArrayList<GraphNode> _temp = new ArrayList<GraphNode>();
        System.out.println("Size of Q=" + pQueue.size());
        while (!pQueue.isEmpty()) {
            GraphNode n = pQueue.remove();
            _temp.add(n);
            System.out.println(n.getVal() + " distance=" + n.getDistance());
        }
        pQueue.addAll(_temp);
    }
}

class LocalDijkstra {

    private LocalGraph graph;
    //priority queue stores all of the nodes, reachable from the start node
    //The queue is sorted by the node.distance
    private GraphNodePriorityQueue priorityQ = new GraphNodePriorityQueue();
    private Hashtable<GraphNode, Integer> distance = new Hashtable<GraphNode, Integer>();

    //1. needs to get the list of all nodes in the graph
    //2. need to initialize distance vector to infinity
    //3. Need Edge Cost function
    LocalDijkstra(LocalGraph g) {
        this.graph = g;
        this.graph.getStartNode().setDistance(0);
        this.priorityQ.add(this.graph.getAllNodes());
    }

    public void go() {
        while (this.priorityQ.hasMore()) {
            GraphNode n = this.priorityQ.remove();
            n.print();
            for (Edge e : n.getOutGoingEdges()) {
                GraphNode adjNode = e.getNode();
                Integer newPossiblePathCost = e.getCost() + n.getDistance();
                if (newPossiblePathCost < adjNode.getDistance()) {
                    adjNode.setDistance(newPossiblePathCost);
                    this.priorityQ.updateGraphNodeDistance(adjNode);
                    System.out.println("current node " + (e.getNode()).getVal());
                    this.priorityQ.PrintContents();
                }
            }
        }
    }

    public void PrintStatusOfPriorityQ() {
        this.priorityQ.PrintContents();
    }
}

