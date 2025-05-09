import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Integer> cart = (List<Integer>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        String bookId = request.getParameter("book_id");
        if (bookId != null) {
            cart.add(Integer.parseInt(bookId));
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        List<Integer> cart = (List<Integer>) session.getAttribute("cart");

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Shopping Cart</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
        out.println("<link rel='preconnect' href='https://fonts.googleapis.com'>");
        out.println("<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Lora&family=Oswald:wght@400;700&display=swap' rel='stylesheet'>");
        out.println("<style>");
        out.println("body { font-family: 'Lora', serif; background-color: beige; }");
        out.println(".navbar { width: 100%; background-color: #555; overflow: auto; }");
        out.println(".navbar a { float: left; padding: 20px 25px; color: white; text-decoration: none; font-size: 30px; }");
        out.println(".navbar a:hover { background-color: #000; }");
        out.println(".active { background-color: #04AA6D; }");
        out.println(".navbar a.logout { float: right; }");
        out.println("@media screen and (max-width: 500px) { .navbar a { float: none; display: block; } }");
        out.println("h1 { font-family: 'Oswald', sans-serif; font-weight: 700; }"); // Heading font
        out.println("</style>");
        out.println("<script>");
        out.println("function checkCart() {");
        out.println("   var cartIsEmpty = " + (cart == null || cart.isEmpty()) + ";");
        out.println("   if (cartIsEmpty) {");
        out.println("       alert('Your cart is empty. Please add items to your cart before checking out.');");
        out.println("       return false;");
        out.println("   } else {");
        out.println("       return true;");
        out.println("   }");
        out.println("}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");

        // Navigation Bar Section!!!
        out.println("<div class='navbar'>");
         out.println("<a href='/ebookshop/eshopquery'><i class='fa fa-fw fa-home'></i> Home</a>");
        out.println("<a href='login.html' class='logout'><i class='fa fa-fw fa-sign-out'></i> Log Out</a>");
        out.println("</div>");

        out.println("<div class='container mt-5'>");
        out.println("<div class='card shadow-lg p-4'>");
        out.println("<h1 class='text-center text-primary'>Shopping Cart</h1>");

        double totalPrice = 0.0;

        if (cart == null || cart.isEmpty()) {
            out.println("<p class='text-center'>Your cart is currently empty :(.</p>");
        } else {
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "myuser", "password")) {
                String sql = "SELECT id, title, price, image_url FROM books WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                out.println("<table class='table table-bordered mt-3'>");
                out.println("<tr><th>Image</th><th>Title</th><th>Price</th><th>Action</th></tr>");

                for (Integer bookId : cart) {
                    pstmt.setInt(1, bookId);
                    ResultSet rset = pstmt.executeQuery();
                    if (rset.next()) {
                        double price = rset.getDouble("price");
                        totalPrice += price;
                        out.println("<tr>");
                        out.println("<td><img src='" + rset.getString("image_url") + "' width='50' height='70'></td>");
                        out.println("<td>" + rset.getString("title") + "</td>");
                        out.println("<td>$" + rset.getDouble("price") + "</td>");
                        out.println("<td><a href='removeFromCart?book_id=" + bookId + "' class='btn btn-danger'>Remove</a></td>");
                        out.println("</tr>");
                    }
                }
                out.println("<tr><td colspan='2'><strong>Total Price:</strong></td><td><strong>$" + totalPrice + "</strong></td><td></td></tr>");
                out.println("</table>");
                pstmt.close();
            } catch (Exception ex) {
                out.println("<p>Error retrieving cart items: " + ex.getMessage() + "</p>");
            }
        }

        out.println("<a href='eshopquery' class='btn btn-primary mt-3'>Continue Shopping</a>");
        out.println("<a href='checkout' class='btn btn-success mt-3' onclick='return checkCart()'>Checkout</a>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}