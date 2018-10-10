package BST_A2;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make the take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations


  public boolean containsNode(BST_Node root, String s) {
	    if (root == null) return false;
	    if (s.compareTo(root.data) == 0) return true;
	    if (s.compareTo(root.data) < 0)  return containsNode(root.left, s);    
	    return containsNode(root.right, s);
	}
  
  public boolean insertNode(BST_Node node){
	  if (this.containsNode(this, node.data))
		  return false;
	  if (node.data.compareTo(this.data) < 0) {
		  if (this.left == null) {
			  this.left = node;
			  return true;
		  }
		  else
			  this.left.insertNode(node);
	  }
	  else if (node.data.compareTo(this.data) > 0) {
		  if (this.right == null) {
			  this.right = node;
			  return true;
		  }
		  else
			  this.right.insertNode(node);
	  }
	  return false;
  }
  
  
  public BST_Node removeNode(BST_Node root,String s){ 
	  if (root == null) return root;
      if (s.compareTo(root.data) < 0) 
          root.left = removeNode(root.left, s); 
      else if (s.compareTo(root.data) > 0) 
          root.right = removeNode(root.right, s); 
      // if value is same as root's value, then this is the node to be deleted 
      else
      { 
          // node with only one child or no child 
          if (root.left == null) {
        	  root = root.right;
              return root; 
          }
          else if (root.right == null) {
        	  root = root.left;
              return root; 
          }
          // node with two children: replace the original data with smallest node's data in the right sub 
          root.data = minVal(root.right); 
          // Delete the successor 
          root.right = removeNode(root.right, root.data); 
      } 
      return root; 
	  }
  
  
public String minVal(BST_Node root) { 
	String minv = root.data; 
	while (root.left != null) { 
		minv = root.left.data; 
		root = root.left; 
	 } 
	return minv; 
} 
	
  
  public String maxVal(BST_Node root){
	  String maxv = root.data; 
		while (root.right != null) { 
			maxv = root.right.data; 
			root = root.right; 
		 } 
		return maxv;
  }
  
  
  public int getHeight(BST_Node node){
	  if (node == null) 
		  return -1;
		
      int lHeight = getHeight(node.left); 
      int rHeight = getHeight(node.right); 
        /* use the larger one */
      if (lHeight > rHeight) 
          return (lHeight + 1); 
      else 
          return (rHeight + 1); 
}


  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}