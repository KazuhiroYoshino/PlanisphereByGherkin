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

/**  Window切り替え **/
    @ならば("親子タブを取得する$")
    public void parent() {
    	connector.setWindow();
    }

    @ならば("子タブに切り替える$")
    public void child() {
    	connector.setChild();
    }

    @ならば("画面更新$")
    public void refresh() throws InterruptedException {
    	connector.refresh();
    }

    @もし("\"([^\"]*)\"で指定できるカレンダー表示を消して")
    public void eraseCalendar(String name) throws InterruptedException {
    	connector.btnClickAndWait_X(name);
    }

    /**
* 表示結果のチェック
     * @param pattern 検索するテキスト
     */
    @ならば("画面に\"([^\"]*)\"と表示されていること$")
    public void search_text(String pattern) {
        assertTrue(connector.isTextPresent(pattern));
    }

    @ならば("ポップアップ表示に\"([^\"]*)\"と表示される$")
    public void search_popUp(String pattern) throws InterruptedException {
    	assertTrue(connector.isPopUpPresent(pattern));
    }

    @ならば("表示\"([^\"]*)\"のプラン名が\"([^\"]*)\"については表示\"([^\"]*)\"である$")
    public void test_ContentsList(String commandLocater, String planName, String hyouji) throws InterruptedException {
    	assertTrue(connector.checkContensList(commandLocater, planName, hyouji));
    }

    @ならば("合計金額は\"([^\"]*)\"に表示される\"([^\"]*)\"となり$")
    public void testPrice(String commanLocater, String price) {

    }

    @ならば("部屋タイプは\"([^\"]*)\"に表示される\"([^\"]*)\"となり$")
    public void testRoomType(String commandLocater, String roomType) {

    }

    @ならば("宿泊期間の表示が正しく$")
    public void testReserveTerm() {

    }

    @ならば("宿泊人数の表示が\"([^\"]*)\"で\"([^\"]*)\"名様となり$")
    public void testHeadCount(String commandLocater, String headcount) {

    }

    @ならば("追加プラン1はCSSセレクタ\"([^\"]*)\"の表示が正しく$")
    public void testOption1(String commandLocater) {

    }

    @ならば("追加プラン2はCSSセレクタ\"([^\"]*)\"の表示が正しく$")
    public void testOption2(String commandLocater) {

    }
    @ならば("追加プラン3はCSSセレクタ\"([^\"]*)\"の表示が正しく$")
    public void testOption3(String commandLocater) {

    }

    @ならば("氏名の表示\"([^\"]*)\"で\"([^\"]*)\"様となり$")
    public void testUsername(String commandLocater, String username) {

    }

    @ならば("確認の連絡の表示が\"([^\"]*)\"で正しく$")
    public void testContact(String commandLocater) {

    }

    @ならば("連絡事項の表示が\"([^\"]*)\"で正しく$")
    public void testComment(String commandLocater) {

    }

    @ならば("ポップアップ表示の\"([^\"]*)\"は\"([^\"]*)\"になり$")
    public void testPopUp(String commandLocater, String message) {

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

    @ならば("CSSセレクタ名が\"([^\"]*)\"のボタンをクリックする$")
    public void css_button_click(String commandLocater) throws InterruptedException {
    	connector.btnClickAndWait_CSS(commandLocater);
    }

    @ならば("IDセレクタ名が\"([^\"]*)\"のボタンをクリックする$")
    public void css_button_clickID(String commandLocater) throws InterruptedException {
    	connector.btnClickAndWait_ID(commandLocater);
    }

    @もし("CSSセレクタ名が\"([^\"]*)\"のボタンをクリックしてポップアップ表示を出す$")
    public void css_buttonClickAndPopUp(String commandLocater) throws InterruptedException {
    	connector.cssButtonClickAndPopUp(commandLocater);
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

    @もし("年月日要素\"([^\"]*)\"に\"([^\"]*)\"を入力する$")
    public void birthdayInput(String selector, String birthday) throws InterruptedException {
    	connector.birthdayInput(selector, birthday);
    }

    @もし("宿泊プランを\"([^\"]*)\"にして$")
    public void planSelect(String plan) {
    	String commandLocater;
    	switch(plan) {
    	case("お得な特典付きプラン"):
    		commandLocater = "./reserve.html?plan-id=0";
    		connector.clickHrefAndWait(commandLocater);
    		break;
    	case("プレミアムプラン"):
    		commandLocater = "./reserve.html?plan-id=1";
    		connector.clickHrefAndWait(commandLocater);
    		break;
    	case("ディナー付きプラン"):
    		commandLocater = "./reserve.html?plan-id=2";
    		connector.clickHrefAndWait(commandLocater);
    		break;
    	case("お得なプラン"):
    		commandLocater = "./reserve.html?plan-id=3";
    		connector.clickHrefAndWait(commandLocater);
    		break;
    	case("素泊まり"):
    		commandLocater = "./reserve.html?plan-id=4";
    		connector.clickHrefAndWait(commandLocater);
    		break;
    	case("出張ビジネスプラン"):
    		commandLocater = "./reserve.html?plan-id=5";
    		connector.clickHrefAndWait(commandLocater);
    		break;
    	case("エステ・マッサージプラン"):
    		commandLocater = "./reserve.html?plan-id=6";
    		connector.clickHrefAndWait(commandLocater);
    		break;
    	case("貸し切り露天風呂プラン"):
    		commandLocater = "./reserve.html?plan-id=7";
    		connector.clickHrefAndWait(commandLocater);
    		break;
    	case("カップル限定プラン"):
    		commandLocater = "./reserve.html?plan-id=8";
    		connector.clickHrefAndWait(commandLocater);
    		break;
    	case("テーマパーク優待プラン"):
    		commandLocater = "./reserve.html?plan-id=9";
    		connector.clickHrefAndWait(commandLocater);
    		break;
    	default:
    		commandLocater = "./reserve.html?plan-id=0";
    		connector.clickHrefAndWait(commandLocater);
    	}
    }

    @もし("宿泊初日の曜日を\"([^\"]*)\"に\"([^\"]*)\"として$")
    public void fromDay(String commandLocater, String startDay) throws InterruptedException {
    	switch(startDay) {
    	case("Sunday"):
            connector.sunday(commandLocater);
            connector.weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Monday"):
            connector.monday(commandLocater);
            connector.weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Tuesday"):
            connector.tuesday(commandLocater);
            connector.weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Wednesday"):
            connector.wednesday(commandLocater);
            connector.weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Thursday"):
            connector.thursday(commandLocater);
            connector.weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Friday"):
            connector.friday(commandLocater);
            connector.weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Saturday"):
            connector.saturday(commandLocater);
            connector.weekEnd = 0;
            connector.dateFromSet();
            break;
        default:
    	}

    }

    @もし("連泊数を\"([^\"]*)\"で\"([^\"]*)\"にして$")
    public void termSetting(String commandLocater, String termText) {
        connector.termValue = Integer.valueOf(termText) - connector.weekEnd;
        connector.termValueWeekEnd = connector.weekEnd;
        int term = connector.termValue + connector.termValueWeekEnd;
        connector.inputAndWait(commandLocater, termText);
        connector.termSet(term);
    }

    @もし("宿泊人数を\"([^\"]*)\"で\"([^\"]*)\"にして$")
    public void headSetting(String commandLocater, String headText) {

    }

    @もし("朝食バイキング有無を\"([^\"]*)\"で\"([^\"]*)\"にして$")
    public void breakFastSetting(String commandLocater, String breakfast) {

    }

    @もし("昼からチェックインプランを\"([^\"]*)\"で\"([^\"]*)\"にして$")
    public void earlySetting(String commanLocater, String earlyset) {

    }

    @もし("お得な観光プランを\"([^\"]*)\"で\"([^\"]*)\"にして$")
    public void sightSeeingSetting(String commandLocater, String seeing) {

    }

    @もし("氏名を\"([^\"]*)\"で\"([^\"]*)\"として$")
    public void nameSetting(String commandLocater, String name) {

    }

    @もし("連絡手段を\"([^\"]*)\"で\"([^\"]*)\"にして$")
    public void contactSetting(String commandLocater, String contact) {

    }

    @もし("電話番号を\"([^\"]*)\"で\"([^\"]*)\"にして$")
    public void telSetting(String commandLocater, String tel) {

    }

    @もし("メールアドレスを\"([^\"]*)\"で\"([^\"]*)\"にして$")
    public void mailSetting(String commandLocater, String email) {

    }

    @もし("連絡事項を\"([^\"]*)\"で\"([^\"]*)\"にして$")
    public void comentSetting(String commandLocater, String coment) {

    }

    @もし("予約内容を記録して$")
    public void reserveRec() {

    }

    @もし("シナリオを終了してブラウザを閉じる$")
    public void close() {
        connector.destroySelenium();
    }
}
