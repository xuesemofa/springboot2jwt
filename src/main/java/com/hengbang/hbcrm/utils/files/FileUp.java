package com.hengbang.hbcrm.utils.files;

import java.io.*;

public class FileUp {

    public static void main(String[] args) throws Exception {
        String srcPath = "/usr/local/tools/workSpace/hbcrm/target/hbcrm.jar";
        File parentDir = new File("\\\\192.168.1.178\\fx");
        File targetPath = new File(parentDir, "hbcrm.jar");
        InputStream in = new FileInputStream(srcPath);
        OutputStream out = new FileOutputStream(targetPath);
        try {
            byte[] bs = new byte[1024];
            int len = -1;
            while ((len = in.read(bs)) != -1) {
                out.write(bs, 0, len);
            }
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("完成");
    }
}
