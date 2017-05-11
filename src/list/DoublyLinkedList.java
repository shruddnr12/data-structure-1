package list;

public class DoublyLinkedList<E> implements List<E> {
	private int size = 0;
	private Node<E> head = null;
	private Node<E> tail = null;
	
	@Override
	public void add(E element) {
		final Node<E> newNode = new Node<E>( element );
		
		if( head == null ) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		
		size++;
	}

	@Override
	public void add(int index, E element) {
	}

	@Override
	public E get(int index) {
		if( size <= index ) {
			throw new IndexOutOfBoundsException( "Index:" + index + ", size:" + size );
		}
		
		Node<E> x = head;
		for( int i = 0; i < index; i++ ) {
			x = x.next;
		}
		
		return x.data;
	}

	@Override
	public E remove(int index) {
		return null;
	}

	@Override
	public void removeAll() {
		Node<E> x = head;
		
		while( x != null ) {
			Node<E> next = x.next;
			x.next =null;
			x.prev = null;
			x = next;
		}
		
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[ size ];
		
		Node<E> x = head;
		int index = 0;
		while( x != null ) {
			arr[ index++ ] = x.data;
			x = x.next;
		}		
		return arr;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private Node<E> x = head;
			
			@Override
			public boolean hasNext() {
				return x != null;
			}

			@Override
			public E next() {
				E data = x.data;
				x = x.next;
				return data;
			}
		};
	}
	
	private static class Node<E>{
		private Node<E> next;
		private Node<E> prev;
		private E data;

		private Node( E element ) {
			this.data = element;
			this.next = null;
			this.prev = null;
		}
	}	
}
