package j01_basic;

class StoreG<T> {
	private T data;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		System.out.println(data + "임");
	}
}

class GenArr<T> {
	private T[] arrr;
	public T[] getData() {
		return arrr;
	}
	public void setData(T[] arrr) {
		this.arrr = arrr;
	}
	public void arrayPrint() {
		for (T t : arrr) {
			System.out.println(t + "번 째 ");
		}
	}
}
// Number는 추상클래스
class Box<T extends Number> {
	private T dada;
	
	public void setData(T tata) {
		this.dada = tata;
	}
	
	public T getData() {
		return this.dada;
	}
}

public class Gn01_StoreTest {

	public static void main(String[] args) {
		
		StoreG<Integer> abc = new StoreG<Integer>();
		abc.setData(1234);
		
		StoreG<String> stst = new StoreG<String>();
		stst.setData("하호하호");
		
		GenArr<Integer> arrr = new GenArr<Integer>();
		Integer[] ab = {1, 2, 3, 4, 5};
		arrr.setData(ab);
		arrr.arrayPrint();
		
		// => 제네릭 클래스의 타입 인자 제한
		Box<Integer> b1 = new Box();
		b1.setData(12345);
		
//		Box<String> b2 = new Box(); // 컴파일 오류 
		
		
	} //main

} //class




