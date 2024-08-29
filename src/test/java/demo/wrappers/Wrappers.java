package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void EnterText(WebElement element ,String text){
        try{
            element.clear();
            element.sendKeys(text);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
        public static void ratinglist(ChromeDriver  driver)
        {
            List<WebElement> ratingList = driver.findElements(By.xpath("//div[@class='XQDdHH']"));
            int count=0;
            for(int i = 0;  i<ratingList.size(); i++){
                String ratingText=ratingList.get(i).getText();
                double ratingValue = Double.parseDouble(ratingText);
                if(ratingValue<=4.0){
                    count++;
                    System.out.println("Count of Items" + " " + count);

                }             
                              
            }

                }

                public static void discount_Title(ChromeDriver driver){
                   try{
                    List<WebElement> discountList = driver.findElements(By.xpath("//div[@class='UkUFwK']"));
                    List<WebElement> TitleList = driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
                    for(int i=0; i<discountList.size();i++ ){
                        String discountText= discountList.get(i).getText();
                        String TitleText= TitleList.get(i).getText();
                       int discountpercent=Integer.parseInt(discountText.replaceAll("[^0-9]", ""));
                        if(discountpercent>17){
                            System.out.println("Disount % of Items greater the 17 %" + " " + discountpercent + " " + "%");
                            System.out.println("Phone Title :" + " " + TitleText);
                        }
                        
                    }
                }
                catch(Exception e){
                    e.printStackTrace();

                }
            }

            public static void Title_Image(ChromeDriver driver){
                try{
                    
                 List<WebElement> CoffeeMugsList = driver.findElements(By.xpath("//div[@class='slAVV4']"));
                // List<WebElement> Title_image_List = driver.findElements(By.xpath("//div[@class='slAVV4']//a[@class='wjcEIp']"));
                // List<WebElement> title= driver.findElements(By.xpath("//a[@class='wjcEIp']"));
                // List<WebElement> rating= driver.findElements(By.xpath("//div[@class='XQDdHH']"));
                // List<WebElement> image= driver.findElements(By.xpath("//img[@class='DByuf4']"));
                // int count=0;
                //  for(int i=0; i<CoffeeMugsList.size();i++ ){
                //    String Titlevalue= title.get(i).getText();
                //    String ratingvalue= rating.get(i).getText();
                //    String imagevalue= image.get(i).getAttribute("src");
                //    //double ratingvalueint= Double.parseDouble(ratingvalue.replaceAll("[^0-9]", ""));
                //    double ratingvalueint= Double.parseDouble(ratingvalue);
                //    System.out.println(Titlevalue);
                //    System.out.println(imagevalue);
                //    System.out.println(ratingvalueint);

                  // Filter items with 4 stars and above
            List<ItemData> itemDataList = new ArrayList<>();
            for (WebElement item : CoffeeMugsList) {
                try {
                    //Extract rating
                    WebElement ratingElement = item.findElement(By.xpath("//div[@class='XQDdHH']"));
                    String ratingText = ratingElement.getText();
                    double ratingvalueint= Double.parseDouble(ratingText);
                    if (ratingText.startsWith("4") || ratingText.startsWith("5")) {
                       // Extract title
                        WebElement titleElement = item.findElement(By.xpath("//a[@class='wjcEIp']"));
                        String title = titleElement.getText();

                        // Extract image URL
                        WebElement imageElement = item.findElement(By.xpath("//img[@class='DByuf4']"));
                        String imageUrl = imageElement.getAttribute("src");

                        // Extract review count
                        WebElement reviewElement = item.findElement(By.xpath("//span[@class='Wphh3N']"));
                        String reviewText = reviewElement.getText();
                        int reviewCount = parseReviewCount(reviewText);

                        itemDataList.add(new ItemData(title, imageUrl, reviewCount));
                    }
                } catch (Exception e) {
                    // Handle cases where elements are not found
                    System.out.println("Error extracting data from item: " + e.getMessage());
                }
            }

            // Sort items by number of reviews in descending order
            itemDataList.sort((a, b) -> Integer.compare(b.getReviewCount(), a.getReviewCount()));

            // Print the top 5 items
            for (int i = 0; i < Math.min(5, itemDataList.size()); i++) {
                ItemData data = itemDataList.get(i);
                System.out.println("Title: " + data.getTitle());
                System.out.println("Image URL: " + data.getImageUrl());
                System.out.println("Review count + "+  data.getReviewCount());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    private static int parseReviewCount(String reviewText) {
        try {
            return Integer.parseInt(reviewText.replaceAll("[^0-9]", "")); // Extracts the number from the text
        } catch (NumberFormatException e) {
            return 0; // Return 0 if parsing fails
        }
    }

    static class ItemData {
        private final String title;
        private final String imageUrl;
        private final int reviewCount;

        public ItemData(String title, String imageUrl, int reviewCount) {
            this.title = title;
            this.imageUrl = imageUrl;
            this.reviewCount = reviewCount;
        }

        public String getTitle() {
            return title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getReviewCount() {
            return reviewCount;
        }
    }
    
    }

       
            
        

        
    

