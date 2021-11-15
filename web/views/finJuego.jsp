<%@ page import="Models.Board" %>
<%@ page import="Models.Square" %><%--
  Created by IntelliJ IDEA.
  User: Adria
  Date: 15/11/2021
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div>
  <h2>Has Perdido!!</h2>
  <%
    Board tablero = (Board) request.getAttribute("tablero");

    for(int i = 0; i < tablero.getNumRows(); i++){
      for (int j = 0; j < tablero.getNumCols(); j++){
        Square s = tablero.getSquare(i, j);

        if(s.isMine())
          out.println("<button class='casilla' id='mina' name='"+i+"' value='"+j+"'>*</button>");
        else
          out.println("<button class='casilla' id='abierta' name='"+i+"' value='"+j+"'>"+ s.getNearMines() +"</button>");
      }
      out.println("<br>");
    }
  %>
</div>

</html>
