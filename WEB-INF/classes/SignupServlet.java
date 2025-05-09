import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String signupDate = request.getParameter("signup_date");
        String password = request.getParameter("psw");
        String passwordRepeat = request.getParameter("psw-repeat");

        // Server-side validation
        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Name is required.");
            request.getRequestDispatcher("signup.html").forward(request, response);
            return;
        }

        if (!email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}")) {
            request.setAttribute("errorMessage", "Invalid email format.");
            request.getRequestDispatcher("signup.html").forward(request, response);
            return;
        }

        if (!phone.matches("\\d{8}")) {
            request.setAttribute("errorMessage", "Phone number must be exactly 8 digits.");
            request.getRequestDispatcher("signup.html").forward(request, response);
            return;
        }

        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(signupDate);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Invalid date format. Use YYYY-MM-DD.");
            request.getRequestDispatcher("signup.html").forward(request, response);
            return;
        }

        if (!password.equals(passwordRepeat)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("signup.html").forward(request, response);
            return;
        }

        // Database insertion
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "myuser", "password")) {
            String checkSql = "SELECT email FROM users WHERE email = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    // Email exists, forward to email-exists.html
                    request.getRequestDispatcher("email-exists.html").forward(request, response);
                    return;
                }
            }

            String insertSql = "INSERT INTO users (email, password, username, phone, signup_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                String username = email.split("@")[0];
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                pstmt.setString(3, username);
                pstmt.setString(4, phone);
                pstmt.setDate(5, java.sql.Date.valueOf(signupDate));
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    // Signup successful, forward to signup-success.html
                    request.getRequestDispatcher("signup-success.html").forward(request, response);
                    return;
                } else {
                    // Signup failed, forward to signup.html with error message
                    request.setAttribute("errorMessage", "Signup failed. Please try again.");
                    request.getRequestDispatcher("signup.html").forward(request, response);
                    return;
                }
            }
        } catch (SQLException ex) {
            request.setAttribute("errorMessage", "Error: " + ex.getMessage());
            request.getRequestDispatcher("signup.html").forward(request, response);
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("signup.html");
    }
}