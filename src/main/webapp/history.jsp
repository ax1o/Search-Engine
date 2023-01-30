<%@page import = "java.util.ArrayList"%>
<%@page import = "pack.HistoryResult"%>
<html>
<head>
    <link rel = "stylesheet" type = "text/css" href = "styles.css">
</head>
<body>

<form action = "Search">
    <input type="text" name ="keyword">
    <button type = "submit">Search</button>
</form>

    <div class = "resultstable">
    <table border = 2>

        <tr>
            <td>Keyword</td>
            <td>Link</td>
        </tr>

        <%
        //getting result from history servlet
        ArrayList<HistoryResult> results = (ArrayList<HistoryResult>)request.getAttribute("results");
        //Iterate through results arraylist
        for(HistoryResult result:results){

        %>

            <tr>
                <td><%out.println(result.getKeyword());%></td>
                <td><a href = "<%out.println(result.getLink());%>"><%out.println(result.getLink());%></a></td>
            </tr>

            <%
            }
            %>

    </table>
    </div>
</body>
</html>