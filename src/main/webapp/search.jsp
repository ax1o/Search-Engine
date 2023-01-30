<%@page import = "java.util.ArrayList"%>
<%@page import = "pack.SearchResult"%>
<%@page import = "pack.Search"%>

<html>

<head>
    <link rel = "stylesheet" type = "text/css" href = "styles.css">
</head>

<body>

<form action = "Search">
    <input type="text" name ="keyword">
    <button type = "submit">Search Again</button>
</form>
<div class = "resultstable">
 <table border = 2>
        <tr>
            <td>Title</td>
            <td>Link</td>
        </tr>
        <%
         //get results from search servlet
         ArrayList<SearchResult> results = (ArrayList<SearchResult>) request.getAttribute("results");

         //Iterate for every data present in results array
         for(SearchResult result : results){

         %>
         <tr>
            <td><% out.println(result.getPageTitle());  %></td>
            <td><a href= "<%out.println(result.getPageLink()); %>"><% out.println(result.getPageLink()); %></a></td>
         </tr>

         <%
         }
         %>

    </table>
</div>
</html>
</body>
