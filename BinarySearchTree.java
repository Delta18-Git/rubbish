package ClassXII.Extra;

public class BinarySearchTree<T extends Comparable<T>>{

    private Node<T> root;

    public BinarySearchTree() {
        this.root = new LeafNode<>();
    }

    // Insertion, as previously discussed
    public void insert(T value) {
        if (root.isEmpty()) {
            root = new InternalNode<>(value);
        } else {
            insert(root, value);
        }
    }

    private void insert(Node<T> node, T value) {
        InternalNode<T> currentNode = (InternalNode<T>) node;
        if (currentNode.getData().compareTo(value) > 0) {
            if (currentNode.getLeft().isEmpty()) {
                currentNode.setLeft(new InternalNode<>(value));
            } else {
                insert(currentNode.getLeft(), value);
            }
        } else {
            if (currentNode.getRight().isEmpty()) {
                currentNode.setRight(new InternalNode<>(value));
            } else {
                insert(currentNode.getRight(), value);
            }
        }
    }

    // Search
    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node<T> node, T value) {
        if (node.isEmpty()) {
            return false;
        }

        InternalNode<T> currentNode = (InternalNode<T>) node;
        int cmp = currentNode.getData().compareTo(value);
        if (cmp < 0)
            return contains(currentNode.getRight(), value);
        else if (cmp > 0)
            return contains(currentNode.getLeft(), value);
        else
            return true;
    }

    // Deletion
    public void delete(T value) {
        root = delete(root, value);
    }

    private Node<T> delete(Node<T> node, T value) {
        // Returns the same node if is a leaf node
        if (node.isEmpty()) {
            return node;
        }

        InternalNode<T> currentNode = (InternalNode<T>) node;
        int cmp = currentNode.getData().compareTo(value);
        if (cmp < 0)
            currentNode.setRight(delete(currentNode.getRight(), value));
        else if (cmp > 0)
            currentNode.setLeft(delete(currentNode.getLeft(), value));
        else {
            if (currentNode.getLeft().isEmpty())
                return currentNode.getRight();
            else if (currentNode.getRight().isEmpty())
                return currentNode.getLeft();
            else {
                currentNode.setData(min(currentNode.getRight()));
                currentNode.setRight(delete(currentNode.getRight(), currentNode.getData()));
            }
        }
        return currentNode;
    }

    private T min(Node<T> node) {
        InternalNode<T> currentNode = (InternalNode<T>) node;
        return currentNode.getLeft().isEmpty() ? currentNode.getData() : min(currentNode.getLeft());
    }

    // Traversal
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node<T> node) {
        if (!node.isEmpty()) {
            InternalNode<T> currentNode = (InternalNode<T>) node;
            inOrderTraversal(currentNode.getLeft());
            System.out.print(" " + currentNode.getData());
            inOrderTraversal(currentNode.getRight());
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node<T> node) {
        if (!node.isEmpty()) {
            InternalNode<T> currentNode = (InternalNode<T>) node;
            System.out.print(" " + currentNode.getData());
            preOrderTraversal(currentNode.getLeft());
            preOrderTraversal(currentNode.getRight());
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node<T> node) {
        if (!node.isEmpty()) {
            InternalNode<T> currentNode = (InternalNode<T>) node;
            postOrderTraversal(currentNode.getLeft());
            postOrderTraversal(currentNode.getRight());
            System.out.print(" " + currentNode.getData());
        }
    }

    public void levelOrderTraversal() {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            printLevel(root, i);
            System.out.println();
        }
    }
    
    private void printLevel(Node<T> node, int level) {
        if (node.isEmpty()) {
            return;
        }
        InternalNode<T> currentNode = (InternalNode<T>) node;
        if (level == 1) {
            System.out.print(currentNode.getData() + " ");
        } else if (level > 1) {
            printLevel(currentNode.getLeft(), level - 1);
            printLevel(currentNode.getRight(), level - 1);
        }
    }
    
    private int height(Node<T> node) {
        if (node.isEmpty()) {
            return 0;
        }
        InternalNode<T> currentNode = (InternalNode<T>) node;
        int left = height(currentNode.getLeft());
        int right = height(currentNode.getRight());
    
        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }
    }
    
}