package planisphereTest.PlanisphereTestByCucumber;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebConnector {
//    private final static long DEFAULT_TIMEOUT = 5000;

    // ブラウザ名
    private final String BROWSER_IE = "IE";
    private final String BROWSER_EDGE = "Edge";
    private final String BROWSER_FF = "FireFox";
    private final String BROWSER_OPERA = "Opera";
    private final String BROWSER_CR = "Chrome";
    // ブラウザタイプ
    private final int BROWSER_TYPE_IE = 0;
    private final int BROWSER_TYPE_EDGE = 1;
    private final int BROWSER_TYPE_FF = 2;
    private final int BROWSER_TYPE_OPERA = 3;
    private final int BROWSER_TYPE_CR = 4;

    /** WebDriverクラス */
    public static WebDriver driver;

    /** 実行中のWebDriverタイプを保持する */
    private int DriverType;
    /** Screen Shot FolderName */
    private String screenShotPath = null;
    private Actions builder = null;
    private ChromeOptions options = new ChromeOptions();

    private boolean acceptNextAlert = true;

    /**
     * コンストラクタ
     */
//    public WebConnector() {}

	public Date dt;
	public String testDate;
	public int weekEnd;
	public String dateFrom;
	public String dateTo;

	public int termValue;
	public int termValueWeekEnd;

	public int headCountValue;

    public static WebDriverWait wait;
    private String window1;
    private String window2;

    /**
     * WebDriverの選択
     * @param browser ブラウザ名
     * @throws InterruptedException
     */
    public void selectWebDriver(String browser) throws InterruptedException {
        this.DriverType = BROWSER_TYPE_CR;
        if (browser.equals(BROWSER_IE)) {
            this.DriverType = BROWSER_TYPE_IE;
        } else if (browser.equals(BROWSER_EDGE)) {
            this.DriverType = BROWSER_TYPE_EDGE;
        } else if (browser.equals(BROWSER_FF)) {
            this.DriverType = BROWSER_TYPE_FF;
        } else if (browser.equals(BROWSER_OPERA)) {
            this.DriverType = BROWSER_TYPE_OPERA;
        }
        //WebDriverのセット
        this.setWebDriver();
    }

    /**
     * WebDriverインスタンスを生成する
     * @throws InterruptedException
     */
    private void setWebDriver() throws InterruptedException {
        switch (this.DriverType) {
        case BROWSER_TYPE_IE: // IE
            //Windows10では、 64bit 版だと動かないので32bitを使う
            System.setProperty("webdriver.ie.driver", "./exe32bit/IEDriverServer.exe");

            driver = new InternetExplorerDriver();
            break;

        case BROWSER_TYPE_EDGE: // Edge
            System.setProperty("webdriver.edge.driver", "C:\\WebDrivers\\edgedriver_win64\\msedgedriver.exe");

            driver = new EdgeDriver();
            break;

        case BROWSER_TYPE_FF: // FireFox
            System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver_win64\\geckodriver.exe");
            driver = new FirefoxDriver();
            break;

        case BROWSER_TYPE_OPERA: // Opera
            System.setProperty("webdriver.opera.driver", "./exe64bit/operadriver.exe");
            driver = new OperaDriver();
            break;

        default: // Chrome
            System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver_win32\\chromedriver.exe");
//            driver = new ChromeDriver();
            driver = new ChromeDriver(options);
            break;
        }
//        this.builder =  new Actions(this.driver);
        Thread.sleep(3000);
        //暗黙wait
        //this.driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);

		dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		testDate = sdf.format(dt);

        wait = new WebDriverWait(driver, 10);

    }

    /**
     * 指定されたURLでブラウザを開く
     * @param location 表示するURL
     * @throws InterruptedException
     */
    public void openAndWait(String location) throws InterruptedException {
        //location(URL)のタイプミスとかでもエラーにはならない
        driver.navigate().to(location);
        Thread.sleep(5000);
    }


    /**
     * Windowを最大化する
     * ただし、ChromeDriver以外
     * @throws InterruptedException
     */
    public void setWindowMax() throws InterruptedException {
        //ただし、Chromeではorg.openqa.selenium.NoSuchSessionExceptionで動かない）
            try {
                driver.manage().window().maximize();
            } catch (Exception e) {
//                driver.quit();
            }
        Thread.sleep(2000);
    }

    /**
     * 使用言語対応
     */
    public void setLangEnglish() {
//        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en-GB");
//      Map<String, Object> prefs = new HashMap<String, Object>();
//      prefs.put("intl.accept_languages", "en-GB,en");
//      options.setExperimentalOption("prefs", prefs);

    }

    public void setlangJapanese() {
//        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=ja-JP");

    }


    /**
     * 指定時間待つ
     * @param sec waitする秒数
     */
    public void sleep(int sec) {
        long msec = sec * 1000;
        try {
            Thread.sleep(msec);
        } catch (Exception e) {
        }
    }

    /**
     * iFrame を切り替える
     * @param sec waitする秒数
     */
    public WebDriver switchFrame(String selector) {
        return driver.switchTo().frame(selector);
    }

    /**
     * フレームが表示されるまで待つ
     * DEFAULT_TIMEOUTで表示されなかったら Exceptionをスローする
     * @param iFrameのIDセレクタ
     * @param フレームが表示された後で、フレーム内で探すセレクタ(ID->name->class順）
     * 　　　　　targetClass iFrame内の class名
     * FireFoxだとダメみたいです。

    public void sleepFrame(String selector, String targetClass) {
        String parentHandle = this.driver.getWindowHandle();
        logger.log_info(this, "WindowHandle["+ parentHandle+"]");
        //iFrame エレメントを取得
        WebElement iframe = this.driver.findElement(By.id(selector));
        logger.log_info(this, "iframe["+ iframe.getLocation()+"]");
        //iFrame Switch
        this.driver.switchTo().frame(iframe);
        WebElement element =this.driver.findElement(By.className(targetClass));
        //親画面に戻る
        this.driver.switchTo().window(parentHandle);
    }
    */

    public void parentWindow() {
        String parentHandle = driver.getWindowHandle();
        //親画面に戻る
        driver.switchTo().window(parentHandle);
    }

    public void setWindow() {
        Set<String> set = driver.getWindowHandles();
        java.util.Iterator<String> it = set.iterator();
        window1 = it.next();
        window2 = it.next();
        driver.switchTo().window(window1);
    }

    public void setChild() {
    	driver.switchTo().window(window2);
    }

    public void refresh() throws InterruptedException {
    	driver.navigate().refresh();
    	Thread.sleep(5000);
    }
    public void eraseCalendar() {

    }
    /**
     * スクリーンショット画像格納フォルダを指定する
     * @param path フォルダ名
     */
    public void setScreenShotPath(String path) {
        this.screenShotPath = path;
    }


    public void initSelenium() throws Exception {
    }

    /**
     * 終了処理
     */
    public void destroySelenium() {
        //WebDriver プロセスを終了し、ブラウザを閉じる
        driver.quit();
    }

    /**
     * ブラウザのスクリーンショットを実行する
     * @param filename 保存するファイル名（パスは付けない）
     */
//    public void getScreenShot(String filename) {
//        String path = this.screenShotPath + "/" + filename;
        /**
         * Chromeではスクショ　できません、2.9では
         * 色々やってみたけど、Dhromeでは Exceptionで動作しないため、無効化
         */
//        if(this.DriverType != BROWSER_TYPE_CR) {
//            TakesScreenshot screen = (TakesScreenshot)this.driver;
//            Path capture = Paths.get(path);
//            try {
//                Files.write(capture, screen.getScreenshotAs(OutputType.BYTES));
//            } catch(Exception e) {
//                this.driver.quit();
//            }
//        }
//    }

    /**
     * セレクタに値を送信する
     * @param selector id or name属性セレクタ
     * @param value 送信する値
     */
    public void inputAndWait(String selector, String value) {
        try {
            WebElement element = this.driver.findElement(By.id(selector));
            element.sendKeys(value);
            return;
        } catch(Exception e) {
        }
        try {
            WebElement element = this.driver.findElement(By.name(selector));
            element.sendKeys(value);
        } catch(Exception e) {
            this.driver.quit();
        }
    }

    /**
     * セレクタにEnterを送信する
     * @param selector id or name属性セレクタ
     * @param value 送信する値
     */
    public void inputEnterAndWait(String selector) {
        try {
            WebElement element = this.driver.findElement(By.id(selector));
            element.sendKeys(Keys.RETURN);
            return;
        } catch(Exception e) {
        }
        try {
            WebElement element = this.driver.findElement(By.name(selector));
            element.sendKeys(Keys.RETURN);
        } catch(Exception e) {
            this.driver.quit();
        }
    }

    /**
     * ボタンセレクタをクリックする
     * @param selector [input type]セレクタ
     */
    public void btnClickAndWait(String value) {
        try {
            WebElement element = this.driver.findElement(
                By.xpath("//input[@type=\"submit\" and @value=\"" + value + "\"]"));
            element.click();
        } catch(Exception e) {
            this.driver.quit();
        }
    }

    /**
     * 複数のボタンの内、value属性がlabelのボタンをクリックする
     * @param type [input type] "button"  or "submit"
     * @param label value属性値
     */
    public void btnClickAndWait(String type, String label) {
        try {
            List <WebElement>element = this.driver.findElements(By.xpath("//input[@type=\"" + type + "\"]"));
            for(WebElement entity : element) {
                if(entity.getAttribute("value").equals(label)) {
                    entity.click();
                    break;
                }
            }
        } catch(Exception e) {
            this.driver.quit();
        }
    }

    /**
     * 規則的に並ぶ複数のボタンの内、value属性がvalueのindexで指定された順番のボタンをクリックする
     * 複数のボタン配列の特定のvalueのボタンをクリックする
     * @param selector [input type]セレクタ
     * @param value value属性値
     * @param index 配列の順番(0オリジン)
     */
    public void btnByblockClickAndWait(String selector, String value, int index) {
        int i = 0;
        try {
            List <WebElement>element = this.driver.findElements(
                    By.xpath("//input[@type=\"" + selector + "\" and @value=\"" + value + "\"]"));
            for(WebElement entity : element) {
                if(i == index) {
                    entity.click();
                    break;
                }
                i++;
            }
        } catch(Exception e) {
            this.driver.quit();
        }
    }

    /**
     * 複数のボタンの内 selector名の name属性のボタンをクリックする
     * @param selector
     */
    public void btnBynameClickAndWait(String selector) {
        try {
            WebElement element = this.driver.findElement(By.name(selector));
            element.click();
        } catch(Exception e) {
            this.driver.quit();
        }
    }

    /*
     * Locater名が、id属性のボタンをクリックする
     */
    public void btnClickAndWait_ID(String commandLocater) throws InterruptedException {
		WebElement elementPos = driver.findElement(By.id(commandLocater));
		Actions actions = new Actions(driver);
		actions.moveToElement(elementPos);
		actions.perform();
		Thread.sleep(500);
		WebElement submitButton = driver.findElement(By.id(commandLocater));
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();

		Thread.sleep(1000);
    }

    /*
     * Locater名が、xpath属性のエレメントをクリックする
     */
    public void btnClickAndWait_X(String commandLocater) throws InterruptedException {
		WebElement elementPos = driver.findElement(By.xpath(commandLocater));
		Actions actions = new Actions(driver);
		actions.moveToElement(elementPos);
		actions.perform();
		Thread.sleep(500);
		WebElement element = driver.findElement(By.xpath(commandLocater));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();

		Thread.sleep(500);
    }

    /*
     * href リンクをクリックする
     * @param text 検索したいリンクテキスト
     */
    public void clickAndWait(String text) {
        try {
            //エレメントから、a href のリンク文字列を探す
            WebElement element = this.driver.findElement(By.linkText(text));
            element.click();
        } catch(Exception e) {
            this.driver.quit();
        }
    }

    /**
     * href リンクをクリックする
     * @param href リンクテキスト
     */
    public void clickHrefAndWait(String href) {
        try {
            //エレメントから、a href のリンクを探す
            WebElement element = this.driver.findElement(By.xpath("//a[@href='"+href+"']"));
            element.click();    //見つかったらクリック
        } catch(Exception e) {
        }
    }

    /**
     * 指定されたテキストが、現在のページ内にあるかチェックする
     * @param text 検索対象のテキスト
     * @return テキストが見つかれば true, 見つからなければ false を返す
     */
    public boolean isTextPresent(String text) {
        WebElement content = this.driver.findElement(By.tagName("body"));
        boolean res = content.getText().contains(text);
        return res;
    }

	public boolean isPopUpPresent(String text) throws InterruptedException {
	    try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        if (acceptNextAlert) {
		            alert.accept();
		        } else {
		            alert.dismiss();
		        }
		        boolean res = alertText.contains(text);
		        Thread.sleep(1000);
			    acceptNextAlert = true;
		        return res;

		    } finally {
		      acceptNextAlert = true;
		    }
	}

	public boolean checkContensList(String commandLocater, String planName, String hyouji) throws InterruptedException {
		String targetContent;
		List<WebElement> contentsList = driver.findElements(By.className(commandLocater));
		boolean res;

		for(WebElement content : contentsList) {
			targetContent = content.getText();
			if(targetContent.equals(planName)) {
				res = hyouji.contains("yes");
			}else {
				res= hyouji.contains("no");
			}
		}
		Thread.sleep(500);
		return false;
	}

    /**
     * spanタグをクリックする
     * @param tagname "span"を期待
     * @param text タグから検索したい文字列
     */
    public void spanClickAndWait(String tagname, String text) {
        int i = 0;
        try {
            //tagnameの名前のエレメントを探す
            List <WebElement>element = this.driver.findElements(By.tagName(tagname));
            for(WebElement entity : element) {
                //エレメントのテキストが検索したい文字列と合致
                if(entity.getText().equals(text)) {
                    entity.click(); //エレメントをクリックする
                    break;
                }
            }
        } catch(Exception e) {
            this.driver.quit();
        }
    }

    /**
     * aタグをクリックする
     * @param text a タグのテキスト
     * @throws InterruptedException
     */
    public void linkClickAndWait(String text) throws InterruptedException {
		WebElement elementPos = driver.findElement(By.partialLinkText(text));
		Actions actions = new Actions(driver);
		actions.moveToElement(elementPos);
		actions.perform();
		Thread.sleep(500);

		WebElement element = driver.findElement(By.partialLinkText(text));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
        Thread.sleep(500);
    }

/**  チェックボックスをクリックする
 * @throws InterruptedException */
    public void checkBoxClick(String commandLocater) throws InterruptedException {
		WebElement elementPos = driver.findElement(By.id(commandLocater));
		Actions actions = new Actions(driver);
		actions.moveToElement(elementPos);
		actions.perform();
		Thread.sleep(500);

		WebElement element = driver.findElement(By.id(commandLocater));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
        Thread.sleep(500);
    }

/**  ドロップダウンメニューから選択する
 * @throws InterruptedException */
    public void dropDownSelect(String commandLocater, String selText) throws InterruptedException {
		WebElement element = this.driver.findElement(By.id(commandLocater));
		Actions actions = new Actions(this.driver);
		actions.moveToElement(element);
		actions.perform();
		Thread.sleep(1000);
        Select output_Select = new Select(this.driver.findElement(By.id(commandLocater)));
        output_Select.selectByVisibleText(selText);
        Thread.sleep(500);

    }


    public void assertTable(String className, List expect) {

        WebElement table = this.driver.findElement(By.className(className));

        List <WebElement>rows = table.findElements(By.tagName("tr"));
        int columnCount = ((SearchContext) rows.get(0)).findElements(By.xpath("./*")).size();
        assertThat(rows.size() * columnCount, is(expect.size()));

        int ix = 0;
        for (WebElement row : rows) {

            List <WebElement>cells = row.findElements(By.xpath("./*"));
            for (WebElement cell : cells) {
                assertThat(cell.getText(), is(expect.get(ix)));
                ix++;
            }
        }
    }


	public void btnClickAndWait_CSS(String commandLocater) throws InterruptedException {
		WebElement elementPos = driver.findElement(By.cssSelector(commandLocater));
		Actions actions = new Actions(driver);
		actions.moveToElement(elementPos);
		actions.perform();
		Thread.sleep(500);

		WebElement element = driver.findElement(By.cssSelector(commandLocater));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
        Thread.sleep(1000);
	}

	public void cssButtonClickAndPopUp(String commandLocater) throws InterruptedException {
		acceptNextAlert = true;

		WebElement exitButton = driver.findElement(By.cssSelector(commandLocater));
		Actions actions = new Actions(driver);
		actions.moveToElement(exitButton);
		actions.perform();
		Thread.sleep(500);
		exitButton = driver.findElement(By.cssSelector(commandLocater));
		wait.until(ExpectedConditions.elementToBeClickable(exitButton));
		exitButton.click();
		Thread.sleep(500);
	}

	public void birthdayInput(String commandLocater, String birthday) throws InterruptedException {
		String[] inputText = {"//"};
		if(birthday.length() != 0) {
			inputText = birthday.split("/", -1);
			WebElement inputBox = driver.findElement(By.id(commandLocater));
			wait.until(ExpectedConditions.elementToBeClickable(inputBox));
			inputBox.clear();
			Thread.sleep(500);
			inputBox.sendKeys(inputText[0]);
			Thread.sleep(500);
			inputBox.sendKeys(Keys.RIGHT);
			Thread.sleep(500);
			inputBox.sendKeys(inputText[1]);
			Thread.sleep(500);
			inputBox.sendKeys(Keys.RIGHT);
			Thread.sleep(500);
			inputBox.sendKeys(inputText[2]);
			Thread.sleep(500);
		}
	}


	public void sunday(String commandLocater) throws InterruptedException {
		Date reserveDate;
		String testReserveDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, 1);
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
//			calendar.add(Calendar.DATE, 1);
			break;
		case Calendar.MONDAY:
			calendar.add(Calendar.DATE, 6);
			break;
		case Calendar.TUESDAY:
			calendar.add(Calendar.DATE, 5);
			break;
		case Calendar.WEDNESDAY:
			calendar.add(Calendar.DATE, 4);
			break;
		case Calendar.THURSDAY:
			calendar.add(Calendar.DATE, 3);
			break;
		case Calendar.FRIDAY:
			calendar.add(Calendar.DATE, 2);
			break;
		case Calendar.SATURDAY:
			calendar.add(Calendar.DATE, 1);
			break;
		default:
		}

		reserveDate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		testReserveDate = sdf.format(reserveDate);
		dt = reserveDate;

		String inputText = testReserveDate.substring(0, 10);

        WebElement inputDate = driver.findElement(By.id(commandLocater));
        wait.until(ExpectedConditions.elementToBeClickable(inputDate));
        inputDate.clear();
        Thread.sleep(500);
//        reserveYear = reserveYear + "\n";
        inputDate.sendKeys(inputText);
        Thread.sleep(500);
	}

	public void dateFromSet() {
		String reserveFrom;
		int reserveYear;
		int reserveMonth;
		int reserveDay;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		reserveFrom = sdf.format(dt);
		reserveYear = Integer.valueOf(reserveFrom.substring(0, 4));
		reserveMonth = Integer.valueOf(reserveFrom.substring(5, 7));
		reserveDay = Integer.valueOf(reserveFrom.substring(8, 10));
		dateFrom = String.valueOf(reserveYear) + "年" + String.valueOf(reserveMonth) + "月" + String.valueOf(reserveDay) + "日";
	}

	public void monday(String commandLocater) throws InterruptedException {
		Date reserveDate;
		String testReserveDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, 1);
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			calendar.add(Calendar.DATE, 1);
			break;
		case Calendar.MONDAY:
//			calendar.add(Calendar.DATE, 5);
			break;
		case Calendar.TUESDAY:
			calendar.add(Calendar.DATE, 6);
			break;
		case Calendar.WEDNESDAY:
			calendar.add(Calendar.DATE, 5);
			break;
		case Calendar.THURSDAY:
			calendar.add(Calendar.DATE, 4);
			break;
		case Calendar.FRIDAY:
			calendar.add(Calendar.DATE, 3);
			break;
		case Calendar.SATURDAY:
			calendar.add(Calendar.DATE, 2);
			break;
		default:
		}

		reserveDate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		testReserveDate = sdf.format(reserveDate);
		dt = reserveDate;

		String inputText = testReserveDate.substring(0, 10);

        WebElement inputDate = driver.findElement(By.id(commandLocater));
        wait.until(ExpectedConditions.elementToBeClickable(inputDate));
        inputDate.clear();
        Thread.sleep(500);
//        reserveYear = reserveYear + "\n";
        inputDate.sendKeys(inputText);
        Thread.sleep(500);
	}

	public void tuesday(String commandLocater) throws InterruptedException {
		Date reserveDate;
		String testReserveDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, 1);
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			calendar.add(Calendar.DATE, 2);
			break;
		case Calendar.MONDAY:
			calendar.add(Calendar.DATE, 1);
			break;
		case Calendar.TUESDAY:
//			calendar.add(Calendar.DATE, 6);
			break;
		case Calendar.WEDNESDAY:
			calendar.add(Calendar.DATE, 6);
			break;
		case Calendar.THURSDAY:
			calendar.add(Calendar.DATE, 5);
			break;
		case Calendar.FRIDAY:
			calendar.add(Calendar.DATE, 4);
			break;
		case Calendar.SATURDAY:
			calendar.add(Calendar.DATE, 3);
			break;
		default:
		}

		reserveDate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		testReserveDate = sdf.format(reserveDate);
		dt = reserveDate;

		String inputText = testReserveDate.substring(0, 10);

        WebElement inputDate = driver.findElement(By.id(commandLocater));
        wait.until(ExpectedConditions.elementToBeClickable(inputDate));
        inputDate.clear();
        Thread.sleep(500);
//        reserveYear = reserveYear + "\n";
        inputDate.sendKeys(inputText);
        Thread.sleep(500);
	}

	public void wednesday(String commandLocater) throws InterruptedException {
		Date reserveDate;
		String testReserveDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, 1);
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			calendar.add(Calendar.DATE, 3);
			break;
		case Calendar.MONDAY:
			calendar.add(Calendar.DATE, 2);
			break;
		case Calendar.TUESDAY:
			calendar.add(Calendar.DATE, 1);
			break;
		case Calendar.WEDNESDAY:
//			calendar.add(Calendar.DATE, 6);
			break;
		case Calendar.THURSDAY:
			calendar.add(Calendar.DATE, 6);
			break;
		case Calendar.FRIDAY:
			calendar.add(Calendar.DATE, 5);
			break;
		case Calendar.SATURDAY:
			calendar.add(Calendar.DATE, 4);
			break;
		default:
		}

		reserveDate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		testReserveDate = sdf.format(reserveDate);
		dt = reserveDate;

		String inputText = testReserveDate.substring(0, 10);

        WebElement inputDate = driver.findElement(By.id(commandLocater));
        wait.until(ExpectedConditions.elementToBeClickable(inputDate));
        inputDate.clear();
        Thread.sleep(500);
//        reserveYear = reserveYear + "\n";
        inputDate.sendKeys(inputText);
        Thread.sleep(500);
	}

	public void thursday(String commandLocater) throws InterruptedException {
		Date reserveDate;
		String testReserveDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, 1);
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			calendar.add(Calendar.DATE, 4);
			break;
		case Calendar.MONDAY:
			calendar.add(Calendar.DATE, 3);
			break;
		case Calendar.TUESDAY:
			calendar.add(Calendar.DATE, 2);
			break;
		case Calendar.WEDNESDAY:
			calendar.add(Calendar.DATE, 1);
			break;
		case Calendar.THURSDAY:
//			calendar.add(Calendar.DATE, 6);
			break;
		case Calendar.FRIDAY:
			calendar.add(Calendar.DATE, 6);
			break;
		case Calendar.SATURDAY:
			calendar.add(Calendar.DATE, 5);
			break;
		default:
		}

		reserveDate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		testReserveDate = sdf.format(reserveDate);
		dt = reserveDate;

		String inputText = testReserveDate.substring(0, 10);

        WebElement inputDate = driver.findElement(By.id(commandLocater));
        wait.until(ExpectedConditions.elementToBeClickable(inputDate));
        inputDate.clear();
        Thread.sleep(500);
//        reserveYear = reserveYear + "\n";
        inputDate.sendKeys(inputText);
        Thread.sleep(500);
	}

	public void friday(String commandLocater) throws InterruptedException {
		Date reserveDate;
		String testReserveDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, 1);
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			calendar.add(Calendar.DATE, 5);
			break;
		case Calendar.MONDAY:
			calendar.add(Calendar.DATE, 4);
			break;
		case Calendar.TUESDAY:
			calendar.add(Calendar.DATE, 3);
			break;
		case Calendar.WEDNESDAY:
			calendar.add(Calendar.DATE, 2);
			break;
		case Calendar.THURSDAY:
			calendar.add(Calendar.DATE, 1);
			break;
		case Calendar.FRIDAY:
//			calendar.add(Calendar.DATE, 6);
			break;
		case Calendar.SATURDAY:
			calendar.add(Calendar.DATE, 6);
			break;
		default:
		}

		reserveDate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		testReserveDate = sdf.format(reserveDate);
		dt = reserveDate;

		String inputText = testReserveDate.substring(0, 10);

        WebElement inputDate = driver.findElement(By.id(commandLocater));
        wait.until(ExpectedConditions.elementToBeClickable(inputDate));
        inputDate.clear();
        Thread.sleep(500);
//        reserveYear = reserveYear + "\n";
        inputDate.sendKeys(inputText);
        Thread.sleep(500);
	}

	public void saturday(String commandLocater) throws InterruptedException {
		Date reserveDate;
		String testReserveDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, 1);
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			calendar.add(Calendar.DATE, 6);
			break;
		case Calendar.MONDAY:
			calendar.add(Calendar.DATE, 5);
			break;
		case Calendar.TUESDAY:
			calendar.add(Calendar.DATE, 4);
			break;
		case Calendar.WEDNESDAY:
			calendar.add(Calendar.DATE, 3);
			break;
		case Calendar.THURSDAY:
			calendar.add(Calendar.DATE, 2);
			break;
		case Calendar.FRIDAY:
			calendar.add(Calendar.DATE, 1);
			break;
		case Calendar.SATURDAY:
//			calendar.add(Calendar.DATE, 1);
			break;
		default:
		}

		reserveDate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		testReserveDate = sdf.format(reserveDate);
		dt = reserveDate;

		String inputText = testReserveDate.substring(0, 10);

        WebElement inputDate = driver.findElement(By.id(commandLocater));
        wait.until(ExpectedConditions.elementToBeClickable(inputDate));
        inputDate.clear();
        Thread.sleep(500);
//        reserveYear = reserveYear + "\n";
        inputDate.sendKeys(inputText);
        Thread.sleep(500);
	}

	public void termSet(int term) {
		Date reserveEnd;
		String reserveTo;
		int reserveToYear;
		int reserveToMonth;
		int reserveToDay;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, term);
		reserveEnd = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		reserveTo = sdf.format(reserveEnd);
		reserveToYear = Integer.valueOf(reserveTo.substring(0, 4));
		reserveToMonth = Integer.valueOf(reserveTo.substring(5, 7));
		reserveToDay = Integer.valueOf(reserveTo.substring(8, 10));
		dateTo = String.valueOf(reserveToYear) + "年" + String.valueOf(reserveToMonth) + "月" + String.valueOf(reserveToDay) + "日";
	}

    /**
	*ページタイトルの検証
	*/
    public boolean isTitlePresent(String title) {
    	boolean res = WebConnector.driver.getTitle().contains(title);
    	return res;
    }

}
