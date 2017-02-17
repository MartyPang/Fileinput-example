package action;

import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Marty Pang on 2017/2/17.
 */

public class RequestParseWrapper  extends JakartaMultiPartRequest {
    public void parse(HttpServletRequest servletRequest, String saveDir)throws IOException {
    }
}