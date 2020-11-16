
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ui.WelcomeScene;

public class WelcomeSceneTest {
    
    private volatile boolean success = false;
    
    public WelcomeSceneTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testMain() {
        // test to make sure that the application launches
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Application.launch(WelcomeScene.class);
                    success = true;
                } catch(Throwable t) {
                    if(t.getCause() != null && t.getCause().getClass().equals(InterruptedException.class)) {
                        // this exception is expected after interrupting application
                        success = true;
                        return;
                    }
                    Logger.getLogger(WelcomeSceneTest.class.getName()).log(Level.SEVERE, null, t);
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(3000);  // wait for 3 seconds
        } catch(InterruptedException ex) {
        }
        thread.interrupt();
        try {
            thread.join(1); // wait 1 second
        } catch(InterruptedException ex) {
        }
        assertTrue(success);
    }
}
