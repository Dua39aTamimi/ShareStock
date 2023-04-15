package application;

public class List<T> {
	Node<T> first;
	Node<T> last;
	int size;

	public List() {
		this.last = null;
		this.first = null;
		this.size = 0;
	}

	public void addFirst(T data) {
		Node<T> node = new Node<T>(data);
		if (first == null) {
			first = node;
			size++;
		}else {

		node.next = first;
		first = node;
		size++;
		}
	}

	public void addLast(T data) {
		Node<T> node = new Node<>(data);
		if (first == null) {
			first = node;
			size++;
		}
		else {
			last=first;
		while (last.next != null) {

			last = last.next;
		}
		last.next = node;
		size++;}
	}

	public T removeFirst() {
		T data = first.data;
		first = first.next;
		size--;
		return data;

	}

	public T removeLast() {
		last = first;
		T data;
		if(first==null)
			return null;
		else if(first.next==null) {
			data = first.data;
			first = null;
			size--;
			return data;
		}
		
		else {
		while (last.next.next != null) {

			last = last.next;
		}
		 data = last.next.data;
		last.next = null;
		size--;
		
		return data;
		}
	}
	
	public void print() {
		Node<T> temp = first;
		for (int i = 0; i < size; i++) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}

}
