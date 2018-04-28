# Performance analysis of Dijikstras algorithm using array based priority queue and Fibonacci heap  

## Performance analysis of Dijikstras algorithm using arrays and Fibonacci heap  
**Compiling**   
javac -d out -sourcepath dijkstraa dijkstraa/dijikstra.java  
Where out is a directory which will house the compiled class files, dijkstraa is is the directory that contain all the java files and dijikstra.java is the main program of the project. Make sure to create the out directory before firing this command using mkdir out.  
**Executing**   
java dijikstra -r 5000 10 0 / java dijikstra -s /Desktop/T5.txt /  
java dijikstra -f /Desktop/T5.txt  
Once the code has been compiled go to the out directory and fire the execution commands give the path to the input file as an argument  
## Classes  
**1.Vertex**  
This class represents a vertex in a graph, it has an ID and a list of edges associated with it, the constructor for this class initializes the ID of the vertex which is either read from a file or randomly generated. Arraylist myEdges is a list containing all the edges associated with this vertex. The function retID() returns the ID of the vertex whereas myEdges is a public variable which can be accessed directly.  
**2.Edge**  
This class represents an edge in the graph, it has 3 variables namely cost, vertex1 and vertex2 which represent the endpoint of the edges. The constructor initializes the edge given the cost, ID of vertex1 and vertex2. Methods retVertex1(), retVeretex2() and retCost() return the source vertex, destination vertex and cost respectively.  
**3.Graph**  
The class graph is composed of an array of vertices ‘Varr’ , an array list of edges ‘E’, an array list of vertices ‘V’. This class represents the graph as an Adjacency list which can be visualized as an array of vertices with each vertex having a list of associated edges dangling from it.   
**4.RandomData**  
This class is used to generate a randomly connected graph given the number of nodes and density, it uses BFS to ensure that the graph is connected.   
**5.Reader**  
This class contains the functionality to read the given file and return the graph containing the edges and vertices in the graph.  
**6.FibHeap**  
This class implements a Min. priority queue using Fibonacci heap, the algorithm used is as described in CLRS.  
**7.MinPQ**  
Implements a Min Priority queue using an array in which each operation takes O(n) time.  
**8. DijkstraSimple/DijkstraFib**  
Both these classes are essentially identical except the fact the one uses Array based priority queue and the other Fibonacci heap backed priority queue. The signatures and functionality of all the methods is identical.  
**9.DijkstraA**  
This is the main class of the program which decides the flow depending upon the arguments given to the program. Takes the measurement of run times of both the modes and prints them and the output on the console. The functionality is very simple consisting of only if-else constructs and method calls to relevant classes depending upon the flow.  

## Expectation versus obtained results  
**Expectations**  
In the simple scheme all the operations take O(n) time , the most frequently used operations in dijkstra are insert, delMin and decreaseKey. The operation delMin is done V times where V is number of vertices, suppose V is equal to n than the complexity would be O(n^2) for it. decreaseKey operation is done E times where E is number of edges. Thus the overall complexity of performing Dijkstras using simple scheme should be O(n^2).  

In the Fibonacci heap actual complexity of insert is O(1), delMin is O(n) and decrease key is also O(n) but the amortized complexities are O(1),O(log n) and O(1) respectively. Thus the expected result would be O(V *log V + E*1).  
**Actual results**  
In the actual results obtained Fibonacci heap scheme performs better than the simple scheme. As we go on increasing the number of nodes the performance gap between the simple and Fibonacci scheme also widens I believe this is due to the fact the fact we are better able to exploit the amortized cost of the operations carried out using Fib Heap If you look at the shape of the graph it seems that the simple scheme is following a quadratic curve whereas the Fib scheme is almost close the X-Axis thus we can safely assume that for large values of Vertices simple scheme runs in O(n^2) time and FibHeap in O(V log V + E ) time . However when the number of nodes is less the gap is not very large, this might be attributed to the fact that constant factors in this case are very large and cannot be ignored for example as the graph becomes more dense it itself becomes of the order of O(n^2). Other factors because of which Fibonacci heap is not as fast as it could be might be because of Java memory model which is inherently slower than C/C++, a number of constant factors involved might play an important role in this experiment.  
For example in the case of [3000,10] where we have 3000 vertices and density is 10% , there is a very prominent spike where the performance of simple scheme suffers a big blow whereas FibHeap remains unaffected. This might actually be the case where simple scheme runs at close to its worst case performance. This is not an irregular result I ran the experiment several times and got matching values, This happens only on Netbeans while running on linux this behaviour is not repeated.  
For experiment with 5000 vertices and over I was not able to execute with graphs having density more than 50% as my compiler kept running out of heap-space, This might be due to the fact the Graph is generated several times until we do not arrive at a connected graph (BFS succeeds) also for addition of every Edge to the graph we have to ensure that it is not a duplicate edge. These operations must be carried umpteen times to arrive at a final connected graph free of duplicate Edges thus exhausting the memory. However if it was given a file with 5000 or more nodes with higher densities it is supposed to run fine.
