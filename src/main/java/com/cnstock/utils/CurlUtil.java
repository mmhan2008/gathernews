package com.cnstock.utils;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author user01
 * @create 2019/3/12
 */
public class CurlUtil {

    public static String doCurl(String url){
        String param = "-d "+url.substring(url.indexOf("?")+1);
        String[] cmds = {"curl", "-i", param, url};
        ProcessBuilder pb = new ProcessBuilder(cmds);
        pb.redirectErrorStream(true);
        Process p;
        StringBuilder builder = new StringBuilder();
        try {
            p = pb.start();
            BufferedReader br = null;
            String line = null;
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        String url = "http://www.nhc.gov.cn/zwgk/rdts/ejlist.shtml";
        String param = null;
        ProcessBuilder pb = null;
        if(url.contains("?")){
            param = "-d "+url.substring(url.indexOf("?")+1);
            String[] cmds = {"curl" , "-i", param , url};
            pb = new ProcessBuilder(cmds);
        } else {
//            String[] cmds = {"curl" , "-c cookie",url};
                String[] cmds1 = {"curl" , "-H cookie:zA0xIpCczzBN2CPrS1fuL0fUC6EU0I2FCdv13JdHF3GbbbBEztK4NSA9Df4Yi07B",url};
            pb = new ProcessBuilder(cmds1);
        }
        pb.redirectErrorStream(true);
        Process p;
        StringBuilder builder = new StringBuilder();
        try {
            p = pb.start();
            BufferedReader br = null;
            String line = null;
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = br.readLine()) != null) {
//                builder.append(line);
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
