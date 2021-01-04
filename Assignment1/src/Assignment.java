import java.util.Scanner;
	public class Assignment{
		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Please enter age of employee: ");
			int age = scan.nextInt();
			System.out.println("Please enter salary of employee: ");
			float salary = scan.nextFloat();
			double tax;
			if(age < 18 || age > 95) {
				System.out.println("Please enter an age between 18 and 95!!! ");
			}
			if (18 <= age && age <= 35) {
				tax = 0.16 * 17000 + 0.25 * (salary - 17000);
				System.out.println("tax is " + tax);
				 		}
			else if (36 <= age && age <= 50) {
				tax = 0.16 * 23000 + 0.25 * (salary - 23000);
				System.out.println("tax is " + tax);
			}
			else if (51 <= age && age <= 74) {
				tax = 0.16 * 29000 + 0.25 * (salary - 29000);
				System.out.println("tax is " + tax);
			}
			else if (75 <= age && age <= 95) {
				tax = 0.16 * 14000 + 0.25 * (salary - 14000);
				System.out.println("tax is " + tax);
			}
			
		}
	}
