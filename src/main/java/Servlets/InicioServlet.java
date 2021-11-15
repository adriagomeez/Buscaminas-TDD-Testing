package Servlets;

import Models.Board;
import Models.RandomNumber;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class InicioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int r = Integer.parseInt(request.getParameter("rows"));
        int c = Integer.parseInt(request.getParameter("cols"));
        int m = Integer.parseInt(request.getParameter("mines"));
        Board buscaminas = Board.getInstance(r,c,m);
        buscaminas.createMines(new RandomNumber());
        request.setAttribute("tablero", buscaminas);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/juego.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
