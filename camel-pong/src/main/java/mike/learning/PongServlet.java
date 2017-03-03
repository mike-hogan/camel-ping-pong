package mike.learning;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PongServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("ponging...");
        CamelContext context = new DefaultCamelContext();
        System.out.println("pong context.getClass().getClassLoader() = " + context.getClass().getClassLoader());
        try {
//            context.addRoutes(new RouteBuilder() {
//                @Override
//                public void configure() throws Exception {
//                    from("direct:ping").to("direct:pong");
//                }
//            });

            ConsumerTemplate consumer = context.createConsumerTemplate();

            context.start();
            while(true) {
                final String body = consumer.receiveBody("vm:pong", String.class);
                System.out.println("body = " + body);
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
