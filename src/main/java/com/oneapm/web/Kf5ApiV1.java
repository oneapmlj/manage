package com.oneapm.web;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;

public class Kf5ApiV1 {
	public static String gongdanCount(){
	      TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
	      //apiparamsMap.put("lijiang", "lijiang@oneapm.com");
	      //apiparamsMap.put("sign", "ef1cd5c3205104eceff100d7c57738");
	      //生成签名
	      String sign = Sdk.md5Signature(apiparamsMap);
	      apiparamsMap.put("sign", sign);
	      StringBuilder param = new StringBuilder();
	      for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
	      .iterator(); it.hasNext();) {
	          Map.Entry<String, String> e = it.next();
	          param.append("&").append(e.getKey()).append("=").append(e.getValue());
	      }
	      String str = param.toString().substring(1);
	      String result = Sdk.getResult("ticket/list", str);
	      JSONObject  dataJson=new JSONObject(result);
	      String num = dataJson.getString("count");
	      return num;
	    }
	 /**"ticket/list"
	 * @return 工单json列表
	 */
	public static String gongdanList(){
	      TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
	      //apiparamsMap.put("lijiang", "lijiang@oneapm.com");
	      //apiparamsMap.put("sign", "ef1cd5c3205104eceff100d7c57738");
	      String num = gongdanCount();
	      apiparamsMap.put("pagesize", num);
	      //生成签名
	      String sign = Sdk.md5Signature(apiparamsMap);
	      apiparamsMap.put("sign", sign);
	      StringBuilder param = new StringBuilder();
	      for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
	      .iterator(); it.hasNext();) {
	          Map.Entry<String, String> e = it.next();
	          param.append("&").append(e.getKey()).append("=").append(e.getValue());
	      }
	      return param.toString().substring(1);
	    }
	 /**工单查询"ticket/view"
	 * @param id
	 * @return	工单json查看
	 */
	public static String gongdanInquiry(String id){
	      TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
	      apiparamsMap.put("id", id);	      
	      //生成签名
	      String sign = Sdk.md5Signature(apiparamsMap);
	      apiparamsMap.put("sign", sign);
	      StringBuilder param = new StringBuilder();
	      for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
	      .iterator(); it.hasNext();) {
	          Map.Entry<String, String> e = it.next();
	          param.append("&").append(e.getKey()).append("=").append(e.getValue());
	      }
	      return param.toString().substring(1);
	    }
	/**工单添加"ticket/add"
	 * @param title
	 * @param content
	 * @param requester
	 * @return	
	 */
	public static String gongdanAdd(String title, String content, String requester){
	      TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
	      apiparamsMap.put("title", title);	
	      apiparamsMap.put("content", content);	 
	      apiparamsMap.put("requester", requester);		 
	      //生成签名
	      String sign = Sdk.md5Signature(apiparamsMap);
	      apiparamsMap.put("sign", sign);
	      StringBuilder param = new StringBuilder();
	      for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
	      .iterator(); it.hasNext();) {
	          Map.Entry<String, String> e = it.next();
	          param.append("&").append(e.getKey()).append("=").append(e.getValue());
	      }
	      return param.toString().substring(1);
	    }
	
	/**工单搜索，有多个参数可以设置默认（待完善）"ticket/search"
	 * @param title
	 * @param pagesize
	 * @return
	 */
	public static String gongdanSearch(String title, String pagesize){
	      TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
	      apiparamsMap.put("title", title);	
	      apiparamsMap.put("pagesize", pagesize);	 	   		 
	      //生成签名
	      String sign = Sdk.md5Signature(apiparamsMap);
	      apiparamsMap.put("sign", sign);
	      StringBuilder param = new StringBuilder();
	      for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
	      .iterator(); it.hasNext();) {
	          Map.Entry<String, String> e = it.next();
	          param.append("&").append(e.getKey()).append("=").append(e.getValue());
	      }
	      return param.toString().substring(1);
	    }
	
	/**工单修改"ticket/update"
	 * @param id
	 * @return
	 */
	public static String gongdanUpdate(String id){
	      TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
	      apiparamsMap.put("id", id);		 	   		 
	      //生成签名
	      String sign = Sdk.md5Signature(apiparamsMap);
	      apiparamsMap.put("sign", sign);
	      StringBuilder param = new StringBuilder();
	      for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
	      .iterator(); it.hasNext();) {
	          Map.Entry<String, String> e = it.next();
	          param.append("&").append(e.getKey()).append("=").append(e.getValue());
	      }
	      return param.toString().substring(1);
	    }
	
	/**工单回复"ticket/reply"
	 * @param id
	 * @param content
	 * @param username
	 * @return
	 */
	public static String gongdanReply(String id, String content, String username, String replyType){
	      TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
	      apiparamsMap.put("id", id);	
	      apiparamsMap.put("content", content);
	      apiparamsMap.put("username", username);
	      apiparamsMap.put("public", replyType);
	      //生成签名
	      String sign = Sdk.md5Signature(apiparamsMap);
	      apiparamsMap.put("sign", sign);
	      StringBuilder param = new StringBuilder();
	      for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
	      .iterator(); it.hasNext();) {
	          Map.Entry<String, String> e = it.next();
	          param.append("&").append(e.getKey()).append("=").append(e.getValue());
	      }
	      return param.toString().substring(1);
	    }

	/**工单删除"ticket/del"
	 * @param id
	 * @return
	 */
	public static String gongdanDel(String id){
	      TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
	      apiparamsMap.put("id", id);	
	     
	      //生成签名
	      String sign = Sdk.md5Signature(apiparamsMap);
	      apiparamsMap.put("sign", sign);
	      StringBuilder param = new StringBuilder();
	      for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
	      .iterator(); it.hasNext();) {
	          Map.Entry<String, String> e = it.next();
	          param.append("&").append(e.getKey()).append("=").append(e.getValue());
	      }
	      return param.toString().substring(1);
	    }
	 public static void main(String[] args) {
		// String result = Sdk.getResult("ticket/list",gongdanList());
		 //JSONObject  dataJson=new JSONObject(result);
		// String num = dataJson.getString("count");
		 //String result = Sdk.getResult("ticket/view",gongdanInquiry("100386"));
		 //String result = Sdk.getResult("ticket/search",gongdanSearch("111","10"));
		 //String result = Sdk.getResult("ticket/update",gongdanUpdate("4"));
		String result = Sdk.getResult("ticket/reply", gongdanReply("1","测试","wangzhili@oneapm.com", "0"));
		// String result = Sdk.getResult("ticket/del",gongdanDel("4"));
		String nresult = DecodeUni.decodeUnicode(result);
		 System.out.println(nresult);
		
	
	 }
		 
	
}
