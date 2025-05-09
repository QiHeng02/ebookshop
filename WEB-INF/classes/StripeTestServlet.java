import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.stripe.Stripe;
import com.stripe.model.Balance;

@WebServlet("/stripe-test")
public class StripeTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Set your Stripe secret key (Replace with your actual key from Stripe Dashboard)
            Stripe.apiKey = "sk_test_your_secret_key_here";

            // Fetch account balance to verify Stripe API works
            Balance balance = Balance.retrieve();
            out.println("<h1>Stripe Integration Successful!</h1>");
            out.println("<p>Stripe Balance: " + balance + "</p>");
        } catch (Exception e) {
            out.println("<h1>Error: Stripe Integration Failed</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
        }
    }
}
