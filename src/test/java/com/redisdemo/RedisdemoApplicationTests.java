package com.redisdemo;

import com.redisdemo.service.impl.UserServiceImpl;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisdemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    public void test() {
        Long start = System.currentTimeMillis();
        createXml();
        System.out.println("运行时间：" + (System.currentTimeMillis() - start));
    }

    /**
     * 生成xml方法
     */
    public static void createXml() {
        try {
            // 1、创建document对象
            Document document = DocumentHelper.createDocument();
            // 2、创建根节点rss
            Element rss = document.addElement("rss");
            // 3、向rss节点添加version属性
            rss.addAttribute("version", "2.0");
            // 4、生成子节点及子节点内容
            Element channel = rss.addElement("channel");
            Element title = channel.addElement("title");
            title.setText("国内最新新闻");
            // 5、设置生成xml的格式
            OutputFormat format = new OutputFormat();
            format.setEncoding("utf-8");// 设置XML文件的编码格式
            format.setLineSeparator("\r\n");
            format.setTrimText(false);
            format.setNewlines(true);
            format.setIndent(true);
            format.setIndent("    ");


            // 6、生成xml文件
            File file = new File("D:/rss.xml");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            // 设置是否转义，默认使用转义字符
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
            System.out.println("生成rss.xml成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成rss.xml失败");
        }
    }

}
