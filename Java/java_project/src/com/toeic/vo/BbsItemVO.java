package com.toeic.vo;

import org.json.JSONException;
import org.json.JSONObject;

import com.toeic.util.DateUtil;
import com.toeic.util.StringUtil;

public class BbsItemVO {
	public int no = 0;
	public String userId = "";
	public String userName = "";
	public String title = "";
	public String content = "";
	public int count = 0;
	public String insertTime = "";
	public String updateTime = "";
	
	public JSONObject getJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("no", no);
		jsonObject.put("userId", StringUtil.replaceNullForDB(userId));
		jsonObject.put("userName", StringUtil.replaceNullForDB(userName));
		jsonObject.put("title", StringUtil.replaceNullForDB(title));
		jsonObject.put("content", StringUtil.replaceNullForDB(content));
		jsonObject.put("count", count);
		jsonObject.put("insertTime", DateUtil.getDisplayDateTime(insertTime));
		jsonObject.put("updateTime", DateUtil.getDisplayDateTime(updateTime));
		return jsonObject;
	}
}
