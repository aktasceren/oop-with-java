
public class Square extends GeometricObject implements Colorable {
	private double side;
	public Square() {
		}
	public Square(double side) {
		this.side = side;
		}
	@Override
	public String howToColor() {
		return "Color all four sides";
		}
	@Override
	public double getArea() {
		return side*side;
		}
	@Override
	public String toString() {
		return "[Square] "+ super.toString() + "\nSide: " + side + "\nArea of object is " + getArea() + "\n" +howToColor();
		}
	@Override
	public double getPerimeter() {
		return 4*side;
		}
}
