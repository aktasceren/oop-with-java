import java.util.Scanner;

public class ATM extends Account{
	int choice;
	Account[] arr = new Account[10];{
		for(int i = 0; i < 10; i++)
		{
			arr[i] = new Account(i, 100);
		}
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter an id: ");
		int id = scan.nextInt();
		do {
			if(1 > id || id > 10) {
				System.out.print("Please enter correct id: ");
				id = scan.nextInt();
				}
			System.out.println("Main menu \n 1: Check balance \n 2: Withdraw \n 3: Deposit \n 4: Exit");
			System.out.print("Enter a choice:");
			choice = scan.nextInt();
			switch(choice){
			case 1:
				System.out.println("The balance is: " + arr[id].getterBalance());
				System.out.println("");
				break;
			case 2:
				System.out.print("Enter an amount to withdraw: ");
				int withdraw = scan.nextInt();
				arr[id].withdraw(withdraw);
				System.out.println("");
				break;
			case 3:
				System.out.print("Enter an amount to deposit: ");
				int deposit = scan.nextInt();
				arr[id].deposit(deposit);
				System.out.println();
				break;
			case 4:
				System.out.print("Enter an id: ");
				id = scan.nextInt();
				break;
			default:
				System.out.println("Wrong entry!!!\n");
				}
		}while(choice > 0);
	}
}