
public abstract class GeometricObject {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;
	protected GeometricObject() {
		dateCreated = new java.util.Date();
		}
	public String getColor() {
		return color;
		}
	public boolean isFilled() {
		return filled;
		}
	public java.util.Date getDateCreated() {
		return dateCreated;
		}
	@Override
	public String toString() {
		return "created on " + getDateCreated() + "\ncolor: " + getColor() + " and filled: " + isFilled();
	}
	public abstract double getArea();
	public abstract double getPerimeter();
}
