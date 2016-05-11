import fi.iki.elonen.NanoHTTPD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Describe this class and the methods exposed by it.
 */
public class Server extends NanoHTTPD {

    private String mContentRoot;

    public Server(Integer port, String contentRoot) throws IOException {
        super(port);
        mContentRoot = contentRoot;
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("Well and alive at http://localhost:" + port);
    }

    private String getMimeType(String path){
        if (path.endsWith(".html")){
            return "text/html";
        } else if (path.endsWith(".js")){
            return "application/javascript";
        } else if (path.endsWith(".mp3")){
            return "audio/mpeg";
        } else {
            return "text/plain";
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        if (uri.equals("/") || uri.equals("/game")){
            uri = "index.html";
        }
        String path = mContentRoot + uri;
        System.out.print("Attempted access to " + path + ": ");
        try {
            FileInputStream stream = new FileInputStream(path);
            System.out.println("SUCCESS");
            return newChunkedResponse(Response.Status.OK, getMimeType(path), stream);
        } catch (FileNotFoundException e){
            System.out.println("NOT FOUND");
            return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "404.");
        }
    }
}
