package org.example;
import java.io.IOException;
import java.io.OutputStream;

public class OutputDevice implements AutoCloseable {

    private OutputStream outputStream;

    public OutputDevice(OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("OutputStream cannot be null");
        }
        this.outputStream = outputStream;
    }

    public void writeMessage(String message) throws IOException {
        this.outputStream.write(message.getBytes());
        this.outputStream.write('\n');
    }

    @Override
    public void close() throws IOException {
        this.outputStream.close();
    }

    public Object getOutputStream() {
        return this.outputStream;
    }
}
