#language: ja

フィーチャ: Hotel Planisphere 会員登録テスト
    背景:動作初期設定
        前提        スクリーンショット保管場所「C:\PlanisphereTestResults」
        #Webドライバ ： "IE", "Edge", "FireFox", "Opera", "Chrome" のいづれかを指定

        前提        Webドライバ「Chrome」を選択する

        #Hotel Planisphere TOPページを開く
        前提        ページ「https://hotel.testplanisphere.dev/ja/index.html」を表示する

        ならば  Windowを最大化する

    シナリオ: 会員登録テスト_01

    		#会員登録ページに移動
    		もし            リンクテキスト「会員登録」をクリックする

        #登録操作
        もし            「email」要素に「mogu@sky.hi-ho.ne.jp」と入力する
        もし            「password」要素に「mogumogu1211」と入力する
        もし            「password-confirmation」要素に「mogumogu1211」と入力する
        もし            「username」要素に「吉野和宏」と入力する
        かつ            「password-confirmation」要素にEnterを入力する
        もし             リンクテキスト「Spring-MVCの散歩道 Eclipse,Spring-MVC,Thymeleaf,MyBatis による ...」をクリックする
        ならば        「2」秒待つ

        #Spring-MVCの散歩道が表示されている（はず）
        もし            ラベル「■Eclipse の小径」をクリックする
        ならば       「2」秒待つ

        #左側メニューを選択してクリックする
        もし            リンクテキスト「玄関」をクリックする
        ならば       「2」秒待つ

        #左側メニューを選択してクリックする
        もし            リンクテキスト「１－１）EclipseのDL/Install」をクリックする
        ならば       「2」秒待つ

        #左側メニューを選択してクリックする
        もし            リンクテキスト「１－２）JDKのDL/Install」をクリックする
        ならば       「2」秒待つ

        #左側メニューを選択してクリックする
        もし            リンクテキスト「１－３）TomcatのDL/Install」をクリックする
        ならば       「2」秒待つ

        #終了処理
        もし            シナリオを終了してブラウザを閉じる

#おしまい