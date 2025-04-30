package com.toeic.api.app;

import java.util.ArrayList;

import org.json.JSONObject;

import com.toeic.vo.RankingItemVO;

public class AppInformationManager {
	private static AppInformationManager mAppManager = null;
	private AppInformationDAO mAppDAO = null;
	
	private AppInformationManager() {
		init();
	}
	
	public static AppInformationManager getInstance() {
		if (mAppManager == null) {
			synchronized (AppInformationManager.class) {
				if (mAppManager == null)
					mAppManager = new AppInformationManager();
			}
		}
		return mAppManager;
	}
	
	private void init() {
		mAppDAO = AppInformationDAOFactory.getInstance().getDAO();
	}
	
	public JSONObject login(String userId, String password) {
		return mAppDAO.login(userId, password);
	}
	
	public JSONObject getMyInformation(String userId) {
		return mAppDAO.getMyInformation(userId);
	}
	public JSONObject updateMyInformation(String userId, String nickName, String pictureUrl, String birthDay, String sex) {
		return mAppDAO.updateMyInformation(userId, nickName, pictureUrl, birthDay, sex);
	}
	
	public JSONObject join(String userId, String password, String nickName, String pictureUrl) {
		return mAppDAO.join(userId, password, nickName, pictureUrl);
	}
	
	public JSONObject getUserList() {
		return mAppDAO.getUserList();
	}
	
	public JSONObject insertRankingKBO(ArrayList<RankingItemVO> list) {
		return mAppDAO.insertRankingKBO(list);
}
