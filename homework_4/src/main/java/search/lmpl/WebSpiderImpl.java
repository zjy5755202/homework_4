package search.lmpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import search.Parser;
import search.WebSpider;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WebSpiderImpl implements WebSpider {
    public Parser getParser()
    {
        return new ParserImpl();
    }

    public List<String> getHtmlFromWeb() throws IOException {
        List<String> urlList= new ArrayList<String>();
        Document doc;
        //从页面获取url list
        String Url="http://gradschool.oregonstate.edu/programs";
       try {
            doc = Jsoup.connect(Url)
                   .data("query", "Java")
                   .userAgent("Mozilla")
                   .cookie("auth", "token")
                   .timeout(3000)
                   .get();
       }
       catch (Exception e){
           doc = null;
           e.printStackTrace();
       }
//选取program板块
        Elements content = doc.select("#block-system-main > div > div > div.view-content");
       //在板块里面选出每行项目
       //Elements viewsrow = content.select("#block-system-main > div > div > div.view-content > div:nth-child(2)");
        Elements viewsrow = content.select("[title=Program details]");
        //在每行里面获取urlLink
        Elements links = viewsrow.select("a");
        for (Iterator it = links.iterator(); it.hasNext();) {
            Element e = (Element) it.next();
            urlList.add("http://gradschool.oregonstate.edu"+e.attr("href"));
        }
//        for (Element link : links) {
//            String linkText = link.text();
//             String finallinkText ="http://gradschool.oregonstate.edu"+linkText;
//            urlList.add(finallinkText);
//        }
        return urlList;
    }
}
