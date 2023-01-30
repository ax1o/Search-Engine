package pack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        //get parameter called "parameter: from request
        String keyword = request.getParameter("keyword");

        System.out.println(keyword);
        try{
            System.out.println(keyword);
            //establish connection with DB
            Connection connection = DatabaseConnection.getConnection();

            //save query and link associated into history table
            PreparedStatement preparedStatement = connection.prepareStatement("insert into history values(?,?)");
            preparedStatement.setString(1,keyword);
            preparedStatement.setString(2,"http://localhost:8080/SearchEngineJava/Search?keyword="+keyword);
            preparedStatement.executeUpdate();

            //executing a query related to keyword and get the results
            ResultSet resultSet = connection.createStatement().executeQuery("select pagetitle,pagelink,(length(pagetext)-length(replace(lower(pagetext),'"+keyword+"','')))/length('"+keyword+"') as countoccurence from pages order by countoccurence desc limit 30;");
            System.out.println(keyword);
            //System.out.println(resultSet);
            //iterate through resultSet and save all elements in results arrayList
            ArrayList<SearchResult> results = new ArrayList<>();
            while(resultSet.next()){

                SearchResult searchResult = new SearchResult();
                //get pageTitle
                searchResult.setPageTitle(resultSet.getString("pageTitle"));
                //System.out.println(resultSet.getString("pageTitle"));
                //get pageLink
                searchResult.setPageLink(resultSet.getString("pageLink"));
                results.add(searchResult);

            }
            //printing result in console
//            for(SearchResult searchResult : results){
//                System.out.println(searchResult.getPageTitle() + " " + searchResult.getPageLink()+"\n");
//            }

            //set attribute of request with results arraylist
            request.setAttribute("results",results);
            //forward request to the frontend
            request.getRequestDispatcher("/search.jsp").forward(request,response);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

        }catch (SQLException | ServletException | IOException sqlException){

            sqlException.printStackTrace();
        }



    }

}
