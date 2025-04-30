package com.toeic.api.app;

import java.util.ArrayList;

import org.json.JSONObject;

import com.toeic.vo.RankingItemVO;

public interface AppInformationDAO {
	public JSONObject login(String userId, String password);
	
	public JSONObject getMyInformation(String userId);
	public JSONObject updateMyInformation(String userId, String nickName, String pictureUrl, String birthDay, String sex);
	public JSONObject join(String userId, String password, String nickName, String pictureUrl);
	
	public JSONObject getUserList();
	
	public JSONObject insertRankingKBO(ArrayList<RankingItemVO> list);
}