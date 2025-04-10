import org.apache.catalina.startup.Tomcat;


import java.io.File;
import java.util.logging.Logger;

public class WebServerLauncher {
    private static final Logger logger = Logger.getLogger(WebServerLauncher.class.getName());

    public static void main(String[] args) throws Exception {
        //왜 여기에 WEB-INF를 추가하면 안 될까?
        //WEB-INF는 톰캣이 직접 서빙할 수 없는 폴더.
        //Tomcat은 WEB-INF 아래의 파일을 직접 노출하거나 실행하지 못하게 막아놨다.
        //WEB-INF를 루트 경로로 잡으면 JSP 실행도 안 되고, 서블릿도 못찾고, 404/500 오류가 뜬다.
        String webappDirLocation = "./webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        logger.info("configuring app with basedir: " + new File(webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
