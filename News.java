package news;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class News {
    public WebDriver WD;
    public static ArrayList<HashMap<String,String>> newsList = new ArrayList<>();
    public static ArrayList<String> links = new ArrayList<>();

    public static final String SUPER_URL = "https://www.supersport.com";
    public static final String MY_URL = "http://localhost:8000/news/add";
    public static final int TIME = 10000; 

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "D:\\3rd YEar\\SE\\chromedriver.exe");
        
            newsList = new ArrayList<>();
            links.clear();

            WebDriver WD = new ChromeDriver();
            WD.navigate().to(SUPER_URL);

            List<WebElement> newws = WD.findElements(By.id("col-sm-12 col-xs-12 col-md-8 col-lg-8"));
            List<WebElement> newws2 = WD.findElements(By.id("col-sm-12 col-xs-12 col-md-4 col-lg-4"));
            newws.addAll(newws2);

            
            for (WebElement link:newws.subList(0,3)){
                String li = link.getAttribute("href");

                if(li.contains("www.supersport.com/football/news")){
                    links.add(li);
                }
            }
            
            
            //Get news
            ArrayList<HashMap<String,String>> newsList = new ArrayList<>();


        for(int i=0;i<links.size();i++){
            HashMap<String, String> news = new HashMap<>();

            String link = links.get(i);

            WD.navigate().to(link);

            WebElement title = WD.findElement(By.cssSelector(".story-body__h1"));
            news.put("title", title.getText());

            List<WebElement> contents = WD.findElements(By.cssSelector(".story-body__inner p"));

            String Pars = "";

            for (WebElement content:contents){
                String par = content.getText();
                Pars += par + "\n\n";
            }

            news.put("content", Pars);
            System.out.println(Pars);

            newsList.add(news);
        }
        
            newsList.addAll(newsList);

            links.clear();
            if(newsList.size()>0){
                
                //Add news
                WD.navigate().to(MY_URL);

        for(HashMap<String, String> n:newsList){
            WebElement title = WD.findElement(By.name("title"));
            WebElement content = WD.findElement(By.name("content"));

            title.clear();
            title.sendKeys(n.get("title"));

            content.clear();
            content.sendKeys(n.get("content"));

            content.submit();
        }
                 }

            try{
                Thread.sleep(TIME);
            }catch (Exception e){
                System.out.println("Timeout");
            }
        
            showNews(WD);
    }
        public static void showNews(WebDriver WD){
            WD.navigate().to("http://localhost:8000/supersport");
    }
}