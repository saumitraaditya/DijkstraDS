/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;


public class DijkstraFib
{
    
    private double[] Dist;          // Dist[v] = recorded distance of node from source vertex, holds the minimum known distance.
    private Edge[] Edge;    // Edge[v] = holds the last edge on the shortest path to source.
    private FibHeap3 pq; //priority queue based on Fibonacci heap implementation
    
    
    
     public DijkstraFib(Graph G, int s) 
     {
        //pass the graph and source node to the constructor.
            //initialize the Dist and Edge Arrays , which have as many entries as the number of vertices in the graph G.
        Dist = new double[G.retnumV()];
        Edge = new Edge[G.retnumV()];
        //initilally the distance of all nodes from the source are marked to infinity,
        //indicating that they are unreachable from the source node.
        for (int v = 0; v < G.retnumV(); v++)
            Dist[v] = Double.POSITIVE_INFINITY;
        //the distance of source node from itself is marked as 0.
        Dist[s] = 0.0;

        // relax vertices in order of distance from s.
        //initialize the priority queue.
        pq = new FibHeap3(G.retnumV());
        //insert source into PQ.
        pq.insert(s, Dist[s]);
        /*while the queue is not empty we will go on inserting vertices into it
        than relax all the edges from it.*/
        while (!pq.isEmpty()) 
        {
            int v = pq.delMin();
            for (Edge e : G.Varr[v].myEdges)
                relax (e);
        }

    }
     
    /*in this method for a given vertex we note down and correct the weight of 
     all of its neighbours, relaxing means correcting if the new known distance of a neighbour node
     via this node is less than the previously known distance we update it else leave it unchanged
     this is accomplished using the decreaseKey operation if the node was already in the queue or
     inserting it if it was not.*/
    private void relax(Edge e) 
    {
        int v = e.retVertex1().retID(), w = e.retVertex2().retID();
        if (Dist[w] > Dist[v] + e.retCost()) {
            Dist[w] = Dist[v] + e.retCost();
            Edge[w] = e;
            if (pq.contains(w))
                pq.decreaseKey(w, Dist[w]);
            else                
                pq.insert(w, Dist[w]);
        }
    }
    /*returns the distance of any node from the source*/
    public double Dist(int v)
    {
        return Dist[v];
    }
    /*tells if there is a path from this node to source node*/
    public boolean hasPathTo(int v) 
    {
        return Dist[v] < Double.POSITIVE_INFINITY;
    }
    
     /*uses a stack to record the path from a given node to the source
     */
    public Iterable<Edge> Path(int v) 
    {
        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        for (Edge e = Edge[v]; e != null; e = Edge[e.retVertex1().retID()]) {
            path.push(e);
        }
        return path;
    }
    
    /*This method displays the cost of path from node to the source node along with the path*/
    public void display(Graph G,int s)
    {
         // print shortest path
        for (int t = 0; t < G.retnumV(); t++) {
            if (this.hasPathTo(t)) {
                System.out.println(s + " to " + t + " distance is " + this.Dist(t));
               
                if (this.hasPathTo(t)) {
                    for (Edge e : this.Path(t)) {
                        System.out.println("[ " + e.retVertex1().retID() + "," + e.retVertex2().retID() + " ]" );
                       
                    }
                }
                System.out.println();
                
            }
            
        }
    }
    public void displayProject(Graph G,int s)
    {
     for (int t = 0; t < G.retnumV(); t++)
         System.out.println((int)this.Dist(t));
    }
}
