/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





public class Edge {
    
    private double cost;
    private Vertex vertex1,vertex2;
    /* initialize both the vertices and cost of the edge.*/
    public Edge(Vertex v1,Vertex v2, double cost)
    {
        vertex1 = v1;
        vertex2 = v2;
        this.cost = cost;
    }
    /*returns cost of the edge*/
    public double retCost() { return this.cost; }
    /* returns the first node of an edge */
    public Vertex retVertex1() {
        return vertex1; //To change body of generated methods, choose Tools | Templates.
    }
    /*  returns the second node of an edge */
    public Vertex retVertex2() {
        return vertex2;
    }

    


}
