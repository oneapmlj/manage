package com.oneapm.service.info;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.oneapm.dao.info.impl.ZhengzailianxiDaoImpl;
import com.oneapm.dto.Zhengzailianxi;
import com.oneapm.dto.Account.Admin;
import com.oneapm.service.account.AccountService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class ZhengzailianxiService {

        protected static final Logger LOG = LoggerFactory.getLogger(ZhengzailianxiService.class);
        
        public static Zhengzailianxi insert(Long adminId, Long infoId){
                Zhengzailianxi zhengzailianxi = findByInfoId(infoId);
                if(zhengzailianxi != null){
                        return null;
                }
                try{
                        Long id = ZhengzailianxiDaoImpl.getInstance().getIdest();
                        zhengzailianxi = new Zhengzailianxi(id, adminId, TimeTools.format(), null, 0, 0, infoId);
                        if(ZhengzailianxiDaoImpl.getInstance().insert(zhengzailianxi)){
                                return zhengzailianxi;
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return zhengzailianxi;
        }
        
        public static void push(){
                try{
                      List<Zhengzailianxi> zhengzailianxis = ZhengzailianxiDaoImpl.getInstance().findAll();
                      if(zhengzailianxis != null && zhengzailianxis.size() > 0){
                              for(Zhengzailianxi zhengzailianxi : zhengzailianxis){
                                      if(zhengzailianxi.getStatus() != 0 && zhengzailianxi.getEndTime() != null){
                                              zhengzailianxi.setStay((int)((TimeTools.formatTime.parse(zhengzailianxi.getEndTime()).getTime() - TimeTools.formatTime.parse(zhengzailianxi.getStartTime()).getTime())/1000/60));
                                      }else{
                                              zhengzailianxi.setStay((int)((new Date().getTime() - TimeTools.formatTime.parse(zhengzailianxi.getStartTime()).getTime())/1000/60));
                                      }
                                      ZhengzailianxiDaoImpl.getInstance().updateStay(zhengzailianxi);
                              }
                      }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
        }
        
        public static void main(String[] args) throws ParseException{
                System.out.println(new Date().getTime());
                System.out.println((new Date().getTime() - TimeTools.formatTime.parse("2015-09-11 16:00:00").getTime())/1000/60);
        }
        
        public static String findPush(Long infoId, Admin admin){
                try{
                        Zhengzailianxi zhengzailianxi = findByInfoId(infoId);
                        if(zhengzailianxi == null){
                                return OneTools.getResult(2, "无人跟进");
                        }
                        zhengzailianxi.setAdminName(AccountService.findName(zhengzailianxi.getAdminId()));
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        object.put("id", zhengzailianxi.getId());
                        object.put("admin_name", zhengzailianxi.getAdminName());
                        object.put("admin_id", zhengzailianxi.getAdminId());
                        object.put("start_time", zhengzailianxi.getStartTime());
                        object.put("stay", zhengzailianxi.getStay());
                        object.put("info_id", zhengzailianxi.getInfoId());
                        object.put("quxiao", 0);
                        if(admin.getId().equals(zhengzailianxi.getAdminId())){
                                object.put("quxiao", 1);
                        }
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        
        public static String lianxi(Long infoId, Admin admin){
                try{
                        Zhengzailianxi zhengzailianxi = findByInfoId(infoId);
                        if(zhengzailianxi != null){
                                return OneTools.getResult(2, "已有人跟进,请刷新查看");
                        }
                        zhengzailianxi = insert(admin.getId(), infoId);
                        zhengzailianxi.setAdminName(AccountService.findName(zhengzailianxi.getAdminId()));
                        JSONObject object = new JSONObject();
                        object.put("status", 1);
                        object.put("id", zhengzailianxi.getId());
                        object.put("admin_name", zhengzailianxi.getAdminName());
                        object.put("admin_id", zhengzailianxi.getAdminId());
                        object.put("start_time", zhengzailianxi.getStartTime());
                        object.put("stay", zhengzailianxi.getStay());
                        object.put("info_id", zhengzailianxi.getInfoId());
                        return object.toJSONString();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        
        public static String unlianxi(Long infoId, Admin admin){
                try{
                        Zhengzailianxi zhengzailianxi = findByInfoId(infoId);
                        if(zhengzailianxi == null){
                                return OneTools.getResult(2, "没有人在跟进,请刷新尝试");
                        }
                        if(!admin.getId().equals(zhengzailianxi.getAdminId())){
                                return OneTools.getResult(3, "不是你在跟进,请刷新尝试");
                        }
                        if(update(admin.getId(), 1, zhengzailianxi.getId())){
                                JSONObject object = new JSONObject();
                                object.put("status", 1);
                                return object.toJSONString();
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        
        public static Zhengzailianxi findByInfoId(Long infoId){
                return ZhengzailianxiDaoImpl.getInstance().findByInfoId(infoId);
        }
        public static Zhengzailianxi findByGroupId(Long groupId){
            return ZhengzailianxiDaoImpl.getInstance().findByGroupId(groupId);
        }
        
        public static Zhengzailianxi findById(Long id){
                return ZhengzailianxiDaoImpl.getInstance().findById(id);
        }
        
        public static List<Zhengzailianxi> findByAdminId(Long adminId){
                return ZhengzailianxiDaoImpl.getInstance().findByAdminId(adminId);
        }
        
        public static boolean update(Long adminId, int status, Long id){
                try{
                        Zhengzailianxi zhengzailianxi = findById(id);
                        if(zhengzailianxi == null)return false;
                        if(!zhengzailianxi.getAdminId().equals(adminId))return false;
                        zhengzailianxi.setStatus(status);
                        if(status == 1){
                                zhengzailianxi.setEndTime(TimeTools.format());
                        }
                        return ZhengzailianxiDaoImpl.getInstance().update(zhengzailianxi);
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
}
