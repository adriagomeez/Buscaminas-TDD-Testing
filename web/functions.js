
$(document).ready(function () {
    $('#container').on('click', '.casilla', function (){
        var r = $(this).attr('name');
        var c = $(this).attr('value');
        $.ajax({
            method:"POST",
            url: "GameServlet",
            data: {
                row: r,
                col: c
            },
            success:function(response){
                $("#container").html(response);
            }})
    })

    $("#easy").click(function (){
        $.ajax({
            method: "POST",
            url: "InicioServlet",
            data:{
                rows: 5,
                cols: 5,
                mines: 2
            },
            success:function(response){
                $("#container").html(response);
            }})
    })

    $("#medium").click(function (){
        $.ajax({
            method: "POST",
            url: "InicioServlet",
            data:{
                rows: 8,
                cols: 8,
                mines: 7
            },
            success:function(response){
                $("#container").html(response);
            }})
    })

    $("#hard").click(function (){
        $.ajax({
            method: "POST",
            url: "InicioServlet",
            data:{
                rows: 10,
                cols: 10,
                mines: 15
            },
            success:function(response){
                $("#container").html(response);
            }})
    })
});