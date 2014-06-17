<%
  		if((String)session.getAttribute("alert") != null){
  			out.println("<strong>"+ (String)session.getAttribute("alert") +"</strong>");
  			out.println("<hr/>");
  			session.removeAttribute("alert");
  		}
%>