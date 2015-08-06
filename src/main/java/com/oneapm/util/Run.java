package com.oneapm.util;

import com.oneapm.service.info.ZhengzailianxiService;

public class Run implements Runnable{
        public static Thread thread = new Thread(new Run());
        public void run() {
                while(true){
                        ZhengzailianxiService.push();
                        try {
                                Thread.sleep(5000);
                        } catch (InterruptedException e) {
                        }
                }
        }

}
