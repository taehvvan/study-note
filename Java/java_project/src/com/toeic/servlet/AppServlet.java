package com.toeic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.toeic.api.app.AppInformationManager;
import com.toeic.util.StringUtil;
// import com.toeic.vo.BuildingItemVO;
// import com.toeic.vo.ContactItemVO;

@WebServlet("/app.do")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String jsonString = "";

		Enumeration<String> params = request.getParameterNames();
		System.out.println("------------------------------");
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    System.out.println(name + " : " +request.getParameter(name));
		}

		String kind = StringUtil.replaceNullForServlet(request.getParameter("kind"));
		//

		if(kind.equals("login")) {
			String userId = StringUtil.replaceNullForServlet(request.getParameter("userId"));
			String password = StringUtil.replaceNullForServlet(request.getParameter("password"));
			System.out.println("----- "+kind+" ----" + userId + "-----" + password);
			JSONObject jsonObject = AppInformationManager.getInstance().login(userId, password);
			jsonString = jsonObject.toString();
		} else if(kind.equals("mjb")) {
			String[] array = {"����", "����", "��"};
			int userCard = StringUtil.getStringNumber(request.getParameter("userCard"));
			if(userCard > 2) {
				userCard = 2;
			} else if(userCard < 0) {
				userCard = 0;
			}
			
			Random random = new Random();
			int computerCard = random.nextInt(3);
			
			jsonString += ("�� ī��: " + array[userCard] + "\n");
			jsonString += ("��ǻ�� ī��: " + array[computerCard] + "\n");
			
			if(userCard == computerCard) {
				jsonString += " ===> [����� ���]";
			} else if(userCard > computerCard) {
				jsonString += " ===> [����� ���� �̱�]";
			} else {
				jsonString += " ===> [����� ���� ��]";
			}
		} else if(kind.equals("getToday")) {
			LocalDate now = LocalDate.now();
			jsonString = now.toString();
		} 
		/*
		else if(kind.equals("searchForSeoil")) {
			String buildingName = StringUtil.replaceNullForServlet(request.getParameter("buildingName"));
			if(buildingName.equals("ȣõ��")) {
				BuildingItemVO item = new BuildingItemVO();
				item.name = "ȣõ��";
				item.description = "���� �ֽ� �ǹ��� �ϳ��Դϴ�.";
				item.address = "����Ư���� �߶��� ��� 37";
				item.x = "127.098177";
				item.y = "37.587762";
				jsonString = item.getInformation();
			} else if(buildingName.equals("����")) {
				BuildingItemVO item = new BuildingItemVO();
				item.name = "����";
				item.description = "���� ����⿡ �ִ� �ǹ��Դϴ�.";
				item.address = "����Ư���� �߶��� ��� 37";
				item.x = "127.097133";
				item.y = "37.585748";
				jsonString = item.getInformation();
			} else {
				jsonString = "�ǹ����� �Է��� �ּ���.";
			}
		}
		*/
		else if(kind.equals("getWeight")) { 
			int width = StringUtil.getStringNumber(request.getParameter("width"));
			int height = StringUtil.getStringNumber(request.getParameter("height"));
			int result = getWeight(width, height);
			jsonString = width+"x"+height+"="+result;
		} else if(kind.equals("drawTriangle")) {
			for(int i=0; i<5; i++) {
				if(i == 0) {
					break;
				}
				for(int j=0; j<i; j++) {
					jsonString += "*";
				}
				jsonString += "<br/>";
			}

			for(int i=0; i<5; i++) {
				if(i == 0) {
					continue;
				}
				for(int j=0; j<i; j++) {
					jsonString += "*";
				}
				jsonString += "<br/>";
			}

			for(int i=0; i<10; i++) {
				System.out.println("+");
			}
		} else if(kind.equals("showGugudan")) { 
			int dan = StringUtil.getStringNumber(request.getParameter("dan"));
			for(int i=1; i<=9; i++) {
				jsonString += dan + " X " + i + " = " + (dan*i) + ";";
				jsonString += "<br/>";
			}
		} 
		/* 
		else if(kind.equals("compare")) { 
		
			ArrayList<ContactItemVO> list1 = new ArrayList<ContactItemVO>();

			ContactItemVO contact = new ContactItemVO("ȫ�浿","01012345678","�����");
			System.out.println("contact.name: "+ contact.name);

			list1.add(contact);
			System.out.println("list1.get(0).name: "+ list1.get(0).name);

			list1.add(new ContactItemVO("��浿","01011112222","��⵵"));
			list1.add(new ContactItemVO("�ڱ浿","01033334444","���"));
			list1.add(new ContactItemVO("���浿","01055556666","����"));
			
			int size1 = list1.size();
			for(int i=0; i<size1; i++) {
				System.out.println(i+"��° �̸�: " + list1.get(i).name);
				System.out.println(i+"��° ��ȭ��ȣ: " + list1.get(i).phoneNumber);
			}
			
			
			ArrayList<ContactItemVO> list2 = new ArrayList<ContactItemVO>();
			list2.add(new ContactItemVO("��浿","01011112222","��⵵"));
			list2.add(new ContactItemVO("�ֱ浿","01077778888","��û��"));

			//�ݺ��� �ȿ��� if�� �Ἥ �ߺ��Ǵ� ����ó�� ���������
			//System.out.println���� ����Ͻÿ�.
			
			int size2 = list2.size();
			
			for(int i=0; i<size1; i++) {
				String phoneNumber1 = list1.get(i).phoneNumber;
				for(int j=0; j<size2; j++) {
					String phoneNumber2 = list2.get(j).phoneNumber;
					if(phoneNumber1.equals(phoneNumber2)) {
						String name2 = list2.get(j).name;
						System.out.println("�ߺ��� �̸�: " + name2);
						System.out.println("�ߺ��� ��ȭ��ȣ: " + phoneNumber1);
					}
				}
			}
		} else if(kind.equals("getUserList")) {
			ArrayList<ContactItemVO> list = new ArrayList<ContactItemVO>();
			list.add(new ContactItemVO("ȫ�浿", "01011112222", "����� �߶���"));
			list.add(new ContactItemVO("�ڱ浿", "01033334444", "����� ������"));
			list.add(new ContactItemVO("�ֱ浿", "01055556666", "����� ������"));
			
			JSONArray array = new JSONArray();
			
			int size = list.size();
			
			try {
				for(int i=0; i<size; i++) {
					ContactItemVO item = list.get(i);
					
					JSONObject itemObject = new JSONObject();
					itemObject.put("name", item.name);
					itemObject.put("phoneNumber", item.phoneNumber);
					itemObject.put("address", item.address);
					array.put(itemObject);
				}
			} catch(JSONException e) {
			}
			
			jsonString = array.toString();
			
			
			
		} 
		*/ 
		else if(kind.equals("getMyInformation")) {
			String userId = StringUtil.replaceNullForServlet(request.getParameter("userId"));
			JSONObject jsonObject = AppInformationManager.getInstance().getMyInformation(userId);
			jsonString = jsonObject.toString();
		} else if(kind.equals("updateMyInformation")) {
			String userId = StringUtil.replaceNullForServlet(request.getParameter("userId"));
			String nickName = StringUtil.replaceNullForServlet(request.getParameter("nickName"));
			String birthDay = StringUtil.replaceNullForServlet(request.getParameter("birthDay"));
			String pictureUrl = StringUtil.replaceNullForServlet(request.getParameter("pictureUrl"));
			String sex = StringUtil.replaceNullForServlet(request.getParameter("sex"));
			
			JSONObject jsonObject = AppInformationManager.getInstance().updateMyInformation(userId, nickName, pictureUrl, birthDay, sex);
			jsonString = jsonObject.toString();
		} else if(kind.equals("join")) {
			String userId = StringUtil.replaceNullForServlet(request.getParameter("userId"));
			String password = StringUtil.replaceNullForServlet(request.getParameter("password"));
			String nickName = StringUtil.replaceNullForServlet(request.getParameter("nickName"));
			String pictureUrl = StringUtil.replaceNullForServlet(request.getParameter("pictureUrl"));
			
			JSONObject jsonObject = AppInformationManager.getInstance().join(userId, password, nickName, pictureUrl);
			jsonString = jsonObject.toString();
		} else if(kind.equals("getUserList")) {
			
			JSONObject jsonObject = AppInformationManager.getInstance().getUserList();
			jsonString = jsonObject.toString();
		} else {
			jsonString = "Fail";
		}

		System.out.println(jsonString);
		System.out.println("----------------------------");
		
		PrintWriter out = response.getWriter();
		out.print(jsonString);
	}
	
	public int getWeight(int width, int height) {
		int result = width * height;
		return result;
	}
	
	public JSONObject getFailObject() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", "N");
		} catch(JSONException e) {
		}
		return jsonObject;
	}
}