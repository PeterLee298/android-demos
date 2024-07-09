package com.bitmask.android.sqlitesimple.sdk;

import android.app.Application;
import android.content.res.XmlResourceParser;
import android.hardware.lights.LightState;

import androidx.annotation.XmlRes;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *    解析db_tables.xml文件中的表名，返回表名列表
 */
public class RegeditParser {
    private static RegeditParser regeditParser;

    private RegeditParser() {
    }

    public static RegeditParser getInstance() {
        if (regeditParser == null) {
            synchronized (RegeditParser.class) {
                if (regeditParser == null) {
                    regeditParser = new RegeditParser();
                }
            }
        }
        return regeditParser;
    }

    public List<String> getTableNames(Application application, @XmlRes int id) {
        // 解析db_tables.xml文件中的表名，返回表名列表
        List<String> tableList = new ArrayList<>();
        XmlResourceParser parser = application.getResources().getXml(id);

        try {
            int eventType = parser.getEventType();
            while (eventType!= XmlResourceParser.END_DOCUMENT) {
                if (eventType == XmlResourceParser.START_TAG) {
                    String tagName = parser.getName();
                    if ("table".equals(tagName)) {
//                        String tableName = parser.getAttributeValue(null, "name");
                        String tableName = parser.getAttributeValue(0);
                        tableList.add(tableName);
                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        parser.close();

        return tableList;
    }



}
