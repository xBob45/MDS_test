package mds.project;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Component
public class ProjectResourceComponent extends ResourceHttpRequestHandler {

    public final static String ATTR_FILE = ProjectResourceComponent.class.getName() + ".file";

    @Override
    protected Resource getResource(HttpServletRequest request) throws IOException {

        // vytvoření finální verze objektu typu file, který obsahuje objekt s videem
        final File file = (File) request.getAttribute(ATTR_FILE);
        // vrácení požadovaného souboru v objektu file
        // objekt file je v konstruktoru třídy FileSystemResource, který je objektem z frameworku Spring
        // třída resp. objekt FileSystemResource se stará o předání souboru serveru Spring
        return new FileSystemResource(file);
    }

}

