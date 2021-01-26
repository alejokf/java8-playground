package interfaces;

public class Cylinder implements Figure3D {

    private double radius;
    private double length;

    public Cylinder(double radius, double length) {
        this.radius = radius;
        this.length = length;
    }

    @Override
    public double volume() {
        return Math.PI * Math.pow(radius, 2) * length;
    }
}
