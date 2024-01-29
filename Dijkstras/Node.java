/**made by Evan Rohan 2/5/2023
node class
*/
public class Node{
   //variables
   private int value;
   private Node next;
   private int length;
   
   /**Constructor for node
   @param int v for value
   @param Node n for next Node
   */
   public Node(int v,int l, Node n){
      value = v;
      next = n;
      length = l;
   }
   /**Secondary Node
   sets next to null
   @param int v for value
   */
   public Node(int v){
      value = v;
      next = null;
   }
   /**getter for value
   @return int value;
   */
   public int getValue(){
      return value;
   }
   /**setter for value
   @param int v for value;
   */
   public void setValue(int v){
      value = v;
   }
   /**getter for next node
   @return Node next
   */
   public Node getNext(){
      return next;
   }
   /**setter for next node
   @param Node n as next Node
   */
   public void setNext(Node n){
      next = n;
   }
   /**getter for length
   @return length
   */
   public int getLength(){
      return length;
   }
   
}