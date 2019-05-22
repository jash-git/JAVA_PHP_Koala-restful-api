<?php
	function GetAllHeader()
	{
		// 忽略获取的header数据。这个函数后面会用到。主要是起过滤作用
		$ignore = array('host','accept','content-length','content-type'); 
		$headers = array();
		//这里大家有兴趣的话，可以打印一下。会出来很多的header头信息。咱们想要的部分，都是‘http_'开头的。所以下面会进行过滤输出。
		/*var_dump($_SERVER);
		exit;*/
 
		foreach($_SERVER as $key=>$value){
			if(substr($key, 0, 5)==='HTTP_'){
				//这里取到的都是'http_'开头的数据。
				//前去开头的前5位
				$key = substr($key, 5);
				//把$key中的'_'下划线都替换为空字符串
				$key = str_replace('_', ' ', $key);
				//再把$key中的空字符串替换成‘-’
				$key = str_replace(' ', '-', $key);
				//把$key中的所有字符转换为小写
				$key = strtolower($key);
 
				//这里主要是过滤上面写的$ignore数组中的数据
				if(!in_array($key, $ignore)){
					$headers[$key] = $value;
				}
			}
		}
		//输出获取到的header
		return $headers;
	}
	//header('Content-Type: application/json; charset=UTF-8'); //設定資料類型為 json，編碼 utf-8
	set_time_limit(0);//確保不會超時
	date_default_timezone_set("Asia/Taipei");
	$filename="client_header_".date('Y-m-d-H-i-s').".txt";
	$myfile = fopen($filename, "w") or die("Unable to open file!");
	foreach (getallheaders() as $name => $value) {
		fwrite($myfile, "$name: $value\n");
	}	  
	fwrite($myfile, "\n=========================\n\n");
	foreach (GetAllHeader() as $name => $value) {
		fwrite($myfile, "$name: $value\n");	
	}
	fwrite($myfile, "\n=========================\n\n");
	foreach($_SERVER as $key=>$value){
		fwrite($myfile, "$key: $value\n");		
	}
	fwrite($myfile, "\n=========================\n\n");
	
	$data = file_get_contents("php://input");//接收client set json data
	fwrite($myfile, "client set json data: $data\n");	
	
	fwrite($myfile, "\n=========================\n\n");
	
	$Subarraydata=array();
	$Subarraydata["id"]=200;
	
	$arraydata=array();
	$arraydata["code"]=0;
	$arraydata["data"]=$Subarraydata;
	
	echo json_encode($arraydata);	
	fclose($myfile);
?>