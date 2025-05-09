import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/processOrder")
public class ProcessOrderServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Order Confirmation</title>");
        out.println("<style>");
        out.println("body { font-family: 'Arial', sans-serif; background-color: rgba(0, 0, 0, 0.4); display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
        out.println(".modal { background-color: white; padding: 40px; border-radius: 12px; box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2); width: 85%; max-width: 650px; text-align: center; position: relative; }"); // Added position: relative
        out.println("h1 { font-family: 'Georgia', serif; color: #333; margin-bottom: 30px; font-size: 28px; }");
        out.println("p { font-size: 18px; line-height: 1.6; color: #555; margin-bottom: 20px; }");
        out.println("ul { list-style: none; padding: 0; margin-bottom: 20px; }");
        out.println("li { font-size: 16px; color: #666; margin-bottom: 8px; }");
        out.println("strong { font-weight: bold; color: #333; }");
        out.println(".close-button { position: absolute; top: 10px; right: 10px; cursor: pointer; background: none; border: none; font-size: 20px; color: #888; }"); // Added
        out.println(".close-button:hover { color: #333; }"); // Added
        out.println("</style>");
        out.println("</head><body>");

        out.println("<div class='modal'>");
        out.println("<button class='close-button' onclick=\"window.location.href='eshopquery'\">&times;</button>"); // Added close button
        out.println("<h1>Order Confirmation</h1>");
        out.println("<p>Thank you, <strong>" + name + "</strong>, for your order!</p>");
        out.println("<p>Your order details:</p>");
        out.println("<ul>");
        out.println("<li><strong>Email:</strong> " + email + "</li>");
        out.println("<li><strong>Phone:</strong> " + phone + "</li>");
        out.println("<li><strong>Address:</strong> " + address + "</li>");
        out.println("</ul>");

        // Here, you would typically add database logic to save the order
        // and send confirmation emails, etc.

        out.println("</div>");
        out.println("</body></html>");
        out.close();
    }
}