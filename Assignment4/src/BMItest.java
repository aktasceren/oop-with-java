public class BMItest {
	public static void main(String[] args) {
		BMI person1 = new BMI();		
		BMI person2 = new BMI("Sara KING", 215, 70);	
		BMI person3 = new BMI("Kim YOUNG", 18, 145, 70);
		System.out.println("The BMI for "+ person1.getName() + " is " + person1.getBMI() + " " + person1.getStatus());
		System.out.println("The BMI for "+ person2.getName() + "  is " + person2.getBMI() + " " + person2.getStatus());
		System.out.println("The BMI for "+ person3.getName() + "  is " + person3.getBMI() + " " + person3.getStatus());
		}
}