package helpers;

import java.awt.Color;

/**
 * Lab 2: Debugging with Eclipse and Red Black Tree) <br />
 * The {@code RedBlackTree} class of integers only <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Red%E2%80%93black_tree">
 * https://en.wikipedia.org/wiki/Red%E2%80%93black_tree </a>
 */
public class RedBlackTree {

	/**
	 * Root node of the red black tree
	 */
	private Node root = null;

	/**
	 * Size of the tree
	 */
	private int size = 0;

	/**
	 * Gets the parent of a tree node
	 * 
	 * @param n {@code Node} The node who's parent to find
	 * @return {@code Node} The parent of Node n
	 */
	public Node parent(Node n) {
		return n.parent;
	}

	/**
	 * Gets the grandparent of a tree node
	 * 
	 * @param n {@code Node} The node who's grandparent to find
	 * @return {@code Node} The grandparent of Node n
	 */
	public Node grandparent(Node n) {
		Node parent = parent(n);
		if (parent == null) {
			return null;
		}
		return parent(parent);
	}

	/**
	 * Gets the sibling of a tree node
	 * 
	 * @param n {@code Node} The node who's sibling to find
	 * @return {@code Node} The sibling of Node n
	 */
	public Node sibling(Node n) {
		Node parent = parent(n);
		if (parent == null) {
			return null;
		}
		if (n.equals(parent.lChild)) {
			return parent.rChild;
		} else {
			return parent.lChild;
		}
	}

	/**
	 * Gets the uncle of a tree node
	 * 
	 * @param n {@code Node} The node who's uncle to find
	 * @return {@code Node} The uncle of Node n
	 */
	public Node uncle(Node n) {
		Node parent = parent(n);
		Node grandparent = grandparent(n);
		if (grandparent == null) {
			return null;
		}
		return sibling(parent);
	}

	/**
	 * rotates the tree left upon a Node n. n is brought down and n.rChild becomes
	 * the root of the subtree
	 * 
	 * @param n {@code Node} The node upon which the tree is rotated
	 */
	public void rotate_left(Node n) {
		Node nnew = n.rChild;
		assert (nnew.value != null);
		n.rChild = nnew.lChild;
		n.rChild.parent = n;
		nnew.lChild = n;
		if (n.parent != null) {
			nnew.parent = n.parent;
			if (nnew.parent.lChild == n) {
				nnew.parent.lChild = nnew;
			} else {
				nnew.parent.rChild = nnew;
			}
		} else {
			root = nnew;
			nnew.parent = null;
		}
		n.parent = nnew;
	}

	/**
	 * rotates the tree right upon a Node n. n is brought down and n.lChild becomes
	 * the root of the subtree
	 * 
	 * @param n {@code Node} The node upon which the tree is rotated
	 */
	public void rotate_right(Node n) {
		Node nnew = n.lChild;
		assert (nnew.value != null);
		n.lChild = nnew.rChild;
		n.lChild.parent = n;
		nnew.rChild = n;
		if (n.parent != null) {
			nnew.parent = n.parent;
			if (nnew.parent.lChild == n) {
				nnew.parent.lChild = nnew;
			} else {
				nnew.parent.rChild = nnew;
			}
		} else {
			root = nnew;
			nnew.parent = null;
		}
		n.parent = nnew;
	}

	/**
	 * Search the tree to find if the value is contained
	 * 
	 * @param value {@code int} the value to be checked
	 * @return {@code boolean} If contains, return {@code true}, otherwise return
	 *         {@code false}
	 */
	public boolean contains(int value) {
		// TODO: Lab 2 Part 2-1 -- find an integer from the tree
		Node insertion = location(value);
		if (insertion != null && insertion.value != null && insertion.value == value) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the location of where to insert a value
	 * 
	 * @param value {@code K} The value to search the tree for
	 * @return {@code Node} The Node containing the value if found, or the null node
	 *         where the value should be placed if not
	 */
	public Node location(int value) {
		Node current = root;
		if (current == null) {
			return root;
		}
		while (current.value != null) {
			if (value == current.value) {
				return current;
			} else if (value < current.value) {
				current = current.lChild;
			} else if (value > current.value) {
				current = current.rChild;
			}
		}
		return current;
	}

	/**
	 * Insert an integer to the tree
	 * 
	 * @param data {@code int} New element to be inserted
	 */
	public void insert(int value) {
		// TODO: Lab 2 Part 2-2 -- insert an integer into the tree
		if (!this.contains(value)) {
			Node insertion = location(value);
			if (insertion == null) {
				insertion = new Node(value);
			} else {
				insertion.value = value;
			}
			insertion.lChild = new Node(null);
			insertion.lChild.parent = insertion;
			insertion.rChild = new Node(null);
			insertion.rChild.parent = insertion;
			if (root == null) {
				root = insertion;
			}
			repairColor(insertion);
			size += 1;
		}
	}

	/**
	 * Repair color of nodes in the tree after insertion
	 * 
	 * @param insertion {@code Node} The tree Node that was inserted
	 */
	public void repairColor(Node insertion) {
		insertion.color = Node.RED;
		// case 1
		if (insertion == root) {
			root.color = Node.BLACK;
		}
		// case 2
		else if (insertion.parent != null && insertion.parent.color == Node.BLACK) {
			return;
		}
		// case 3
		else if (insertion.parent != null && uncle(insertion) != null && insertion.parent.color == Node.RED
				&& uncle(insertion).color == Node.RED) {
			insertion.parent.color = Node.BLACK;
			uncle(insertion).color = Node.BLACK;
			Node gp = grandparent(insertion);
			if (gp != null) {
				gp.color = Node.RED;
				repairColor(gp);
			}
		}
		// case 4/5
		else if (insertion.parent != null && insertion.parent.color == Node.RED && uncle(insertion) != null
				&& uncle(insertion).color == Node.BLACK) {
			if (insertion.parent.rChild == insertion && grandparent(insertion).lChild == insertion.parent) { // grandparent
																												// will
																												// exist
																												// since
																												// uncle
																												// exists
				rotate_left(insertion.parent);
				insertion = insertion.lChild;
			} else if (insertion.parent.lChild.equals(insertion)
					&& grandparent(insertion).rChild.equals(insertion.parent)) {
				rotate_right(insertion.parent);
				insertion = insertion.rChild;
			}
			// case 5
			if (insertion.parent != null && insertion.parent.color == Node.RED && uncle(insertion) != null
					&& uncle(insertion).color == Node.BLACK
					&& ((insertion.parent.lChild.equals(insertion)
							&& grandparent(insertion).lChild.equals(insertion.parent))
							|| (insertion.parent.rChild.equals(insertion)
									&& grandparent(insertion).rChild.equals(insertion.parent)))) {
				insertion.parent.color = Node.BLACK;
				grandparent(insertion).color = Node.RED;
				if (insertion.parent.lChild == insertion) {
					rotate_right(grandparent(insertion));
				} else {
					rotate_left(grandparent(insertion));
				}
			}
		}

		// case 5
		else if (insertion.parent != null && insertion.parent.color == Node.RED && uncle(insertion) != null
				&& uncle(insertion).color == Node.BLACK
				&& ((insertion.parent.lChild.equals(insertion)
						&& grandparent(insertion).lChild.equals(insertion.parent))
						|| (insertion.parent.rChild.equals(insertion)
								&& grandparent(insertion).rChild.equals(insertion.parent)))) {
			insertion.parent.color = Node.BLACK;
			grandparent(insertion).color = Node.RED;
			if (insertion.parent.lChild == insertion) {
				rotate_right(grandparent(insertion));
			} else {
				rotate_left(grandparent(insertion));
			}
		}
	}

	/**
	 * Get the size of the tree
	 * 
	 * @return {@code int} size of the tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Recursive method to generate a String of the RedBlackTree
	 * 
	 * @param current {@code Node} The current Node being traversed
	 * @return {@code String} The String representing the RedBlackTree
	 */
	public String traverse(Node current) {
		String output = "";
		if (current == null || current.value == null) {
			return "";
		} else {
			output += traverse(current.lChild);
			output += current.toString();
			output += traverse(current.rChild);
		}
		return output;
	}

	/**
	 * Cast the tree into a string
	 * 
	 * @return {@code String} Printed format of the tree
	 */
	@Override
	public String toString() {
		// TODO: Lab 2 Part 2-3 -- print the tree, where each node contains both value
		// and color
		// You can print it by in-order traversal
		return traverse(root);
	}

	/**
	 * Main entry
	 * 
	 * @param args {@code String[]} Command line arguments
	 */
	public static void main(String[] args) {
		RedBlackTree rbt = new RedBlackTree();
		for (int i = 0; i < 10; i++)
			rbt.insert((int) (Math.random() * 200));
		assert rbt.root.color == RedBlackTree.Node.BLACK;
		System.out.println(rbt.root); // This helps to figure out the tree structure
		System.out.println(rbt);
	}

	/**
	 * The {@code Node} class for {@code RedBlackTree}
	 */
	private class Node {
		public static final boolean BLACK = true;
		public static final boolean RED = false;

		public Integer value;
		public boolean color = BLACK;
		public Node parent = null, lChild = null, rChild = null;

		public Node(Integer value) { // By default, a new node is black with two NIL children
			this.value = value;
			if (value != null) {
				lChild = new Node(null); // And the NIL children are both black
				lChild.parent = this;
				rChild = new Node(null);
				rChild.parent = this;
			}
		}

		/**
		 * Print the tree node: red node wrapped by "<>"; black node by "[]"
		 * 
		 * @return {@code String} The printed string of the tree node
		 */
		@Override
		public String toString() {
			if (value == null)
				return "";
			return (color == RED) ? "<" + value + ">" : "[" + value + "]";
		}
	}

}
