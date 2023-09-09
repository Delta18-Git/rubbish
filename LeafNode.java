package ClassXII.Extra;

public final class LeafNode<T extends Comparable<T>> extends Node<T> {
    public LeafNode() {
        super(null);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}