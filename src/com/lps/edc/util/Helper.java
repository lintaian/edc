package com.lps.edc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.lps.edc.dto.TableDto;

public class Helper {
	@SuppressWarnings("unchecked")
	public static TableDto filterId(TableDto dto, List<Integer> positions) {
		List<Object> title = dto.getTitle();
		for (Integer i : positions) {
			title.remove(i);
		}
		dto.setTitle(title);
		List<Object> data = dto.getData();
		for (Object object : data) {
			List<Object> d = (List<Object>) object;
			for (Integer i : positions) {
				d.remove(i);
			}
		}
		dto.setData(data);
		return dto;
	}
	@SuppressWarnings("unchecked")
	public static List<Object> converToObj(List<Object> obj) {
		List<Object> rs = new ArrayList<Object>();
		List<Object> temp = new ArrayList<Object>();
		Map<String, Object> m = new HashMap<String, Object>();
		for (Object object : obj) {
			List<Object> obj2 = (List<Object>) object;
			temp = new ArrayList<Object>();
			for (Object object2 : obj2) {
				m = new HashMap<String, Object>();
				m.put("value", object2);
				temp.add(m);
			}
			rs.add(temp);
		}
		return rs;
	}
	public static String spliceId(JSONArray ids) {
		StringBuffer sb = new StringBuffer();
		int size = ids.size();
		for (int i = 0; i < size; i++) {
			sb.append("'");
			sb.append(ids.get(i).toString());
			sb.append("'");
			if (i != size - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
	public static List<String> covertJsonArrayToList(JSONArray array) {
		List<String> list = new ArrayList<String>();
		for (Object object : array) {
			list.add(object.toString());
		}
		return list;
	} 
	public static List<Object> spliceList(List<Object> data, int page, int per_page) {
		List<Object> rs = new ArrayList<Object>();
		int start = (page - 1) * per_page;
		int end = page * per_page > data.size() ? data.size() : page * per_page;
		for (int i = start; i < end; i++) {
			rs.add(data.get(i));
		}
		return rs;
	}
}
