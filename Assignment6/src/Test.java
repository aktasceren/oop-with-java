import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double side1; double side2; double side3; String color; Boolean filled;
		System.out.println("Enter three sides: ");
		side1 = input.nextDouble();
		side2 = input.nextDouble();
		side3 = input.nextDouble();
		System.out.println("Enter the color: ");
		color = input.next();
		System.out.println("Enter a boolean value for filled: ");
		filled = input.nextBoolean();
		Triangle triangle = new Triangle(side1, side2, side3);
		triangle.setColor(color);
		triangle.setFilled(filled);
		System.out.println(triangle.toString());
		System.out.println("\n ----------OUTPUT OF POLIMORPHISM EXAMPLE--------------");
		GeometricObject triangle2 = new Triangle(side1, side2, side3);
		triangle2.setFilled(filled);
		triangle2.setColor(color);
		System.out.println(triangle2.toString()); 
		//Which class does the invoked toString() method belong? GeometricObject(declared type) Triangle(actual type)
		//Here triangle2’s actual type isTriangle, because triangle2 references an object created using new Triangle().
		//toString() method is invoked by triangle2 is determined by triangle2’s actual type
	}
}
