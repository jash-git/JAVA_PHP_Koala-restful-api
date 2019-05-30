JAVA_PHP_Koala HW模擬程式 [JAVA 和 PHP 實現 restful api 通訊]


參考資料:http://techsupport.megvii.com/hc/kb/article/178244/

GITHUB: https://github.com/jash-git/JAVA_PHP_Koala-restful-api


目標:按照參考資料撰寫對應的『JAVA Client』 和對應的 『模擬用PHP SERVER』

PHP[authLogin API 模擬]~koala_authLogin.php
	01.抓取/紀錄(寫入檔案)所以Client的所有Header資訊
	02.抓取/紀錄(寫入檔案)Client傳送過來的JSON資料
	03.撰寫輸出對應JSON資訊，達到Client可以判斷並認為登入成功

JAVA[呼叫 authLogin API]
	01.按照參考資料建立 authLogin 函數
	
使用C# 用 JAVA 為參照 實際測試真實API 可以得到下面結果
	{"code":0,"data":{"avatar":"","company":{"attendance_on":true,"attendance_weekdays":[1,2,3,4,5],"consigner":"\u66f2","create_time":1558543636,"data_version":1,"deployment":1,"door_range":[[0,0],[24,0]],"door_weekdays":[1,2,3,4,5,6,7],"feature_version":7,"fmp_on":false,"full_day":true,"id":1,"logo":"/static/images/logo.png","name":"\u5ba2\u6237","notdetermined_on":true,"remark":"","scenario":"\u6b63\u5e38\u4f7f\u7528","upload":true,"yellowlist_warn":true},"company_id":1,"id":2,"organization_id":null,"password_reseted":true,"permission":[],"role_id":2,"username":"test@admin.com","verify":false},"page":{}}	