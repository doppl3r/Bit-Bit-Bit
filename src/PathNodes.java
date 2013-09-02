import java.util.LinkedList;

public class PathNodes {
    private LinkedList<Node> pathNodes;
    private int currentNode;

    public PathNodes(){
        pathNodes = new LinkedList<Node>();
    }
    public void add(int x, int y){
        pathNodes.add(new Node(x,y));
    }
    public void remove(int i){ pathNodes.remove(i); }
    public void setNextNode(){
        currentNode++;
        if (currentNode > pathNodes.size()-1) currentNode = 0; //reset path
    }
    public Node getNode(int i){ return pathNodes.get(i); }
    public int getCurrentNode(){ return currentNode; }
    public int size(){ return pathNodes.size(); }
    public double getCurrentX(){ return pathNodes.get(currentNode).getX(); }
    public double getCurrentY(){ return pathNodes.get(currentNode).getY(); }

    //Node class.
    public class Node {
        private double x;
        private double y;
        private double speedX;
        private double speedY;

        public Node(int x, int y){
            this.x=x;
            this.y=y;
        }
        public double getX(){ return x; }
        public double getY(){ return y; }
    }
}
