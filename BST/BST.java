package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

@Override
public boolean insert(String s) {
	if (root == null) {
		root = new BST_Node(s);
		size++;
		return true;
	}
	if (root.containsNode(root, s))
		return false;	
	boolean check = root.insertNode(new BST_Node(s));
	if (check) {
		size++;
		return true;
	}
	return false;
}

@Override
public boolean remove(String s) {
	//override
	if (size == 0) return false;
	if (!root.containsNode(root, s)) return false;
	root = root.removeNode(root, s);
	size--;
	return true;
}

@Override
public String findMin() {
	if (size == 0)
		return "This tree doesn't have any node.";
	return root.minVal(root);
}

@Override
public String findMax() {
	if (size == 0)
		return "This tree doesn't have any node.";
	return root.maxVal(root);
}

@Override
public boolean empty() {
	if (size == 0)
		return true;
	else
		return false;
}

@Override
public boolean contains(String s) {
	if(root.containsNode(root, s)) return true;
	return false;
	
}

@Override
public int size() {
	return size;
}

@Override
public int height() {
	if (size == 0)
		return -1;
	else
		return root.getHeight(root);
}

}