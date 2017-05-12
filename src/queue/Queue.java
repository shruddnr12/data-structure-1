package queue;

public interface Queue<E> {
	void offer( E item );
	E poll();
	E peek();
}
