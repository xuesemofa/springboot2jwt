package com.springboot.crm.utils.files;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileTool {
    /**
     * 下载文件
     *
     * @param fileName --文件完整路径
     * @param response
     */
    public static void downloadFile(
            String fileName,
            javax.servlet.http.HttpServletResponse response) {
        try {
//            new String(fileName.getBytes("utf-8"), "iso8859-1")
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename="
                            + new String(fileName.getBytes("utf-8"), "iso8859-1"));
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os;
            try {
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 新建目录
     *
     * @param folderPath 目录
     * @return 返回目录创建后的路径
     */
    public static void createFolder(String folderPath) {
        try {
            File myFilePath = new File(new String(folderPath.getBytes("UTF-8"), "UTF-8"), "UTF-8");
            if (!myFilePath.exists()) {
                boolean b = myFilePath.mkdir();
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件
     *
     * @param filePath 需要创建文件的全路径
     * @return
     */
    public static int createFile(String filePath, String fileName) {
        try {
            File myFilePath = new File(filePath, fileName);
            boolean boo = false;
            if (myFilePath.exists()) {
                boo = myFilePath.delete();
                if (!boo)
//                    删除文件出错
                    return -2;
            }
            boo = myFilePath.createNewFile();
            if (!boo)
//                创建文件错误
                return -3;
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
//            文件操作出错，请检查是否占用
            return -1;
        }
    }

    public static boolean replaceFileStr(String filepath, String filepath2, String sourceStr, String targetStr) {
        try {
//            FileReader fis = new FileReader(filepath);  // 创建文件输入流
//            BufferedReader br = new BufferedReader(fis);

            InputStreamReader fis = new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(fis);

            char[] data = new char[1024];               // 创建缓冲字符数组
            int rn = 0;
            StringBuilder sb = new StringBuilder();       // 创建字符串构建器
            //fis.read(data)：将字符读入数组。在某个输入可用、发生 I/O 错误或者已到达流的末尾前，此方法一直阻塞。读取的字符数，如果已到达流的末尾，则返回 -1
            while ((rn = fis.read(data)) > 0) {         // 读取文件内容到字符串构建器
                String str = String.valueOf(data, 0, rn);//把数组转换成字符串
                sb.append(str);
            }
            fis.close();// 关闭输入流
            // 从构建器中生成字符串，并替换搜索文本
            String str = sb.toString().replace(sourceStr, new String(targetStr.getBytes("UTF-8"), "UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath2, false), "UTF-8"));
            writer.write(str);
            writer.close();
//            FileWriter fout = new FileWriter(filepath2);// 创建文件输出流
//            fout.write(str.toCharArray());// 把替换完成的字符串写入文件内
//            fout.close();// 关闭输出流

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean replaceFileStr2(String filepath, String filepath2, String sourceStr, String targetStr) {
        try {
//            FileReader fis = new FileReader(filepath);  // 创建文件输入流
//            BufferedReader br = new BufferedReader(fis);

            InputStreamReader fis = new InputStreamReader(new FileInputStream("D:\\zhiban.html"), StandardCharsets.UTF_8);

            char[] data = new char[1024];               // 创建缓冲字符数组
            int rn = 0;
            StringBuilder sb = new StringBuilder();       // 创建字符串构建器
            //fis.read(data)：将字符读入数组。在某个输入可用、发生 I/O 错误或者已到达流的末尾前，此方法一直阻塞。读取的字符数，如果已到达流的末尾，则返回 -1
            while ((rn = fis.read(data)) > 0) {         // 读取文件内容到字符串构建器
                String str = String.valueOf(data, 0, rn);//把数组转换成字符串
                sb.append(str);
            }
            fis.close();// 关闭输入流
            // 从构建器中生成字符串，并替换搜索文本
            String str = sb.toString().replace("${th}", new String("只是一个入侵文件".getBytes(StandardCharsets.UTF_8), "UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("\\\\222:123456@192.168.1.170\\新建文件夹\\入侵.html", false), "UTF-8"));
            writer.write(str);
            writer.close();
//            FileWriter fout = new FileWriter(filepath2);// 创建文件输出流
//            fout.write(str.toCharArray());// 把替换完成的字符串写入文件内
//            fout.close();// 关闭输出流

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除空目录
     *
     * @param folderPath 目录
     * @return 返回目录创建后的路径
     */
    public static void deleteFolder(String folderPath) {
        try {
            File myFilePath = new File(new String(folderPath.getBytes("UTF-8"), "UTF-8"), "UTF-8");
            if (myFilePath.exists()) {
                boolean b = myFilePath.delete();
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
