package crawler.example;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.abola.crawler.CrawlerPack;


/**
 * 資料探索練習 Facebook Graph Api Search 
 * 
 * 重點
 * 1. 利用Graph Api調整出需要的資料
 * 2. 取得一組Access Token (試著使用 long term token)
 * 3. 試著用『excel』或任何最簡易的方式，對資料進行探索
 * 
 * @author Abola Lee
 *
 */
public class FacebookExample {
	
	public static void main(String[] args) {
		
		// 遠端資料路徑
		// [query sample]
		// search?q=世大運&type=page&limit=1000&fields=name,id,likes,talking_about_count
		String uri = 
				"https://graph.facebook.com/v2.5"
				+ "/search?q=%e4%b8%96%e5%a4%a7%e9%81%8b&type=page&limit=1000&fields=name,id,likes,talking_about_count"
				+ "&access_token=EAACEdEose0cBAGd4HzAKfh5hS2JKjXijvhkIFSUKtVpMdIiEK24B8ENXfRVV9SUNyIXec5EclLzH1KbLPZAQZBx59hrZCHnVI6aZCrEZBgtr0uvKI3ALrZBzzL0jZBZCSyWOuOi1rEu5VMTJESwzX9cBwdmEpqEFhBlYqFIuXl4gVZBrd1JuufqPFi0DhZBbjRGYCBTzMBz0Q30wZDZD";



		// Jsoup select 後回傳的是  Elements 物件
//		[data sample]
//		----
//		{
//		"data": [
//		{
//			"name": "Taipei 2017 Universiade - 世大運",
//				"id": "157215971120682",
//				"likes": 419693,
//				"talking_about_count": 464907
//		},
//		{
//			"name": "2017世界大學運動會 (世大運)",
//				"id": "347690225327950",
//				"likes": 4514,
//				"talking_about_count": 702
//		},
//		{
//			"name": "世大運",
//				"id": "399198176891474",
//				"likes": 95,
//				"talking_about_count": 0
//		},
//		{
//			"name": "Around Taiwan 世大運台旅通",
//				"id": "307923779667068",
//				"likes": 140,
//				"talking_about_count": 27
//		},
//		{
//			"name": "世大運項目",
//				"id": "734096279996790",
//				"likes": 119,
//				"talking_about_count": 0
//		},
		Elements elems =
				CrawlerPack.start()
				.getFromJson(uri)
				.select("data");

		String output = "id,名稱,按讚數,討論人數\n";
		
		// 遂筆處理
		for( Element data: elems ){
			String id = data.select("id").text();
			String name = data.select("name").text();
			String likes = data.select("likes").text();
			String talking_about_count = data.select("talking_about_count").text();
			
			output += id+",\""+name+"\","+likes+","+talking_about_count+"\n";
		}
		
		System.out.println( output );
	} 
}
