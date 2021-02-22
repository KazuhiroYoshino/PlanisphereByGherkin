package planisphereTest.PlanisphereTestByCucumber;

import static org.junit.Assert.*;

import cucumber.api.java.ja.かつ;
import cucumber.api.java.ja.ならば;
import cucumber.api.java.ja.もし;
import cucumber.api.java.ja.前提;

public class StepDefinitions {
    private WebConnector connector = new WebConnector();


//    public void WebSteps(WebConnector connector) {
//        this.connector = connector;
//    }

    /**
     * 使用するWebドライバを指定する
     * @param browserType　Webドライバ名称
     * 　　　　　 "IE", "Edge", "FireFox", "Opera", "Chrome"
     * 　　ただし、Edge はまともに動きません
     * @throws InterruptedException
     */
	@前提("^Webドライバは\"([^\"]*)\"を選択する$")
    public void select_webdriver(String browserType) throws InterruptedException {
//		System.out.println(browserType);
		connector.selectWebDriver(browserType);
    }

    /**
     * 指定したURLを表示します
     * @param url　表示するURL
     * @throws InterruptedException
     */
    @前提("^ページ\"([^\"]*)\"を表示する$")
    public void display_url(String url) throws InterruptedException {
        connector.openAndWait(url);
    }

    @もし("Windowを最大化する$")
    public void window_maximized() throws InterruptedException {
        connector.setWindowMax();
    }

/**　待機 */
    @ならば("^\"([^\"]*)\"秒待つ$")
    public void wait(int sec) {
        connector.sleep(sec);
    }

    /**
* 表示結果のチェック
     * @param pattern 検索するテキスト
     */
    @ならば("画面に\"([^\"]*)\"と表示されていること$")
    public void search_text(String pattern) {
        assertTrue(connector.isTextPresent(pattern));
    }

/** クリックイベント各種 */
    /**
     * spanタグセレクタのクリックイベント
     * @param text spanタグで括られたテキストを指定する
     */
    @もし("ラベル\"([^\"]*)\"をクリックする$")
    public void span_click(String text) {
        connector.spanClickAndWait("span", text);
    }

    @もし("リンクテキスト\"([^\"]*)\"をクリックする$")
    public void link_click(String text) throws InterruptedException {
//		System.out.println(text);
        connector.linkClickAndWait(text);
    }

    /**
     * aタグセレクタのクリックイベント
     * @param href aタグの アンカー(href)を指定する
     */
    @もし("アンカー\"([^\"]*)\"をクリックする$")
    public void anchor_click(String href) {
        connector.clickHrefAndWait(href);
    }

    /**
     * input type="button"タグセレクタのクリックイベント
     * @param name ボタンのテキスト(value)を指定する
     */
    @もし("ボタン\"([^\"]*)\"をクリックする$")
    public void button_click(String name) {
        connector.btnClickAndWait(name);
    }

    /**
     * input type="button"タグセレクタのクリックイベント
     * @param name ボタンのテキスト(value)を指定する
     */
    @かつ("かつボタン\"([^\"]*)\"をクリックする$")
    public void and_button_click(String name) {
        connector.btnClickAndWait(name);
    }

    /**
     * 複数のinput type="submit" or "button"タグセレクタのnameの名前を持つ
     * ボタンクリックイベント
     * @param name ボタンのテキスト(value)を指定する
     * @param type ボタンのタイプ(button or submitを期待)を指定する
     */
    @もし("名前が\"([^\"]*)\"のボタン\"([^\"]*)\"をクリックする$")
    public void something_button_click(String name, String type) {
        connector.btnClickAndWait(type, name);
    }

    /**
     * 規則的に並ぶ複数のinput type="submit" or "button"タグセレクタのnameの名前を持つ
     * ボタンクリックイベント
     * @param name ボタンのテキスト(value)を指定する
     * @param type ボタンのタイプ(button or submitを期待)を指定する
     * @param index ボタンの配列の順番を指定する(1 origin)
     */
    @もし("名前が\"([^\"]*)\"の\"([^\"]*)\"番目のボタンをクリックする$")
    public void index_button_click(String value, String type, int index ) {
        connector.btnByblockClickAndWait(type, value, index-1);
    }

/** チェックボックスのクリックイベント
 * @throws InterruptedException */
    @もし("チェックボックス\"([^\"]*)\"をクリックする$")
    public void checkBox_click(String check) throws InterruptedException {
    	connector.checkBoxClick(check);
    }

/** ドロップダウンメニュー */
    @もし("ドロップダウン\"([^\"]*)\"から\"([^\"]*)\"を選択する$")
    public void dropDown_select(String dropDown, String selText) throws InterruptedException {
    	connector.dropDownSelect(dropDown, selText);;
    }


/** スクリーンショット */
//    @ならば("ファイル名「([^\"]*)」でスクリーンショットを保存する$")
//    public void screen_shot(String filename) {
//        connector.getScreenShot(filename);
//    }

//    @もし("画面に「([^\"]*)」と表示されていなければ、ファイル名「([^\"]*)」でスクリーンショットを保存する$")
//    public void not_indicated_check(String pattern, String filename) {
//        if(!connector.isTextPresent(pattern)) {
//            connector.getScreenShot(filename);
//            connector.destroySelenium();
//        }
//    }

/** 入力系 */
    /**
     * id or nameのセレクタに文字を入力する
     * @param selector id or name セレクタ名
     * @param val 入力する値
     */
    @もし("\"([^\"]*)\"要素に\"([^\"]*)\"と入力する$")
    public void input_element(String selector, String val) {
        connector.inputAndWait(selector,val);
    }

    /**
     * 入力要素に Enter キーを入力する
     * @param selector id or name セレクタ名
     */
    @もし("\"([^\"]*)\"要素にEnterを入力する$")
    public void input_enter_element(String selector) {
        connector.inputEnterAndWait(selector);
    }

    /**
     * 入力要素に Enter キーを入力する
     * @param selector id or name セレクタ名
     */
     @かつ("かつ\"([^\"]*)\"要素にEnterを入力する$")
    public void and_enter_element(String selector) {
        connector.inputEnterAndWait(selector);
    }

    /**
     * id or nameのセレクタに文字を入力する
     * @param selector id or name セレクタ名
     * @param val 入力する値
     */
    @かつ("かつ\"([^\"]*)\"要素に\"([^\"]*)\"と入力する$")
    public void and_input_element(String selector, String val) {
        connector.inputAndWait(selector,val);
    }


    @もし("シナリオを終了してブラウザを閉じる$")
    public void close() {
        connector.destroySelenium();
    }
}
