package hypercube;

public class HypercubeValue extends Hypercube {
    Integer value;

    HypercubeValue() {
        super(0, 0);
    }

    @Override
    public String toString(){
        return "[] = " + value;
    }
}
