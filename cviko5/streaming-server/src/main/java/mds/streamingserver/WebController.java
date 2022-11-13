package mds.streamingserver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import mds.streamingserver.component.MyResourceHttpRequestHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;

@Controller
public class WebController {

    //Komponent potřebný pro zasílání částí souboru videa
    private MyResourceHttpRequestHandler handler;

    //Autowired vytváří závislost na objektu handler, který je využit v metodě byterange
    @Autowired
    public WebController(MyResourceHttpRequestHandler handler){this.handler = handler;}

    //Deklarace objektu 'File' s cestou k souboru videa
    private final File MP4_FILE = new File("D:\\MDS\\videos\\bbb_1080p.mp4");

    @GetMapping("video")
    public String video(){
        return "videoMP4stream";}

    //Komponentami 'MyResourceHttpRequestHandler', 'WebController' 'file' a 'video'
    //nějakým způsobem 'definujeme' to video v rámci stránky
    //metodou 'byterange' definujeme, že se soubor bude stahova a předáva do 'videoMP4stream' po kouskách
    //HttpServetRequest/Response umožňuje byte-range(po kouskách) dotazy/odpovědi
    public void byterange(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, MP4_FILE);
        handler.handleRequest(request,response);}




    //Metoda, která vrací celý soubor videa najednou
    //@GetMapping obsahuje, kromě cesty také typ souboru, který je poskytován
    @GetMapping(path = "/file", produces = "video/mp4")
    @ResponseBody
    public FileSystemResource wholeFile(){
        return new FileSystemResource(MP4_FILE);}

    //-------------------------------------------UKOL-STREAM--------------------------

    //Index stránka s formulářem
    @GetMapping("/index")
    public String index() {
        return "index";
    }


    //Formulář - URL videa, výběr autoplay, muted . . .
    @RequestMapping(value = "player", method = {RequestMethod.POST, RequestMethod.GET})
    public String player(@RequestParam String url, @RequestParam(defaultValue = "false") boolean muted, @RequestParam(defaultValue = "false") boolean autoplay, @RequestParam(defaultValue = "1000") String width, Model model){

        if(!StringUtils.isEmpty(url)){
            model.addAttribute("url", url);
            model.addAttribute("width", width);
            model.addAttribute("muted", muted ? "true" : "");
            model.addAttribute("autoplay", autoplay);}

        else{
            model.addAttribute("error", "No URL has been provided");}

        return "player";

    }

}


