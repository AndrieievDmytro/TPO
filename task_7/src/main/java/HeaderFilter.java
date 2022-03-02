import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "header" , urlPatterns = {"*.html"})
public class HeaderFilter implements Filter {

    private static final String header = "<html>"
                                           + "<head>"
                                                + "<script src = \"scripts/jquery.js\"></script>"
                                                + "<script src = \"scripts/index.js\"></script>"
                                            +"<head>"
                                        +"<body>";

    private static final String content = "<br>Header for the web-page</br>";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        writer.write(header);
        writer.write(content);
        chain.doFilter(request,response);
    }
}
