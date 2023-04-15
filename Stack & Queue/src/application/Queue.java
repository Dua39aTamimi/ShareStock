package application;

public class Queue<T> {
	List<T> list;
	
	public Queue() {
		this.list=new List<>();
	}
	public void enqueue(T data) {
		list.addLast(data);
	}
	
	public T dequeue() {
		return list.removeFirst();
	}
	public void print() {
		list.print();
	}
	public boolean isEmpty() {
		return list.first==null;
	}
}
