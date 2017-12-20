package search.lmpl;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import search.Parser;
import vo.Program;

import java.util.UUID;


public class ParserImpl implements Parser{
    public Program parseHtml(String html)
    {
        Document doc;
        Program temp=new Program();
        try{

            doc = Jsoup.connect(html)
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
        String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
        uuid = uuid.replace("-", "");
        temp.setId(uuid);
        temp.setCountry("United States of America");
        temp.setUniversity("Oregonstate University");
        //学院
        String finalschool="";
        Elements school = doc.select("[title=Visit the college website.]");
        if(school!=null)
            finalschool=school.text();
        temp.setSchool(finalschool);
        //项目名称
        String finalprogramName="";
        Elements programName = doc.select("[id=page-title]");
        if(programName!=null) {
            finalprogramName = programName.text();
//去除项目括号里面的内容
            int head=finalprogramName.indexOf("(");
            int next=finalprogramName.indexOf(")")+1;
            String notneed=finalprogramName.substring(head,next);
            finalprogramName=finalprogramName.replace(notneed,"");
        }
        temp.setProgramName(finalprogramName);
        //项目主页
        String finalhomePage="";
        Elements  homePage = doc.select("[title=Visit this program's website.]");
        if(homePage!=null)
            finalhomePage=homePage.attr("href");
        temp.setHomepage(finalhomePage);
        //地址
        String finallocation="";
        Elements location = doc.select(".icon-map-marker");

        if(location!=null)
        { Element templocation = location.parents().first();
            finallocation = templocation.text();
        }
        temp.setLocation(finallocation);
        //项目email
        String finalemail="";
        Elements tempemail = doc.select(".icon-envelope");
        Element parent = tempemail.parents().first();
        if(parent!=null) {
            Elements email=parent.select("a");
            finalemail=email.attr("href");
        }
        temp.setEmail(finalemail);
        //联系电话
        String finalphoneNumber="";
        Elements phoneNumber = doc.select(".icon-bell");
        Element tempphoneNumber = phoneNumber.parents().first();
        if(tempphoneNumber!=null) {
            finalphoneNumber = tempphoneNumber.text();
        }
        temp.setPhoneNumber(finalphoneNumber);
        //学位
        String finalDegree="";
        Elements Degree = doc.select("[id=page-title]");
        if(Degree!=null) {
            finalDegree = Degree.text();
            //取括号里面内容
            int head=finalDegree.indexOf("(")+1;
            int next=finalDegree.indexOf(")");
            String need=finalDegree.substring(head,next);
            finalDegree=need;
        }
        temp.setDegree(finalDegree);
        //奖学金入学截止日期
        String finaldeadlineWithAid =null;
        temp.setDeadlineWithAid(finaldeadlineWithAid);

        //无奖学金入学截至日期
        String finaldeadlineWithoutAid =null;
        temp.setDeadlineWithoutAid(finaldeadlineWithoutAid);

        return temp;
    }
}
