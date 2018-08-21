package com.example;

//import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.NoSuchElementException;

//java-client1.2.1�汾��appiumDriver 2.2.0��AndroidDriver�����ϰ汾�����
public class WeiXinApp {
	
    public static void main(String[] args) throws MalformedURLException, InterruptedException{
       
    	AndroidDriver driver = init();
    	WebDriverWait wait = new WebDriverWait(driver, 300);
    	login("13691467901","sunjingtao1314",wait);
    	enter(wait);
    	crawl(wait,driver);
    }
    /**
     * ��ʼ��
     * @return
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    private static  AndroidDriver init() throws MalformedURLException, InterruptedException{
    	//�������ó�ʼ��
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //�豸����
        capabilities.setCapability("deviceName", "Coolpad8675-0x03a2b1f1");
//        capabilities.setCapability("deviceName", "emulator-5554");
//        capabilities.setCapability("deviceName", "127.0.0.1:62001");
        capabilities.setCapability("automationName", "Appium");
        //ƽ̨
        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("platformVersion", "6.0");
        //app����
        capabilities.setCapability("appPackage", "com.tencent.mm");
        //�������
        capabilities.setCapability("appActivity", ".ui.LauncherUI");
      //Appium��ַ
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		return driver;
    }
    /**
     * ��¼΢��
     * @param username
     * @param password
     */
    private static void login(String username,String password,WebDriverWait wait){
    	
    	// ��¼��ť
    	WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.tencent.mm:id/d75")));
        login.click();
        // �ֻ�����
        WebElement phone = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.tencent.mm:id/hz")));
        phone.sendKeys(username);
        // ��һ��
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.tencent.mm:id/alr")));
        next.click();
        // ����
        WebElement password1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@resource-id='com.tencent.mm:id/hz'][1]")));
        password1.sendKeys(password);
        // �ύ
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.tencent.mm:id/alr")));
        submit.click();
    }
    /**
     * ��������Ȧ
     */
    private static void enter(WebDriverWait wait){
    	// ѡ� ����
    	WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@resource-id='com.tencent.mm:id/b0w'][3]")));
        tab.click();
        // ����Ȧ
        WebElement moments = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.tencent.mm:id/aab")));
        moments.click();
    }
    /**
     * ��ȡ
     */
    private static void crawl(WebDriverWait wait,AndroidDriver driver){
    	while(true){
            //��ǰҳ����ʾ������״̬
    		List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@resource-id='com.tencent.mm:id/dkb']//android.widget.FrameLayout")));
             // �ϻ�
            driver.swipe(300, 300, 300, 300, 700);
            // ����ÿ��״̬
            for(WebElement item:items){
                try{
                    // �ǳ�
                    String nickname = item.findElement(By.id("com.tencent.mm:id/as6")).getText();
                    // ����
                    String content = item.findElement(By.id("com.tencent.mm:id/ib")).getText();
                    // ����
                    String date = item.findElement(By.id("com.tencent.mm:id/dki")).getText();
                    
                    // ��������
//                    date = self.processor.date(date)
                    System.out.println(nickname+","+content+","+date);
//                    data = {
//                        'nickname': nickname,
//                        'content': content,
//                        'date': date,
//                    }
                    // ����MongoDB
//                    self.collection.update({'nickname': nickname, 'content': content}, {'$set': data}, True)
//                    sleep(SCROLL_SLEEP_TIME)
                }catch(Exception NoSuchElementException){
                	System.out.println("δ�ҵ���Ԫ��");
                }
                finally{
                	driver.quit();
                }
                }
    }
    }
}