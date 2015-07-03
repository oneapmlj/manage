package com.oneapm.dao.info.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.tag.Tag;

public class TagDaoImpl extends DaoImplBase<Tag> {
        protected static final Logger LOG = LoggerFactory.getLogger(TagDaoImpl.class);
        protected final String TABLE_NAME = "tag";

        static {
                Instance = new TagDaoImpl();
        }

        private final static TagDaoImpl Instance;

        public static TagDaoImpl getInstance() {
                return Instance;
        }

        public List<Tag> findByLanguage(String language) {
                List<Tag> tags = null;
                try {
                        DBObject object = new BasicDBObject();
                        Pattern pattern = Pattern.compile("^.*" + language.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                        object.put("language", pattern);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        tags = new ArrayList<Tag>();
                        while (cursor.hasNext()) {
                                tags.add(getTagFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return tags;
        }

        public Tag findByInfoId(Long infoId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("info_id", infoId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getTagFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public List<Tag> findTagsByMetric(int metric) {
                List<Tag> tags = null;
                try {
                        DBObject object = new BasicDBObject();
                        object.put("metric", metric);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        tags = new ArrayList<Tag>();
                        while (cursor.hasNext()) {
                                tags.add(getTagFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return tags;
        }

        public List<Tag> findTagsByLoudou(int loudou) {
                List<Tag> tags = null;
                try {
                        DBObject object = new BasicDBObject();
                        object.put("loudou", loudou);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        tags = new ArrayList<Tag>();
                        while (cursor.hasNext()) {
                                tags.add(getTagFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return tags;
        }

        public Tag findById(Long id) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getTagFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public Long getIdest() {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(object).limit(1);
                        if (cursor.hasNext()) {
                                return Long.parseLong(cursor.next().get("id").toString().trim());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 100L;
        }

        public Tag insert(Tag tag) {
                try {
                        DBObject object = new BasicDBObject();
                        tag.setId(getIdest() + 1);
                        object.put("id", tag.getId());
                        object.put("info_id", tag.getInfoId());
                        object.put("category", tag.getCategory());
                        object.put("metric", tag.getMetric());
                        object.put("loudou", tag.getLoudou());
                        object.put("description", tag.getDescription());
                        object.put("status", 0);
                        object.put("from", tag.getFrom());
                        object.put("language", tag.getLanguage());
                        object.put("person", tag.getPerson());
                        object.put("province", tag.getProvince());
                        object.put("fuwuqi", tag.getFuwuqi());
//                        object.put("pv", tag.getPv());
//                        object.put("uv", tag.getUv());
                        object.put("rongzi", tag.getRongzi());
                        if (getDBCollection(TABLE_NAME).insert(object).getN() > -1) {
                                return tag;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public boolean update(Tag tag) {
                try {
                        DBObject object = new BasicDBObject("id", tag.getId());
                        DBObject value = new BasicDBObject();
                        value.put("info_id", tag.getInfoId());
                        value.put("category", tag.getCategory());
                        value.put("metric", tag.getMetric());
                        value.put("loudou", tag.getLoudou());
                        value.put("description", tag.getDescription());
                        value.put("status", tag.getStatus());
                        value.put("from", tag.getFrom());
                        value.put("language", tag.getLanguage());
                        value.put("person", tag.getPerson());
                        value.put("province", tag.getProvince());
                        value.put("fuwuqi", tag.getFuwuqi());
//                        value.put("pv", tag.getPv());
//                        value.put("uv", tag.getUv());
                        value.put("rongzi", tag.getRongzi());
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public long countLoudou(Integer id) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("loudou", id);
                        return getDBCollection(TABLE_NAME).count(object);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 0;
        }

        public long countMetric(Integer id) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("metric", id);
                        return getDBCollection(TABLE_NAME).count(object);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 0;
        }

        public List<Tag> search(int metric, int loudou) {
                List<Tag> tags = null;
                try {
                        if (metric <= 0 && loudou <= 0)
                                return null;
                        DBObject object = new BasicDBObject();
                        if (metric > 0) {
                                object.put("metric", metric);
                        }
                        if (loudou > 0) {
                                object.put("loudou", loudou);
                        }
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        tags = new ArrayList<Tag>();
                        while (cursor.hasNext()) {
                                tags.add(getTagFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return tags;
        }

        public Tag getTagFromResult(DBObject object) {
                Tag tag = null;
                try {
                        Long id = Long.parseLong(object.get("id").toString().trim());
                        Long infoId = Long.parseLong(object.get("info_id").toString().trim());
                        int category = Integer.parseInt(object.get("category").toString().trim());
                        int from = Integer.parseInt(object.get("from").toString().trim());
                        Integer metric = Integer.parseInt(object.get("metric").toString().trim());
                        Integer loudou = Integer.parseInt(object.get("loudou").toString().trim());
                        String description = null;
                        try {
                                description = object.get("description").toString();
                        } catch (Exception e) {
                        }
                        int status = Integer.parseInt(object.get("status").toString().trim());
                        String language = null;
                        Integer province = new Integer(0);
                        Integer person = new Integer(0);
                        Integer pv = new Integer(0);
                        Integer uv = new Integer(0);
                        try {
                                language = object.get("language").toString().trim();
                        } catch (Exception e) {
                        }
                        try {
                                province = Integer.parseInt(object.get("province").toString().trim());
                        } catch (Exception e) {
                        }
                        try {
                                person = Integer.parseInt(object.get("person").toString().trim());
                        } catch (Exception e) {
                        }
//                        try {
//                                pv = Integer.parseInt(object.get("pv").toString().trim());
//                        } catch (Exception e) {
//                        }
//                        try {
//                                uv = Integer.parseInt(object.get("uv").toString().trim());
//                        } catch (Exception e) {
//                        }
                        int fuwuqi = 0;
                        try{
                                fuwuqi = Integer.parseInt(object.get("fuwuqi").toString().trim());
                        }catch(Exception e){}
                        int rongzi = 0;
                        try{
                                rongzi = Integer.parseInt(object.get("rongzi").toString().trim());
                        }catch(Exception e){}
                        tag = new Tag(id, infoId, category, from, metric, loudou, description, status, province, person, language, rongzi, fuwuqi);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return tag;
        }
}
