package mds.streamingserver;

import java.io.File;

public class FilePaths {

    //Základ cesty, umožní jednoduchou změnu například při odevzdávání
    private final static String BASE_PATH = "D:\\MDS\\files\\";

    // Deklarace objektu typu File s názvem MP4_FILE s cestou k souboru videa
    public final static File MP4_FILE = new File(BASE_PATH + "videos\\bbb_1080p.mp4");

    // Deklarace proměnné String s cestou k souborům streamu
    public final static String HLS_PATH = BASE_PATH + "streams\\HLS\\";
    public final static String DASH_PATH = BASE_PATH + "streams\\MPEG-DASH\\";

    //Proměnné pro využití v galerii
    public final static String MP4_DIRECTORY = BASE_PATH + "videos\\";
    public final static String IMAGES_DIRECTORY = BASE_PATH + "videos\\images\\";
    public final static String SUFFIX = "mp4";

}
