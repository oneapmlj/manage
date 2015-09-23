package com.oneapm.service.info;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.group.impl.UserGroupsDaoImpl;
import com.oneapm.dao.info.impl.GuanlianDaoImpl;
import com.oneapm.dto.info.Guanlian;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class GuanlianService {

        protected static final Logger LOG = LoggerFactory.getLogger(GuanlianService.class);

        public static String add(Long userId, Long guanlianId){
                try{
                        if(userId == null){
                                return  OneTools.getResult(0, "必须是注册用户");
                        }
                        if(!UserGroupsDaoImpl.getInstance().exist(guanlianId)){
                        	return OneTools.getResult(0, guanlianId+"关联账号不存在，请核对后添加");
                        }
                        if(!GuanlianDaoImpl.getInstance().exist(userId, guanlianId)){
                                if(GuanlianDaoImpl.getInstance().exist(guanlianId)){
                                        return OneTools.getResult(0, guanlianId+"已经有关联帐号，请进入添加");
                                }
                                if(!GuanlianDaoImpl.getInstance().exist(userId)){
                                        GuanlianDaoImpl.getInstance().insert(GuanlianDaoImpl.getInstance().getIdest(),
                                                        userId, userId, 1, TimeTools.format());
                                        GuanlianDaoImpl.getInstance().insert(GuanlianDaoImpl.getInstance().getIdest(),
                                        				guanlianId, guanlianId, 0, TimeTools.format());
                                        GuanlianDaoImpl.getInstance().insert(GuanlianDaoImpl.getInstance().getIdest(),
                                                        guanlianId, userId, 0, TimeTools.format());
                                        GuanlianDaoImpl.getInstance().insert(GuanlianDaoImpl.getInstance().getIdest(),
                                                        userId, guanlianId, 0, TimeTools.format());
                                }else{
//                                        GuanlianDaoImpl.getInstance().insert(GuanlianDaoImpl.getInstance().getIdest(),
//                                                                userId, guanlianId, 0, TimeTools.format());
                                        List<Guanlian> guanlians = GuanlianDaoImpl.getInstance().findByUserId(userId); 
                                        for(Guanlian guanlian : guanlians){
                                                if(guanlian.getRole() == 1){
                                                        GuanlianDaoImpl.getInstance().insert(GuanlianDaoImpl.getInstance().getIdest(),
                                                                        guanlianId, guanlian.getGuanlianId(), 0, TimeTools.format());
                                                }else{
                                                        GuanlianDaoImpl.getInstance().insert(GuanlianDaoImpl.getInstance().getIdest(),
                                                                        guanlianId, guanlian.getGuanlianId(), 0, TimeTools.format());
                                                }
                                                GuanlianDaoImpl.getInstance().insert(GuanlianDaoImpl.getInstance().getIdest(),
                                                                guanlian.getGuanlianId(), guanlianId, 0, TimeTools.format());
                              
                                        }
                                        GuanlianDaoImpl.getInstance().insert(GuanlianDaoImpl.getInstance().getIdest(),
                                        		guanlianId, guanlianId, 0, TimeTools.format());
                                }
                                JSONObject object = new JSONObject();
                                object.put("status", 1);
                                object.put("guanlian_id", guanlianId);
                                return object.toString();
                        }else{
                                return OneTools.getResult(0, "已经是关联帐号了");
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        
        public static String remove(Long userId, Long guanlianId){
                try{
                        if(!GuanlianDaoImpl.getInstance().exist(userId, guanlianId)){
                                return OneTools.getResult(0, "不是关联帐号");
                        }
                        int role = GuanlianDaoImpl.getInstance().findUserRole(userId);
                        if(role < 1){
                                return OneTools.getResult(0, "不是主帐号，无权限移除关联帐号");
                        }
                        if(GuanlianDaoImpl.getInstance().drop(guanlianId)){
                        	if(GuanlianDaoImpl.getInstance().findTheMainSize(userId)==1){
                        		GuanlianDaoImpl.getInstance().drop(userId);
                        	}
                                JSONObject object = new JSONObject();
                                object.put("status", 1);
                                object.put("guanlian_id", guanlianId);
                                return object.toString();
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器错误");
        }
        
        public static List<Guanlian> findByUserId(Long userId){
                return GuanlianDaoImpl.getInstance().findByUserId(userId);
        }
        public static String findAllGuanlian(List<Long> userIdList){
        	/*List<Guanlian> listAll = new ArrayList<Guanlian>();*/
        	JSONObject object = new JSONObject();
        	int role = 1;
        	for(int i = 0; i < userIdList.size(); i++){
        		List<Guanlian> list = GuanlianDaoImpl.getInstance().findByUserId(userIdList.get(i));
        		for(int l = 0; l < list.size(); l++){
        		/*listAll.add(list.get(l));*/
        		if(list.get(l).getRole() == role){
        			 object.put("status", 1);
        	         object.put("user_id", list.get(l).getUserId());
        	         
        		}
        		}
        	}
   
        	
           
            
            return object.toString();
    }
        
        public static String change(Long userId, Long guanlianId){
                try{
                        int role = GuanlianDaoImpl.getInstance().findUserRole(userId);
                        if(role != 1){
                                return OneTools.getResult(0, "只有主帐号才能更换主帐号");
                        }
                        if(!GuanlianDaoImpl.getInstance().exist(userId, guanlianId)){
                                return OneTools.getResult(0, "转移目标不是管理帐号");    
                        }
                        if(GuanlianDaoImpl.getInstance().remove(userId)){
                                GuanlianDaoImpl.getInstance().add(guanlianId);
                                JSONObject object = new JSONObject();
                                object.put("status", 1);
                                object.put("user_id", userId);
                                object.put("guanlian_id", guanlianId);
                                return object.toString();
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
}
