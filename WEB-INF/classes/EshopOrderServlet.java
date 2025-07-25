import java.io.*;
import java.sql.*;
import jakarta.servlet.*;            // Tomcat 10 (Jakarta EE 9)
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/eshoporder")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class EshopOrderServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get an output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
      // Print an HTML page as the output of the query
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head><title>Query Response</title></head>");
      out.println("<body>");

      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "password");   // For MySQL

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         // Step 3 & 4: Execute a SQL SELECT query and Process the query result
         // Retrieve the books' id. Can order more than one books.
         String[] ids = request.getParameterValues("id");
         if (ids != null) {
            String sqlStr;
            int count;
 
            // Process each of the books
            for (int i = 0; i < ids.length; ++i) {
               // Update the qty of the table books
               sqlStr = "UPDATE books SET qty = qty - 1 WHERE id = " + ids[i];
               out.println("<p>" + sqlStr + "</p>");  // for debugging
               count = stmt.executeUpdate(sqlStr);
               out.println("<p>" + count + " record updated.</p>");
 
               //sqlStr = "";

               // Create a transaction record
               sqlStr = "INSERT INTO order_records (id, qty_ordered) VALUES ("
                     + ids[i] + ", 1)";
               out.println("<p>" + sqlStr + "</p>");  // for debugging
               count = stmt.executeUpdate(sqlStr);
               out.println("<p>" + count + " record inserted.</p>");
               out.println("<h3>Your order for book id=" + ids[i]
                     + " has been confirmed.</h3>");
            }
            out.println("<h3>Thank you.<h3>");
         }  else { // No book selected
            out.println("<h3>Please go back and select a book...</h3>");
            }
      } catch (SQLException ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  

      out.println("</body></html>");
      out.close();
   }  // Properly closes doGet()

   // doPost() should be OUTSIDE doGet()
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      doGet(request, response);  // Re-direct POST request to doGet()
   }

} // Properly closes class