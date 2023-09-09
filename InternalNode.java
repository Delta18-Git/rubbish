\public final class InternalNode<T extends Comparable<T>> extends Node<T> {
    private Node<T> left;
    private Node<T> right;

    public InternalNode(T value) {
        super(value);
        this.left = Node.empty();
        this.right = Node.empty();
    }
  
    public Node<T> getLeft() {
        return this.left;
    }

    public Node<T> getRight() {
        return this.right;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
