package com.toeic.api.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.toeic.database.DBConstant;
import com.toeic.database.DBUtil;
import com.toeic.util.PrintUtil;
import com.toeic.util.StringUtil;
import com.toeic.vo.UserItemVO;
import com.toeic.vo.RankingItemVO;

public class AppInformationSQLDAO implements AppInformationDAO {

	@Override
	public JSONObject login(String userId, String password) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;
		int no = 0;
		String name = "";
		
		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();

			sql = "SELECT no, name FROM user_t WHERE userId='"+userId+"' AND password='"+password+"' LIMIT 1";
			result = stmt.executeQuery(sql);
			while(result.next()) {
				no = result.getInt("no");
				name = result.getString("name");
				break;
			}
			result.close();

			if(no > 0) {
				isConfirm = true;
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
			jsonObject.put("name", StringUtil.replaceNullForDB(name));
			
		} catch(JSONException e) {
		}
		return jsonObject;
	}

	@Override
	public JSONObject getMyInformation(String userId) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;
		
		int no = 0;
		String nickName = "";
		String pictureUrl = "";
		String birthDay = "";
		String sex = "";
		
		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();

			sql = "SELECT no, nickName, pictureUrl, birthDay, sex FROM user_t WHERE userId='"+userId+"' LIMIT 1";
			result = stmt.executeQuery(sql);
			while(result.next()) {
				no = result.getInt("no");
				nickName = result.getString("nickName");
				pictureUrl = result.getString("pictureUrl");
				birthDay = result.getString("birthDay");
				sex = result.getString("sex");
				break;
			}
			result.close();

			if(no > 0) {
				isConfirm = true;
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
			jsonObject.put("nickName", StringUtil.replaceNullForDB(nickName));
			jsonObject.put("pictureUrl", StringUtil.replaceNullForDB(pictureUrl));
			jsonObject.put("birthDay", StringUtil.replaceNullForDB(birthDay));
			jsonObject.put("sex", StringUtil.replaceNullForDB(sex));
		} catch(JSONException e) {
		}
		return jsonObject;
	}

	@Override
	public JSONObject updateMyInformation(String userId, String nickName, String pictureUrl, String birthDay,
			String sex) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;
		
		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();

			sql = "UPDATE user_t SET nickName='"+nickName+"', pictureUrl='"+pictureUrl+"', birthDay='"+birthDay+"', sex='"+sex+"' WHERE userId='"+userId+"'";
			int resultExecute = stmt.executeUpdate(sql);
			if(resultExecute > 0) {
				isConfirm = true;
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	@Override
	public JSONObject getUserList() {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;
		
		ArrayList<UserItemVO> list = new ArrayList<UserItemVO>();
		
		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();

			sql = "SELECT userId, nickName, pictureUrl FROM user_t";
			result = stmt.executeQuery(sql);
			while(result.next()) {
				String _userId = result.getString("userId");
				String _nickName = result.getString("nickName");
				String _pictureUrl = result.getString("pictureUrl");
				UserItemVO userItem = new UserItemVO(_userId, _nickName, _pictureUrl);
				list.add(userItem);
			}
			result.close();

			if(list.size() > 0) {
				isConfirm = true;
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			JSONArray userArray = new JSONArray();
			int listSize = list.size();
			for(int i=0; i<listSize; i++) {
				UserItemVO item = list.get(i);
				userArray.put(item.getJSONObject());
			}
			
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
			jsonObject.put("userList", userArray);
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	public JSONObject join(String userId, String password, String nickName, String pictureUrl) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;
		
		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();

			sql = "INSERT INTO user_t(userId, password, nickName, pictureUrl) VALUES('"+userId+"', '"+password+"', '"+nickName+"', '"+pictureUrl+"')";	
			int resultExecute = stmt.executeUpdate(sql);
			if(resultExecute > 0) {
				isConfirm = true;
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	public JSONObject insertRankingKBO(ArrayList<RankingItemVO> list) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;
		
		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();

			int size = list.size();
			for(int i=0; i<size; i++) {
				RankingItemVO item = list.get(i);
				
				sql = "INSERT INTO kbo_t(rank, name) VALUES("+item.rank+", '"+item.name+"')";
				stmt.execute(sql);
			}
			
			if(size > 0) {
				isConfirm = true;
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			JSONArray jsonArray = new JSONArray();
			int size = list.size();
			if(size > 0) {
				for(int i=0; i<size; i++) {
					RankingItemVO item = list.get(i);
					jsonArray.put(item.getJSONObject());
				}
			}
			
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
			jsonObject.put("jsonArray", jsonArray);
		} catch(JSONException e) {
		}
		return jsonObject;
	}

}
