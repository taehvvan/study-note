package com.toeic.vo;

import org.json.JSONException;
import org.json.JSONObject;

import com.toeic.util.StringUtil;

public class QuestionItemVO {
	public int no = 0;
	public String title = "";
	public String content = "";
	public int answer = 0;
	public int pick = 0;
	public int displayNumber = 0;
	
	public JSONObject getJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("no", no);
		jsonObject.put("title", StringUtil.replaceNullForDB(title));
		jsonObject.put("content", StringUtil.replaceNullForDB(content));
		jsonObject.put("pick", pick);
		jsonObject.put("displayNumber", displayNumber);
		return jsonObject;
	}
	
	public JSONObject getJSONObjectForResult() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("no", no);
		jsonObject.put("title", StringUtil.replaceNullForDB(title));
		jsonObject.put("content", StringUtil.replaceNullForDB(content));
		jsonObject.put("answer", answer);
		jsonObject.put("pick", pick);
		jsonObject.put("displayNumber", displayNumber);
		return jsonObject;
	}
}
