package com.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
//highcharts 封装成指定的数据格式
public class MapKeyAndValue {

	public static void main(String[] args) {
		List<Map<String, Object>> lm=new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		map.put("date", "2011-11-11");
		map.put("h01", 10);
		map.put("h02", 12);
		map.put("h03", 13);
		map.put("h04", 14);
		map.put("h05", 15);
		map.put("h06", 16);
		map.put("h07", 17);
		map.put("h08", 18);
		map.put("h09", 19);
		map.put("h10", 20);
		map.put("h11", 21);
		map.put("h00", 21);
		map.put("total", 320);
		lm.add(map);
		lm.add(map);
		System.out.println(lm);
		
		System.out.println(HighChartsMapSort(lm));
		System.out.println(HighChartsMapSort2(lm));
	}
	
	public static void getKV(Map<String, Object> map){
		Set<String> keys=map.keySet();
		for (String key : keys) {
			System.out.println(key+":"+map.get(key));
		}
	}
	
	public static void getKV2(Map<String, Object> map){
		Set<Entry<String, Object>> entries=map.entrySet();
		for (Entry<String, Object> entry : entries) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
	
	public static void getKeys(Map<String, Object> map){
		Set<String> keys=map.keySet();
		System.out.println(keys);
	}
	
	public static void getValues(Map<String, Object> map){
		Collection<Object> c= map.values();
		System.out.println(c);
	}
	
	//排序类
	public static class KeyComparator implements Comparator<Map.Entry<String, Object>>{
		public int compare(Map.Entry<String, Object> m,Map.Entry<String, Object> n){
			return m.getKey().compareTo(n.getKey());
		}
	}
	
	//排序测试
	public static void compareTest(Map<String, Object> map){
		List<Map.Entry<String, Object>> list=new ArrayList<>();
		list.addAll(map.entrySet());
		
		KeyComparator kc=new KeyComparator();
		Collections.sort(list,kc);
		for (Entry<String, Object> entry : list) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
	
	//排序
	public static List<Map.Entry<String, Object>> compare(Map<String, Object> map){
		List<Map.Entry<String, Object>> list=new ArrayList<>();
		list.addAll(map.entrySet());
		
		KeyComparator kc=new KeyComparator();
		Collections.sort(list,kc);
		return list;
	}
		
	//未排序
	public static Map<String, Object>  HighChartsMap(List<Map<String, Object>> lm){
		Map<String, Object> hc=new HashMap<>();
		//title
		Map<String, Object> title=new HashMap<>();
		title.put("text", "标题内容");
		hc.put("title", title);
		if(lm!=null&&lm.size()>0){			
			//x轴
			//y轴
			List<Object> xAxis=new ArrayList<>();
			List<Map<String, Object>> series=new ArrayList<>();
			boolean flag=true;
			for (Map<String, Object> map : lm) {
				Set<Entry<String, Object>> entries=map.entrySet();
				Map<String, Object> temp=new HashMap<>();
				Map<String, Object> dataLabels=new HashMap<String, Object>();
				dataLabels.put("enabled", true);
				temp.put("dataLabels", dataLabels);
				List<Object> data=new ArrayList<>();
				for (Entry<String, Object> entry : entries) {										
				    if(!entry.getKey().equals("date")&&!entry.getKey().equals("total")){
						if(flag){
							xAxis.add(entry.getKey());
						}
						data.add(entry.getValue());
					}
				}				
				temp.put("data", data);
				temp.put("name", map.get("date")+"("+map.get("total")+")");
				series.add(temp);
				flag=false;
			}
			hc.put("xAxis", xAxis);
			hc.put("series", series);
		}
		
		
		return hc;
	}
	
	//排序
	public static Map<String, Object>  HighChartsMapSort(List<Map<String, Object>> lm){
		Map<String, Object> hc=new HashMap<>();
		//title
		Map<String, Object> title=new HashMap<>();
		title.put("text", "标题内容");
		hc.put("title", title);
		if(lm!=null&&lm.size()>0){			
			//x轴
			//y轴
			List<Object> xAxis=new ArrayList<>();
			List<Map<String, Object>> series=new ArrayList<>();
			boolean flag=true;
			for (Map<String, Object> map : lm) {
				List<Map.Entry<String, Object>> entries=compare(map);
				Map<String, Object> temp=new HashMap<>();
				Map<String, Object> dataLabels=new HashMap<String, Object>();
				dataLabels.put("enabled", true);
				temp.put("dataLabels", dataLabels);
				List<Object> data=new ArrayList<>();
				for (Entry<String, Object> entry : entries) {										
				    if(!entry.getKey().equals("date")&&!entry.getKey().equals("total")){
						if(flag){
							xAxis.add(entry.getKey());
						}
						data.add(entry.getValue());
					}
				}				
				temp.put("data", data);
				temp.put("name", map.get("date")+"("+map.get("total")+")");
				series.add(temp);
				flag=false;
			}
			hc.put("xAxis", xAxis);
			hc.put("series", series);
		}
			
		return hc;
	}
	
	
	//排序方法2
	public static Map<String, Object>  HighChartsMapSort2(List<Map<String, Object>> lm){
		Map<String, Object> hc=new HashMap<>();
		//title
		Map<String, Object> title=new HashMap<>();
		title.put("text", "标题内容");
		hc.put("title", title);
		if(lm!=null&&lm.size()>0){			
			//x轴
			//y轴 
			List<String> xAxis=new ArrayList<>();
			List<Map<String, Object>> series=new ArrayList<>();
			boolean flag=true;
			for (Map<String, Object> map : lm) {
				//Set<Entry<String, Object>> entries=map.entrySet();
				//按keys排序
				List<Map.Entry<String, Object>> entries=new ArrayList<>(map.entrySet());
				Collections.sort(entries, new Comparator<Map.Entry<String, Object>>() {   
				    public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {      
				        return (o1.getKey()).toString().compareTo(o2.getKey());
				    }
				}); 
				Map<String, Object> temp=new HashMap<>();
				Map<String, Object> dataLabels=new HashMap<String, Object>();
				dataLabels.put("enabled", true);
				temp.put("dataLabels", dataLabels);
				List<Object> data=new ArrayList<>();
				for (Entry<String, Object> entry : entries) {										
				    if(!entry.getKey().equals("date")&&!entry.getKey().equals("total")){
				    	if(flag){
							xAxis.add(entry.getKey());
						}
						data.add(entry.getValue());
					}
				}		
				temp.put("data", data);
				temp.put("name", map.get("date")+"(合计:"+map.get("total")+")");
				series.add(temp);
				flag=false;
			}
			hc.put("xAxis", xAxis);
			hc.put("series", series);
		}
		
		
		return hc;
	}
}
