package MyADT.Queue;

public class MyQueueADT<E> implements AbstractQueue<E> {
    private class Node<E> {
        private E element;
        private Node<E> next;

        private Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    private int size;
    private Node<E> front;
    private Node<E> rear;

    @Override
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            this.front = newNode;
            this.rear = newNode;
        } else {
            this.rear.next = newNode;
            this.rear = newNode;
        }
        this.size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty, cannot dequeue.");
        }
        E el = this.front.element;
        this.front = this.front.next;
        this.size--;
        if (isEmpty()) {
            this.rear = null;
        }
        return el;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty, cannot peek.");
        }
        return this.front.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void display() {
        Node<E> current = this.front;
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.next;
        }
        System.out.println();
    }

}