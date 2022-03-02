import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "footer", urlPatterns = {"*.html"})
public class FootFilter implements Filter {

    private static final String content = "<div id=\"date\"></div>"
                                                + "<body>"
                                            + "<html>";

    private void addServletResponse(ServletResponse response, ResponseStringWrapper wrapper) throws  IOException{
        String wrappedContent = wrapper.getContent();
        PrintWriter responseWriter = response.getWriter();
        responseWriter.print(wrappedContent);
    }

    private void addFooter(ServletResponse response) throws  IOException{
        PrintWriter writer = response.getWriter();
        writer.println(content);
        writer.close();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ResponseStringWrapper wrapperResponse = new ResponseStringWrapper(response);
        chain.doFilter(request,wrapperResponse);
        addServletResponse(response,wrapperResponse);
        addFooter(response);
    }
}
