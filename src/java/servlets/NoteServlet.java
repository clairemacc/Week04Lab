package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

/**
 *
 * @author 822408
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        
        String title = "";
        String contents = "";
        String ln;
        
        while ((ln = br.readLine()) != null) {
            title = ln;
            while ((ln = br.readLine()) != null){
                contents += ln + "<br>";
            }
        } 
        
        Note note = new Note(title, contents);
        request.setAttribute("note", note);
        br.close();
        
        if (request.getParameter("edit") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/viewNote.jsp")
                .forward(request, response);
        }
        else {
            String newContents = contents.replaceAll("<br>", "&#13;&#10;");
            note.setContents(newContents);
            
            getServletContext().getRequestDispatcher("/WEB-INF/editNote.jsp")
                .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/viewNote.jsp")
            .forward(request, response);
    }

}
