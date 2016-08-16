<%-- 
    Document   : show
    Created on : 12 Aug, 2016, 8:40:08 PM
    Author     : a10441
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $( document ).ready(function() {
                console.log( "ready!" );
                $('.button1').click(function(data, status){
                    var $this = $(this);
                    var val = $this.val();
                    var val1 = $this.attr('data-val');
                    var idVal = parseInt(val1);
                    
                    $.get("/servlet_pro/myServlet?id=" + val1, function(data, status){
                    alert("Data: " + data + "\nStatus: " + status);
                    data = JSON.parse(data);
                    if( status === "success" && data != null) {
                        $('#id').val(data.id);
                        $('#name').val(data.name);
                        $('#pass').val(data.password);
                        $('#preBtn').attr('data-val', idVal - 1);  
                        $('#nextBtn').attr('data-val', idVal + 1); 
                    } else {
                        alert("Data not found")
                    }
                    
                });
                });
                
            });
        </script>
    </head>
    <body>
        <!--<h1>Hello World!</h1>-->
        
        <div>
                <label>ID: </label>
                <input type="text" id="id" value="<%=request.getAttribute("id")%>"/>
                 <br>
            </div>
           
            <div>
                <label>Name: </label>
                <input type="text" id="name" value="<%=request.getAttribute("name")%>">
               <br>
            </div>

            
            <div>
                <label>Password: </label>
                <input type="text" id="pass" value="<%=request.getAttribute("password")%>"/>
                <br>
            </div>
                
                <input type="button" id="preBtn" class="button1" data-val="1" value="Previous" />
                <input type="button" id="nextBtn" class="button1" data-val="2" value="Next" />
    </body>
</html>
