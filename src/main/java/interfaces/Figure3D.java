package interfaces;

public interface Figure3D {

    double GOLD_DENSITY = 19.3;

    double volume();

    default double weight() {
        return volume() * GOLD_DENSITY;
    }
}
