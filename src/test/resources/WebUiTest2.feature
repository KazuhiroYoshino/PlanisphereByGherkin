#language: ja
機能: Hotel Planisphere 会員登録テスト

    シナリオ: 動作初期設定
#        Given スクリーンショット保管場所は「C:\PlanisphereTestResults」
#Webドライバ ： "IE", "Edge", "FireFox", "Opera", "Chrome" のいづれかを指定

        前提 Webドライバは"Chrome"を選択する

#Hotel Planisphere TOPページを開く
        前提 ページ"https://hotel.testplanisphere.dev/ja/index.html"を表示する

        もし  Windowを最大化する
#        ならば        "2"秒待つ
#@tag2
    シナリオ: 会員登録テスト_01
    		#会員登録ページに移動
    		もし            リンクテキスト"会員登録"をクリックする

        #登録操作
        もし            "email"要素に"mogu@sky.hi-ho.ne.jp"と入力する
        もし            "password"要素に"mogumogu1211"と入力する
        もし            "password-confirmation"要素に"mogumogu1211"と入力する
        もし            "username"要素に"吉野和宏"と入力する
        もし            チェックボックス"rank-premium"をクリックする
        もし            "address"要素に"奈良県葛城市"と入力する
        もし            "tel"要素に"07456991267"と入力する
        もし            ドロップダウン"gender"から"男性"を選択する
        もし            チェックボックス"notification"をクリックする

        ならば        "2"秒待つ


        #終了処理
        もし            シナリオを終了してブラウザを閉じる

#おしまい