public final class ListNode<T extends Comparable<T>> extends Node<T> {
    private ListNode<T> next;

    public ListNode(T value) {
        super(value);
        next = ListNode.empty();
    }

    public ListNode<T> getNext() {
        return this.next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public static <T extends Comparable<T>> ListNode<T> empty() {
        return new ListNode<>(null);
    }

    public boolean isEmpty() {
        return this.getData() == null;
    }
}
