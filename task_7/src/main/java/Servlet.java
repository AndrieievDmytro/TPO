import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/date")
public class Servlet extends HttpServlet {
    private static final DateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    private static  final String dateString = "date";

    private static String date() {
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("{\"" + dateString + "\":\"" + date()+ "\" }");
        writer.close();
    }
}
