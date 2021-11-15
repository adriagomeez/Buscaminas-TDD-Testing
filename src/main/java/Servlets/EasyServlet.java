package Servlets;

import Models.Board;
import Models.Square;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/EasyServlet"})
public class EasyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Board board = Board.getInstance(4, 4, 3);
//        request.setAttribute("tablero", board);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/showBoard");
//        requestDispatcher.forward(request, response);
        response.getWriter().write("<h2>Hola</h2>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
