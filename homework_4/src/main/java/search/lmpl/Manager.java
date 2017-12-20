package search.lmpl;

import main.SearchManager;
import search.FileHandler;
import search.WebSpider;
import search.lmpl.*;


/**
 * 请在此类中完成自己对象初始化工作，并注册
 */
public class Manager {

    static{
        // TODO:在此初始化所需组件，并将其注册到SearchManager中供主函数使用
        // SearchManager.registFileHandler(new yourFileHandler());
        // SearchManager.registSpider(new yourSpider());
        WebSpider webSpider = new WebSpiderImpl();
        SearchManager.registSpider(webSpider);
        FileHandler fileHandler = new FileHandlerImpl();
        SearchManager.registFileHandler(fileHandler);
    }
}
