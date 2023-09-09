package ClassXII.Extra;

import java.util.function.IntFunction;

public class LinkedList<T extends Comparable<T>>
{
    protected ListNode<T> start;
    private int length;
    
    LinkedList() {
        start = ListNode.empty();
        length = 0;
    }
    
    public boolean isEmpty() {
        return start.isEmpty();
    }
    
    public int size() {
        return length;
    }
    public ListNode<T> getFirstNode() {
        return this.start;
    }

    public ListNode<T> getLastNode() {
        ListNode<T> pointer = start;
        while (!pointer.getNext().isEmpty()) {
            pointer = pointer.getNext();
        }
        return pointer;
    }

    public void clear() {
        start = null;
        length = 0;
    }
    

    public void insert(T val) {
        ListNode<T> newNode = new ListNode<>(val);
        ListNode<T> copy = null;
        ListNode<T> pointer;
        boolean inserted = false;
        if(this.isEmpty()) {
            start = newNode;
            newNode.setNext(ListNode.empty());
        }
        else if(val.compareTo(start.getData()) < 0) {
            newNode.setNext(start);
            start = newNode;
        } 
        else {
            copy = this.start;
            pointer = copy.getNext();
            while(pointer != null) {
                if(val.compareTo(copy.getData()) >= 0 && val.compareTo(pointer.getData()) <= 0) {
                    copy.setNext(newNode);
                    newNode.setNext(pointer);
                    inserted = true;
                    break;
                }
                else {
                    copy = pointer;
                    pointer = pointer.getNext();
                }
            }
            if(!inserted) {
                copy.setNext(newNode);
                newNode.setNext(ListNode.empty());
            }
        }
        this.length++;
    }
    
    public boolean delete(T val) {
        ListNode<T> copy = this.start;
        ListNode<T> pointer = this.start;
        while(pointer != null) {
            if(pointer.getData().compareTo(val) == 0) {
                break;
            }
            else {
                copy = pointer;
                pointer = pointer.getNext();
            }
        }
        if(pointer == null) {
            return false;
        }
        if(pointer == start) {
            start = start.getNext();
        }
        else {
            copy.setNext(pointer.getNext());
        }
        this.length--;
        if(length == 0) {
            start = ListNode.empty();
        }
        return true;
    }
    
    public String toString() {
        StringBuilder out = new StringBuilder();
        ListNode<T> pointer = this.start;
        while (pointer.getNext() != null) {
            out.append(pointer.getData() + " -> ");
            pointer = pointer.getNext();
        }
        out.append(pointer.getData());
        return out.toString();
    }

    public T[] toArray(IntFunction<T[]> generator) {
        T[] array = generator.apply(this.length);
        ListNode<T> pointer = start;
        for(int i = 0; i < length; i++) {
            array[i] = pointer.getData();
            pointer = pointer.getNext();
        }
        return array;
    }
    
    
    public int search(T val) {
        ListNode<T> pointer = this.start;
        int pos = 0;
        while(!pointer.isEmpty()) {
            if(pointer.getData().compareTo(val) == 0) {
                return pos;
            } else {
                pointer = pointer.getNext();
                pos++;
            }
        }
        return -1;
    }

    public boolean contains(T val) {
        return search(val) != -1;
    }
    
    
    public void concat(LinkedList<T> B) {
        if(this.isEmpty()) {
            this.start = B.start;
        }
        else {
            ListNode<T> pointer = this.start;
            while(pointer.getNext() != null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(B.start);
        }
        this.length += B.length;
    }

    public void reverse() {
        ListNode<T> pointer = this.start;
        ListNode<T> prev = null;
        ListNode<T> next;
        while(pointer != null) {
            next = pointer.getNext();
            pointer.setNext(prev);
            prev = pointer;
            pointer = next;
        }
        this.start = prev;
    }

    public LinkedList<T> split(T val) {
        LinkedList<T> newList = new LinkedList<>();
        int pos = this.search(val);
        if(pos != -1) {
            ListNode<T> pointer = this.start;
            for(int i = 0; i < pos; i++) {
                pointer = pointer.getNext();
            }
            newList.start = pointer.getNext();
            pointer.setNext(ListNode.empty());
            this.length = pos + 1;
            newList.length = this.length - pos - 1;
        }
        return newList;
    }
    
}
