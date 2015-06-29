package com.oneapm.util;

import com.oneapm.service.info.ZhengzailianxiService;

public class Run implements Runnable{

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
