package servlets;

import java.io.*;
import java.net.URL;
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
                if (request.getParameter("edit") == null) {
                    request.setAttribute("code1", "edit param is null");
                    contents += ln + "<br>";
                }
                else {
                    contents += ln;
                }
            }
        }
        
        if (contents.contains("<br><br>")) {
            contents = contents.replaceAll("<br><br>", "<br>");
        }
        
        
        Note note = new Note(title, contents);
        request.setAttribute("note", note);
        br.close();
        
        if (request.getParameter("edit") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/viewNote.jsp")
                .forward(request, response);
        }
        else {
            contents = contents.replaceAll("<br>", "&#13;&#10;");
            note.setContents(contents);
            getServletContext().getRequestDispatcher("/WEB-INF/editNote.jsp")
                .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String contents = request.getParameter("editContents");
        String title = request.getParameter("editTitle");
        StringBuffer text = new StringBuffer(contents);
        int i = (new String(text).indexOf("\n"));
        
        if (!contents.contains("<br>") || i == -1) {
            while (i > 0) {
                text.replace(i, i + 1, "<br>");
                i = (new String(text).indexOf("\n"));
            }
            contents = text.toString();
        }
        
        Note note = new Note(title, contents);
        request.setAttribute("note", note);
        
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
        
        pw.write(note.getTitle() + "\n");
        pw.write(note.getContents());
        
        pw.close();
        
        getServletContext().getRequestDispatcher("/WEB-INF/viewNote.jsp")
                .forward(request, response);
    }

}