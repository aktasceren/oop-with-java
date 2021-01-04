import java.util.Scanner;

public class HMW1 {
	public static void main(String[] args) {
		int choice;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("----------MENU---------\n\n 1- Calculate BMI \n\n 2- Quit \n\n ----------------------- ?");
		do {
			System.out.println("Your option? ");
			choice = scan.nextInt();
			switch(choice) {
			case 1:
				System.out.print("Enter weight in pounds: ");
				double weight = scan.nextDouble();		
				System.out.print("Enter height in inches: ");
				double height = scan.nextDouble();	
				double BMI = (weight * 0.45359237) / Math.pow((height * 0.0254), 2);
				System.out.println("BMI is " + BMI);
				if(BMI < 18.5) {
					System.out.println("Underweight\n");
				}
				else if(18.5 <= BMI && BMI <= 25.0) {
					System.out.println("Normal\n");
				}
				else if(25.0 <= BMI && BMI <= 30.0) {
					System.out.println("Overweight\n");
				}
				else {
					System.out.println("Obese\n");
				}
				break;
			case 2:
				System.out.println("Sayonara!");
				break;
			default:
				System.out.println("Wrong Entry!!!");
			}
			}while(choice != 2);
	}
}

