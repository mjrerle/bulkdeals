package p2.serf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import p2.util.Glogger;


public class RequestHelper {
  public static Logger logger = Glogger.logger;

  public static void Process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String uri = request.getRequestURI();
    logger.info(uri);
    switch (uri) {

      // define routes here
    }
  }
}