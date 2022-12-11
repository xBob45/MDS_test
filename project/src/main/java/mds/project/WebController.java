package mds.project;

import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;
import mds.streamingserver.model.VideoLibrary;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import static mds.project.FilePaths.*;

@Controller
public class WebController {



    private ProjectResourceComponent handler;


    @Autowired
    public WebController(ProjectResourceComponent handler) {
        this.handler = handler;
    }


    @GetMapping("/index")
    public String index(){return "index";}

    @RequestMapping(value = "/video", method = {RequestMethod.POST})
    public String video(@RequestParam (defaultValue = VIDEO_FROM_URL) String url,
                         @RequestParam(defaultValue = "600") String height, //Šířka jako string umožňí zapsat i %, takže můžu definovat velikost v procentech
                         Model model) {

        if (!StringUtils.isEmpty(url)) {
            model.addAttribute("url", url);
            model.addAttribute("height", height);}

        return "video";}

    @RequestMapping(value = {"/dash/{stream}/{file}"}, method = RequestMethod.GET)
    public void streaming(
            @PathVariable("file") String file,
            @PathVariable(value = "stream") String stream,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

            File STREAM_FILE = null;
            STREAM_FILE  = new File(DASH_DIRECTORY + stream + "\\" + file);
            request.setAttribute(ProjectResourceComponent.ATTR_FILE, DASH_DIRECTORY);
            handler.handleRequest(request, response);}

    @RequestMapping(value = {"/player/{stream}"}, method = RequestMethod.GET)
    public ModelAndView player(@PathVariable("stream") String stream) throws IOException {
        Map<String, Object> model = new HashMap<>();
        model.put("videoName", stream);
        return new ModelAndView("player", model);
    }


    private  VideoLibrary library = null;

    @GetMapping("/videocollection")
    public String videoCollection(Model model) throws IOException, JCodecException {
        if (library == null) {
            library = new VideoLibrary(IMAGES_DIRECTORY, DASH_DIRECTORY, SUFFIX, 150);
        }
        model.addAttribute("moviesItems", library);
        return "videocollection";
    }



}//Konec tridy


