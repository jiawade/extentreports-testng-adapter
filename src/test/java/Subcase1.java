import io.github.adapter.TestngExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Subcase1 implements TestngExtentReportListener {

    @Test
    public void aa(){
        System.out.println(123);
    }

    @Test
    public void bb(){
        Assert.fail("aaaa...");
    }

}