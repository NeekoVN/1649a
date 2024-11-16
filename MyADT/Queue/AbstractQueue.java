package MyADT.Queue;

public interface AbstractQueue<E> {
    void enqueue(E element);

    E dequeue();

    E peek();

    int size();

    boolean isEmpty();

    void display();

}
