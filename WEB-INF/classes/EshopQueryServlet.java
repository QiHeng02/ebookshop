// To save as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;            // Tomcat 10 (Jakarta EE 9)
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/eshopquery")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class EshopQueryServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)   
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();

      // Print an HTML page as the output of the query
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Yet Another e-Bookshop</title>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
        out.println("<link rel='preconnect' href='https://fonts.googleapis.com'>");
        out.println("<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Lora&family=Oswald:wght@400;700&display=swap' rel='stylesheet'>");
        out.println("<style>");
        out.println("body { font-family: 'Lora', serif; }");
        out.println(".navbar { width: 100%; background-color: #555; overflow: auto; }");
        out.println(".navbar a { float: left; padding: 20px 25px; color: white; text-decoration: none; font-size: 30px; }"); 
        out.println(".navbar a:hover { background-color: #000; }");
        out.println(".active { background-color: #04AA6D; }");
        //css for logout button
        out.println(".navbar a.logout { float: right; }");
        out.println("@media screen and (max-width: 500px) { .navbar a { float: none; display: block; } .navbar a.logout { float: none; } }");
        out.println("body { font-family: 'Lora', serif; margin: 0; padding: 0; background-color: beige; }");
        out.println("header { background-color: #fff; padding: 20px; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); }");
        out.println("@media screen and (max-width: 500px) { .navbar a { float: none; display: block; } }");
        out.println("body { font-family: 'Lora', serif; margin: 0; padding: 0; background-color: beige; }");
        out.println("header { background-color: #fff; padding: 20px; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); }");
        out.println("header h1 { margin: 0; font-family: 'Oswald', sans-serif; font-weight: 700; }"); // Heading font
        out.println("header form { display: flex; align-items: center; }");
        out.println("header label { margin: 0 10px; font-size: 24px; }");
        out.println("header select, header input[type='submit'] { padding: 15px 20px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 24px; margin: 0 10px;}");
        out.println("header input[type='submit'] { background-color: #007bff; color: white; cursor: pointer; transition: background-color 0.3s; }");
        out.println("header input[type='submit']:hover { background-color: #0056b3; }");
        out.println("main { width: 90%; max-width: 1200px; margin: 20px auto; }");
        out.println(".book-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 30px; }");
        out.println(".book-item { background-color: white; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15); }");
        out.println(".book-image { width: 100%; height: 350px; object-fit: contain; }");
        out.println(".book-details { padding: 25px; }");
        out.println(".book-details h3 { font-size: 1.2em; margin-top: 0; font-family: 'Oswald', sans-serif; font-weight: 400;}"); // Book title font
        out.println(".book-details p { font-size: 1em; margin-bottom: 12px; }");
        out.println(".add-to-cart { background-color: #007bff; color: white; padding: 12px 18px; border: none; border-radius: 4px; cursor: pointer; transition: background-color 0.3s; width: 100%; font-size: 1em; }");
        out.println(".add-to-cart:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head><body>");

        // Responsive Navigation Bar
        out.println("<div class='navbar'>");
        out.println("<a class='active' href='/ebookshop/eshopquery'><i class='fa fa-fw fa-home'></i> Home</a>");
        out.println("<a href='/ebookshop/cart' class='cart'><i class='fa fa-shopping-cart'></i> Cart</a>");
        out.println("<a href='login.html' class='logout'><i class='fa fa-fw fa-sign-out'></i> Log Out</a>");
        out.println("</div>");

        //main header
        out.println("<header>");
        out.println("<div class='logo-container'>");
        out.println("<img src='img_logo.png' alt='Bookshop Logo' style='width: 150px; height: auto;'>");
        out.println("</div>");
        out.println("<h1>Welcome to NTU e-Bookshop!</h1>");
        out.println("<form method='get' action='eshopquery'>");
        out.println("<label for='author'>Choose an author:</label>");
        out.println("<select name='author' id='author' size='1'>");
        out.println("<option value=''>None</option>");
        
        /*out.println("<option value='Tan Ah Teck'>Ah Teck</option>");
        out.println("<option value='Mohammad Ali'>Ali</option>");
        out.println("<option value='Kumar'>Kumar</option>");
        out.println("</select>");
        out.println("<label for='category'>Choose a category:</label>"); // Added category filter
        out.println("<select name='category' id='category' size='1'>");
        out.println("<option value=''>All</option>");
        out.println("<option value='Fiction'>Fiction</option>");
        out.println("<option value='Science'>Science</option>");
        out.println("<option value='Educational'>Educational</option>");
        out.println("<input type='submit' value='Filter' />");*/
        out.println("</form>");
        out.println("</header>"); 

      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "password");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement()) {

         List<String> authors = getAuthors(conn);
         for (String author : authors) {
            out.println("<option value='" + author + "'>" + author + "</option>");
         }

         out.println("</select>");
         out.println("<label for='category'>Choose a category:</label>");
         out.println("<select name='category' id='category' size='1'>");
         out.println("<option value=''>All</option>");

         List<String> categories = getCategories(conn);
         for (String category : categories) {
            out.println("<option value='" + category + "'>" + category + "</option>");
         }

         out.println("</select>");
         out.println("<input type='submit' value='Filter' />");
         out.println("</form>");
         out.println("</header>");

         // Step 3: Execute a SQL SELECT query
         // === Form the SQL command - BEGIN ===
         String sqlStr = "SELECT id, title, author, price, image_url, category FROM books WHERE 1=1";
         String[] authorsParam = request.getParameterValues("author");
         String categoryParam = request.getParameter("category");

          if (authorsParam != null && authorsParam.length > 0 && !(authorsParam.length == 1 && authorsParam[0].isEmpty())) {
                sqlStr += " AND author IN (";
                for (int i = 0; i < authorsParam.length; ++i) {
                    sqlStr += "'" + authorsParam[i] + "'";
                    if (i < authorsParam.length - 1) {
                        sqlStr += ", ";
                    }
                }
                sqlStr += ")";
            }

            if (categoryParam != null && !categoryParam.isEmpty()) {
                sqlStr += " AND category = '" + categoryParam + "'";
            }
         // === Form the SQL command - END ===

         ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server
         //out.println("<h3>Thank you for your query.</h3>");
         //out.println("<p>Your SQL statement is: " + sqlStr + "</p>"); // Echo for debugging
         

         // Step 4: Process the query result set
         out.println("<main>");
         out.println("<div class='book-grid'>");
         // For each row in ResultSet, print one checkbox inside the <form>
         while(rset.next()) {
            out.println("<div class='book-item'>");
                out.println("<img src='" + rset.getString("image_url") + "' alt='" + rset.getString("title") + "' class='book-image'>");
                out.println("<div class='book-details'>");
                out.println("<h3>" + rset.getString("title") + "</h3>");
                out.println("<p>Author: " + rset.getString("author") + "</p>");
                out.println("<p>Price: $" + rset.getDouble("price") + "</p>");
                out.println("<form action='cart' method='post'>");
                out.println("<input type='hidden' name='book_id' value='" + rset.getInt("id") + "'>");
                out.println("<button type='submit' class='add-to-cart'>Add to Cart</button>");
                out.println("</form>");
                out.println("</div></div>");
         }
         out.println("</div>");
         out.println("</main>");
         
         // === Step 4 ends HERE - Do NOT delete the following codes ===
      } catch(SQLException ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
 
      out.println("</body></html>");
      out.close();
   }

   private List<String> getAuthors(Connection conn) throws SQLException {
        List<String> authors = new ArrayList<>();
        String sql = "SELECT DISTINCT author FROM books ORDER BY author";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                authors.add(rs.getString("author"));
            }
        }
        return authors;
    }

    private List<String> getCategories(Connection conn) throws SQLException {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT category FROM books ORDER BY category";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
        }
        return categories;
    }
}