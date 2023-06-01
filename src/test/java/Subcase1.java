import io.github.adapter.TestngExtentReportListener;
import org.testng.annotations.Test;


public class Subcase1 extends TestngExtentReportListener {

    @Test
    public void aa(){
        System.out.println(123);
    }

}
