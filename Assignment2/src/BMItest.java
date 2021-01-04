import java.util.Scanner;

public class BMItest {
	public static void main(String[] args) {
		double a = 0;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter weight in pounds: ");
		double weight = scan.nextDouble();	
		System.out.print("Enter height in inches: ");
		double height = scan.nextDouble();
		a = BMI.calculateBMI(weight, height);
		BMI.interpretBMI(a);
	}
}
