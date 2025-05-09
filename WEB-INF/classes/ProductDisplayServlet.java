/*import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/productdisplay")
public class ProductDisplayServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Book Collection</title>");
        // ... (Include your CSS styles here) ...
        out.println("</head><body>");

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "myuser", "password");
             Statement stmt = conn.createStatement()) {

            String sqlStr = "SELECT id, title, author, price, image_url FROM books";
            ResultSet rset = stmt.executeQuery(sqlStr);

            out.println("<main>");
            out.println("<div class='book-grid'>");

            while (rset.next()) {
                out.println("<div class='book-item'>");
                out.println("<img src='" + rset.getString("image_url") + "' alt='" + rset.getString("title") + "' class='book-image'>");
                out.println("<div class='book-details'>");
                out.println("<h3>" + rset.getString("title") + "</h3>");
                out.println("<p>Author: " + rset.getString("author") + "</p>");
                out.println("<p>Price: $" + rset.getDouble("price") + "</p>");
                out.println("<button class='add-to-cart'>Add to Cart</button>");
                out.println("</div></div>");
            }

            out.println("</div>");
            out.println("</main>");

        } catch (SQLException ex) {
            out.println("<p>Error: " + ex.getMessage() + "</p>");
            ex.printStackTrace();
        }

        out.println("</body></html>");
        out.close();
    }
} */