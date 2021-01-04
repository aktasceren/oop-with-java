
public class Test {
	public static void main(String[] args) {
		GeometricObject[] array = {new Square(2), new Circle(5), 
				new Square(5), new Rectangle(3, 4), new Square(4.5)};
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i].toString()); ;
		 } 
	}
}


