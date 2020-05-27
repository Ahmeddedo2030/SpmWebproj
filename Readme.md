# SpmWebProjct--Data Analyse with Weka

## 静态网页。大致分为以下部分：(路径在*WebContent下*)

>* index *(Baudas 公司的主页，包含了我们的团队介绍和公司简介)
>* login *(简单的登录界面，密码和用户名都是admin)
>* startseite *(分成两个部分，1. 登录之后，选择本地文件上传（*.csv）  2. 然后进入按钮进入数据分析界面)
>* Display *(使用E-Charts js,将分析结果使用图表展示)


----------


### 简单的Servlet + jsp + Weka
*Weka 的功能只使用了Instance，将数据按属性或者行读取出来，存入ArrayList.自己写了排序*
*******

>* 通过营业额排序寻找购物的最佳（坏）星期和小时

>* 通过营业额排序寻找卖得最多（少）的商品

>* 通过利润率排序寻找卖得最多（少）的商品

>* 根据csv文件收集分类用户的基本信息（性别，年龄，居住地等）



----------



### 添加了一个简单的单元测试（Junit）
*用来测试核心的数据分析结果*




**********
----------
**********



## Statische Seiten. Es ist grob in folgende Teile unterteilt: (Der Pfad befindet sich unter * WebContent *)

>* index *(Baudas Firmenhomepage, einschließlich unserer Teameinführung und unseres Firmenprofils)
>* login *(Einfache Login-Oberfläche, Passwort und Benutzername sind admin)
>* startseite *(in zwei Teile unterteilt: 1. Wählen Sie nach dem Anmelden den lokalen Datei-Upload aus (* .csv). 2. Geben Sie dann die Schaltfläche ein, um die Datenanalyse-Oberfläche aufzurufen.)
>* display *(mit E-Charts js werden die Analyseergebnisse mit Hilfe von Diagrammen angezeigt)


----------


### Einfaches Servlet + jsp + Weka
*Wekas(Funktion) verwendet nur Instanz, liest die Daten nach Attributen oder Zeilen und speichert sie in ArrayList. Wir schreiben die Funktion(sortieren) selbst.*
*******

>* Finden die beste (schlechte) Woche und Stunde des Einkaufs nach Umsatzbestellung

>* Finden die meistverkauften (weniger) Produkte nach Umsatzauftrag

>* Finden die meistverkauften (weniger) Produkte durch Gewinnsortierung

>* Sammeln grundlegende Benutzerinformationen (Geschlecht, Alter, Wohnort usw.) basierend auf der CSV-Datei



----------



### Ein einfacher Unit-Test wurde hinzugefügt (Junit)
*Wird zum Testen der Ergebnisse der Kerndatenanalyse verwendet*

 
