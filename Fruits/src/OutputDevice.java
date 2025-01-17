import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;

public class OutputDevice {

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
}
