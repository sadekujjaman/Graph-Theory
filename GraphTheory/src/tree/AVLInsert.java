package tree;

public class AVLInsert {

	public static void main(String[] args) {

		AVLTree tree = new AVLTree();

		/* Constructing tree given in the above figure */
		// tree.root = tree.insert(tree.root, 10);
		// tree.root = tree.insert(tree.root, 20);
		// tree.root = tree.insert(tree.root, 30);
		// tree.root = tree.insert(tree.root, 40);
		// tree.root = tree.insert(tree.root, 50);
		// tree.root = tree.insert(tree.root, 25);

		tree.root = tree.insert(tree.root, 3);
		tree.root = tree.insert(tree.root, 2);
		tree.root = tree.insert(tree.root, 4);
		tree.root = tree.insert(tree.root, 5);
		tree.root = tree.insert(tree.root, 6);
		
		
		tree.preOrder(tree.root);
	}

	static int xyz(int b) {
		return b--;
	}

	private static class Node {
		int val, ht;
		Node left, right;

		Node(int key) {
			this.val = key;
			this.ht = 1;
		}

	}

	private static class AVLTree {
		Node root;

		int height(Node node) {
			if (node == null)
				return 0;
			return node.ht;
		}

		int max(int a, int b) {
			return a > b ? a : b;
		}

		Node rightRotate(Node y) {
			Node x = y.left;
			Node T = x.right;

			x.right = y;
			y.left = T;

			y.ht = max(height(y.left), height(y.right)) + 1;
			x.ht = max(height(x.left), height(x.right)) + 1;

			return x;
		}

		Node leftRotate(Node y) {
			Node x = y.right;
			Node T = x.left;

			x.left = y;
			y.right = T;

			y.ht = max(height(y.left), height(y.right)) + 1;
			x.ht = max(height(x.left), height(x.right)) + 1;

			return x;
		}

		int getBalance(Node node) {
			if (node == null)
				return 0;

			return height(node.left) - height(node.right);
		}

		Node insert(Node node, int key) {

			if (node == null)
				return new Node(key);

			if (key < node.val) {
				node.left = insert(node.left, key);
			} else if (key > node.val) {
				node.right = insert(node.right, key);
			} else
				return node;

			node.ht = 1 + max(height(node.left), height(node.right));

			int balance = getBalance(node);

			// left left
			if (balance > 1 && key < node.left.val) {
				return rightRotate(node);
			}

			// right right
			if (balance < -1 && key > node.right.val) {
				return leftRotate(node);
			}

			// left right
			if (balance > 1 && key > node.left.val) {
				node.left = leftRotate(node.left);
				return rightRotate(node);
			}

			// right left
			if (balance < -1 && key < node.right.val) {
				node.right = rightRotate(node.right);
				return leftRotate(node);
			}
			return node;
		}

		void preOrder(Node node) {
			if (node == null)
				return;
			System.out.print(node.val + "(BF=" + getBalance(node) + ") ");

			preOrder(node.left);
			preOrder(node.right);

		}

	}
}
