import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class WebScrape {
    public static void main(String[] args) {
        /*try {
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
        }*/
        int i = 1;
        String url;
        final String folderPath = "C:\\Users\\Emanuel\\Documents\\GitKraken\\mangafox-webscraping\\img\\";
        while ( i < 396){
            url = "http://fanfox.net/directory/" + i + ".html";
            try {
                final Document document = Jsoup.connect(url).get();
                Elements a = document.select("body.bg-gary div.container div.line-list div.manga-list-1 ul.manga-list-1-list.line li p.manga-list-1-item-title a");
                Elements img = document.select("body.bg-gary div.container div.line-list div.manga-list-1 ul.manga-list-1-list.line li a img.manga-list-1-cover");
                /*BufferedWriter writer = new BufferedWriter(new FileWriter("scrape.txt", true));
                for (Element element : a){
                    writer.append(element.attr("title"));
                    writer.append(" ");
                    writer.append("http://fanfox.net").append(element.attr("href"));
                    writer.newLine();
                }
                writer.close();*/
                for (Element element : img){
                    URL urli = new URL(element.attr("src"));
                    String name = element.attr("alt");
                    System.out.println(element.attr("alt"));
                    if (name.contains("?") || name.contains("!") || name.contains("<") || name.contains(">") || name.contains("*") || name.contains("\\") || name.contains(":") || name.contains("/")){
                        name = name.replace("?", "");
                        name = name.replace("!", "");
                        name = name.replace("<", "");
                        name = name.replace(">", "");
                        name = name.replace("*", "");
                        name = name.replace("\\", "");
                        name = name.replace(":", "");
                        name = name.replace("/", "");
                    }
                    URLConnection uc = urli.openConnection();
                    uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                    InputStream in = uc.getInputStream();
                    OutputStream out = new BufferedOutputStream(new FileOutputStream( folderPath + name + ".png"));
                    for (int b; (b = in.read()) != -1;) {
                        out.write(b);
                    }
                    out.close();
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}