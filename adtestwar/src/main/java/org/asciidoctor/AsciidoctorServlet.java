package org.asciidoctor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Test")
public class AsciidoctorServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            System.out.println(Thread.currentThread().getContextClassLoader());
            Asciidoctor asciidoctor = Asciidoctor.Factory.create();
            
            String rendered = asciidoctor.convert("= Hello, World\n\nYes really, hello, World!", OptionsBuilder.options().headerFooter(true).safe(SafeMode.UNSAFE));
            
            resp.getOutputStream().print(rendered);
        } catch (Exception e) {
            resp.getOutputStream().println("<html><body><pre>");
            PrintWriter pw = new PrintWriter(resp.getOutputStream());
            e.printStackTrace(pw);
            pw.close();
            resp.getOutputStream().println("</pre></body></html>");
        }
    }
    
}
