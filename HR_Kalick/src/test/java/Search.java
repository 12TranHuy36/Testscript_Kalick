import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;


public class Search {
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
        sleep(3000);
    }

    @Test( description = "Verify that move to Employee management screen when user click on function Employees at vertical toolbar on the left")
    public void runTestscript1() {
        // 1. Xác định và nhấp vào  "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(3000);

        // 2. Xác minh xem người dùng có được chuyển đến màn hình "Search" không
        boolean isOnAddSearchScreen;
        try {
            // Kiểm tra xem có phần tử duy nhất trên màn hình "Search" không
            WebElement addEmployeeHeader = guruWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='capitalize text-muted'])[1]")));
            isOnAddSearchScreen = addEmployeeHeader.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            isOnAddSearchScreen = false;
        }

        try {
            Assert.assertTrue(isOnAddSearchScreen, "User is not on the Search screen");
            System.out.println("Testcase pass");
        } catch (AssertionError e) {
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }
    @Test(description = "Verify that the title Employees is displayed correctly")
    public void runTestscript2(){
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(3000);
        // 2. Lấy phần tử ra để so sánh
        String text = guru.findElement(By.xpath(("//div[@class='text-30 fw-bold mb-3']"))).getText();
        try{
            Assert.assertEquals(text,"Employees");
            System.out.println(" Testcase pass");

        }catch (AssertionError e) {
            System.out.println(" Testcase fail" + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Verify that the title Employee Details is displayed correctly")
    public void runTestscript3(){
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(3000);
        // 2. Lấy phần tử ra để so sánh
        String text = guru.findElement(By.xpath(("(//div[@class='row']//div)[1]"))).getText();
        try{
            Assert.assertEquals(text,"Employee Details");
            System.out.println(" Testcase pass");

        }catch (AssertionError e) {
            System.out.println(" Testcase fail" + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Verify that the dropdown list Role title is displayed correctly")
    public void runTestscript4(){
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(3000);
        // 2. Xác định tên label của dropdown list
        String dropdown1 = guru.findElement(By.xpath(("//label[@for='roleTitle']"))).getText();

        try{
            Assert.assertEquals(dropdown1,"Role Title");
            System.out.println(" Testcase pass");

        }catch (AssertionError e) {
            System.out.println(" Testcase fail" + e.getMessage());
            throw e;
        }
    }
    @Test(description = "Verify that the dropdown list Employment Status is displayed correctly")
    public void runTestscript5(){
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(3000);
        // 2. Xác định tên label của dropdown list
        String dropdown2 = guru.findElement(By.xpath(("//label[@for='employmentStatus']"))).getText();

        try{
            Assert.assertEquals(dropdown2,"Employment Status");
            System.out.println(" Testcase pass");

        }catch (AssertionError e) {
            System.out.println(" Testcase fail" + e.getMessage());
            throw e;
        }
    }
    @Test(description = "Verify that values list is displayed when user click on Role title dropdown list")
    public void runTestscript6(){
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(3000);
        // 2. Xác minh hiển thị của dropdown list
        boolean dropdown1 = guru.findElement(By.xpath(("//label[@for='roleTitle']"))).isDisplayed();
        if (dropdown1){
            // Click vào dropdown list
            WebElement c = guru.findElement(By.id("roleTitle"));
            c.click();
            sleep(5000);
            // 3. Xác minh hiển thị pop-up list danh sách
            boolean check = c.isDisplayed();
            if (check){
                System.out.println(" Testcase pass");
            }
            else{
                System.out.println(" Testcase fail");
            }
        }
        else{
            System.out.println(" Testcase fail");
        }
    }

    @Test(description = "Verify that values list is displayed when user click on Employee status dropdown list")
    public void runTestscript7(){
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(3000);
        // 2. Xác minh hiển thị của dropdown list
        boolean dropdown2 = guru.findElement(By.xpath(("//label[@for='employmentStatus']"))).isDisplayed();
        if (dropdown2){
            // Click vào dropdown list
            WebElement c = guru.findElement(By.id("employmentStatus"));
            c.click();
            sleep(5000);
            // 3. Xác minh hiển thị pop-up list danh sách
            boolean check = c.isDisplayed();
            if (check){
                System.out.println(" Testcase pass");
            }
            else{
                System.out.println(" Testcase fail");
            }
        }
        else{
            System.out.println(" Testcase fail");
        }
    }
    @Test(description = "Verify that action auto filter record when user selected value at Role title dropdown list")
    public void runTestscript8() {
        String value = "Dev";
        int dem = 0;
        int sl = 0;
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(5000);
        // 2. Xác minh hiển thị của dropdown list
        boolean dropdown1 = guru.findElement(By.xpath("//label[@for='roleTitle']")).isDisplayed();
        if (dropdown1) {
            // 3. Thực hiện chọn giá trị trong list
            Select select = new Select(guru.findElement(By.id("roleTitle")));
            select.selectByVisibleText(value);
            sleep(5000);
            // 4. Kiểm tra số lượng record hiển thị có lọc đúng theo từ khóa tìm kiếm
            while (true) {
                List<WebElement> list = guru.findElements(By.xpath("//tbody//td[6]"));
                for (WebElement i : list) {
                    if (value.equals(i.getText())) {
                        dem += 1;
                    }
                }
                sl += list.size();
                // Kiểm tra nút next
                WebElement next;
                try {
                    next = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='First page']/a")));
                } catch (Exception e) {
                    break;
                }
                // Cuộn đến phần tử trước khi click
                ((JavascriptExecutor) guru).executeScript("arguments[0].scrollIntoView(true);", next);
                sleep(500);
                if (next.isDisplayed()) {
                    // Sử dụng JavascriptExecutor để click phần tử
                    ((JavascriptExecutor) guru).executeScript("arguments[0].click();", next);
                    sleep(3000);
                } else {
                    break;
                }
            }

            if (dem == sl) {
                System.out.println("Testcase pass");
            } else {
                System.out.println("Testcase fail: expected " + sl + " but found " + dem);
            }

        } else {
            System.out.println("Testcase fail: dropdown not found");
        }
    }

    @Test(description = "Verify that action auto filter record when user selected value at Employee status dropdown list")
    public void runTestscript9() {
        String value = "Active";
        int dem = 0;
        int sl = 0;

        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(5000);
        // 2. Xác minh hiển thị của dropdown list
        boolean dropdown2 = guru.findElement(By.xpath("//label[@for='employmentStatus']")).isDisplayed();
        if (dropdown2) {
            // 3. Thực hiện chọn giá trị trong list
            Select select = new Select(guru.findElement(By.id("employmentStatus")));
            select.selectByVisibleText(value);
            sleep(5000);
            // 4. Kiểm tra số lượng record hiển thị có lọc đúng theo từ khóa tìm kiếm
            while (true) {
                List<WebElement> list = guru.findElements(By.xpath("//tbody//td[7]"));
                for (WebElement i : list) {
                    if (value.equals(i.getText())) {
                        dem += 1;
                    }
                }
                sl += list.size();
                // Kiểm tra nút next
                WebElement next;
                try {
                    next = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='First page']/a")));
                } catch (Exception e) {
                    break;
                }
                // Cuộn đến phần tử trước khi click
                ((JavascriptExecutor) guru).executeScript("arguments[0].scrollIntoView(true);", next);
                sleep(500);
                if (next.isDisplayed()) {
                    // Sử dụng JavascriptExecutor để click phần tử
                    ((JavascriptExecutor) guru).executeScript("arguments[0].click();", next);
                    sleep(3000);
                } else {
                    break;
                }
            }

            if (dem == sl) {
                System.out.println("Testcase pass");
            } else {
                System.out.println("Testcase fail: expected " + sl + " but found " + dem);
            }

        } else {
            System.out.println("Testcase fail: dropdown not found");
        }
    }

    @Test(description = "Verify that the Table content is displayed correctly")
    public void runTestscript10() {
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(5000);
        // 2. Xác tịnh tên các cột trong bảng
        String profile = guru.findElement(By.xpath(("//th[1]"))).getText();
        String employeelID = guru.findElement(By.xpath(("//th[2]"))).getText();
        String name = guru.findElement(By.xpath(("//th[3]"))).getText();
        String emailID = guru.findElement(By.xpath(("//th[4]"))).getText();
        String dateJoined = guru.findElement(By.xpath(("//th[5]"))).getText();
        String roleTitle = guru.findElement(By.xpath(("//th[6]"))).getText();
        String employmentStatus = guru.findElement(By.xpath(("//th[7]"))).getText();
        String actions = guru.findElement(By.xpath(("//th[8]"))).getText();
        System.out.println(profile+"; "+employeelID+"; "+name+"; "+emailID+"; "+dateJoined+"; "+roleTitle+"; "+employmentStatus+"; "+actions);
        try{
            Assert.assertEquals(profile,"Profile");
            Assert.assertEquals(employeelID,"Employee ID #");
            Assert.assertEquals(name,"Name");
            Assert.assertEquals(emailID,"Email ID");
            Assert.assertEquals(dateJoined,"Date Joined");
            Assert.assertEquals(roleTitle,"Role Title");
            Assert.assertEquals(employmentStatus,"Employment Status");
            Assert.assertEquals(actions,"Actions");
            System.out.println(" Testcase pass");

        }catch (AssertionError e) {
            System.out.println(" Testcase fail " + e.getMessage());
            throw e;
        }
    }
    @Test(description = "Verify the number of records on 1 page")
    public void runTestscript11() {
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(5000);
        // 2. Lấy từng record rồi đếm tổng số lượng
        List<WebElement> list = guru.findElements(By.xpath("//tbody//tr"));
        try{
            Assert.assertEquals(list.size(),15,"The actual number of records does not match the expected count.");
            System.out.println(" Testcase pass");

        }catch (AssertionError e) {
            System.out.println(" Testcase fail " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Verify the pagination button displays color when on that page")
    public void runTestscript12() {
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(5000);
        Random random = new Random();

        // Tạo số ngẫu nhiên từ 1 đến 5
        int randomNumber = random.nextInt(6);
        guruWait =  new WebDriverWait(guru, Duration.ofSeconds(10));
        WebElement button = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//li[@title='%s']/a",randomNumber))));

        // Cuộn đến phần tử trước khi click
        ((JavascriptExecutor) guru).executeScript("arguments[0].scrollIntoView(true);", button);
        sleep(500);
        // Sử dụng JavascriptExecutor để click phần tử
        ((JavascriptExecutor) guru).executeScript("arguments[0].click();", button);

        if (button.isDisplayed()) {
            sleep(5000);
            String color = button.getCssValue("background-color");
            // Kiểm tra xem màu có đúng như mong đợi không
            String hexColor = rgbaToHex(color);
            try{
                Assert.assertEquals(hexColor, "#00cdc2", "Button không highligh màu xanh");
                System.out.println(" Testcase pass");

            }catch (AssertionError e) {
                System.out.println(" Testcase fail " + e.getMessage());
                throw e;
            }
        }
        else{
            System.out.println(" Testcase fail ");
        }
    }
    @Test(description = "Verify that Back button and Fisrt button is hidden when user is on the first page")
    public void runTestscript13() {
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        // 2. Xác định và nhấn vào nút phân trang 1
        WebElement button = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='1']/a")));

        // Cuộn đến phần tử trước khi click
        ((JavascriptExecutor) guru).executeScript("arguments[0].scrollIntoView(true);", button);
        sleep(500);
        // Sử dụng JavascriptExecutor để click phần tử
        ((JavascriptExecutor) guru).executeScript("arguments[0].click();", button);
        sleep(5000);
        // 3. Kiểm tra các nút Back và Firt có hiển thị không
        boolean buttonBackDisplayed;
        try {
            WebElement buttonBack = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='Pre page']/a")));
            buttonBackDisplayed = buttonBack.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            buttonBackDisplayed = false;
        }
        boolean buttonFirstDisplayed;
        try {
            WebElement buttonFirst = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='Next page']")));
            buttonFirstDisplayed = buttonFirst.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            buttonFirstDisplayed = false;
        }

        try {
            Assert.assertFalse(buttonFirstDisplayed, "The 'First' button displays on the first page");
            Assert.assertFalse(buttonBackDisplayed, "The 'Back' button displays on the first page");
            System.out.println("Testcase pass");
        } catch (AssertionError e) {
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }
    @Test (description = "Verify that Next button and Last button is hidden when user is on the last page")
    public void runTestscript14() {
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        // 2. Xác định và nhấn vào nút phân trang Last
        WebElement button = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='Last page']/a")));

        // Cuộn đến phần tử trước khi click
        ((JavascriptExecutor) guru).executeScript("arguments[0].scrollIntoView(true);", button);
        sleep(500);
        // Sử dụng JavascriptExecutor để click phần tử
        ((JavascriptExecutor) guru).executeScript("arguments[0].click();", button);
        sleep(5000);
        // 3. Kiểm tra các nút Next và Last có hiển thị không
        boolean buttonLastDisplayed;
        try {
            WebElement buttonBack = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='Last page']/a")));
            buttonLastDisplayed = buttonBack.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            buttonLastDisplayed = false;
        }
        boolean buttonNextDisplayed;
        try {
            WebElement buttonFirst = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='First page']")));
            buttonNextDisplayed = buttonFirst.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            buttonNextDisplayed = false;
        }

        try {
            Assert.assertFalse(buttonNextDisplayed, "The 'Next' button displays on the first page");
            Assert.assertFalse(buttonLastDisplayed, "The 'Last' button displays on the first page");
            System.out.println("Testcase pass");
        } catch (AssertionError e) {
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }
    @Test(description = "Verify that button maximum number displayed")
    public void runTestscript15() {
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        sleep(5000);
        // 2. Xác định và đếm số lượng button phân trang loại có đánh số
        List<WebElement> paginationButtons = guru.findElements(By.xpath("//a[@class='page-link']"));
        String[] Ds = {"Back","First","Next","Last"};
        int dem=paginationButtons.size();
        for(WebElement buttons: paginationButtons){
            String b = buttons.getText();
            for(String i: Ds){
                if(b.equals(i)) {dem-=1;}
            }
        }
        try{
            Assert.assertEquals(dem,5);
            System.out.println("Testcase pass");
        }catch(AssertionError e) {
            System.out.println(" Testcase fail" + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Verify that the placeholder of Search textbox is displayed correctly ")
    public void runTestscript16() {
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        // 2. Xác định title placeholder
        WebElement search = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='form-control ']")));
        String s = search.getAttribute("placeholder");
        try{
            Assert.assertEquals(s,"Search");
            System.out.println("Testcase pass");
        }catch(AssertionError e) {
            System.out.println("Testcase fail " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Verify that displayed is message 'Table is empty' when don't have records")
    public void runTestscript17() {
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        // 2. Xác định và nhập giá trị tìm kiếm vào textbox
        WebElement search = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='form-control ']")));
        sleep(5000);
        search.sendKeys("%%%");
        sleep(5000);
        // 3. Xác định title khi lọc không có dữ liệu
        WebElement text = guruWait.until(ExpectedConditions.elementToBeClickable(By.tagName("td")));
        String t = text.getText();
        try{
            Assert.assertEquals(t,"Table is empty");
            System.out.println("Testcase pass");
        }catch(AssertionError e){
            System.out.println(" Testcase fail " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Verify that the error message “Exceeds 255 characters” is displayed when user input 256 character into Search textbox")
    public void runTestscript18() {
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        // 2. Nhập 256 ký tự vào ô tìm kiếm
        WebElement search = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='form-control ']")));
        sleep(5000);
        // tạo dữ liệu tự nhập
        String inputString = new String(new char[256]).replace("\0", "a");
        search.sendKeys(inputString);
        sleep(5000);
        // 3. Kiểm tra xem thông báo lỗi "Exceeds 255 characters" có hiển thị không
        boolean errorMessageDisplayed;
        try {
            WebElement errorMessage = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='table-search-bar']//span[contains(text(),'Exceeds 255 characters')]")));
            errorMessageDisplayed = errorMessage.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            errorMessageDisplayed = false;
        }
        // 4. Kiểm tra thông báo lỗi chính xác
        try {
            Assert.assertTrue(errorMessageDisplayed, "Error message is not displayed");
            if (errorMessageDisplayed) {
                WebElement errorMessage = guruWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='table-search-bar']//span[contains(text(),'Exceeds 255 characters')]")));
                String errorText = errorMessage.getText();
                try{
                    Assert.assertEquals(errorText, "Exceeds 255 characters", "Error message is not as expected");
                    System.out.println("Testcase pass");
                } catch (AssertionError e) {
                    System.out.println("Testcase fail: " + e.getMessage());
                    throw e;
                }
            }
        } catch (AssertionError e) {
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }
    @Test(description = "Verify that the successfully when user input 255 character into Search textbox")
    public void runTestscript19() {
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        // 2. Nhập 255 ký tự vào ô tìm kiếm
        WebElement search = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='form-control ']")));
        sleep(5000);
        String inputString = new String(new char[255]).replace("\0", "a");
        search.sendKeys(inputString);
        sleep(5000);
        // 3. Kiểm tra xem thông báo lỗi "Exceeds 255 characters" không có hiển thị
        boolean errorMessageDisplayed;
        try {
            WebElement errorMessage = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='table-search-bar']//span[contains(text(),'Exceeds 255 characters')]")));
            errorMessageDisplayed = errorMessage.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            errorMessageDisplayed = false;
        }
        // 4. Kiểm tra không hiển thị
        try {
            Assert.assertFalse(errorMessageDisplayed, "Error message is not displayed");
            System.out.println("Testcase pass");
        } catch (AssertionError e) {
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }
    @Test(description = "Verify that the successfully when user input 254 character into Search textbox")
    public void runTestscript20() {
        // 1. Xác định và nhấp vào "Employee"
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        // 2. Nhập 254 ký tự vào ô tìm kiếm
        WebElement search = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='form-control ']")));
        sleep(5000);
        String inputString = new String(new char[254]).replace("\0", "a");
        search.sendKeys(inputString);
        sleep(5000);
        // 3. Kiểm tra xem thông báo lỗi "Exceeds 255 characters" không có hiển thị
        boolean errorMessageDisplayed;
        try {
            WebElement errorMessage = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='table-search-bar']//span[contains(text(),'Exceeds 255 characters')]")));
            errorMessageDisplayed = errorMessage.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            errorMessageDisplayed = false;
        }
        // 4. Kiểm tra không hiển thị
        try {
            Assert.assertFalse(errorMessageDisplayed, "Error message is not displayed");
            System.out.println("Testcase pass");
        } catch (AssertionError e) {
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }
    }
    @Test(description = "Verify that move to Add employee screen when user click on Add employee button")
    public void runTestscript21() {
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        // 1. Xác định và nhấp vào nút "Add employee"
        WebElement button = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']/img")));
        button.click();
        sleep(5000);

        // 2. Xác minh xem người dùng có được chuyển đến màn hình "Add employee" không
        boolean isOnAddEmployeeScreen;
        try {
            // Kiểm tra xem có phần tử duy nhất trên màn hình "Add employee" không
            WebElement addEmployeeHeader = guruWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='capitalize text-muted'])[2]")));
            isOnAddEmployeeScreen = addEmployeeHeader.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            isOnAddEmployeeScreen = false;
        }

        try {
            Assert.assertTrue(isOnAddEmployeeScreen, "User is not on the Add Employee screen");
            System.out.println("Testcase pass");
        } catch (AssertionError e) {
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }

    }
    @Test(description ="Verify that the Add employee button is displayed correctly")
    public void runTestscript22() {
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();

        // 1. Xác định nút "Add employee"
        WebElement addEmployeeButton = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']")));
        sleep(5000);
        // 2. Kiểm tra văn bản của nút
        String buttonText = addEmployeeButton.getText();
        try {
            Assert.assertEquals(buttonText, "+ Add employee", "Button text is not as expected");
        } catch (AssertionError e) {
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }

        // 3. Kiểm tra trạng thái mặc định của nút
        boolean isButtonEnabled = addEmployeeButton.isEnabled();
        try {
            Assert.assertTrue(isButtonEnabled, "Button is not in active state");
        } catch (AssertionError e) {
            System.out.println("Testcase fail: " + e.getMessage());
            throw e;
        }

        // Nếu tất cả các kiểm tra đều qua, in ra "Testcase pass"
        System.out.println("Testcase pass");
    }

    @Test(description = "Verify that move to Update employee screen when user click on button eye at Actions field of one record ")
    public void runTestscript23() {
        WebElement em = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='nav-item'])[5]")));
        em.click();
        Random random = new Random();
        // Tạo số ngẫu nhiên từ 1 đến 10
        int randomNumber = random.nextInt(11);
        sleep(5000);
        // 1. Xác định và nhấp vào biểu tượng con mắt trong cột "Action" của bản ghi
        WebElement eyeIcon = guruWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//img[@alt='detail-icon'])[%s]",randomNumber))));
        ((JavascriptExecutor) guru).executeScript("arguments[0].click();", eyeIcon);

        // 2. Xác minh xem di chuyển đến màn hình chỉnh sửa chưa
        boolean isUpdateDisplayed;
        try {
            WebElement update = guruWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='capitalize text-muted'])[3]")));
            isUpdateDisplayed = update.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            isUpdateDisplayed = false;
        }
        try {
            Assert.assertTrue(isUpdateDisplayed, "Update screen is not displayed");
            System.out.println("Testcase pass");
        } catch (AssertionError e) {
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
    // Hàm chuyển đổi từ rgba sang HEX
    private String rgbaToHex(String color) {
        String[] numbers = color.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        return String.format("#%02x%02x%02x", r, g, b);
    }
}


