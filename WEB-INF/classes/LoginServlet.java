import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><body style='font-family: Arial, sans-serif; text-align: center; padding: 20px;'>");

            String loginId = request.getParameter("login_id");
            String password = request.getParameter("psw");

            // Server-side validation
            if (loginId == null || loginId.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                out.println("<p>Please provide username/email and password. <a href='login.html'>Try again</a></p>");
                out.println("</body></html>");
                return;
            }

            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "myuser", "password")) {
                String sql = "SELECT email, username FROM users WHERE (username = ? OR email = ?) AND password = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, loginId);
                    pstmt.setString(2, loginId);
                    pstmt.setString(3, password);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("username", rs.getString("username"));
                        session.setAttribute("email", rs.getString("email"));
                        response.sendRedirect("eshopquery");
                    } else {
                        out.println("<p>Invalid username/email or password. <a href='login.html'>Try again</a></p>");
                    }
                }
            } catch (SQLException ex) {
                out.println("<p>Error: " + ex.getMessage() + " <a href='login.html'>Try again</a></p>");
                ex.printStackTrace();
            }

            out.println("</body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect("/eshopquery");
        } else {
            response.sendRedirect("login.html");
        }
    }
}