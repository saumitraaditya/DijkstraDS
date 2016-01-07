/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;
import java.util.Iterator;
import java.util.BitSet;

public class Graph {
    
    	/* ArrayList V is used to store the nodes of the graph */
	private ArrayList<Vertex> V = new ArrayList<Vertex>();
        /*Array of Vertices for speed-up of graph generation*/
        public  Vertex[] Varr;
	/* ArrayList E is used to store the edges of the graph */
	private ArrayList<Edge> E = new ArrayList<Edge>();
        
        /*Array for checking whether an edge btw two vertices exist or not
        if it exist corresponding value will be 0 else 1, we are basically
        trying to implement a array of bit-sets*/
        BitSet[] bitArray;
        
        /*number of vertices*/
        private int numV;
        
        /*number of Edges*/
        private int numE;
        
        /*Constructor for Graph*/
        public Graph(int numV,int numE)
        {
         this.numV = numV;
         this.numE = numE;
         Varr = new Vertex[numV];
         bitArray = new BitSet[numV];
         //initialize the bitArray
         for(int i =0;i<numV;i++)
             bitArray[i] = new BitSet(numV);
        }
        
        /*Constructor for graph with no parameters passed*/
        public Graph()
        {
            
        }
        
        /* Adding Edges to ArrayList E */
	public boolean AddEdge(Edge e)
        {
            //check to see if the entry is valid.
            if(CheckEdge(e))
            {
                E.add(e); 
                int v1 = e.retVertex1().retID(); //ID of vertex1
                int v2 = e.retVertex2().retID(); //ID of vertex2
                //update the edges into array for both the vertices for speedy random access.
                Varr[v1].myEdges.add(new Edge(e.retVertex1(),e.retVertex2(),e.retCost()));
                Varr[v2].myEdges.add(new Edge(e.retVertex2(),e.retVertex1(),e.retCost()));
                //update the bitArray so that a duplicate entry can be checked.
                bitArray[e.retVertex1().retID()].set(e.retVertex2().retID());
                return true;
            }
            //if check failed return false.
            else 
            return false; 
        }
        
        /*speedy version of AddEdge where we will not create a arrayList of edges rather simply put 
        the edges into Adjacency list of vertices, this obviates the need of iterarting over E, which is a 
        very time consuming process*/
        /*This will work well for reading from a file case where there wont be duplicate edges, in random case this wont work fine.*/
        public boolean AddEdgeS(Edge e)
        {
           int v1 = e.retVertex1().retID(); //ID of vertex1
           int v2 = e.retVertex2().retID(); //ID of vertex2
           //Add into array of corresponding vertices.
           //new Edge(e.retVertex1(),e.retVertex2(),e.retCost())
           Varr[v1].myEdges.add(new Edge(e.retVertex1(),e.retVertex2(),e.retCost()));
           Varr[v2].myEdges.add(new Edge(e.retVertex2(),e.retVertex1(),e.retCost()));
           return true;
        }
        
        /*Method to check that the edge is not already in the list of Edges*/
        private boolean CheckEdge(Edge e)
        {
            /*Check-1, both the vertices of the edge should not be same, this is possible while doing random edge generation.*/
            if (e.retVertex1().retID()==e.retVertex2().retID())
                return false;
            /*Check-2, if edge I<-->J is present in E than neither edge I<-->J nor J<-->I can be allowed.*/
            if ((bitArray[e.retVertex1().retID()].get(e.retVertex2().retID())== true) || (bitArray[e.retVertex2().retID()].get(e.retVertex1().retID())== true) )
                return false;
            return true;
            /*for (Edge edge : E)
            {
                if(edge.retVertex1() == e.retVertex1() || edge.retVertex1() == e.retVertex2() )
                {
                    if(edge.retVertex2() == e.retVertex1() || edge.retVertex2() == e.retVertex2() )
                        return false;
                }
            }*/
            //return true;
        }
        
        /* Adding nodes to ArrayList V */
	public boolean AddVertex(Vertex v){
	if(!V.contains(v)) { V.add(v); return true;}
	else { return false;}
	}
        
        /*Adding nodes to Array of Nodes,S stands for speeded version instead of using a list we use array for sppedy access*/
        public boolean AddVertexS(Vertex v)
        {
            //if already contain this vertex return false else add vertex.
            if(Varr[v.retID()]!=null)
            {
                return false;
            }
            else
            {
                Varr[v.retID()] = v;
                return true;
            }
        }
        
        /* This function returns the ArrayList V */
	public ArrayList<Vertex> fetchVertices()  { return V;}
        
        /*return the Array of verices*/
        public Vertex[] fetchVerticesS() {return Varr;}
	/* This function returns the ArrayList E */
	public ArrayList<Edge> fetchEdges()   { return E;}
        
        /*This function returns number of Vertices in the Graph.*/
        public int retnumV()
        {
            //return V.size();
            return numV;
        }
        
         /*This function returns number of Edges in the Graph.*/
        public int retnumE()
        {
            //return E.size();
            return numE;
        }
        
        /*this function returns a vertex with a given ID*/
        public Vertex retVertex(int ID)
        {
            /*Vertex v_retval = null;
            for (Vertex v : V)
            {
                if (v.retID() == ID)
                {
                    v_retval = v;
                    break;
                }
            }
            return v_retval;*/
            return Varr[ID];
        }
        
        /* This function creates adjacency list representation of the graph by attaching edges in the 
        array lsit of both their endpoint vertices.
        this method is not used or called after implementing Array of vertices, was used in initial design phase
        had poor performance because had to traverse the list.*/
	/*public void AdjacencyList()
            {
             For each edge we consider both the vertexes i.e. vertex1 and vertex2 
                for(Vertex v: V)
                    { for(Edge e: E)
                        {
                            if(e.retVertex1().equals(v)) 
                            {
                                v.myEdges.add(new Edge(e.retVertex1(),e.retVertex2(),e.retCost()));
                            }
                            if(e.retVertex2().equals(v)) 
                            {
                               v.myEdges.add(new Edge(e.retVertex2(),e.retVertex1(),e.retCost()));  
                            }
                        } 
                    }
            }*/
        
        /* The function display() prints the edges and nodes from ArrayList circles and ArrayList lines */
     public void display()
     {
        //AdjacencyList();
        //for(Vertex v : V)//iterating over Array list V
        for (int i = 0;i<Varr.length;i++)
        {
            //System.out.println("Node: "+v.retID()+", ");
            System.out.println("Node: "+i+", ");
            //traverse the array and print all its edges.
            for (Edge e : Varr[i].myEdges)
            {
               System.out.println("Edge: "+ e.retVertex1().retID() +"->"+e.retVertex2().retID()+" Weight: "+(int)e.retCost() +", "); 
            }
            
          System.out.println("##########################################");  
        }
       
    }
     
     /*Method to display #(edges),#(vertices) and details of Edges of a Graph, will be used to visualize a randomly
     generated graph*/
     public void displayGraphRandom()
     {
         System.out.println("Vertices "+retnumV());
         System.out.println("Edges "+retnumE());
         for(Edge e : E)
         {
             System.out.println(e.retVertex1().retID()+ " "+e.retVertex2().retID()+" "+(int)e.retCost());
         }
     }
    
}
