package com.oneapm.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grade {
        private List<Quanxian> list;

        private Map<Integer, Quanxian> map;

        public Grade() {
                list = new ArrayList<Quanxian>();
                map = new HashMap<Integer, Quanxian>();
                // 所有权限
                list.add(new Quanxian(999, "q_999_e", "全权限", 1));
                map.put(999, new Quanxian(999, "q_999_e", "全权限", 1));
                // 发邮件
                list.add(new Quanxian(100, "q_100_e", "发邮件", 1));
                map.put(100, new Quanxian(100, "q_100_e", "发邮件", 1));
                // 添加用户
                list.add(new Quanxian(101, "q_101_e", "添加用户", 1));
                map.put(101, new Quanxian(101, "q_101_e", "添加用户", 1));
                //
                list.add(new Quanxian(102, "q_102_e", "添加名片", 1));
                map.put(102, new Quanxian(102, "q_102_e", "添加名片", 1));
                //
                list.add(new Quanxian(103, "q_103_e", "添加记录", 1));
                map.put(103, new Quanxian(103, "q_103_e", "添加记录", 1));
                //
                list.add(new Quanxian(104, "q_104_e", "查看工单", 1));
                map.put(104, new Quanxian(104, "q_104_e", "查看工单", 1));
                //
                list.add(new Quanxian(105, "q_105_e", "分配", 1));
                map.put(105, new Quanxian(105, "q_105_e", "分配", 1));
                //
                list.add(new Quanxian(106, "q_106_e", "分类", 1));
                map.put(106, new Quanxian(106, "q_106_e", "分类", 1));
                //
                list.add(new Quanxian(107, "q_107_e", "上传文件", 1));
                map.put(107, new Quanxian(107, "q_107_e", "上传文件", 1));
                //
                list.add(new Quanxian(108, "q_108_e", "删除文件", 1));
                map.put(108, new Quanxian(108, "q_108_e", "删除文件", 1));
                //
                list.add(new Quanxian(109, "q_109_e", "分析数据", 1));
                map.put(109, new Quanxian(109, "q_109_e", "分析数据", 1));
                //
                list.add(new Quanxian(110, "q_110_e", "工作记录查看", 1));
                map.put(110, new Quanxian(110, "q_110_e", "工作记录查看", 1));
                //
                list.add(new Quanxian(111, "q_111_e", "可接收任务", 0));
                map.put(111, new Quanxian(111, "q_111_e", "可接收任务", 0));
                //付费信息一级管理
                list.add(new Quanxian(112, "q_112_e", "付费管理", 0));
                map.put(112, new Quanxian(112, "q_112_e", "付费管理", 0));
                //
                list.add(new Quanxian(113, "q_113_e", "用户数据断点", 0));
                map.put(113, new Quanxian(113, "q_113_e", "用户数据断点", 0));
                //
                list.add(new Quanxian(114, "q_114_e", "分配显示优先", 0));
                map.put(114, new Quanxian(114, "q_114_e", "分配显示优先", 0));
              //付费信息二级管理
                list.add(new Quanxian(115, "q_115_e", "付费二级管理", 0));
                map.put(115, new Quanxian(115, "q_115_e", "付费二级管理", 0));
                //推送销售
                list.add(new Quanxian(116, "q_116_e", "推送销售", 0));
                map.put(116, new Quanxian(116, "q_116_e", "推送销售", 0));
                //状态更改
                list.add(new Quanxian(117, "q_117_e", "状态更改", 0));
                map.put(117, new Quanxian(117, "q_117_e", "状态更改", 0));
        }

        public List<Quanxian> getList() {
                return list;
        }

        public void setList(List<Quanxian> list) {
                this.list = list;
        }

        public Map<Integer, Quanxian> getMap() {
                return map;
        }

        public void setMap(Map<Integer, Quanxian> map) {
                this.map = map;
        }
}