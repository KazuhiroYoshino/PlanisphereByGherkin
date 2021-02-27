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

フィーチャ: 予約プラン一覧ページ

	背景: Guestユーザーは、一般会員またはプレミアム会員と違い、プレミアムプラン / ディナー付きプラン / お得なプランは利用できない

  シナリオ: テスト設定
  	前提 Webドライバは"Chrome"を選択する
#Hotel Planisphere TOPページを開く
    前提 ページ"https://hotel.testplanisphere.dev/ja/index.html"を表示する

    もし  Windowを最大化する

	シナリオ: Guestユーザーの場合
		もし	リンクテキスト"宿泊予約"をクリックする

	シナリオアウトライン: 予約メニュー一覧を検証
		ならば    表示"card-title"のプラン名が<プラン名>については表示<表示>である
		例:
		|プラン名                  |表示|
		|"お得な特典付きプラン"    |"yes"|
		|"プレミアムプラン"        |"no"|
		|"ディナー付きプラン"      |"no"|
		|"お得なプラン"            |"no"|
		|"素泊まり"                |"yes"|
		|"出張ビジネスプラン"      |"yes"|
		|"エステ・マッサージプラン"|"yes"|
		|"貸し切り露天風呂プラン"  |"yes"|
		|"カップル限定プラン"      |"yes"|
		|"テーマパーク優待プラン"  |"yes"|

	シナリオ: ホーム画面にもどる
	  もし  HOME画面にもどる

	シナリオ: 一般会員の場合
		もし  リンクテキスト"ログイン"をクリックする
		もし  "email"要素に"sakura@example.com"と入力する
    もし  "password"要素に"pass1234"と入力する
    ならば  IDセレクタ名が"login-button"のボタンをクリックする
		もし	リンクテキスト"宿泊予約"をクリックする

	シナリオアウトライン: 予約メニュー一覧を検証
		ならば    プラン名が<プラン名>については表示<表示>である
		例:
		|プラン名                  |表示|
		|"お得な特典付きプラン"    |"yes"|
		|"プレミアムプラン"        |"no"|
		|"ディナー付きプラン"      |"yes"|
		|"お得なプラン"            |"yes"|
		|"素泊まり"                |"yes"|
		|"出張ビジネスプラン"      |"yes"|
		|"エステ・マッサージプラン"|"yes"|
		|"貸し切り露天風呂プラン"  |"yes"|
		|"カップル限定プラン"      |"yes"|
		|"テーマパーク優待プラン"  |"yes"|

	シナリオ: ホーム画面にもどる
	  もし  HOME画面にもどる

	シナリオ: プレミアム会員の場合
		もし  リンクテキスト"ログイン"をクリックする
		もし  "email"要素に"ichiro@example.com"と入力する
    もし  "password"要素に"password"と入力する
    ならば  IDセレクタ名が"login-button"のボタンをクリックする
		もし	リンクテキスト"宿泊予約"をクリックする

	シナリオアウトライン: 予約メニュー一覧を検証
		ならば    プラン名が<プラン名>については表示<表示>である
		例:
		|プラン名                  |表示|
		|"お得な特典付きプラン"    |"yes"|
		|"プレミアムプラン"        |"yes"|
		|"ディナー付きプラン"      |"yes"|
		|"お得なプラン"            |"yes"|
		|"素泊まり"                |"yes"|
		|"出張ビジネスプラン"      |"yes"|
		|"エステ・マッサージプラン"|"yes"|
		|"貸し切り露天風呂プラン"  |"yes"|
		|"カップル限定プラン"      |"yes"|
		|"テーマパーク優待プラン"  |"yes"|

	シナリオ: テスト終了
        #終了処理
    もし シナリオを終了してブラウザを閉じる
#おしまい