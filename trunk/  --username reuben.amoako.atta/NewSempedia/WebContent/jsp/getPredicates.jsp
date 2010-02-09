<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.jquery.GetAutoCompleteData"%>
<%
	GetAutoCompleteData db = new GetAutoCompleteData("predicate");
    String query = request.getParameter("q");
   	List countries = db.getData(query);
    Iterator iterator = countries.iterator();
    while(iterator.hasNext()) 
    {
        String country = (String)iterator.next();
        out.println(country);
    }
%>