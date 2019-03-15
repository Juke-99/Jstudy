import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.logging.Logger;

public class CheckCharTest {
    @Test
    public void chackTest() {
        CheckChar c = new CheckChar();
        Logger LOG = Logger.getLogger("CheckCharTest");
        
        LOG.info("localhost");
        Arrays.stream(c.check("localhost")).forEach(System.out::println);
        //assertThat(c.check("localhost")).
        
        LOG.info("localhost:8080");
        Arrays.stream(c.check("localhost:8080")).forEach(System.out::println);
        
        LOG.info("ds.localhost-test:8080");
        Arrays.stream(c.check("ds.localhost-test:8080")).forEach(System.out::println);
        
        LOG.info("文字列");
        Arrays.stream(c.check("文字列")).forEach(System.out::println);
        
        LOG.info("/");
        Arrays.stream(c.check("/")).forEach(System.out::println);
    }
}
