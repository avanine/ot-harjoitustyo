package dao;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.scene.paint.Color;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import pong.dao.ThemeColorDao;

public class ThemeColorDaoTest {

    ThemeColorDao dao;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        dao = new ThemeColorDao(properties.getProperty("colorTest"));      
    }

    @Test
    public void changeColorWorks() {
        dao.changeColor("blue", 0);
        assertTrue(dao.getColor() == Color.BLUE);
        assertTrue(dao.getMarkerLayout() == 0);
        
        dao.changeColor("white", 20);
        assertTrue(dao.getColor() == Color.WHITE);
        assertTrue(dao.getMarkerLayout() == 20);
        
        dao.changeColor("green", 100);
        assertTrue(dao.getColor() == Color.YELLOWGREEN);
        assertTrue(dao.getMarkerLayout() == 100);
        
        dao.changeColor("yellow", 40);
        assertTrue(dao.getColor() == Color.YELLOW);
        assertTrue(dao.getMarkerLayout() == 40);
    }

}
