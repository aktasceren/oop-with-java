public class BMI {
	public static double calculateBMI(double weight, double height){
		double x = (weight * 0.45359237) / Math.pow((height * 0.0254), 2);
		System.out.println("BMI is " + x);
		return x;
	}
	public static String interpretBMI(double bmi) {
		if(bmi < 18.5) {
			Double.toString(bmi);
			System.out.println("Underweight");
		}
		else if(18.5 <= bmi && bmi < 25) {
			Double.toString(bmi);
			System.out.println("Normal");
		}
		else if(25 <= bmi && bmi < 30) {
			Double.toString(bmi);
			System.out.println("Overweight");
		}
		else {
			Double.toString(bmi);
			System.out.println("Obese");
		}
		return "";
	}
}
