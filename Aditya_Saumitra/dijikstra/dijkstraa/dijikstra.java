


import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*This is the main class of the program which decides the flow depending upon the arguments given to the program.*/
public class dijikstra 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        long SimpleStart,SimpleEnd,FibStart,FibEnd;
        //initialize scanner.
         Reader read = new Reader();
         //read the first argument.
         String s = args[0];
         //read the filename
         String file = null;
         int Snode = -1;
         //*****************Simple Scheme*********************
         if (s.equals("-s"))
         {
             //read file name
             file = args[1].trim();
             Scanner scan = new Scanner(new File(file));
             //read source node
             Snode = Integer.parseInt(scan.next());
             scan.close();
	     //Print name of the file read.
             //System.out.println(args[1].trim());
             //call read method of the reader class to construct and return a graph from the given file.
             Graph G =  read.readf(args[1].trim());
             // call the dijkstra method and take time measurements.
             SimpleStart = System.currentTimeMillis();
             DijkstraSimple d = new DijkstraSimple(G,Snode);
             SimpleEnd = System.currentTimeMillis();
             d.displayProject(G,Snode);
             long simpleTime = SimpleEnd - SimpleStart;
             //System.out.println("*********** TIME_SIMPLE "+simpleTime+" *************");
         }
	//****************Fibonacci Scheme*************************
         else if (s.equals("-f"))
         {
             file = args[1].trim();
             Scanner scan = new Scanner(new File(file));
             Snode = Integer.parseInt(scan.next());
             scan.close();
             //System.out.println(file);
             Graph G =  read.readf(file);
             FibStart = System.currentTimeMillis();
             DijkstraFib d2 = new DijkstraFib(G,Snode);
             FibEnd = System.currentTimeMillis();
             d2.displayProject(G,Snode);
             long fibTime = FibEnd - FibStart;
             //System.out.println("*********** TIME_FIB "+fibTime+" *************");
         }
         else if (s.equals("-r"))
         {
            //random mode
            // read number of vertices.
            String vertices= args[1];
            /* Parsing the string argument into Integer */
            int numNodes= Integer.parseInt(vertices);
            //read density of the graph.
            String density = args[2];
            /* Parsing the string argument into Integer */
            Double Density = Double.parseDouble(density); 
            //read the source node.
            String SourceNode = args[3];
            int SrcNode = Integer.parseInt(SourceNode);
            //call constructor of RandomData class to initialize it.
            RandomData R = new RandomData(numNodes,Density);
            //call retGraph method of RandomData class to return the randomly constructed graph.
            Graph G = R.retGraph();
            //System.out.println("Graph Generated.");
            //G.display();
            //run dijkstra using simple mode on this graph and take time measurements.
            long start = 0;
            long stop,time;
            start = System.currentTimeMillis();
	    //call the simple scheme on the graph.
            DijkstraSimple d = new DijkstraSimple(G,SrcNode);
            stop = System.currentTimeMillis();
            time = stop - start;
            d.displayProject(G,SrcNode);
            //System.out.println("*********** "+start+" *************");
            //System.out.println("*********** "+stop+" *************");
            System.out.println("*********** TIME_SIMPLE "+time+" *************");
            //************************************************************
            System.out.println("#####################################################################");
            start = System.currentTimeMillis();
            //call the Fibonacci scheme on the graph.
            DijkstraFib d2 = new DijkstraFib(G,SrcNode);
            stop = System.currentTimeMillis();
            time = stop - start;
            d2.displayProject(G, SrcNode);
            //System.out.println("*********** "+start+" *************");
            //System.out.println("*********** "+stop+" *************");
            System.out.println("***********TIME_FIB "+time+" *************");
         }
    }
    
      
}
