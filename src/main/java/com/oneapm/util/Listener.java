package com.oneapm.util;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.oneapm.dao.DBManager;
import com.oneapm.dao.MongoConnection;
import com.oneapm.data.RedisBase;
import com.oneapm.web.SupportAction;

public class Listener implements ServletContextListener {

        private static final Logger LOG = LoggerFactory.getLogger(Listener.class);

        public void contextDestroyed(ServletContextEvent arg0) {
                Run.thread.stop();
                MongoConnection.close();
                LOG.info("error..........................................");
        }

        public void contextInitialized(ServletContextEvent arg0) {
                LOG.info("db finish..........................................");
                try {
                        init(arg0);
                } catch (DocumentException e) {
                        e.printStackTrace();
                }
        }

        public void init(ServletContextEvent event) throws DocumentException {
                SAXReader reader = new SAXReader();
                SupportAction.systemApplication = event.getServletContext();
                Document doc = reader.read(new File(SupportAction.systemApplication.getRealPath("/"), "WEB-INF/classes/systemserver-config.xml"));
                Element root = doc.getRootElement();
                LOG.info("init staticPath .....................................................");
                try {
                        String staticPath = root.selectSingleNode("staticPath").getText().trim();
                        SupportAction.systemApplication.setAttribute("staticPath", staticPath);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                LOG.info("init basePath.....................................................");
                try {
                        String basePath = root.selectSingleNode("basePath").getText().trim();
                        SupportAction.systemApplication.setAttribute("basePath", basePath);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                LOG.info("init crmaddress...................................................");
                try {
                        String crmAddress = root.selectSingleNode("crmAddress").getText().trim();
                        SupportAction.systemApplication.setAttribute("crmAddress", crmAddress);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                LOG.info("init mongodb.......................................................");
                try {
                        initMongoDao((Element) root.selectSingleNode("mongodb/mongodata"));
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                try {
                        String filesystem = root.selectSingleNode("fileSystem").getText().trim();
                        SupportAction.systemApplication.setAttribute("filesystem", filesystem);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                LOG.info("init mail mode .....................................................");
                try {
                        SupportAction.mailModeInit();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                LOG.info("init tag .....................................................");
                try {
                        SupportAction.tagInit();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                LOG.info("init redis ...................................................");
                try {
                        initRedis((Element) root.selectSingleNode("redis/manage"));
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                LOG.info("init mysql ...................................................");
                try {
                        initMysql((Element) root.selectSingleNode("mysql/manage"));
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                LOG.info("init finished .....................................................");
                LOG.info("start run ......................................................");
                Run.thread.start();
                new Thread(new Run()).start();
        }

        public static void initMongoDao(Element Element) throws UnknownHostException {
                Element element = (Element) Element.selectSingleNode("serverNode");
                String host = element.getTextTrim();
                element = (Element) Element.selectSingleNode("serverPort");
                int port = Integer.parseInt(element.getTextTrim());
                element = (Element) Element.selectSingleNode("autoConnectRetry");
                boolean autoConnectRetry = Boolean.parseBoolean(element.getText().trim());
                element = (Element) Element.selectSingleNode("connectionsPerHost");
                int connectionsPerHost = Integer.parseInt(element.getText().trim());
                element = (Element) Element.selectSingleNode("maxWaitTime");
                int maxWaitTime = Integer.parseInt(element.getText().trim());
                element = (Element) Element.selectSingleNode("defauledb");
                String defaultDb = element.getText().trim();
                List<ServerAddress> replicaSet = new ArrayList<ServerAddress>();
                replicaSet.add(new ServerAddress(host, port));
                MongoClientOptions option = MongoClientOptions.builder().autoConnectRetry(autoConnectRetry).connectionsPerHost(connectionsPerHost).maxWaitTime(maxWaitTime).build();
                MongoConnection.init(replicaSet, option, defaultDb);
                LOG.info("mongodb连接参数初始化完毕.....");

        }
        
        public static void initRedis(Element Element){
                Element element = (Element) Element.selectSingleNode("serverNode");
                String host = element.getTextTrim();
                element = (Element) Element.selectSingleNode("serverPort");
                String port = element.getTextTrim();
                element = (Element) Element.selectSingleNode("poolSize");
                int poolSize = Integer.parseInt(element.getTextTrim());
                String passwordString = Element.selectSingleNode("password").getText();
                RedisBase.init(host, poolSize, port, passwordString);
        }
        
        public static void initMysql(Element Element){
        	Element element = (Element) Element.selectSingleNode("driver");
            String driver = element.getTextTrim();
            element = (Element) Element.selectSingleNode("url");
            String url = element.getTextTrim();
            element = (Element) Element.selectSingleNode("username");
            String username = element.getText().trim();
            element = (Element) Element.selectSingleNode("password");
            String password = element.getText().trim();
            DBManager.init(driver, url, username, password);
    }

}
