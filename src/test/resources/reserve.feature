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

フィーチャ: 予約ページ
  I want to use this template for my feature file
	背景: 宿泊初日の曜日と連泊数によって、週末料金アップ適用が異なる。オプションは別料金。連泊数・宿泊人数・オプションでトータル料金が決まる。


  シナリオ: テスト設定
  	前提 Webドライバは"Chrome"を選択する
#Hotel Planisphere TOPページを開く
    前提 ページ"https://hotel.testplanisphere.dev/ja/index.html"を表示する

    もし  Windowを最大化する

	シナリオ: Guestユーザーの場合
		もし	リンクテキスト"宿泊予約"をクリックする

		シナリオアウトライン: Guestユーザーの場合の宿泊料金検証テストケース
	シナリオ: ホーム画面にもどる
	  もし  HOME画面にもどる

	シナリオ: 一般会員の場合
		もし  リンクテキスト"ログイン"をクリックする
		もし  "email"要素に"sakura@example.com"と入力する
    もし  "password"要素に"pass1234"と入力する
    ならば  IDセレクタ名が"login-button"のボタンをクリックする
		もし	リンクテキスト"宿泊予約"をクリックする

		シナリオアウトライン: 一般会員ユーザーの場合の宿泊料金検証テストケース

		シナリオ: ホーム画面にもどる
	  もし  HOME画面にもどる

		シナリオ: プレミアム会員の場合
		もし  リンクテキスト"ログイン"をクリックする
		もし  "email"要素に"ichiro@example.com"と入力する
    もし  "password"要素に"password"と入力する
    ならば  IDセレクタ名が"login-button"のボタンをクリックする
		もし	リンクテキスト"宿泊予約"をクリックする

		シナリオアウトライン: プレミアム会員ユーザーの場合の宿泊料金検証テストケース
