#language: ja

#フィーチャ: Hotel Planisphere 会員登録テスト
#    背景:動作初期設定
#        前提        スクリーンショット保管場所「C:\PlanisphereTestResults」
        #Webドライバ ： "IE", "Edge", "FireFox", "Opera", "Chrome" のいづれかを指定

#        前提 Webドライバ「Chrome」を選択する

        #Hotel Planisphere TOPページを開く
#        前提 ページ「https://hotel.testplanisphere.dev/ja/index.html」を表示する

#        ならば Windowを最大化する

#    シナリオ: 会員登録テスト_01

    		#会員登録ページに移動
#    		もし            リンクテキスト「会員登録」をクリックする

        #登録操作
#        かつ            「email」要素に「mogu@sky.hi-ho.ne.jp」と入力する
#        かつ            「password」要素に「mogumogu1211」と入力する
#        かつ            「password-confirmation」要素に「mogumogu1211」と入力する
#        かつ            「username」要素に「吉野和宏」と入力する
#        かつ            チェックボックス「premium」クリックする
#        かつ            「address」要素に「奈良県葛城市」と入力する
#        かつ            「tel」要素に「07456991267」と入力する
#        かつ            ドロップダウン「gender」から「男性」を選択する
#        かつ            チェックボックス「notification」クリックする

#        ならば        「2」秒待つ


        #終了処理
#        もし            シナリオを終了してブラウザを閉じる

#おしまい