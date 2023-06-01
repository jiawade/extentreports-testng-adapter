
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
#####1. create testng.xml file
#####2.add following plugin:
````
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.19.1</version>
    <configuration>
        <testFailureIgnore>true</testFailureIgnore>
        <argLine>
            -Xmx1024m -XX:MaxPermSize=256m
        </argLine>
        <forkCount>1</forkCount>
        <systemPropertyVariables>
            <env>true</env>
        </systemPropertyVariables>
        <suiteXmlFiles>
            <suiteXmlFile>testng.xml</suiteXmlFile>
        </suiteXmlFiles>
        <argLine>-Dfile.encoding=UTF-8</argLine>
    </configuration>
</plugin>
````
and then: mvn clean test

or
````java
import io.github.adapter.TestngExtentReportListener;
import org.testng.annotations.Test;


public class Test extends TestngExtentReportListener {

    @Test
    public void aa(){
        System.out.println("test");
    }

}
````

## Submitting Issues
For any issues or requests, please submit [here](https://github.com/jiawade/extentreports-testng-adapter/issues)
