package ringbuffer;

public class Element {
    Element next;
    private Integer integer;

    Element() {

    }

    Integer getInteger() {
        return integer;
    }

    void setInteger(Integer integer) {
        this.integer = integer;
    }
}
