package com.github.curtainf2f.translateMod;

import java.io.UnsupportedEncodingException;

import com.baidu.translate.demo.TransApi;
import com.github.curtainf2f.translateMod.config.ConfigLoader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BaiduTranslator {
	public static String translate(String msg, String from, String to) throws Exception{
		try {
			TransApi api = new TransApi(ConfigLoader.appid , ConfigLoader.securityKey);
			String str = api.getTransResult(msg, from, to);
			JsonObject jsonObj = (JsonObject)new JsonParser().parse(str);
			if(jsonObj.has("error_code")) {
				int code = jsonObj.get("error_code").getAsInt();
				if(code == 52001) throw new Exception("翻译: 请求超时");
				if(code == 52002) throw new Exception("翻译: 系统错误");
				if(code == 52003) throw new Exception("翻译: 未授权用户, 请检查mod设置中的appid和密钥");
				if(code == 54005) throw new Exception("翻译: 请求频繁");
				if(code == 58000) throw new Exception("翻译: 客户端IP非法");
				if(code == 58002) throw new Exception("翻译: 服务当前已关闭, 打开您的开发者平台, 通用翻译API, 服务概况打开");
				if(code == 52001) throw new Exception("翻译: 请求超时");
				if(code == 90107) throw new Exception("认证未通过或未生效, 检查你的开发者用户");
				else throw new Exception("翻译: 错误代码" + String.valueOf(code));
			}
			String res = jsonObj.get("trans_result").toString();
			JsonArray js = new JsonParser().parse(res).getAsJsonArray();
			jsonObj = (JsonObject)js.get(0);
	        return jsonObj.get("dst").getAsString();
		} catch (UnsupportedEncodingException e) {
			throw new Exception("翻译失败: " + e.getMessage());
		}
	}
}
