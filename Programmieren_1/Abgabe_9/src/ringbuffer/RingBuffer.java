package ringbuffer;

class RingBuffer {
    private Element readElement;
    private Element writeElement;

    RingBuffer(int size) {
        Element first = new Element();
        Element element = first;
        for (int i = 1; i < size; i++) {
            element.next = new Element();
            element = element.next;
        }

        element.next = first;
        element = first;

        readElement = element;
        writeElement = element;
    }

    Integer read() {
        Integer readInteger = readElement.integer;
        readElement = readElement.next;
        return readInteger;
    }

    void write(Integer integer) {
        writeElement.integer = integer;
        writeElement = writeElement.next;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        Element element = readElement;
        do {
            if (readElement == element && writeElement == element) {
                output.append("r/w ");
            } else if (readElement == element) {
                output.append("r   ");
            } else if (writeElement == element) {
                output.append("w   ");
            } else {
                output.append("    ");
            }
            output.append(element.integer).append("\n");
            element = element.next;
        } while (!element.equals(readElement));

        return output.toString();
    }
}
