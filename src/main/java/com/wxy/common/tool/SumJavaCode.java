package com.wxy.common.tool;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @Author wxy
 * @Date 19-7-29 上午10:06
 * @Description TODO 代码检索工具
 **/
@Slf4j
public class SumJavaCode {

    private static long normalLines = 0; // 代码行
    private static long commentLines = 0; // 注释行
    private static long whiteLines = 0; // 空行

    /**
     * 统计
     *
     * @param resources 检索目录
     */
    public static void sum(String[] resources) {
        SumJavaCode sjc = new SumJavaCode();
        for (String resource : resources) {
            File f = new File(resource);
            sjc.treeFile(f);
            log.info("检索目录：{},代码行：{},注释行：{},空行：{}", f.getName(), normalLines, commentLines, whiteLines);
            // 重置
            normalLines = 0;
            commentLines = 0;
            whiteLines = 0;
        }
    }

    /**
     * 　* 查找出一个目录下所有的.java文件
     * 　*
     * 　* @param f 要查找的目录
     */
    private void treeFile(File f) {
        File[] childs = f.listFiles();
        for (int i = 0; i < childs.length; i++) {
            if (!childs[i].isDirectory()) {
                if (childs[i].getName().matches(".*\\.java$")) {
                    //System.out.println(childs[i].getName());
                    sumCode(childs[i]);
                }
            } else {
                treeFile(childs[i]);
            }
        }
    }

    /**
     * 　* 计算一个.java文件中的代码行，空行，注释行
     * 　*
     * 　* @param file
     * 　* 要计算的.java文件
     */
    private void sumCode(File file) {
        BufferedReader br = null;
        boolean comment = false;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            try {
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.matches(" ^[\\s&&[^\\n]]*$")) {
                        whiteLines++;// 空白行
                    } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                        commentLines++;
                        comment = true;
                    } else if (true == comment) {
                        commentLines++;
                        if (line.endsWith("*/")) {
                            comment = false;
                        }
                    } else if (line.startsWith("//")) {
                        commentLines++;
                    } else {
                        normalLines++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}