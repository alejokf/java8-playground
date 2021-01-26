package interfaces;

public class FigureMain {

    public static void main(String[] args) {
        Cube cube = new Cube(3, 4, 5);
        logFigure(cube);

        Cylinder cylinder = new Cylinder(3, 8);
        logFigure(cylinder);
    }

    private static void logFigure(Figure3D figure3D) {
        System.out.println(String.format("This %s has yhe following values:", figure3D.getClass().getSimpleName()));
        System.out.println(String.format("Volume: %s", figure3D.volume()));
        System.out.println(String.format("Weight: %s", figure3D.weight()));
        System.out.println();
    }
}
