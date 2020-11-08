import java.io.*;
import java.util.*;
import java.awt.*;
public class BinarySearch
{
    int values[];
    int searchValue;
    int low;
    int high;
    int midpoint;
    private Canvas myCanvas;
    private Rectangle rects[];

    public BinarySearch()
    {
        myCanvas = new Canvas("Binary Search", 1000, 800);
        rects = new Rectangle[100];
    }

    public void fillSample()
    {
        values = new int[] {10, 11, 11, 12, 13, 15, 16, 18, 19};
    }

    public void fillRandom()
    {
        Random random = new Random();
        values = new int[100];
        for(int i = 0; i < 100; i++)
        {
            int n = random.nextInt(101);
            values[i] = n;
        }
        Arrays.sort(values);
    }

    public void runSearch(int key)
    {
        high = values.length - 1;
        low = 0;
        midpoint = (high + low) / 2;
        boolean finished = false;
        int iteration = 1;
        System.out.println("Searching for " + key);
        while(!finished)
        {
            if(low >= high)
            {
                System.out.println("Key cannot be found");
                break;
            }
            System.out.println("Iteration #" + iteration);
            System.out.println("Low: index " + low + ", value " + values[low]);
            System.out.println("High: index " + high + ", value " + values[high]);
            System.out.println("Midpoint: index " + midpoint + ", value " + values[midpoint]);
            if(key == values[midpoint])
            {
                finished = true;
                System.out.println(key + " found at index " + midpoint);

            }
            else if(key > values[midpoint])
            {
                low = midpoint + 1;
            }
            else if(key < values[midpoint])
            {
                high = midpoint - 1;
            }
            midpoint = (high + low) / 2;
            iteration++;
        }
    }

    public void runAnimatedSearch(int key)
    {
        high = values.length - 1;
        low = 0;
        midpoint = (high + low) / 2;
        drawAllBars();
        drawGraph();
        myCanvas.wait(2000);
        boolean finished = false;
        int iteration = 1;
        myCanvas.drawString("" + key, 480, 25);

        while(!finished)
        {
            if(low > high)
            {
                finished = true;
                myCanvas.drawString("Key cannot be found", 30, 175);
                break;
            }
            drawGraph();
            drawBar(midpoint, Color.YELLOW);
            myCanvas.wait(2000);
            myCanvas.eraseRectangle(27, 65, 300, 200); 
            myCanvas.drawString("Iteration # " + iteration, 30, 75);
            myCanvas.drawString("Low: index " + low + ", value " + values[low], 30, 100);
            myCanvas.drawString("High: index " + high + ", value " + values[high], 30, 125);
            myCanvas.drawString("Midpoint: index " + midpoint + ", value " + values[midpoint], 30, 150);
            if(key == values[midpoint])
            {
                finished = true;
                myCanvas.drawString(key + " found at index " + midpoint, 30, 175);
                break;
            }
            else if(key > values[midpoint])
            {
                while(low < midpoint + 1)
                {
                    drawBar(low, Color.gray);
                    low++;
                    myCanvas.wait(50);
                }
            }
            else if(key < values[midpoint])
            {
                while(high > midpoint - 1)
                {
                    drawBar(high,Color.gray);
                    high--;
                    myCanvas.wait(50);
                }
            }
            midpoint = (high + low) / 2;
            iteration++;
        }
    }

    public void drawGraph()
    {
        myCanvas.drawString("Binary Search Animation", 25, 25);
        myCanvas.drawString("Searching for: " , 400, 25);
        Rectangle box = new Rectangle(25, 50, 904, 625);
        myCanvas.draw(box);

        myCanvas.eraseRectangle(0, 676, 1000, 100);
        //low
        myCanvas.drawLine(rects[low].x + 3, 675, rects[low].x + 3, 685);
        Rectangle lowbox = new Rectangle(rects[low].x - 10, 684, 25, 15);
        myCanvas.draw(lowbox);
        myCanvas.drawString("low", lowbox.x + 3, 695);
        //high
        myCanvas.drawLine(rects[high].x + 3, 675, rects[high].x + 3, 685);
        Rectangle highbox = new Rectangle(rects[high].x - 10, 684, 25, 15);
        myCanvas.draw(highbox);
        myCanvas.drawString("high", highbox.x + 1, 695);
        //mid
        myCanvas.drawLine(rects[midpoint].x + 3, 675, rects[midpoint].x + 3, 685);
        Rectangle midbox = new Rectangle(rects[midpoint].x - 10, 684, 25, 15);
        myCanvas.draw(midbox);
        myCanvas.drawString("mid", midbox.x + 2, 695);
    }

    public void drawAllBars()
    {
        for(int i = 0; i < values.length; i++)
        {
            // rects[i] = new Rectangle(30 + ((i * 3) * 3), 573, 5, 6 * search.values[i]);
            rects[i] = new Rectangle(30 + ((i * 3) * 3), 674 - (6 * values[i]), 5, 6 * values[i]);
            myCanvas.setForegroundColor(new Color((230 - i), 0 , 255));
            myCanvas.draw(rects[i]);
            myCanvas.fill(rects[i]);
        }
    }

    public void drawBar(int index, Color color)
    {
        Rectangle bar = new Rectangle(30 + ((index * 3) * 3), 674 - (6 * values[index]), 5, 6 * values[index]);
        myCanvas.setForegroundColor(color);
        myCanvas.fill(bar);
        myCanvas.setForegroundColor(new Color(130, 0, 255));
    }

    public static void main (String[] args)
    {
        BinarySearch search = new BinarySearch();
        search.fillRandom();
        search.runSearch(20);
        search.runAnimatedSearch(20);

    }
}