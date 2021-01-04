
public class Circle extends GeometricObject{
	private double radius;
	public Circle() {
		}
	public Circle(double radius) {
		this.radius = radius;
		}
	public double getRadius() {
		return radius;
		}
	public void setRadius(double radius) {
		this.radius = radius;
		}
	@Override
	public double getArea() {
		return radius * radius * Math.PI;
		}
	public String toString() {
		return "[Circle] "+ super.toString() + "\nRadius= " + radius + "\nArea of object is " + getArea();
		}
	@Override
	public double getPerimeter() {
		 return 2*Math.PI*radius;
		}
}
