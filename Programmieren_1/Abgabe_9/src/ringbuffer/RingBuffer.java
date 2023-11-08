package ringbuffer;

public class RingBuffer {
    private Element readElement;
    private Element writeElement;

    private RingBuffer(int size) {

    }

    private int read() {
        int readInteger = readElement.getInteger();
        // rueckt lesekopf 1 vor
        return readInteger;
    }

    private void write(Integer integer) {
        writeElement.setInteger(integer);
        // rueckt schreibkopf 1 vor
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
