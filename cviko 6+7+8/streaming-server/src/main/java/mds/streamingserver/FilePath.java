package mds.streamingserver;

import java.io.File;

public class FilePath {

    private final static String BASE_PATH = "D:\\MDS\\FILES\\";

    public final static File MP4_FILE = new File(BASE_PATH + "videos\\bbb_1080p.mp4");
    public final static String HLS_PATH = BASE_PATH + "streams\\HLS\\";
    public final static String DASH_PATH = BASE_PATH + "streams\\MPEG-DASH\\";

    public final static String MP4_DIRECTORY = BASE_PATH + "videos\\";
    public final static String IMAGES_DIRECTORY = BASE_PATH + "videos\\images\\";
    public final static String SUFFIX = "mp4";

}
