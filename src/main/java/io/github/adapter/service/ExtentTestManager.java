package io.github.adapter.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestManager {

    private static Map<String, ExtentTest> classTestMap = new HashMap<>();
    private static ThreadLocal<ExtentTest> methodTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> dataProviderTest = new ThreadLocal<>();

    public static synchronized ExtentTest getTest() {
        return dataProviderTest.get() == null
                ? methodTest.get()
                : dataProviderTest.get();
    }

    public static synchronized ExtentTest getTest(ITestResult result) {
        return result.getParameters() != null && result.getParameters().length > 0
                ? dataProviderTest.get()
                : methodTest.get();
    }

    public static synchronized ExtentTest createMethod(ITestResult result, Boolean createAsChild) {
        if (!createAsChild)
            return createMethod(result);
        String className = result.getInstance().getClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        String desc = result.getMethod().getDescription();
        ExtentTest classTest;
        if (classTestMap.containsKey(className)) {
            classTest = classTestMap.get(className);
        } else {
            classTest = ExtentService.getInstance().createTest(className, desc);
            classTestMap.put(className, classTest);
        }
        if (result.getParameters().length > 0) {
            boolean anyMatch = classTest.getModel().getChildren()
                    .stream()
                    .anyMatch(x -> x.getName().equals(methodName));
            if (!anyMatch)
                createTest(result, classTest);
            String paramName = Arrays.asList(result.getParameters()).toString();
            ExtentTest paramTest = methodTest.get().createNode(paramName);
            dataProviderTest.set(paramTest);
        } else {
            dataProviderTest.set(null);
            createTest(result, classTest);
        }
        return methodTest.get();
    }

    public static synchronized ExtentTest createMethod(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        if (result.getParameters().length > 0) {
            if (methodTest.get() != null && methodTest.get().getModel().getName().equals(methodName)) {
            } else {
                createTest(result, null);
            }
            String paramName = Arrays.asList(result.getParameters()).toString();
            ExtentTest paramTest = methodTest.get().createNode(paramName);
            dataProviderTest.set(paramTest);
        } else {
            dataProviderTest.set(null);
            createTest(result, null);
        }
        return methodTest.get();
    }

    private static synchronized ExtentTest createTest(ITestResult result, ExtentTest classTest) {
        String methodName = result.getMethod().getMethodName();
        String desc = result.getMethod().getDescription();
        ExtentTest test;
        if (classTest != null)
            test = classTest.createNode(methodName, desc);
        else
            test = ExtentService.getInstance().createTest(methodName, desc);
        methodTest.set(test);
        String[] groups = result.getMethod().getGroups();
        ExtentTestCommons.assignGroups(test, groups);
        return test;
    }

    public static synchronized void log(ITestResult result, Boolean createTestAsChild) {
        String msg = "Test ";
        Status status = Status.PASS;
        switch (result.getStatus()) {
            case ITestResult.SKIP:
                status = Status.SKIP;
                msg += "skipped";
                break;
            case ITestResult.FAILURE:
                status = Status.FAIL;
                msg += "failed";
                break;
            default:
                msg += "passed";
                break;
        }
        if (ExtentTestManager.getTest(result) == null)
            ExtentTestManager.createMethod(result, createTestAsChild);
        if (result.getThrowable() != null) {
            ExtentTestManager.getTest(result).log(status, result.getThrowable());
            return;
        }
        ExtentTestManager.getTest(result).log(status, msg);
    }

    public static synchronized void log(ITestResult result) {
        log(result, false);
    }

}
