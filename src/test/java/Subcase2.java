import io.github.adapter.TestngExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Subcase2 extends TestngExtentReportListener {


    @Test
    public void cc(){

    }



    @Test
    public void dd(){
        Assert.fail("vvvvvv");
    }


}
