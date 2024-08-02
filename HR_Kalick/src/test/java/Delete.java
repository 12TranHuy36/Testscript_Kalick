import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class Delete {
    // Khai báo đối tượng WebDriver để điều khiển trình duyệt
    WebDriver guru;
    // Khai báo đối tượng WebDriverWait để chờ đợi các phần tử trên trang
    WebDriverWait guruWait;
    // Thiết lập phương thức setup() sẽ được thực thi trước mỗi phương thức test
    @BeforeMethod
    public void setup(){
        // Cài đặt WebDriver cho Chrome
        WebDriverManager.chromedriver().setup();

        // Mở rộng cửa sổ trình duyệt
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // Khởi tạo đối tượng ChromeDriver với các tùy chọn đã thiết lập
        guru = new ChromeDriver( options );

        // Mở trang đăng nhập của hệ thống
        guru.get("https://dev-admin.kelick.io/session/signin");
        // Khởi tạo WebDriverWait với thời gian chờ là 10 giây
        guruWait =  new WebDriverWait(guru, Duration.ofSeconds(10));

        //Chờ và click vào radio button
        WebElement radio = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@class='form-check-input'])[2]")));
        radio.click();

        //Chờ và nhập email
        WebElement email = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='email']")));
        email.sendKeys("dev@gmail.com");

        //Chờ và nhập mật khẩu
        WebElement pass = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='password']")));
        pass.sendKeys("123456");

        //Chờ và click vào nút submit
        WebElement submit = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        submit.submit();

        //Dừng chương trình trong 5 giây để chờ trang tải xong sau khi đăng nhập.
        sleep(5000);

        // Bấm di chuyển đền chức năng Employee
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
    }

    @Test(description = "Verify that display Confirm pop-up when user click trash icon at column Action of record ")
    public void runTestscript1() {
        Random random = new Random();
        // Tạo số ngẫu nhiên từ 1 đến 10
        int randomNumber = random.nextInt(11);
        sleep(5000);
        // 1. Xác định và nhấp vào biểu tượng thùng rác trong cột "Action" của bản ghi
        WebElement trashIcon = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%s]",randomNumber))));
        ((JavascriptExecutor) guru).executeScript("arguments[0].click();", trashIcon);

        // 2. Xác minh xem pop-up xác nhận có xuất hiện không
        boolean isConfirmPopupDisplayed;
        try {
            WebElement confirmPopup = guruWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-labelledby='swal2-title']")));
            isConfirmPopupDisplayed = confirmPopup.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            isConfirmPopupDisplayed = false;
        }

        try {
            Assert.assertTrue(isConfirmPopupDisplayed, "Confirm pop-up is not displayed");
            System.out.println("Testcase pass");
        } catch (AssertionError e) {
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Verify that the logo is displayed correctly")
    public void runTestscript2() {
        Random random = new Random();
        // Tạo số ngẫu nhiên từ 1 đến 10
        int randomNumber = random.nextInt(11);
        // 1. Xác định và nhấp vào biểu tượng thùng rác trong cột "Action" của bản ghi
        WebElement trashIcon = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%s]",randomNumber))));
        // Cuộn đến phần tử trước khi click
        ((JavascriptExecutor) guru).executeScript("arguments[0].scrollIntoView(true);", trashIcon);
        sleep(500);
        // Sử dụng JavascriptExecutor để click phần tử
        ((JavascriptExecutor) guru).executeScript("arguments[0].click();", trashIcon);

        // 2. Xác định logo trên pop-up
        WebElement logo = trashIcon.findElement(By.xpath("(//img[@alt='delete-employee-icon'])[1]"));

        // 3. Kiểm tra xem logo có hiển thị không
        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");

        // 4. Kiểm tra xem logo có đúng đường dẫn không
        String logoSrc = logo.getAttribute("src");
        System.out.println(logoSrc);
        Assert.assertTrue(logoSrc.contains("/assets/images/icons/delete.svg"), "Logo is not as expected");

        // Nếu không có lỗi nào, in ra kết quả pass
        System.out.println("Testcase pass");
    }
    @Test(description = "Verify that the title is displayed correctly")
    public void runTestscript3() {
        Random random = new Random();

        // 2. Xác định số lượng biểu tượng thùng rác trên trang
        List<WebElement> trashIcons = guruWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@alt='delete-employee-icon']")));

        // Tạo số ngẫu nhiên từ 1 đến số lượng biểu tượng thùng rác có sẵn
        int randomNumber = random.nextInt(trashIcons.size()) + 1;
        // Xác định lấy tên nhân viên để kiểm tra
        String name = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%d]/ancestor::tr/td[3]", randomNumber)))).getText();

        // 3. Xác định và nhấp vào biểu tượng thùng rác trong cột "Action" của bản ghi
        WebElement trashIcon = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%d]", randomNumber))));
        // Cuộn đến phần tử trước khi click
        ((JavascriptExecutor) guru).executeScript("arguments[0].scrollIntoView(true);", trashIcon);
        sleep(500);
        // Sử dụng JavascriptExecutor để click phần tử
        ((JavascriptExecutor) guru).executeScript("arguments[0].click();", trashIcon);

        // 4. Xác định tiêu đề
        String title = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='text-16 fw-normal mb-2']"))).getText();

        // 5. Kiểm tra kết quả
        try {
            String a = String.format("Deleting Employee - %s\nAre you sure you want to proceed?", name);
            Assert.assertEquals(a,title, "Displaying the wrong name of the person who deleted it");
            System.out.println("Testcase pass");
        }catch (AssertionError e){
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Verify that the button Cancel is displayed correctly")
    public void runTestscript4() {
        Random random = new Random();

        // 1. Xác định số lượng biểu tượng thùng rác trên trang
        List<WebElement> trashIcons = guruWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@alt='delete-employee-icon']")));

        // Tạo số ngẫu nhiên từ 1 đến số lượng biểu tượng thùng rác có sẵn
        int randomNumber = random.nextInt(trashIcons.size()) + 1;
        // Xác định lấy tên nhân viên để kiểm tra
        String name = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%d]/ancestor::tr/td[3]", randomNumber)))).getText();

        // 2. Xác định và nhấp vào biểu tượng thùng rác trong cột "Action" của bản ghi
        WebElement trashIcon = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%d]", randomNumber))));
        // Cuộn đến phần tử trước khi click
        ((JavascriptExecutor) guru).executeScript("arguments[0].scrollIntoView(true);", trashIcon);
        sleep(500);
        // Sử dụng JavascriptExecutor để click phần tử
        ((JavascriptExecutor) guru).executeScript("arguments[0].click();", trashIcon);

        // 3. Xác định text nút cancel
        String cancel = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='swal2-cancel swal2-styled']"))).getText();

        // 4. Kiểm tra kết quả
        try {
            Assert.assertEquals(cancel,"Cancel", "Wrong display");
            System.out.println("Testcase pass");
        }catch (AssertionError e){
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Verify that the button Delete is displayed correctly")
    public void runTestscript5() {
        Random random = new Random();

        // 1. Xác định số lượng biểu tượng thùng rác trên trang
        List<WebElement> trashIcons = guruWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@alt='delete-employee-icon']")));

        // Tạo số ngẫu nhiên từ 1 đến số lượng biểu tượng thùng rác có sẵn
        int randomNumber = random.nextInt(trashIcons.size()) + 1;
        // Xác định lấy tên nhân viên để kiểm tra
        String name = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%d]/ancestor::tr/td[3]", randomNumber)))).getText();

        // 2. Xác định và nhấp vào biểu tượng thùng rác trong cột "Action" của bản ghi
        WebElement trashIcon = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%d]", randomNumber))));
        // Cuộn đến phần tử trước khi click
        ((JavascriptExecutor) guru).executeScript("arguments[0].scrollIntoView(true);", trashIcon);
        sleep(500);
        // Sử dụng JavascriptExecutor để click phần tử
        ((JavascriptExecutor) guru).executeScript("arguments[0].click();", trashIcon);

        // 3. Xác định text nút delete
        String delete = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='swal2-confirm swal2-styled']"))).getText();

        // 4. Kiểm tra kết quả
        try {
            Assert.assertEquals(delete,"Delete", "Wrong display");
            System.out.println("Testcase pass");
        }catch (AssertionError e){
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Verify that an employee don't delete when user click Cancel button")
    public void runTestscript6() {
        Random random = new Random();

        // 1. Xác định số lượng biểu tượng thùng rác trên trang
        List<WebElement> trashIcons = guruWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@alt='delete-employee-icon']")));

        // Tạo số ngẫu nhiên từ 1 đến số lượng biểu tượng thùng rác có sẵn
        int randomNumber = random.nextInt(trashIcons.size()) + 1;

        // 2. Xác định và nhấp vào biểu tượng thùng rác trong cột "Action" của bản ghi
        WebElement trashIcon = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%d]", randomNumber))));
        // Cuộn đến phần tử trước khi click
        ((JavascriptExecutor) guru).executeScript("arguments[0].scrollIntoView(true);", trashIcon);
        sleep(500);
        // Sử dụng JavascriptExecutor để click phần tử
        ((JavascriptExecutor) guru).executeScript("arguments[0].click();", trashIcon);

        // 3. CLick vào nút cancel
        WebElement cancel = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='swal2-cancel swal2-styled']")));
        cancel.click();
        sleep(5000);
        // 4. Kiểm tra pop-up có đóng không
        boolean isConfirmPopupDisplayed;
        try {
            WebElement confirmPopup = guruWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-labelledby='swal2-title']")));
            isConfirmPopupDisplayed = confirmPopup.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            isConfirmPopupDisplayed = false;
        }
        //5. Kiểm tra xem bản ghi vẫn còn hiển thị chưa có bị mất
        try {
            Assert.assertFalse(isConfirmPopupDisplayed, "Confirm pop-up is still displayed");
            boolean test = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%d]/ancestor::tr/td[3]", randomNumber)))).isDisplayed();
            Assert.assertTrue(test, "Don't displayed");
            System.out.println("Testcase pass");
        }catch (AssertionError e){
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Verify that an employee is deleted when user click Delete button")
    public void runTestscript7() {
        Random random = new Random();

        // 1. Xác định số lượng biểu tượng thùng rác trên trang
        List<WebElement> trashIcons = guruWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@alt='delete-employee-icon']")));

        // Tạo số ngẫu nhiên từ 1 đến số lượng biểu tượng thùng rác có sẵn
        int randomNumber = random.nextInt(trashIcons.size()) + 1;

        // 2. Xác định và nhấp vào biểu tượng thùng rác trong cột "Action" của bản ghi
        WebElement trashIcon = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%d]", randomNumber))));
        // Cuộn đến phần tử trước khi click
        ((JavascriptExecutor) guru).executeScript("arguments[0].scrollIntoView(true);", trashIcon);
        sleep(500);
        // Sử dụng JavascriptExecutor để click phần tử
        ((JavascriptExecutor) guru).executeScript("arguments[0].click();", trashIcon);

        // 3. CLick vào nút delete
        WebElement delete = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='swal2-confirm swal2-styled']")));
        delete.click();
        sleep(5000);
        // 4. Kiểm tra pop-up có đóng không
        boolean isConfirmPopupDisplayed;
        try {
            WebElement confirmPopup = guruWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-labelledby='swal2-title']")));
            isConfirmPopupDisplayed = confirmPopup.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            isConfirmPopupDisplayed = false;
        }
        //5. Kiểm tra xem pop-up đã mất chưa
        try {
            Assert.assertFalse(isConfirmPopupDisplayed, "Confirm pop-up is still displayed");
            boolean test = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='delete-employee-icon'])[%d]/ancestor::tr/td[3]", randomNumber)))).isDisplayed();
            Assert.assertFalse(test, "Still displayed");
            System.out.println("Testcase pass");
        }catch (AssertionError e){
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void cleanup(){
        guru.quit();
    }
    private void sleep(int time){
        try{
            Thread.sleep(time);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}