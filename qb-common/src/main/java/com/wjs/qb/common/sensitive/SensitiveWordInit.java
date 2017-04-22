package com.wjs.qb.common.sensitive;

import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.common.base.util.CloseableUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @version 1.0
 * @Description: 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 * @Project：test
 * @Author : chenming
 * @Date ： 2014年4月20日 下午2:27:06
 * <p>
 * 本人对部分代码进行了一些修改(由于要紧急上线，将来再修改)
 */
public class SensitiveWordInit {
    private static final String ENCODING = "utf-8";//字符编码
    public Map<String, String> sensitiveWordMap;

    public SensitiveWordInit() {
    }

    /**
     * @author chenming
     * @date 2014年4月20日 下午2:28:32
     * @version 1.0
     */
    public Map<String, String> initKeyWord() {
        try {
            //读取敏感词库
            Set<String> keyWordSet = readSensitiveWordFile();
            //将敏感词库加入到HashMap中
            addSensitiveWordToHashMap(keyWordSet);
            //spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
        } catch (Exception e) {
            throw new BusinessExecption("qb17");
        }
        return sensitiveWordMap;
    }

    /**
     * @param keyWordSet 敏感词库
     * @author chenming
     * @date 2014年4月20日 下午3:04:20
     * @version 1.0
     */
    private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while (iterator.hasNext()) {
            key = iterator.next();    //关键字
            nowMap = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i);       //转换成char型
                Object wordMap = nowMap.get(keyChar);       //获取

                if (wordMap != null) {        //如果存在该key，直接赋值
                    nowMap = (Map) wordMap;
                } else {     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String, String>();
                    newWorMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }
                if (i == key.length() - 1) {
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
    }

    /**
     * 读取敏感词库中的内容，将内容添加到set集合中
     *
     * @return
     * @throws Exception
     * @author chenming
     * @date 2014年4月20日 下午2:31:18
     * @version 1.0
     */
    @SuppressWarnings("resource")
    private Set<String> readSensitiveWordFile() {
        Set<String> set = null;
        File file = new File("/opt/wechat/config/qb/SensitiveWord.txt");
//        File file = new File("E:/SensitiveWord.txt");
        FileInputStream fileInputStream = null;
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(file);
            read = new InputStreamReader(fileInputStream, ENCODING);
            if (file.isFile() && file.exists()) {      //文件流是否存在
                set = new HashSet<String>();
                bufferedReader = new BufferedReader(read);
                String temp = null;
                while ((temp = bufferedReader.readLine()) != null) {    //读取文件，将文件内容放入到set中
                    set.add(temp);
                }
            } else {
                throw new RuntimeException("敏感词库文件不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            CloseableUtil.close(bufferedReader, read, fileInputStream);
        }
        return set;
    }
}
