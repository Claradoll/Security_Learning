package Learn;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class Context_Learn {
    public static void main(String[] args) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        System.out.println(context);
    }
}
