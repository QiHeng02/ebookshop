import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/payment-success")
public class PaymentSuccessServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("cart"); // Clear cart after successful payment

        response.setContentType("text/html");
        response.getWriter().println("<h1>Payment Successful!</h1>");
        response.getWriter().println("<a href='/ebookshop/eshopquery'>Return to Shop</a>");
    }
}
