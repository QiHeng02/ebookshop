import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Checkout</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: beige; }");
        out.println(".container { width: 80%; max-width: 800px; margin: 50px auto; background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }");
        out.println("h1 { text-align: center; margin-bottom: 30px; color: #333; }");
        out.println("form { display: grid; grid-gap: 20px; }");
        out.println("label { font-weight: bold; }");
        out.println("input[type='text'], input[type='email'], input[type='tel'], textarea { padding: 12px; border: 1px solid #ddd; border-radius: 4px; width: 100%; box-sizing: border-box; }");
        out.println("textarea { resize: vertical; }");
        out.println(".button-container { display: flex; justify-content: space-between; }");
        out.println("input[type='submit'], .cancel-button { background-color: #007bff; color: white; padding: 15px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; transition: background-color 0.3s; }");
        out.println("input[type='submit']:hover, .cancel-button:hover { background-color: #0056b3; }");
        // Pastel red for cancel button
        out.println(".cancel-button { background-color: #f08080; color: white; }"); // Changed
        out.println("</style>");
        out.println("</head><body>");

        out.println("<div class='container'>");
        out.println("<h1>Checkout</h1>");
        out.println("<form method='post' action='processOrder'>");
        out.println("<label for='name'>Name:</label>");
        out.println("<input type='text' id='name' name='name' required>");

        out.println("<label for='email'>Email:</label>");
        out.println("<input type='email' id='email' name='email' required>");

        out.println("<label for='phone'>Phone:</label>");
        out.println("<input type='tel' id='phone' name='phone'>");

        out.println("<label for='address'>Address:</label>");
        out.println("<textarea id='address' name='address' required></textarea>");

        out.println("<div class='button-container'>");
        out.println("<input type='submit' value='Place Order'>");
        // Link to cart page
        out.println("<a href='cart' class='cancel-button'>Cancel</a>"); // Changed
        out.println("</div>");
        out.println("</form>");
        out.println("</div>");

        out.println("</body></html>");
        out.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}