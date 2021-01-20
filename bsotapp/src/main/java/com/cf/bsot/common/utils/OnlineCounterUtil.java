package com.cf.bsot.common.utils;

import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在线人数统计
 *
 * @author cf
 * @version 1.0
 * @date 2020/10/9 22:58
 */
@Component
public class OnlineCounterUtil {

    private static Map<String,Long> onlineCountMap = new ConcurrentHashMap<>(64);

    public static void insertUserAvaliableTime(String username){
        long currentTime = System.currentTimeMillis();
        onlineCountMap.put(username,currentTime + 60*1000);
    }

    public static int getOnlineUserCount(){
        int onlineUserCount = 0;
        Iterator<Map.Entry<String, Long>> iterator = onlineCountMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Long> entry = iterator.next();
            Long avaliableTime = entry.getValue();
            if(avaliableTime > System.currentTimeMillis()){
                onlineUserCount++;
            }
        }
        return onlineUserCount;
    }
}
