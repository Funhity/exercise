package com.huaxia.sso.utils;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 后台通用工具类
 */
public class CommonUtils {
    private static Logger log = LoggerFactory.getLogger(CommonUtils.class);


    /**
     * 生成uuid入口
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String[] dataSplit(String source) {
        return source.split("@#");
    }

    public static Map<String, Object> dataMap(String[] params, HttpServletRequest request, Object[]... primaryKeyValues) {
        if (params == null || params.length == 0) {
            return new HashMap<String, Object>(0);
        }
        Map<String, Object> map = new HashMap<String, Object>(params.length);
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            String value = request.getParameter(param);
            map.put(param, value);
        }
        List<Object[]> keyValues = Arrays.asList(primaryKeyValues);
        if (keyValues != null && keyValues.size() > 0) {
            for (Object[] keyValue : keyValues) {
                if (keyValue[0] != null && keyValue[1] != null)
                    map.put(keyValue[0].toString(), keyValue[1]);
            }
        }
        return map;
    }

    public static List<Map> dataListMap(String[] params, HttpServletRequest request, Object[]... primaryKeyValues) {
        if (params == null || params.length == 0) {
            return new ArrayList(0);
        }
        String[][] arrays = new String[params.length][];
        for (int i = 0; i < params.length; i++) {
            arrays[i] = dataSplit(request.getParameter(params[i]));
        }
        List<Map> list = new ArrayList<Map>(arrays[0][0].length());
        List<Object[]> keyValues = Arrays.asList(primaryKeyValues);
        for (int i = 0; i < arrays[0].length; i++) {
            Map map = new HashMap();
            if (arrays[0][i] != null && !arrays[0][i].isEmpty()) {
                for (int j = 0; j < params.length; j++) {
                    if (arrays[j].length < i + 1) {
                        continue;
                    }
                    map.put(params[j], arrays[j][i].replaceAll("¥", ""));
                }

            }
            if (keyValues != null && keyValues.size() > 0) {
                for (Object[] keyValue : keyValues) {
                    if (keyValue[0] != null && keyValue[1] != null)
                        map.put(keyValue[0].toString(), keyValue[1]);
                }
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 分组
     */
    public static LinkedHashMap groupListByField(List<Map> list, String field) {
        LinkedHashMap resultMap = new LinkedHashMap();
        if (list == null)
            return resultMap;
        int size = list.size();
        if (size == 0) {
            return resultMap;
        }
        for (Map map : list) {
            Object value = map.get(field);
            if (value == null || StringUtils.isBlank(value.toString())) {
                continue;
            }
            if (resultMap.get(value.toString()) == null) {
                resultMap.put(value.toString(), new ArrayList());
            }
            ((List<Map>) resultMap.get(value.toString())).add(map);
        }
        log.info("------------resultMap: " + resultMap);
        return resultMap;
    }

    /**
     * @param several 每组数据的条目数
     */
    public static LinkedHashMap groupList(List<Map> list, int several, String key) {
        LinkedHashMap map = new LinkedHashMap();
        int size = list.size();
        if (size == 0) {
            return map;
        }
        if (several == 0) {
            map.put(key + 1, list);
            return map;
        }
        int groupcount = size % several == 0 ? size / several : size / several + 1;
        List<Map> groupList;
        int index;
        for (int i = 0; i < groupcount; i++) {
            for (int j = 0; j < several; j++) {
                if (map.get(key + i) == null) {
                    groupList = new ArrayList();
                    map.put(key + i, groupList);
                }
                index = i * several + j;
                if (size >= index + 1)
                    ((List<Map>) map.get(key + i)).add(list.get(index));
            }

        }
        return map;
    }


    /**
     * @param sameKey -  mapkey
     */
    public static LinkedHashMap listMapToMapList(List<Map> list, String sameKey) {
        LinkedHashMap map = new LinkedHashMap();
        if (list.size() < 1) {
            return map;
        }
        String keyValue;
        for (Map m : list) {
            if (m.get(sameKey) == null) {
                continue;
            }
            keyValue = m.get(sameKey).toString();
            if (map.get(keyValue) == null) {
                map.put(keyValue, new ArrayList<Map>());
            }
            ((List<Map>) map.get(keyValue)).add(m);
        }
        return map;
    }


    /**
     * @param key -  mapkey
     */
    public static LinkedHashMap listMapToMap(List<Map> list, String key) {
        LinkedHashMap resultMap = new LinkedHashMap();
        if (list.size() < 1) {
            return resultMap;
        }
        String keyValue;
        for (Map m : list) {
            if (m.get(key) == null) {
                continue;
            }
            keyValue = m.get(key).toString();
            resultMap.put(keyValue, m);
        }
        return resultMap;
    }

    public static Map<String, String> requestMapToTableMap(HttpServletRequest request) {
        Map readOnlyMap = request.getParameterMap();
        Map<String, String> params = new HashMap();
        if (readOnlyMap != null && readOnlyMap.size() > 0) {
            Iterator iterator = readOnlyMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();
                String key = entry.getKey();
                String[] value = entry.getValue();
                if (value != null && value.length > 0) {
                    params.put(key, value[0] == null ? "" : value[0].trim());
                }
            }
        }
        return params;
    }
    public static Map<String, Object> getRequestMap(HttpServletRequest request) {
    	Map readOnlyMap = request.getParameterMap();
    	Map<String, Object> params = new HashMap();
    	if (readOnlyMap != null && readOnlyMap.size() > 0) {
    		Iterator iterator = readOnlyMap.entrySet().iterator();
    		while (iterator.hasNext()) {
    			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();
    			String key = entry.getKey();
    			String[] value = entry.getValue();
    			if (value != null && value.length > 0) {
    				params.put(key, value[0] == null ? "" : value[0].trim());
    			}
    		}
    	}
    	return params;
    }

    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    public static List<Map> randomFieldValue(List<Map> list, String field) {
        Random random;
        String itemFlag;
        for (Map map : list) {
            itemFlag = map.get(field).toString();
            if (!StringUtils.isBlank(itemFlag)) {
                if (itemFlag.lastIndexOf(",") == itemFlag.length() - 1) {
                    itemFlag = itemFlag.substring(0, itemFlag.lastIndexOf(","));
                }
                String[] flagNames = itemFlag.split(",");
                map.put("item_flag", flagNames[new Random().nextInt(flagNames.length)]);
            }
        }
        return list;
    }

    public static String getNextStr(String maxStr, int bit) {
        String nextStr = "";
        nextStr = (Integer.parseInt(maxStr) + 1) + "";
        for (int i = nextStr.length(); i < bit; i++) {
            nextStr = "0" + nextStr;
        }
        return nextStr;
    }

    //反序列化 针对前台对多个form序列化传过来的params
    public static Map deSerialize(HttpServletRequest request) {
        Map requestParamsMap = CommonUtils.requestMapToTableMap(request);
        return deSerialize(requestParamsMap);
    }


    public static Map deSerialize(Map paramsMap) {
        Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet().iterator();
        LinkedHashMap<String, Map> resultMap = new LinkedHashMap();
        Map map;
        String key, value;
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            key = entry.getKey();
            value = entry.getValue();
            map = deSerialize(value);
            resultMap.put(key, map);
        }
        return resultMap;
    }


    public static Map deSerialize(String queryString) {
        LinkedHashMap resultMap = new LinkedHashMap();
        if (StringUtils.isBlank(queryString))
            return resultMap;
        String[] params = queryString.split("&");
        String[] keyValues;
        String lastValue;
        try {
            for (String param : params) {
                keyValues = param.split("\\=");
                if (keyValues.length < 2) {
                    lastValue = "";
                } else {
                    lastValue = URLDecoder.decode(keyValues[1], "UTF-8");
                }
                resultMap.put(keyValues[0], lastValue);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    //获取由逗号连接的值
    public static String listMapToStringByKey(List<Map> list, String key) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        boolean isfirst = true;
        for (Map map : list) {
            if (!isfirst) {
                builder.append(",");
            } else {
                isfirst = false;
            }
            builder.append(map.get(key));
        }
        return builder.toString();
    }


    //获取由逗号连接的值
    public static List<Map> stringToListMap(String source, String[] sortKey) {
        List<Map> listMap = new ArrayList();
        if (StringUtils.isBlank(source)) {
            return listMap;
        }

    /*    try {
            source = new String(source.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        String[] array = source.split(",");
        Map map;
        for (String value : array) {
            if (StringUtils.isNotBlank(value)) {
                String[] values = value.split("_");
                map = new HashMap();
                for (int i = 0; i < values.length; i++) {
                    if (sortKey.length > i)
                        map.put(sortKey[i], values[i]);
                }
                listMap.add(map);
            }
        }
        return listMap;
    }


    public static Map[] objectToMap(List<Map> mapList) {
        if (mapList == null || mapList.size() == 0) {
            return new Map[0];
        }
        Object[] objs = mapList.toArray();
        Map[] maps = new Map[objs.length];
        for (int i = 0; i < objs.length; i++) {
            maps[i] = (Map) objs[i];
        }
        return maps;
    }


    public static String listMapToString(List<Map> listMap, String[] fromKey, String[] toKey) {
        if (listMap == null || listMap.size() == 0 || fromKey == null || toKey == null || fromKey.length == 0 || toKey.length == 0) {
            return null;
        }
        if (fromKey.length != toKey.length) {
            throw new RuntimeException("来源键与目标键长度不匹配!");
        }
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (Map map : listMap) {
            if (!isFirst) {
                builder.append("&&");
            } else {
                isFirst = !isFirst;
            }
            boolean isFirstTow = true;
            for (int i = 0; i < fromKey.length; i++) {
                if (!isFirstTow)
                    builder.append(";");
                else
                    isFirstTow = !isFirstTow;
                builder.append(toKey[i] + ":" + map.get(fromKey[i]));
            }

        }
        return builder.toString();
    }

    private static String keySplit = "=";
    private static String elementSplit = "&";

    public static String mapToJson(Map data) {
        return mapToJson(data, elementSplit);
    }

    public static String mapToJson(Map data, String sign) {
        StringBuilder json = new StringBuilder();
        Iterator<String> iterator = data.keySet().iterator();
        boolean isfirst = true;
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (!isfirst) {
                json.append(sign);
            } else {
                isfirst = !isfirst;
            }
            if (data.get(key) != null && !(data.get(key).toString()).isEmpty())
                json.append(key + keySplit + data.get(key));
        }
        return json.toString();
    }


    public static Map jsonToMap(String json) {
        return jsonToMap(json, elementSplit);
    }

    public static Map jsonToMap(String json, String sign) {
        return jsonToMap(json, keySplit, sign);
    }

    public static Map jsonToMap(String json, String keySplit, String sign) {
        LinkedHashMap resultMap = new LinkedHashMap();
        if (StringUtils.isBlank(json))
            return resultMap;
        String[] keyValues = json.split(sign);
        for (String keyValue : keyValues) {
            if (StringUtils.isBlank(keyValue)) {
                continue;
            }
            String[] values = keyValue.split(keySplit);
            if (values == null || values.length == 0)
                continue;
            if (values.length == 1) {
                resultMap.put(values[0], null);
            } else {
                resultMap.put(values[0], values[1]);
            }
        }
        return resultMap;
    }

    public static String createHjbNiceName(String nickName) {
        if(StringUtils.isBlank(nickName)){
            return nickName;
        }
        nickName = nickName.substring(nickName.length() - 4, nickName.length());
        Date now = new Date();
        String str = "HJB" + new SimpleDateFormat("yyMM").format(now) + new SimpleDateFormat("ss").format(now) + nickName;
        return str;
    }


    public static Map defaultEmptyIntValue(Map map, String... keys) {
        for (String key : keys) {
            if (map.get(key) != null && map.get(key) == "") {
                map.put(key, 0);
            }
        }
        return map;
    }

//    public static List<Map> stringToJsonMap(Object jsondata) {
//        if (jsondata == null) {
//            return new ArrayList();
//        }
//        List<Map> map = new ArrayList();
//        JSONArray array = JSONArray.fromObject(jsondata);
//        for (int i = 0; i < array.size(); i++) {
//            JSONObject jsonObject = array.getJSONObject(i);
//            map.add(jsonObject);
//        }
//        return map;
//    }

    public static List<MultipartFile> doMultipartFile(MultipartFile uploadfile1,
                                                      MultipartFile uploadfile2,
                                                      MultipartFile uploadfile3,
                                                      MultipartFile uploadfile4,
                                                      MultipartFile uploadfile5) {
    	List<MultipartFile> files = new ArrayList<MultipartFile>();
    	if(uploadfile1!=null && uploadfile1.getSize()>0){
    		files.add(uploadfile1);
    	}
    	if(uploadfile2!=null && uploadfile2.getSize()>0){
    		files.add(uploadfile2);
    	}
    	if(uploadfile3!=null && uploadfile3.getSize()>0){
    		files.add(uploadfile3);
    	}
    	if(uploadfile4!=null && uploadfile4.getSize()>0){
    		files.add(uploadfile4);
    	}
    	if(uploadfile5!=null && uploadfile5.getSize()>0){
    		files.add(uploadfile5);
    	}
        return files;
    }
    public static List<MultipartFile> doMultipartFile(MultipartFile uploadfile1,
                                                      MultipartFile uploadfile2,
                                                      MultipartFile uploadfile3,
                                                      MultipartFile uploadfile4,
                                                      MultipartFile uploadfile5,
                                                      MultipartFile uploadfile6,
                                                      MultipartFile uploadfile7,
                                                      MultipartFile uploadfile8,
                                                      MultipartFile uploadfile9) {
    	List<MultipartFile> files = new ArrayList<MultipartFile>();
    	if(uploadfile1!=null && uploadfile1.getSize()>0){
    		files.add(uploadfile1);
    	}
    	if(uploadfile2!=null && uploadfile2.getSize()>0){
    		files.add(uploadfile2);
    	}
    	if(uploadfile3!=null && uploadfile3.getSize()>0){
    		files.add(uploadfile3);
    	}
    	if(uploadfile4!=null && uploadfile4.getSize()>0){
    		files.add(uploadfile4);
    	}
    	if(uploadfile5!=null && uploadfile5.getSize()>0){
    		files.add(uploadfile5);
    	}
    	if(uploadfile6!=null && uploadfile6.getSize()>0){
    		files.add(uploadfile6);
    	}
    	if(uploadfile7!=null && uploadfile7.getSize()>0){
    		files.add(uploadfile7);
    	}
    	if(uploadfile8!=null && uploadfile8.getSize()>0){
    		files.add(uploadfile8);
    	}
    	if(uploadfile9!=null && uploadfile9.getSize()>0){
    		files.add(uploadfile9);
    	}
        return files;
    }
    public static List<Map> getRequestListParams(HttpServletRequest request){
    	List<Map> returns = new ArrayList<Map>();
    	Map params = requestMapToTableMap(request);
    	if(params!=null && params.size()>0){
			for (Object key : params.keySet()) {
				Object value = params.get(key);
				Map param = new HashMap();
				param.put("key_param", key);
				param.put("key_value", value);
				returns.add(param);
			}
    	}
    	return returns;
    }

    public static List<String> listString(List<Map> list,String key){
        List<String> newList = new ArrayList();
        for(Map m : list){
            newList.add((String)m.get(key));
        }
        return newList;
    }
}
