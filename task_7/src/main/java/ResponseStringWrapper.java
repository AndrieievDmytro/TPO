import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseStringWrapper extends HttpServletResponseWrapper {

    private final StringWriter writer =new StringWriter();

    public ResponseStringWrapper(HttpServletResponse response) {
        super(response);
    }
    public ResponseStringWrapper(ServletResponse response){
        this((HttpServletResponse) response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(writer);
    }

    public ServletOutputStream getOutputStream()throws IOException{
        throw new IllegalArgumentException("OutputStream is not allowed");
    }

    public String getContent(){
        return writer.toString();
    }
}
