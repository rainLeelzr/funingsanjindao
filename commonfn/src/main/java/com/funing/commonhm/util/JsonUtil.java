package com.funing.commonfn.util;

import com.funing.commonfn.manager.operate.CanDoOperate;
import com.funing.commonfn.manager.operate.Operate;
import com.funing.commonfn.model.RoomMember;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class JsonUtil {
    private static JsonConfig config;

    static {
        config = new JsonConfig();
        config.registerJsonValueProcessor(java.util.Date.class,
                new JsonDateValueProcessor());
    }

    public static String toJson(List list) {
        JSONArray arr = JSONArray.fromObject(list, config);
        return arr.toString();
    }

    public static String toJson(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj, config);
        return jsonObject.toString();
    }

    public static <T> Object toBean(String json, Class<T> type) {
        return toBean(json, type, null);
    }

    public static <T> Object toBean(String json, Class<T> type, Map<String,
            Class> classMap) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object o = JSONObject.toBean(jsonObject, type, classMap);
        if (o instanceof RoomMember) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            RoomMember rm = (RoomMember) o;
            String joinTime = (String) jsonObject.get("joinTime");
            try {
                rm.setJoinTime(sf.parse(joinTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (o instanceof CanDoOperate) {
            CanDoOperate canDoOperate = (CanDoOperate) o;
            if (canDoOperate.getOperates() != null && canDoOperate.getOperates().size() != 0) {
                Iterator<Operate> iterator = canDoOperate.getOperates().iterator();
                boolean isString = false;
                while (iterator.hasNext()) {
                    Object next = iterator.next();
                    if (next instanceof String) {
                        isString = true;
                    }
                    break;
                }
                if (isString) {
                    Set<Operate> newOperates = new TreeSet<>();
                    Iterator it = canDoOperate.getOperates().iterator();
                    while (it.hasNext()) {
                        Object next = it.next();
                        newOperates.add(Operate.valueOf(next.toString()));
                    }
                    canDoOperate.setOperates(newOperates);
                }
            }
        }
        return o;
    }

    public static int getInt(JSONObject data, String key) {
        try {
            return data.getInt(key);
        } catch (JSONException e) {
            throw new RuntimeException(String.format("缺少参数：%s", key));
        }
    }


    public static long getLong(JSONObject data, String key) {
        try {
            return data.getLong(key);
        } catch (JSONException e) {
            throw new RuntimeException(String.format("缺少参数：%s", key));
        }
    }

    //test
    public static void main(String[] args) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(java.util.Date.class,
                new JsonDateValueProcessor());
//		Admin s = new Admin();
//		  // long currentTime=System.currentTimeMillis(); 
//	   s.setCreationtime(String.valueOf(System.currentTimeMillis()));
//		JSONObject jsonObject = JSONObject.fromObject(s, config);
//		System.out.println(jsonObject.toString());
//		
//		
//		List list = new ArrayList();
//		list.add(s);
//		System.out.println(JsonUtil.toJson(s));
//		System.out.println(JsonUtil.toJson(list));
//		
//		Map m = new HashMap();
//		m.put("obj", s);
//		m.put("list", list);
//		System.out.println(JsonUtil.toJson(m));
    }

    /**
     * 将json转化为实体POJO
     *
     * @param jsonStr
     * @param obj
     * @return
     */
    public static <T> Object JSONToObj(String jsonStr, Class<T> obj) {
        T t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            t = objectMapper.readValue(jsonStr,
                    obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
