import com.dell.service.core.AppCore;
import com.dell.service.dao.AppDAO;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class ServiceTest {
    private AppDAO.Builder appDAOBuilder;
    private String inputFile = "input.json";
    private AppCore appCore;

    @Before
    public void setUp(){
        this.appDAOBuilder = mock(AppDAO.Builder.class);
        appCore = new AppCore(inputFile, appDAOBuilder);
    }

    @Test
    public void checkIfDataSourceExists(){
        String path = new File("service").getAbsolutePath();
        String parentDirectory = new File(path).getParentFile().getName();
        assertEquals(parentDirectory, "service");
    }

    @Test
    public void testServiceBuilder(){
        List<AppDAO> list = appCore.bufferedReader().serviceBuilder();
        assertNotNull(list);

        final String[] service = new String[1];
        list.forEach(appDAO -> service[0] = appDAO.getService());
        assertEquals(service[0], "Hospital");
    }
}
