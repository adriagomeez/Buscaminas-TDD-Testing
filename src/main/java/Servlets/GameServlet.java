package Servlets;

import Models.Board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GameServlet", urlPatterns = "/GameServlet")
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int r = Integer.parseInt(request.getParameter("row"));
        int c = Integer.parseInt(request.getParameter("col"));
        Board buscaminas = Board.getInstance(4,4,2);
        boolean res = buscaminas.selectSquare(r, c);
        request.setAttribute("tablero", buscaminas);
        RequestDispatcher requestDispatcher;
        if (!res)
            requestDispatcher = request.getRequestDispatcher("views/juego.jsp");
        else
            requestDispatcher = request.getRequestDispatcher("views/finJuego.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
