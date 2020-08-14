import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;

public class WebScrape {
    public static void main(String[] args) {
        try {
            File manga = new File("scrape.txt");
            if (manga.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already exists.");
                PrintWriter writer = new PrintWriter(manga);
                writer.print("");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int i = 1;
        String url;
        while ( i < 394){
            url = "http://fanfox.net/directory/" + String.valueOf(i) + ".html";
            try {
                final Document document = Jsoup.connect(url).get();
                BufferedWriter writer = new BufferedWriter(new FileWriter("scrape.txt", true));
                Elements a = document.select("body.bg-gary div.container div.line-list div.manga-list-1 ul.manga-list-1-list.line li p.manga-list-1-item-title a");
                for (Element element : a){
                    writer.append(element.attr("title"));
                    writer.append(" ");
                    writer.append("http://fanfox.net").append(element.attr("href"));
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}