package assignment4;
public class DNA {

    private class Node{//nested class
        Node[] nodes;
        String data;

        Node(){ // default constructor
            data = "E";
            nodes = new Node[5];
        }
        Node(String data){
            this.data = data;
            nodes = new Node[5];
        }
    }//end of nested class

    private Node root;

    public DNA(){ //constructor
        root = null;
    }

    /*
        Insert sequence into the DNA tree. Print a message indicating if the insertion was successful,
    and if so, indicate the level of the leaf node inserted. It is an error to insert a duplicate
    sequence. Such an error should be reported in the output, and no changes to the tree structure
    should take place
     */
    public void insert(String sequence){
        sequence = sequence+="$";
        if (root == null) {
            root = new Node();
            root.data = sequence;
            sequence = sequence.substring(0,sequence.length()-1);
            System.out.println(sequence + " inserted: level 0");
        } else {
            insertAux(root,sequence, 0);
        }

    }
    /*
    helper method of insert
    */
    private void insertAux(Node node, String sequence, int charIndex){
        if (node.data.equals("I")) {
            if (node.nodes[getIndex(sequence.charAt(charIndex))] == null) {
                node.nodes[getIndex(sequence.charAt(charIndex))] = new Node(sequence);
                int level =charIndex + 1;
                sequence = sequence.substring(0,sequence.length()-1);
                System.out.println(sequence + " inserted: level "+ level);
                return;
            }
            insertAux(node.nodes[getIndex(sequence.charAt(charIndex))], sequence, charIndex+1);
            return;
        }

        if (node.data.equals(sequence)) {
            System.out.println("Error!");
            return;
        }

        if (node.data.charAt(charIndex) != sequence.charAt(charIndex)) {
            node.nodes[getIndex(node.data.charAt(charIndex))] = new Node(node.data);
            node.nodes[getIndex(sequence.charAt(charIndex))] = new Node(sequence);
            node.data = "I";
            int level =charIndex + 1;
            sequence = sequence.substring(0,sequence.length()-1);
            System.out.println(sequence + " inserted: level "+ level);
            return;
        } else {
            node.nodes[getIndex(node.data.charAt(charIndex))] = new Node(node.data);
            node.data = "I";
            insertAux(node.nodes[getIndex(sequence.charAt(charIndex))], sequence, charIndex+1);
        }

    }

    /*
    returns the index of the char
    */
    private int getIndex(char c){
        if(c =='A')
            return 0;
        if(c =='C')
            return 1;
        if(c =='G')
            return 2;
        if(c =='T')
            return 3;
        if(c =='$')
            return 4;
        return -1;
    }


    /*
    displays the DNA tree
    */
    public void display(){
        System.out.println("---display---");
        auxDisplay(root,0);
        System.out.println("-------------");
    }
    /*
    auxilary method to display
    */
    private void auxDisplay(Node node, int depth){
        if(node == null){ //if there is no tree    
            return;
        }
        for(int i = 0;i<depth;i++){
            System.out.print(".");
        }
        if(node.nodes == null){ //if it is leaf node
            System.out.println(node.data);
        }else{
            System.out.println(node.data);
            for(Node child: node.nodes){
                auxDisplay(child, depth + 1);
            }
        }
    }
    
    /*
    Finds all sequences that match sequenceDecriptor.
    */
    public void search(String sequenceDescriptor){
        System.out.println("Searching " + sequenceDescriptor + ":");
        aux(root,sequenceDescriptor); //calls helper recursive method
        System.out.println("");
    }
    /*
    helper recursive method
    searchs every node in the tree, and prints if the sequence starts with the same char
    */
    private void aux(Node node,String sequence){
        if(node==null) //if node is empty
            return;
        if(node.data.startsWith(sequence)){
            System.out.println(node.data);
        }
        
        for(Node child: node.nodes){
            aux(child,sequence);
        }
    }
}