
public class BMI {
		private String name;
		private int age;
		private double weight;
		private double height;
		static final double KILOGRAMS_PER_POUND = 0.45359237;
		static final double METERS_PER_INCH =  0.0254 ;
		BMI(){
			name = "John BLACK";
			age = 25;
			weight = 100;
			height = 50;
		}
		BMI(String name2, double weight2, double height2) {	
			name = name2;
			age = 20;
			weight = weight2;
			height = height2;
		}
		BMI(String name3, int age3, double weight3, double height3){
			name = name3;
			age = age3;
			weight = weight3;
			height = height3;
		}
		public String getName() {
			return name;
		}
		public void setName(String name_) {
			 name = name_;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age_) {
			age = age_;
		}
		public double getWeight() {
			return weight;
		}
		public void setWeight(double weight_) {
			weight = weight_;
		}
		public double getHeight() {
			return height;
		}
		public void setHeight(double height_) {
			height = height_;
			}

		public double getBMI(){	
			double bmi = (weight * KILOGRAMS_PER_POUND) / Math.pow((height * METERS_PER_INCH), 2);
			return Math.round(bmi * 100)/100.0;	
		}
		public String getStatus() {
			if(getBMI() < 18.5) {
				return "Underweight";
			}
			else if(18.5 <= getBMI() && getBMI() <= 25.0) {
				return "Normal";
			}
			else if(25.0 <= getBMI() && getBMI() <= 30.0) {
				return "Overweight";
			}
			else {
				return "Obese";
			}
		}
}

