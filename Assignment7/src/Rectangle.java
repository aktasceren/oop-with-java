
public class Rectangle extends GeometricObject{
	private double width;
	private double height;
	public Rectangle() { 
		}
	public Rectangle(double width, double height){
		this.width = width;
	    this.height = height;
	    }
	public double getWidth() {
		return width;
		}
	public void setWidth(double width) {
	    this.width = width;
	    }
	public double getHeight() {
	    return height;
	    }
	public void setHeight(double height) {
		this.height = height;
		}
	@Override
	public double getArea() {
		return this.height*this.width;
		}
	@Override
	public double getPerimeter() {
		return 2*height*width;
		}
	public String toString() {
		return "[Rectangle] "+ super.toString() + "\nwidth: " + width + " height: " + height + "\nArea of object is " + getArea();
		}
}
