package com.nowcoder.util;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Created by Administrator on 2017/8/9.
 */
public class RedisKeyUtil {
    private static String SPLIT=":";
    private static String BIZ_LIKE="LIKE";
    private static String BIZ_DISLIKE="DISLIKE";
    private static String BIZ_EVENT="EVENT";

    public static String getEventQueueKey(){
        return BIZ_EVENT;
    }

    public static String getLIkeKey(int entityId,int entityType){
        return BIZ_LIKE+SPLIT+String.valueOf(entityType) +SPLIT+String.valueOf(entityId);
    }

    public static String getDisLikeKey(int entityId,int entityType){
        return BIZ_DISLIKE+SPLIT+String.valueOf(entityType) +SPLIT+String.valueOf(entityId);
    }

}
