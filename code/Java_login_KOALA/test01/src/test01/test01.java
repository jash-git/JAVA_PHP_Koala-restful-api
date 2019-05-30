package test01;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class test01 {

    public static CookieStore authLogin(String url,String username,String password) throws Exception {
        /**
    	    * http://techsupport.megvii.com/hc/kb/article/178244/
    	    * 登录 获取 Cookie
    	    * @param url API地址
    	    * @param username 账号, 注意不要使用admin@megvii.com
    	    * @param password 密码
    	    * @return cookie CookieStore
    	    * @throws Exception 
    	*/    	
        System.out.println("Start /auth/login to ...");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost request = new HttpPost(url);
         
        //设置user-agent为 "Koala Admin" 
        //设置Content-Type为 "application/json"
        request.setHeader("User-Agent", "Koala Admin");
        request.setHeader("Content-Type", "application/json");
         
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", password);
         
        request.setEntity(new StringEntity(json.toString(), "UTF-8"));
         
        //发起网络请求，获取结果值
        HttpClientContext context = HttpClientContext.create();
        System.out.println("Send data:"+json.toString());
        CloseableHttpResponse response = httpclient.execute(request, context);  
        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
        
        //---
        //A JSONObject text must begin with '{' at 1 [character 2 line 1]
        //https://blog.csdn.net/u012860950/article/details/77884515
        int i = responseBody.indexOf("{");
        responseBody = responseBody.substring(i);
        //---A JSONObject text must begin with '{' at 1 [character 2 line 1]
        System.out.println("Get data:"+responseBody);

       
        //解析JSON数据
        JSONObject resp = new JSONObject(responseBody);
        int result = resp.optInt("code", -1);
        if (result != 0) {
            System.err.println("Login failed, code:" + result);
        }else{
            System.out.println("Login Success,id:" + resp.getJSONObject("data").getInt("id"));
            return  context.getCookieStore();
        }
        return null;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Hello World!\n");
		try {
			authLogin("http://192.168.1.237/auth/login","test@admin.com","123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
