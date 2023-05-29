
# extentreports-testng-adapter #

extentreports-testng-adapter is an adapter which is extent report generator 

## Installation

#### Maven
````xml
<dependency>
    <groupId>io.github.jiawade</groupId>
    <artifactId>extentreports-testng-adapter</artifactId>
    <version>0.0.1</version>
</dependency>
````

#### Gradle
````gradle
compile 'io.github.jiawade:extentreports-testng-adapter:0.0.1'
````

#### Version mapping
* for extent-report4<===>0.0.1
* for extent-report5<===>0.0.2


#### Available properties configuration
extent.reporter.spark.start=true
extent.reporter.spark.out=target/report/extent/index.html
extent.reporter.spark.config=src/test/resources/spark-config.xml
extent.reporter.spark.offlineMode=true
extent.reporter.spark.viewConfigurer.viewOrder=dashboard,test,exception,category,device,author,exception


## Usage Example
add following plugin:
````
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.2</version>
    <configuration>
        <includes>
            <include>**/*.java</include>
        </includes>
    </configuration>
</plugin>
<plugin>
    <artifactId>maven-failsafe-plugin</artifactId>
    <version>2.22.2</version>
</plugin>
````
and then: mvn clean test

or
````java
import io.github.adapter.TestngExtentReportListener;
import org.testng.annotations.Test;


public class Subcase1 implements TestngExtentReportListener {

    @Test
    public void aa(){
        System.out.println("start");
    }

}
````

## Submitting Issues
For any issues or requests, please submit [here](https://github.com/jiawade/extentreports-testng-adapter/issues)
