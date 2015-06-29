package com.oneapm.data;

import com.oneapm.dto.info.Info;

public class InfoData extends RedisBase<Info>{

    
    public Long findInfoIdByUserId(Long userId){
        return USER_INFO.get(userId);
    }
}
