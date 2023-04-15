package application;

public class Stack<T> {
	List<T> list;

	public Stack() {

		list=new List<>();
		
	}
	
	//add at the end
	public void push(T data) {
		list.addLast(data);
	}
	//remove from the end
	public T pop() {
		return list.removeLast();
	}
	public void print() {
		list.print();
	}
	public boolean isEmpty() {
		return list.first==null;
	}
}
