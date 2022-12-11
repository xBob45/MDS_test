package mds.streamingserver.component;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import static mds.streamingserver.FilePaths.IMAGES_DIRECTORY;

// Třída, která nám dokáže předávat obrázky. Využívá se zde WebServlet
// Potřeba doplnit v main třídě anotaci @ServletComponentScan
// Vytváří kopletní HTTP hlavičku, včetně potřebných atributů (Content-Type, Lenght, atd)
@WebServlet("/images/*")
public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
        File file = new File(IMAGES_DIRECTORY, filename);
        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
    }
}