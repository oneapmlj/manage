package com.oneapm.service.info;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.DataLiucunDaoImpl;
import com.oneapm.dao.info.impl.SignLiucunDaoImpl;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.DataLiucun;
import com.oneapm.dto.info.DataLiucunShow;
import com.oneapm.dto.info.DataLiucunView;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.info.SignLiucun;
import com.oneapm.dto.info.SignLiucunShow;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

public class LiucunYunyingService {
        protected static final Logger LOG = LoggerFactory.getLogger(LiucunYunyingService.class);

        public static List<List<DataLiucunShow>> findByType(int agent) throws ParseException {
                List<List<DataLiucunShow>> shows = new ArrayList<List<DataLiucunShow>>();
                List<DataLiucunShow> liucuns = DataLiucunDaoImpl.getInstance().findByType(agent);
                if (liucuns != null && liucuns.size() > 0) {
                        for (DataLiucunShow show : liucuns) {
                                List<DataLiucunShow> lius = new ArrayList<DataLiucunShow>();
                                show.setDataTimeEnd(show.getDataTimeEnd().substring(0, 10));
                                show.setDataTimeStart(show.getDataTimeStart().substring(0, 10));
                                lius.add(show);
                                List<DataLiucunShow> lius1 = DataLiucunDaoImpl.getInstance().findByFromTime(show.getFromTime(), agent);
                                for (DataLiucunShow s : lius1) {
                                        s.setDataTimeEnd(s.getDataTimeEnd().substring(0, 10));
                                        s.setDataTimeStart(s.getDataTimeStart().substring(0, 10));
                                        s.setFromTime(s.getFromTime().substring(0, 10));
                                        lius.add(s);
                                }
                                lius.get(0).setFromTime(lius.get(0).getFromTime().substring(0, 10));
                                shows.add(lius);
                        }
                }
                return shows;
        }

        public static List<List<SignLiucunShow>> findByTypeSign(int agent) throws ParseException {
                List<List<SignLiucunShow>> shows = new ArrayList<List<SignLiucunShow>>();
                List<SignLiucunShow> liucuns = SignLiucunDaoImpl.getInstance().findByType();
                if (liucuns != null && liucuns.size() > 0) {
                        for (SignLiucunShow show : liucuns) {
                                List<SignLiucunShow> lius = new ArrayList<SignLiucunShow>();
                                show.setSignTimeEnd(show.getSignTimeEnd().substring(0, 10));
                                show.setSignTimeStart(show.getSignTimeStart().substring(0, 10));
                                lius.add(show);
                                List<SignLiucunShow> lius1 = SignLiucunDaoImpl.getInstance().findByFromTime(show.getFromTime(), agent);
                                for (SignLiucunShow s : lius1) {
                                        s.setSignTimeEnd(s.getSignTimeEnd().substring(0, 10));
                                        s.setSignTimeStart(s.getSignTimeStart().substring(0, 10));
                                        lius.get(0).setFromTime(lius.get(0).getFromTime().substring(0, 10));
                                        lius.add(s);
                                }
                                shows.add(lius);
                        }
                }
                return shows;
        }

        public static String liucun_yunying_sign(int agent) {
                try {
                        List<List<SignLiucunShow>> shows = findByTypeSign(agent);
                        if (shows == null || shows.size() <= 0) {
                                return OneTools.getResult(0, "没有更多了");
                        }
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        args1.add("liucun");
                        args2.add(getArrayArrayFromSignList(shows));
                        return OneTools.getResult(1, args1, args2);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器内部错误");
                }
        }

        public static String liucun_yunying_data(int agent) {
                try {
                        List<List<DataLiucunShow>> shows = findByType(agent);
                        if (shows == null || shows.size() <= 0) {
                                return OneTools.getResult(0, "没有更多了");
                        }
                        List<String> args1 = new ArrayList<String>();
                        List<Object> args2 = new ArrayList<Object>();
                        args1.add("liucun");
                        args2.add(getArrayArrayFromList(shows));
                        return OneTools.getResult(1, args1, args2);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        return OneTools.getResult(0, "服务器内部错误");
                }
        }

        public static DataLiucunView liucun_yunying_data_view(Admin admin, String dataTime, String fromTime, int agent) {
                DataLiucunView view = new DataLiucunView();
                List<Info> infos = new ArrayList<Info>();
                List<DataLiucun> liucuns = DataLiucunDaoImpl.getInstance().findDataByFromTime(fromTime+" 00:00:00", agent);
                for(int i=0;i<liucuns.size()-1;i++){
                        for(int j=i+1;j<liucuns.size();j++){
                                if(liucuns.get(i).getUserId().equals(liucuns.get(j).getUserId())){
                                        liucuns.remove(j);
                                        j--;
                                }
                        }
                }
                view.setCount(liucuns.size());
                view.setInTimeStart(liucuns.get(0).getDataTime().substring(0, 10));
                view.setInTimeEnd(TimeTools.week(liucuns.get(0).getDataTime(), 1, 1).substring(0, 10));
                for (DataLiucun liucun : liucuns) {
                        Info info = InfoService.findByUserId(liucun.getUserId());
                        info.setCustom(0);
                        infos.add(info);
                }
                
                List<DataLiucun> lius = DataLiucunDaoImpl.getInstance().findDataByIdAndDataTime(fromTime+" 00:00:00", dataTime + " 00:00:00", agent);
                if (lius == null || lius.size() <= 0) {
                        view.setInfos(infos);
                } else {
                        for(int i=0;i<lius.size()-1;i++){
                                for(int j=i+1;j<lius.size();j++){
                                        if(lius.get(i).getUserId().equals(lius.get(j).getUserId())){
                                                lius.remove(j);
                                                j--;
                                        }
                                }
                        }
                        view.setPercent(lius.size() * 100 / liucuns.size());
                        view.setLength(lius.size());
                        view.setDataTimeStart(lius.get(0).getDataTime().substring(0, 10));
                        view.setDataTimeEnd(TimeTools.week(lius.get(0).getDataTime(), 1, 1).substring(0, 10));
                        for (int i = 0; i < infos.size(); i++) {
                                Info info = infos.get(i);
                                boolean in = false;
                                for (DataLiucun liu : lius) {
                                        if (info.getUserId() == liu.getUserId() || info.getUserId().equals(liu.getUserId())) {
                                                infos.remove(i);
                                                i--;
                                                in = true;
                                                break;
                                        }
                                }
                                if (!in) {
                                        info.setCustom(1);
                                }
                        }
                        for (DataLiucun liu : lius) {
                                Info info = InfoService.findByUserId(liu.getUserId(), admin);
                                info.setCustom(2);
                                infos.add(info);
                        }
                }
                view.setInfos(infos);
                return view;
        }
        
        public static DataLiucunView liucun_yunying_sign_view(int type, Admin admin, String dataTime, String fromTime, int agent) {
                DataLiucunView view = new DataLiucunView();
                List<Info> infos = new ArrayList<Info>();
                List<SignLiucun> liucuns = SignLiucunDaoImpl.getInstance().findSignByFromTime(fromTime+" 00:00:00");
                view.setCount(liucuns.size());
                view.setInTimeStart(liucuns.get(0).getDataTime().substring(0, 10));
                view.setInTimeEnd(TimeTools.week(liucuns.get(0).getDataTime(), 1, 1).substring(0, 10));
                for (SignLiucun liucun : liucuns) {
                        Info info = InfoService.findByUserId(liucun.getUserId());
                        info.setCustom(0);
                        infos.add(info);
                }
                if(type == 2){
                        List<SignLiucun> lius = SignLiucunDaoImpl.getInstance().findDownloadByIdAndDataTime(fromTime+" 00:00:00", dataTime + " 00:00:00", agent);
                        if (lius == null || lius.size() <= 0) {
                                view.setInfos(infos);
                        } else {
                                for(int i=0;i<lius.size()-1;i++){
                                        for(int j=i+1;j<lius.size();j++){
                                                if(lius.get(i).getUserId().equals(lius.get(j).getUserId())){
                                                        lius.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                view.setPercent(lius.size() * 100 / liucuns.size());
                                view.setLength(lius.size());
                                view.setType(2);
                                view.setDataTimeStart(lius.get(0).getDataTime().substring(0, 10));
                                view.setDataTimeEnd(TimeTools.week(lius.get(0).getDataTime(), 1, 1).substring(0, 10));
                                for (int i = 0; i < infos.size(); i++) {
                                        Info info = infos.get(i);
                                        boolean in = false;
                                        for (SignLiucun liu : lius) {
                                                if (info.getUserId() == liu.getUserId() || info.getUserId().equals(liu.getUserId())) {
                                                        infos.remove(i);
                                                        i--;
                                                        in = true;
                                                        break;
                                                }
                                        }
                                        if (!in) {
                                                info.setCustom(1);
                                        }
                                }
                                for (SignLiucun liu : lius) {
                                        Info info = InfoService.findByUserId(liu.getUserId(), admin);
                                        info.setCustom(2);
                                        infos.add(info);
                                }
                        }
                }else if(type == 3){
                        List<SignLiucun> lius = SignLiucunDaoImpl.getInstance().findAppByIdAndDataTime(fromTime+" 00:00:00", dataTime + " 00:00:00", agent);
                        if (lius == null || lius.size() <= 0) {
                                view.setInfos(infos);
                        } else {
                                for(int i=0;i<lius.size()-1;i++){
                                        for(int j=i+1;j<lius.size();j++){
                                                if(lius.get(i).getUserId().equals(lius.get(j).getUserId())){
                                                        lius.remove(j);
                                                        j--;
                                                }
                                        }
                                }
                                view.setPercent(lius.size() * 100 / liucuns.size());
                                view.setLength(lius.size());
                                view.setType(3);
                                view.setDataTimeStart(lius.get(0).getDataTime().substring(0, 10));
                                view.setDataTimeEnd(TimeTools.week(lius.get(0).getDataTime(), 1, 1).substring(0, 10));
                                for (int i = 0; i < infos.size(); i++) {
                                        Info info = infos.get(i);
                                        boolean in = false;
                                        for (SignLiucun liu : lius) {
                                                if (info.getUserId() == liu.getUserId() || info.getUserId().equals(liu.getUserId())) {
                                                        infos.remove(i);
                                                        i--;
                                                        in = true;
                                                        break;
                                                }
                                        }
                                        if (!in) {
                                                info.setCustom(1);
                                        }
                                }
                                for (SignLiucun liu : lius) {
                                        Info info = InfoService.findByUserId(liu.getUserId(), admin);
                                        info.setCustom(2);
                                        infos.add(info);
                                }
                        }
                }
                
                view.setInfos(infos);
                return view;
        }

        @SuppressWarnings("unchecked")
        public static JSONArray getArrayArrayFromList(List<List<DataLiucunShow>> shows) {
                JSONArray array = new JSONArray();
                try {
                        for (List<DataLiucunShow> show : shows) {
                                array.add(getArrayFromList(show));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return array;
        }

        @SuppressWarnings("unchecked")
        public static JSONArray getArrayArrayFromSignList(List<List<SignLiucunShow>> shows) {
                JSONArray array = new JSONArray();
                try {
                        for (List<SignLiucunShow> show : shows) {
                                array.add(getArrayFromSignList(show));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return array;
        }

        @SuppressWarnings("unchecked")
        public static JSONArray getArrayFromList(List<DataLiucunShow> shows) {
                JSONArray array = new JSONArray();
                try {
                        for (DataLiucunShow show : shows) {
                                array.add(getJSONFromDataLiucunShow(show));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return array;
        }

        @SuppressWarnings("unchecked")
        public static JSONArray getArrayFromSignList(List<SignLiucunShow> shows) {
                JSONArray array = new JSONArray();
                try {
                        for (SignLiucunShow show : shows) {
                                array.add(getJSONFromSignLiucunShow(show));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return array;
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromDataLiucunShow(DataLiucunShow show) {
                JSONObject object = new JSONObject();
                try {
                        object.put("id", show.getId());
                        object.put("type", show.getType());
                        object.put("count", show.getCount());
                        object.put("percent", show.getPercent());
                        object.put("time", show.getTime());
                        object.put("dataTimeStart", show.getDataTimeStart());
                        object.put("dataTimeEnd", show.getDataTimeEnd());
                        object.put("number", show.getNumber());
                        object.put("agent", show.getAgent());
                        object.put("from_time", show.getFromTime());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }

        @SuppressWarnings("unchecked")
        public static JSONObject getJSONFromSignLiucunShow(SignLiucunShow show) {
                JSONObject object = new JSONObject();
                try {
                        object.put("id", show.getId());
                        object.put("type", show.getType());
                        object.put("count_download", show.getCount_download());
                        object.put("count_app", show.getCount_app());
                        object.put("percent_download", show.getPercent_download());
                        object.put("percent_app", show.getPercent_app());
                        object.put("signTimeStart", show.getSignTimeStart());
                        object.put("signTimeEnd", show.getSignTimeEnd());
                        object.put("number_download", show.getNumber_download());
                        object.put("number_app", show.getNumber_app());
                        object.put("from_time", show.getFromTime());
                        object.put("agent", show.getAgent());
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return object;
        }
}
