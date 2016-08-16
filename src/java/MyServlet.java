/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a10441
 */
@WebServlet("/myServlet")
public class MyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
          String idVal = request.getParameter("id");
        int id = idVal.length() > 0 ? Integer.valueOf(idVal) : 1;
        try{  
            Class.forName("com.mysql.jdbc.Driver");  

                        Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/release01","root","oxalic");  

            //here sonoo is database name, root is username and password  
  
            Statement stmt=con.createStatement();
//            stmt.executeUpdate("insert into emp values(" + id +",'"+ name +"','"+ password +"')");
            
            ResultSet rs=stmt.executeQuery("select * from emp where id=" + id);  

            if(idVal.isEmpty()) {
                 while(rs.next()) {
                request.setAttribute("id", rs.getInt(1));
                request.setAttribute("name", rs.getString(2));
                request.setAttribute("password", rs.getString(3));
            } 
                 request.getRequestDispatcher("show.jsp").forward(request, response);
            } else {
                Map<String, Object> data = new HashMap<>();
                    while(rs.next()) {
                    data.put("id", rs.getInt(1));
                    data.put("name", rs.getString(2));
                    data.put("password", rs.getString(3));
                } 
                String json = new Gson().toJson(data);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
  
            //            ResultSet rs=stmt.executeQuery("select * from emp");  

            //while(rs.next())  
            //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  

            con.close();  

            }catch(Exception e){ System.out.println(e);} 
        
        
        
        
        
       
    
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if(id == null || id.isEmpty() || name == null || name.isEmpty() || password == null || password.isEmpty()) {
           request.getRequestDispatcher("error.jsp").forward(request, response); 
        } else {
            try{  
            Class.forName("com.mysql.jdbc.Driver");  

                        Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/release01","root","oxalic");  

            //here sonoo is database name, root is username and password  
  
            Statement stmt=con.createStatement();
            stmt.executeUpdate("insert into emp values(" + id +",'"+ name +"','"+ password +"')");
  
            //            ResultSet rs=stmt.executeQuery("select * from emp");  

            //while(rs.next())  
            //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  

            con.close();  

            }catch(Exception e){ System.out.println(e);} 
            request.getRequestDispatcher("two.jsp").forward(request, response); 

        }
        
         

        
//        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
