using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.Web;
using System.Net;
using System.IO;//CookieContainer
namespace CS_login_KOALA
{
    class Program
    {
        static String m_StrDomain;
        static String m_StrResponse;
        static CookieContainer m_CookieContainer;
        static void pause()
        {
            Console.Write("Press any key to continue...");
            Console.ReadKey(true);
        }
        static void Main(string[] args)
        {
            m_StrDomain = "http://192.168.1.237/";//m_StrDomain = "http://localhost:8080/cs2php/";
            m_StrResponse = "";
            m_CookieContainer = new CookieContainer();
            string url = m_StrDomain + "auth/login";
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.Method = "POST";

            //--
            //request.ContentType = "application/x-www-form-urlencoded";//一般POST
            request.ContentType = "application/json";//POST to AJAX [is_ajax_request()]
            request.Accept = "application/json, text/javascript";//POST to AJAX [is_ajax_request()]
            request.UserAgent = "Koala Admin";//POST to AJAX [is_ajax_request()]
            request.Headers.Add("X-Requested-With", "XMLHttpRequest");//POST to AJAX [is_ajax_request()]
            //--
            string data = "{ \"password\":\"123456\",\"username\":\"test@admin.com\"}";
            request.ContentLength = data.Length;
            StreamWriter writer = new StreamWriter(request.GetRequestStream(), Encoding.ASCII);
            writer.Write(data);
            writer.Flush();
            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            string encoding = response.ContentEncoding;
            if (encoding == null || encoding.Length < 1)
            {
                encoding = "UTF-8"; //默认编码
            }

            StreamReader reader = new StreamReader(response.GetResponseStream(), Encoding.GetEncoding(encoding));
            data = reader.ReadToEnd();

            m_CookieContainer = request.CookieContainer;
            response.Close();

            Console.WriteLine(data);
            pause();
        }
    }
}
