package mike.learning;

import org.junit.Test;

public class AppTest {

    @Test
    public void camelInJunit() {
        try {
            App.main(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
