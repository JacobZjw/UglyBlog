package com.ugly.blog.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * @author JwZheng
 * @date 2021/4/22 14:17
 */
@Slf4j
public class JsonResourceUtils {

    //filename 为文件名字 如 “/json/app_version_info.json”
    public static JSONObject getJsonObjFromResource(String filename) {
        JSONObject json = null;

        if (!filename.contains(".json")) {
            filename += ".json";
        }

        try {
            URL url = JsonResourceUtils.class.getResource(filename);
            String path = url.getPath();
            File file = new File(path);
            if (file.exists()) {
                String content = FileUtils.readFileToString(file, "UTF-8");
                json = JSON.parseObject(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
