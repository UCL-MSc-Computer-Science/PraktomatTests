import java.util.LinkedList;




/**
 * Implementation of LUT using ordered linear search.
 *
 * This class is an implementation of the look-up table abstract data
 * type that uses a sequence array as the underlying data
 * structure. The capacity of the LUT is thus limited. The elements of
 * the look-up table are stored in reverse alphabetical order and
 * linear search is used for retrieval.
 */
/*Added printLevelOrder() method and added toString methods to the Entry and Node inner classes (printLevelOrder() needs these or an equivalent) */
public class BinaryTreeLUT_Printable<E> implements LUT<E> {

    /**
     * The member class Key is used for the indexing keys of the LUT. It
     * is a String with basic comparative methods added.
     */
    protected class Key {

        private String kString;

        Key(String s) {
            kString = s;
        }

        boolean equals(Key k) {
            return kString.equals(k.toString());
        }

        boolean lessThan(Key k) {
            return kString.compareTo(k.toString()) < 0;
        }

        boolean greaterThan(Key k) {
            return kString.compareTo(k.toString()) > 0;
        }

        @Override
        public String toString() {
            return kString;
        }
    }

    /**
     * Implementation of an entry.
     *
     * The member class Entry encapsulates an entry of the LUT and
     * contains a {key, value} pair.
     */
    protected class Entry {

        protected Key key;
        protected E value;

        Entry(Key k, E v) {
            key = k;
            value = v;
        }
        
        public String toString(){
        	return " {" + key.toString() + ":" + value + "} ";
        }
    }

    /**
     * Implementation of a node in a tree that represents a subtree.
     *
     * The member class BSTreeNode encapsulates node of the binary
     * search tree, which contains a LUT entry and links to left and
     * right subtrees.
     */
    protected class BSTreeNode {

        protected Entry kvPair;
        protected BSTreeNode left;
        protected BSTreeNode right;

        BSTreeNode(Entry e) {
            kvPair = e;
            left = null;
            right = null;
        }

        BSTreeNode(Entry e, BSTreeNode l, BSTreeNode r) {
            kvPair = e;
            left = l;
            right = r;
        }
        
        public String toString(){
        	return kvPair.toString();
        }
    }

    // Single protected data member
    protected BSTreeNode root;

    /**
     * Default constructor - no need to specify capacity of LUT.
     */
    public BinaryTreeLUT_Printable() {
        root = null;
    }

    @Override
    public void insert(String key, E value) {
        Entry newEntry = new Entry(new Key(key), value);
        BSTreeNode newNode = new BSTreeNode(newEntry);
        addToTree(newNode, root);
    }

    @Override
    public void remove(String key)
    throws LUTKeyException {
        Key searchKey = new Key(key);
        removeFromTree(searchKey, root);
    }

    @Override
    public E retrieve(String key)
    throws LUTKeyException {
        Key searchKey = new Key(key);
        BSTreeNode treeNode = getFromTree(searchKey, root);
        return treeNode.kvPair.value;
    }

    @Override
    public void update(String key, E value)
    throws LUTKeyException {
        Key searchKey = new Key(key);
        BSTreeNode treeNode = getFromTree(searchKey, root);
        treeNode.kvPair.value = value;
    }

    @Override
    public String toString() {
        return treeString(root);
    }

    // Protected methods implementing recursive operations on the tree.

    /**
     * Adds newNode to the tree rooted at curNode recursively.
     */
    protected void addToTree(BSTreeNode newNode,
                             BSTreeNode curNode) {
        if (curNode == null) {
            // Special case for empty tree.
            root = newNode;
        } else {
            Key curKey = curNode.kvPair.key;
            Key newKey = newNode.kvPair.key;

            if (newKey.lessThan(curKey)) {
                // General case: recurse left or right depending on comparison.
                if (curNode.left == null) {
                    curNode.left = newNode;
                } else {
                    addToTree(newNode, curNode.left);
                }
            } else {
                if (curNode.right == null) {
                    curNode.right = newNode;
                } else {
                    addToTree(newNode, curNode.right);
                }
            }
        }
    }

    /**
     * Returns the node containing k from the tree rooted at node.
     */
    protected BSTreeNode getFromTree(Key k, BSTreeNode node)
    throws LUTKeyException {
        if (node == null) {
            throw new LUTKeyException();
        } else if (k.equals(node.kvPair.key)) {
            return node;
        } else if (k.lessThan(node.kvPair.key)) {
            return getFromTree(k, node.left);
        } else {
            return getFromTree(k, node.right);
        }
    }

    /**
     * Removes the node containing k from the tree rooted at node.
     */
    protected void removeFromTree(Key k, BSTreeNode node)
    throws LUTKeyException {
        if (node == null) {
            // Special case for empty tree.
            throw new LUTKeyException();
        } else if (k.equals(root.kvPair.key)) {
            // Special case when deleting the root node.
            root = lrMerge(root);
        } else if (k.lessThan(node.kvPair.key)) {
            // General case, go left

            // If the search key is less than key at the current node,
            // go to the left subtree.

            if (node.left == null) {
                // If the left subtree is empty, the tree cannot contain the
                // search key.
                throw new LUTKeyException();
            }

            if (k.equals(node.left.kvPair.key)) {
                // If this is the parent of the node to be removed, do the
                // removal.
                node.left = lrMerge(node.left);
            } else {
                // Otherwise, recurse down another level.
                removeFromTree(k, node.left);
            }
        } else {
            // General case, go right

            // Otherwise go to the right subtree.
            if (node.right == null) {
                // If the right subtree is empty, the tree cannot contain the
                // search key.
                throw new LUTKeyException();
            }

            if (k.equals(node.right.kvPair.key)) {
                // If this is the parent of the node to be removed, do the
                // removal.
                node.right = lrMerge(node.right);
            } else {
                // Otherwise, recurse down another level.
                removeFromTree(k, node.right);
            }
        }
    }

    /**
     * Merges the two subtrees of node prior to removal of the node from
     * the tree.
     */
    protected BSTreeNode lrMerge(BSTreeNode node) {
        BSTreeNode mergedTrees = null;
        // First two cases occur when one or both subtrees of the node to
        // be deleted are empty.
        if (node.left == null) {
            mergedTrees = node.right;
        } else if (node.right == null) {
            mergedTrees = node.left;
        } else {
            // general case

            // Otherwise, merge the left and right subtrees and link the
            // merged structure to the current node.
            addToTree(node.right, node.left);
            mergedTrees = node.left;
        }
        return mergedTrees;
    }

    /**
     * Uses in order tree traversal to construct a string containing all
     * the key value pairs in the binary search tree.
     */
    protected String treeString(BSTreeNode node) 
    {
        if (node == null) 
        {
            return "null";
        }
        Entry lutEntry = node.kvPair;
        //Set format for printing this node
        String thisNode = " {" + lutEntry.key.toString() + ":" + lutEntry.value + "} " ;
        //Left first traversal, recursively call on left side then this node, then right side
        return "(" + treeString(node.left) + " <- " + thisNode + " -> " + treeString(node.right) + ")";
     }
    
  //Print breadth first representation of Binary Tree
    void printLevelOrder() 
    {
		if (root == null)
		{
			return;
		}
		//Create two queues, one to store all nodes on current level, one for next level, 
		LinkedList<BSTreeNode> currentLevel = new LinkedList<BSTreeNode>();
		LinkedList<BSTreeNode> nextLevel = new LinkedList<BSTreeNode>();
		//Start by putting root in the currentLevel queue
		currentLevel.push(root);
		//While tree not empty go down level by level
		while (!currentLevel.isEmpty()) 
		{
			//Take node out of current level 
			BSTreeNode currNode = currentLevel.pop();
			if (currNode != null) 
			{
				//Print out current node (need to have overwritten toString()
				System.out.print(" " + currNode + " ");
				//If node has left child node, add to nextLevel (putting this first means it prints left to right
				if(currNode.left != null){
					nextLevel.push(currNode.left);
				}
				//If node has right child node, add to nextLevel
				if(currNode.right != null){
					nextLevel.push(currNode.right);
				}
			}
			//Once current level done, move to next level
			if (currentLevel.isEmpty()) 
			{
				//Print newline
				System.out.println("");
				currentLevel = nextLevel; //Point currentLevel to nextLevel which isn't empty
				nextLevel = new LinkedList<BSTreeNode>(); //Point nextLevel to a empty List (do not use clear, as this would clear firstLevel)
			}
		}
    }
}
