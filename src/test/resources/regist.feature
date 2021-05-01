#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
#language: ja
フィーチャ: Hotel Planisphere 会員登録機能

    シナリオ: 会員登録ページを開く
        前提 Webドライバは"Chrome"を選択する
#Hotel Planisphere TOPページを開く
        前提 ページ"https://hotel.testplanisphere.dev/ja/index.html"を表示する
        もし  Windowを最大化する
        ならば "2"秒待つ

		シナリオアウトライン: 登録とログインと退会を行う
    		もし   リンクテキスト"会員登録"をクリックする
#登録操作
        かつ  メールアドレスに<メルアド>を入力して
        かつ  パスワードに<パスワード>を入力して
        かつ  パスワード再入力に<繰り返し>を入力して
        かつ  氏名に<氏名>を入力して
        かつ  ランクを<ランク>として
        かつ  住所を<住所>として
        かつ  電話番号を<電話>として
        かつ  性別を<性別>として
        かつ  生年月日を<生年月日>として
        かつ  お知らせの受取りを<お知らせ>として
        もし  登録ボタンを押す
        もし ログアウトする
        ならば "1"秒待つ

#登録したアカウントでログイン
        もし  リンクテキスト"ログイン"をクリックする
        かつ  メールアドレスに<メルアド>を入力して
        かつ  パスワードに<パスワード>を入力して
        もし  ログインボタンをクリックする
				ならば  氏名が<氏名>と表示されること
				ならば  会員ランクが<ランク>と表示されること
				ならば  住所が<住所>と表示されること
				ならば  電話番号が<電話>と表示されること
				ならば  性別が<性別>と表示されること
				ならば  生年月日が<生年月日>と表示されること
				ならば  お知らせが<お知らせ>と表示されること

#退会操作
        もし  退会ボタンを押す
        ならば  ポップアップ表示に"退会すると全ての情報が削除されます。"と表示される
        ならば  ポップアップ表示に"退会処理を完了しました。ご利用ありがとうございました。"と表示される

        ならば "2"秒待つ

				例:
				|メルアド             |パスワード|繰り返し  |氏名        |ランク          |住所          |電話         |性別        |生年月日    |お知らせ      |
				|"harunobu@example.jp"|"password"|"password"|"武田晴信"  |"プレミアム会員"|"奈良県葛城市"|"01234567890"|"男性"      |""          |"受け取る"    |
				|"kagetora@example.jp"|"pass1234"|"pass1234"|"長尾景虎"  |"プレミアム会員"|"奈良県葛城市"|"02345678901"|"女性"      |"1960/12/11"|"受け取らない"|
				|"kanta@example.jp"   |"passwd00"|"passwd00"|"山本寛太"  |"一般会員"      |""            |"03456789012"|"女性"      |"1960/12/11"|"受け取る"    |
				|"aiko@example.jp"    |"passpass"|"passpass"|"直江愛子"  |"一般会員"      |"奈良県葛城市"|""           |"女性"      |"1960/12/11"|"受け取らない"|
				|"masatora@example.jp"|"pass5678"|"pass5678"|"真田昌虎"  |"一般会員"      |"奈良県葛城市"|"04567890123"|"回答しない"|"1960/12/11"|"受け取る"    |
				|"teruhide@example.jp"|"PassWord"|"PassWord"|"宇佐美輝秀"|"一般会員"      |"奈良県葛城市"|"05678901234"|"女性"      |""          |"受け取らない"|
				|"nobuko@example.jp"  |"PSWORDDD"|"PSWORDDD"|"武田信子"  |"一般会員"      |""            |""           |"その他"    |""          |"受け取る"    |


	シナリオアウトライン: 登録済ユーザ確認
	      もし  リンクテキスト"ログイン"をクリックする
        かつ  メールアドレスに<メルアド>を入力して
        かつ  パスワードに<パスワード>を入力して
        もし  ログインボタンをクリックする
				ならば  氏名が<氏名>と表示されること
				ならば  会員ランクが<ランク>と表示されること
				ならば  住所が<住所>と表示されること
				ならば  電話番号が<電話>と表示されること
				ならば  性別が<性別>と表示されること
				ならば  生年月日が<生年月日>と表示されること
				ならば  お知らせが<お知らせ>と表示されること
        もし ログアウトする
        ならば "1"秒待つ

					例:
				|メルアド             |パスワード |繰り返し   |氏名        |ランク          |住所                          |電話         |性別    |生年月日    |お知らせ      |
				|"ichiro@example.com" |"password" |"password" |"山田一郎"  |"プレミアム会員"|"東京都豊島区池袋"            |"01234567891"|"男性"  |""          |"受け取る"|
				|"jun@example.com"    |"pa55w0rd!"|"pa55w0rd!"|"林潤"      |"プレミアム会員"|"大阪府大阪市北区梅田"        |"01212341234"|"その他"|"1988/12/17"|"受け取らない"|
				|"sakura@example.com" |"pass1234" |"pass1234" |"松本さくら"|"一般会員"      |"神奈川県横浜市鶴見区大黒ふ頭"|""           |"女性"  |"2000/04/01"|"受け取らない"|
				|"yoshiki@example.com"|"pass-pass"|"pass-pass"|"木村良樹"  |"一般会員"      |""                            |"01298765432"|""      |"1992/08/31"|"受け取る"|

	シナリオ: テスト終了
#終了処理
        もし シナリオを終了してブラウザを閉じる
