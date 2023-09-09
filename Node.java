public abstract sealed class Node<T extends Comparable<T>> permits ListNode, LeafNode, InternalNode {
    private T data;

    protected Node(T value) {
        this.data = value;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T extends Comparable<T>> Node<T> empty() {
        return new LeafNode<>();
    }

    public abstract boolean isEmpty();
}
