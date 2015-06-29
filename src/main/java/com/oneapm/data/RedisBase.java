package com.oneapm.data;

import java.util.concurrent.ConcurrentMap;

import org.redisson.Config;
import org.redisson.Redisson;

import com.oneapm.dto.App;
import com.oneapm.dto.Download;
import com.oneapm.dto.info.Info;

public class RedisBase<T> {

    private static Redisson redisson;
    protected static ConcurrentMap<Long, Long> USER_INFO;
    protected static ConcurrentMap<Long, Info> INFO;
    protected static ConcurrentMap<Long, Download> DOWNLOAD;
    protected static ConcurrentMap<Long, App> APP;
    
    public static void init(){
        Config config = new Config();  
        config.setConnectionPoolSize(10);  
        config.addAddress("10.128.8.247:6379");  
        redisson = Redisson.create(config); 
        USER_INFO = (redisson.getMap("user_info"));
        INFO = (redisson.getMap("info"));
        DOWNLOAD = (redisson.getMap("download"));
        APP = (redisson.getMap("app"));
    }
    
    public T findById(Long id, ConcurrentMap<Long, T> DATA){
        return DATA.get(id);
    }
    
    public void insert(Long id, T t, ConcurrentMap<Long, T> DATA){
        DATA.put(id, t);
    }
}
