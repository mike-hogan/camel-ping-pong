package mike.learning;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class PingServlet extends HttpServlet{


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("pinging....");
        CamelContext context = new DefaultCamelContext();

        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("direct:ping").to("vm:pong");
                }
            });
            ProducerTemplate template = context.createProducerTemplate();
            context.start();
            while(true) {
                template.sendBody("direct:ping", new Date().toString());
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                context.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
