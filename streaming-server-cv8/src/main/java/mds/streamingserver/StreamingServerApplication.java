package mds.streamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //Nutno přidat anotaci pro fungování FileServletu
public class StreamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingServerApplication.class, args);
	}

}
