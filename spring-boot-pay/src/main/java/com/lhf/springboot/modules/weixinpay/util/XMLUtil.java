package com.lhf.springboot.modules.weixinpay.util;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: XMLUtil
 * @Author: liuhefei
 * @Description: XML解析
 * @Date: 2019/12/5 17:40
 */
public class XMLUtil {

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     *
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map doXMLParse(String strxml) throws JDOMException, IOException {
        //过滤关键词，防止XXE漏洞攻击
        strxml = filterXXE(strxml);
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = XMLUtil.getChildrenText(children);
            }

            m.put(k, v);
        }

        // 关闭流
        in.close();

        return m;
    }

    /**
     * 获取子结点的xml
     *
     * @param children
     * @return String
     */
    @SuppressWarnings({ "rawtypes" })
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(XMLUtil.getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }
    /**
     * 通过DOCTYPE和ENTITY来加载本地受保护的文件、替换掉即可
     * 漏洞原理：https://my.oschina.net/u/574353/blog/1841103
          * 防止 XXE漏洞 注入实体攻击
          * 过滤 过滤用户提交的XML数据
          * 过滤关键词：<!DOCTYPE和<!ENTITY，或者SYSTEM和PUBLIC。
         */
    public static String filterXXE(String xmlStr){
        xmlStr = xmlStr.replace("DOCTYPE", "").replace("SYSTEM", "").replace("ENTITY", "").replace("PUBLIC", "");
        return xmlStr;
    }

    /**
     * 微信给出的 XXE漏洞方案
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=23_5
     * @param strXML
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map doXMLParse2(String strXML) throws Exception {
        Map<String,String> m = new HashMap<String,String>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        String FEATURE = null;
        try {
            FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
            documentBuilderFactory.setFeature(FEATURE, true);

            FEATURE = "http://xml.org/sax/features/external-general-entities";
            documentBuilderFactory.setFeature(FEATURE, false);

            FEATURE = "http://xml.org/sax/features/external-parameter-entities";
            documentBuilderFactory.setFeature(FEATURE, false);

            FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
            documentBuilderFactory.setFeature(FEATURE, false);

            documentBuilderFactory.setXIncludeAware(false);
            documentBuilderFactory.setExpandEntityReferences(false);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
        InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
        org.w3c.dom.Document doc = documentBuilder.parse(stream);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int idx=0; idx<nodeList.getLength(); ++idx) {
            Node node = nodeList.item(idx);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                m.put(element.getNodeName(), element.getTextContent());
            }
        }
        stream.close();
        return m;
    }

}
