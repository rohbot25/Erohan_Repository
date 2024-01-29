/**Made by Evan Rohan
Dijkstra's algorithm testing
3/1/2023
*/
import java.util.PriorityQueue;
import java.util.*;

public class DijkstrasTest
{
   //constants
   final static int S = 1;
   final static int T = 8;
   final static int INFINITY = 9999;
   public static void main(String[]args)
   {
      //adjacency list
      Node s = new Node(2,9,new Node(3,15,new Node(4,14,null)));
      Node two = new Node(7,23,null);
      Node three = new Node(5,20,new Node(T,44,null));
      Node four = new Node(3,5,new Node(5,30,new Node(7,18,null)));
      Node five = new Node(6,11,new Node(T,16,null));
      Node six = new Node(7,6,new Node(T,6,null));
      Node seven = new Node(5,2,new Node(T,19,null));
      Node t = null;
      Node[] adjacencyList = {s,two,three,four,five,six,seven,t};
      //running program
      System.out.println("Graph represented by adjacency list.");
      
      Dijkstras(S, adjacencyList);
   }
   /**Dijkstras Algorithm
   @param s start node
   @param aj ajacencylist
   */
   public static void Dijkstras(int s, Node[] aj)
   {
       //variables
       int v;
       int w;
       int node;
       int path;
       int max;
       boolean edges;
       Node n;
       //keeping track of paths and overall length
       ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
       for(int i=0; i < aj.length; i++)
       {
         paths.add(new ArrayList<Integer>());
       }
       
       //adding default values plus start value path length to 0;
       paths.get(0).add(s);
       paths.get(0).add(0);
       
       for(int i=1; i < aj.length; i++)
       {
         paths.get(i).add(INFINITY);
       }

       //Initialize Q of V with lengths infinity for each node v in v
       PriorityQueue<Integer> Q = new PriorityQueue(aj.length);
       Q.add(0);
       for(int i=0; i < aj.length-1; i++)
       {
         Q.add(INFINITY);
       }
       //System.out.println("Q: " + Q);
       //initiliaze empty list S
       ArrayList<Integer> S = new ArrayList<Integer>();
       //while(Q is not empty
       while(Q.size()!=0)
       {
         node = -1;
         //v = ExtractMin(Q)
         v = ExtractMin(Q);
            //find node corresponding to length
            for(int i = 0; i < paths.size(); i++)
            {
               if(paths.get(i).get(paths.get(i).size()-1) == v)
               {
                  node = i;
               }
            }
            
            //if a node found continue
            if(node != -1)
            {
               //add node to S
               S.add(node);
               //Print out New node added
               if(node+1==1)
               {
                  System.out.print("Node S included in S with the shortest path length " + v + " on the path ");
               }
               else if(node+1 == T)
               {
                  System.out.print("Node T included in S with the shortest path length " + v + " on the path ");
               }
               else
               {
                  System.out.print("Node " + (node+1) + " included in S with the shortest path length " + v + " on the path ");
               }
               //Path to new node
               for(int i = 0;i < paths.get(node).size()-1; i++)
               {
                  if(paths.get(node).get(i) == 1 && node+1 == 1)
                  {
                     System.out.print("S");
                  }
                  else if(paths.get(node).get(i) == 1)
                  {
                     System.out.print("S - ");
                  }
                  else if(paths.get(node).get(i) == T)
                  {
                     System.out.print("T");
                  }
                  else if(paths.get(node).get(i) == node+1)
                  {
                     System.out.print(paths.get(node).get(i));
                  }
                  else
                  {
                     System.out.print(paths.get(node).get(i) + " - ");
                  }
                  
               }
               System.out.println();
               //get adjacency list of node added
               edges = true;
               n = aj[node];
               if(n == null)
               {
                  edges = false;
               }
            }
            //else false
            else
            {
               n = null;
               edges = false;
            }
            //for each edge
         while(edges){
            //while edge is not a discovered node
            if(!(S.contains(n.getValue()-1)))
            {
               //get length to node
               path = n.getLength();
               //get node traveling to
               w = n.getValue()-1;
               //get previous length to node
               max = paths.get(w).get(paths.get(w).size()-1);
               //if less than previous length to path
               if(v + path < max)
               {
                  //change key
                  ChangeKey(Q,max,v+path);
                  //clear previous path
                  paths.get(w).clear();
                  //add new path to corresponding node
                  for(int i=0; i < paths.get(node).size()-1; i++)
                  {
                     paths.get(w).add(paths.get(node).get(i));
                  }
                  //add node to path
                  paths.get(w).add(w+1);
                  //add new length
                  paths.get(w).add(v+path);
               }
            }
            //check if adjacency list is done
            if(n.getNext() == null)
            {
               edges = false;
            }
            else
            {
               n = n.getNext();
               edges = true; 
            }
         }
       }
   }
   /**ExtractMin extracts min value from priority Queue
   @param Q as priority queue of lengths
   @return minimum value
   */
   public static int ExtractMin(PriorityQueue<Integer> Q)
   {
      int min = Q.peek();
      Q.remove(min);
      return min;
   }
   /**Changekey removes previous key and adds new shortest path
   @param Q as priority queue
   @param w as previous length
   @param pathLength as new length
   */
   public static void ChangeKey(PriorityQueue<Integer> Q,int w,int pathLength)
   {
      Q.remove(w);
      Q.add(pathLength);
   }
}