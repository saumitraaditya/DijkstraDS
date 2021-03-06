/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Random;
import java.util.*;

public class RandomData {
    
    /* numEdges is for keeping count of the number of edges */
    private int numEdges;
    /* numNodes is for keeping count of the number of nodes */
    private int numNodes;
    
    /* The constructor of RandomData 
    * The number of vertices/nodes and the density of the graph is passed as arguments 
    */
     public RandomData(int numNodes, double density)
        {   
            numEdges = (int) ( (density/100) * numNodes*(numNodes-1)/2); 
            this.numNodes = numNodes; 
        }
     
     
     /*this method returns a connected graph after running BFS on it*/
     public Graph retGraph()
     {
         Graph G;
         do
         {
          G = generateGraph();    
         }while(!BFS(G,0));
         return G;
     }
     
     
     /*Generates graph of Random data*/
     public Graph generateGraph()
     {
         /* Instantiating the object of Graph Class */
         Graph G = new Graph(numNodes,numEdges);
         /* Random is a default class of JAVA. Instantiating it's object */
         Random obj = new Random(System.currentTimeMillis());
         /*RENAME BELOW VARIABLES*/
         int number, weight, NodeA,NodeB, sum = 0;
         for(number = 0; number < numNodes; number++)
         {
             Vertex v = new Vertex(number);
             G.AddVertexS(v);
         }
        //ArrayList<Vertex> arrVertex = G.fetchVertices();
         Vertex[] arrVertex = G.fetchVerticesS();
        /* While goes on till no edge is left */
        
        while (sum < numEdges)
        {
          /* The weight or cost is assigned randomly */
            weight = obj.nextInt(1000) + 1;
            NodeA = obj.nextInt(numNodes);
            NodeB = obj.nextInt(numNodes);
            //Edge e = new Edge(arrVertex.get(NodeA),arrVertex.get(NodeB), (double)weight);
            Edge e = new Edge(arrVertex[NodeA],arrVertex[NodeB], (double)weight);
            boolean check = G.AddEdge(e);
            if(check==true) sum = sum+1;
            //System.out.println(sum);
        }
        //G.AdjacencyList();
        //G.display();
        return G;
     }
      /*BFS*/
     //the method below performs a breadth first search to check if the graph is connected,
     //we will traverse all the vertices and mark the entry corresponding to it in the array as true or '1'
     //if at the end their is any element in the array that is not '1' it implies array is not 
     //connected.
      private boolean BFS(Graph G, int r)
        {
            boolean[] seen = new boolean[G.retnumV()];
            Queue<Vertex> q = new LinkedList<Vertex>();
            Vertex v = G.retVertex(r);
            q.add(v);
            Vertex temp;
            seen[v.retID()] = true;
            while (!q.isEmpty())
            {
                temp = q.remove();
                for (Edge e  : temp.myEdges)
                {
                    if(!seen[e.retVertex2().retID()])
                    {
                        q.add(e.retVertex2());
                        //mark the entry for the vertex in array true.
                        seen[e.retVertex2().retID()] = true;
                    }
                }
                
            }
            //traverse the array and if any of the entry is false means
            //graph is not connected.
            for(int i = 0;i<seen.length;i++)
            {
                if(seen[i] == false)
                    return false;
            }
            return true;
        }
}
