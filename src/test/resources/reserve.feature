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

	シナリオアウトライン: Guestユーザーの場合の宿泊料金検証テストケース
		もし	リンクテキスト"宿泊予約"をクリックする
		ならば  親子タブを取得する
		ならば  子タブに切り替える

		もし  宿泊プランを<宿泊プラン>にして
		もし  宿泊初日の曜日を"date"に<宿泊初日>として
		もし  "//div[@id='ui-datepicker-div']/div[2]/button"で指定できるカレンダー表示を消して
		もし  連泊数を"term"で<連泊数>にして
		もし  宿泊人数を"head-count"で<宿泊人数>にして
		もし  朝食バイキング有無を"breakfast"で<朝食>にして
		もし  昼からチェックインプランを"early-check-in"で<昼からチェックインプラン>にして
		もし  お得な観光プランを"sightseeing"で<お得な観光プラン>にして
		もし  氏名を"username"で<氏名>として
		もし  連絡手段を"contact"で<連絡手段>にして
		もし  電話番号を"tel"で<電話番号>にして
		もし  メールアドレスを"email"で<メルアド>にして
		もし  連絡事項を"comment"で<連絡事項>にして
		もし  予約内容を記録して
		ならば  合計金額は"total-bill"に表示される<合計金額>となり
		ならば  部屋タイプは"room-info"に表示される<部屋タイプ>となり

		ならば  IDセレクタ名が"submit-button"のボタンをクリックする

		ならば  合計金額は"total-bill"に表示される<合計金額>となり
		ならば  宿泊期間の表示が正しく
		ならば  宿泊人数の表示が"head-count"で<宿泊人数>名様となり
		ならば  追加プラン1はCSSセレクタ<プラン1>の表示が正しく
		ならば  追加プラン2はCSSセレクタ<プラン2>の表示が正しく
		ならば  追加プラン3はCSSセレクタ<プラン3>の表示が正しく
		ならば  氏名の表示"username"で<氏名>様となり
		ならば  確認の連絡の表示が"contact"で正しく
		ならば  連絡事項の表示が"comment"で正しく

		ならば  CSSセレクタ名が"//button[@type='button']"のボタンをクリックする

		ならば  ポップアップ表示の"//div[@id='success-modal']/div/div/div/h5"は"予約を完了しました"になり
		ならば  ポップアップ表示の"//div[@id='success-modal']/div/div/div[2]/p"は"ご来館、心よりお待ちしております。"になり

		ならば  親タブに切り替える
		ならば  画面更新

		例:
		|宿泊プラン                |宿泊初日|連泊数|宿泊人数|朝食|昼からチェックインプラン|お得な観光プラン|氏名      |連絡手段        |電話番号     |メルアド|連絡事項 |部屋タイプ    |合計金額|プラン1                    |プラン2                    |プラン3|
		|"エステ・マッサージプラン"|"FRIDAY"|"9"   |"6"     |"on"|"on"                    |"off"           |"吉野和宏"|"電話でのご連絡"|"12345678901"|""      |"144文字"|"部屋指定なし"|"586500"|"//*[@id="plans"]/ul/li[1]"|"//*[@id="plans"]/ul/li[2]"|""|

	シナリオ: ホーム画面にもどる
	  もし  HOME画面にもどる

	シナリオ: 一般会員の場合
		もし  リンクテキスト"ログイン"をクリックする
		もし  "email"要素に"sakura@example.com"と入力する
    もし  "password"要素に"pass1234"と入力する
    ならば  IDセレクタ名が"login-button"のボタンをクリックする
		もし	リンクテキスト"宿泊予約"をクリックする

	シナリオアウトライン: 一般会員ユーザーの場合の宿泊料金検証テストケース
		もし	リンクテキスト"宿泊予約"をクリックする
		ならば  親子タブを取得する
		ならば  子タブに切り替える

		もし  宿泊プランを<宿泊プラン>にして
		もし  宿泊初日の曜日を"date"に<宿泊初日>として
		もし  "//div[@id='ui-datepicker-div']/div[2]/button"で指定できるカレンダー表示を消して
		もし  連泊数を"term"で<連泊数>にして
		もし  宿泊人数を"head-count"で<宿泊人数>にして
		もし  朝食バイキング有無を"breakfast"で<朝食>にして
		もし  昼からチェックインプランを"early-check-in"で<昼からチェックインプラン>にして
		もし  お得な観光プランを"sightseeing"で<お得な観光プラン>にして
		もし  氏名を"username"で<氏名>として
		もし  連絡手段を"contact"で<連絡手段>にして
		もし  電話番号を"tel"で<電話番号>にして
		もし  メールアドレスを"email"で<メルアド>にして
		もし  連絡事項を"comment"で<連絡事項>にして
		もし  予約内容を記録して
		ならば  合計金額は"total-bill"に表示される<合計金額>となり
		ならば  部屋タイプは"room-info"に表示される<部屋タイプ>となり

		ならば  IDセレクタ名が"submit-button"のボタンをクリックする

		ならば  合計金額は"total-bill"に表示される<合計金額>となり
		ならば  宿泊期間の表示が正しく
		ならば  宿泊人数の表示が"head-count"で<宿泊人数>名様となり
		ならば  追加プラン1はCSSセレクタ<プラン1>の表示が正しく
		ならば  追加プラン2はCSSセレクタ<プラン2>の表示が正しく
		ならば  追加プラン3はCSSセレクタ<プラン3>の表示が正しく
		ならば  氏名の表示"username"で<氏名>様となり
		ならば  確認の連絡の表示が"contact"で正しく
		ならば  連絡事項の表示が"comment"で正しく

		ならば  CSSセレクタ名が"//button[@type='button']"のボタンをクリックする

		ならば  ポップアップ表示の"//div[@id='success-modal']/div/div/div/h5"は"予約を完了しました"になり
		ならば  ポップアップ表示の"//div[@id='success-modal']/div/div/div[2]/p"は"ご来館、心よりお待ちしております。"になり

		ならば  親タブに切り替える
		ならば  画面更新

		例:
		|宿泊プラン                |宿泊初日|連泊数|宿泊人数|朝食 |昼からチェックインプラン|お得な観光プラン|氏名      |連絡手段          |電話番号     |メルアド|連絡事項 |部屋タイプ    |合計金額|プラン1                    |プラン2                    |プラン3|
		|"エステ・マッサージプラン"|"MONDAY"|"1"   |"6"     |"off"|"off"                   |"on"            |""        |"メールでのご連絡"|""           |""      |"144文字"|"部屋指定なし"|"60000" |"//*[@id="plans"]/ul/li"   |""                         |""     |

	シナリオ: ホーム画面にもどる
	  もし  HOME画面にもどる

	シナリオ: プレミアム会員の場合
		もし  リンクテキスト"ログイン"をクリックする
		もし  "email"要素に"ichiro@example.com"と入力する
    もし  "password"要素に"password"と入力する
    ならば  IDセレクタ名が"login-button"のボタンをクリックする
		もし	リンクテキスト"宿泊予約"をクリックする

	シナリオアウトライン: プレミアム会員ユーザーの場合の宿泊料金検証テストケース
		もし	リンクテキスト"宿泊予約"をクリックする
		ならば  親子タブを取得する
		ならば  子タブに切り替える

		もし  宿泊プランを<宿泊プラン>にして
		もし  宿泊初日の曜日を"date"に<宿泊初日>として
		もし  "//div[@id='ui-datepicker-div']/div[2]/button"で指定できるカレンダー表示を消して
		もし  連泊数を"term"で<連泊数>にして
		もし  宿泊人数を"head-count"で<宿泊人数>にして
		もし  朝食バイキング有無を"breakfast"で<朝食>にして
		もし  昼からチェックインプランを"early-check-in"で<昼からチェックインプラン>にして
		もし  お得な観光プランを"sightseeing"で<お得な観光プラン>にして
		もし  氏名を"username"で<氏名>として
		もし  連絡手段を"contact"で<連絡手段>にして
		もし  電話番号を"tel"で<電話番号>にして
		もし  メールアドレスを"email"で<メルアド>にして
		もし  連絡事項を"comment"で<連絡事項>にして
		もし  予約内容を記録して
		ならば  合計金額は"total-bill"に表示される<合計金額>となり
		ならば  部屋タイプは"room-info"に表示される<部屋タイプ>となり

		ならば  IDセレクタ名が"submit-button"のボタンをクリックする

		ならば  合計金額は"total-bill"に表示される<合計金額>となり
		ならば  宿泊期間の表示が正しく
		ならば  宿泊人数の表示が"head-count"で<宿泊人数>名様となり
		ならば  追加プラン1はCSSセレクタ<プラン1>の表示が正しく
		ならば  追加プラン2はCSSセレクタ<プラン2>の表示が正しく
		ならば  追加プラン3はCSSセレクタ<プラン3>の表示が正しく
		ならば  氏名の表示"username"で<氏名>様となり
		ならば  確認の連絡の表示が"contact"で正しく
		ならば  連絡事項の表示が"comment"で正しく

		ならば  CSSセレクタ名が"//button[@type='button']"のボタンをクリックする

		ならば  ポップアップ表示の"//div[@id='success-modal']/div/div/div/h5"は"予約を完了しました"になり
		ならば  ポップアップ表示の"//div[@id='success-modal']/div/div/div[2]/p"は"ご来館、心よりお待ちしております。"になり

		ならば  親タブに切り替える
		ならば  画面更新

		例:
		|宿泊プラン                |宿泊初日    |連泊数|宿泊人数|朝食 |昼からチェックインプラン|お得な観光プラン|氏名      |連絡手段          |電話番号     |メルアド|連絡事項 |部屋タイプ    |合計金額|プラン1                    |プラン2                    |プラン3|
		|"エステ・マッサージプラン"|"WedenesDay"|"9"   |"1"     |"on" |"on"                    |"off"           |""        |"電話でのご連絡"  |""           |""      |""       |"部屋指定なし"|"95500" |"//*[@id="plans"]/ul/li[1]"|"//*[@id="plans"]/ul/li[2]"|""     |

	シナリオ: ホーム画面にもどる
	  もし  HOME画面にもどる
	シナリオ: テスト終了
        #終了処理
    もし シナリオを終了してブラウザを閉じる
#おしまい