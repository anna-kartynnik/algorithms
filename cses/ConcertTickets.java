import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.TreeMap;
import java.io.DataInputStream; 
import java.io.FileInputStream;
import java.util.Scanner; 

//import java.util.Date;


public class ConcertTickets {

    static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        // public Reader(String file_name) throws IOException 
        // { 
        //     din = new DataInputStream(new FileInputStream(file_name)); 
        //     buffer = new byte[BUFFER_SIZE]; 
        //     bufferPointer = bytesRead = 0; 
        // } 
  
        // public String readLine() throws IOException 
        // { 
        //     byte[] buf = new byte[64]; // line length 
        //     int cnt = 0, c; 
        //     while ((c = read()) != -1) 
        //     { 
        //         if (c == '\n') 
        //             break; 
        //         buf[cnt++] = (byte) c; 
        //     } 
        //     return new String(buf, 0, cnt); 
        // } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        }
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    }  

  public static int[] distributeTickets(TreeMap<Integer, Integer> prices, int[] offers) {
    int[] tickets = new int[offers.length];
    for (int i = 0; i < offers.length; i++) {
      Integer priceKey = prices.floorKey(offers[i]);
      if (priceKey != null) {
        tickets[i] = priceKey;
        int priceCount = prices.get(priceKey);
        if (priceCount > 1) {
          prices.put(priceKey, priceCount - 1);
        } else {
          prices.remove(priceKey);
        }  
      } else {
        tickets[i] = -1;
      }
    }
    return tickets;
  }

  public static void main(String[] args) throws IOException {
    //long time1 = new Date().getTime();
    // BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    // StringTokenizer nm = new StringTokenizer(input.readLine());
    // int n = Integer.parseInt(nm.nextToken());
    // int m = Integer.parseInt(nm.nextToken());
    // TreeMap<Integer, Integer> prices = new TreeMap<Integer, Integer>();
    // StringTokenizer priceTokenizer = new StringTokenizer(input.readLine());
    // for (int i = 0; i < n; i++) {
    //   int nextKey = Integer.parseInt(priceTokenizer.nextToken());
    //   if (prices.containsKey(nextKey)) {
    //     prices.put(nextKey, prices.get(nextKey) + 1);
    //   } else {
    //     prices.put(nextKey, 1);
    //   }
    // }
    // int[] offers = new int[m];
    // StringTokenizer offerTokenizer = new StringTokenizer(input.readLine());
    // for (int i = 0; i < m; i++) {
    //   offers[i] = Integer.parseInt(offerTokenizer.nextToken());
    // }
    // input.close();
    ConcertTickets.Reader input = new ConcertTickets.Reader();
    int n = input.nextInt();
    int m = input.nextInt();
    TreeMap<Integer, Integer> prices = new TreeMap<Integer, Integer>();
    for (int i = 0; i < n; i++) {
      int nextKey = input.nextInt();
      Integer treeKey = prices.get(nextKey);
      if (treeKey != null) {
        prices.put(nextKey, treeKey + 1);
      } else {
        prices.put(nextKey, 1);
      }
    }
    int[] offers = new int[m];
    for (int i = 0; i < m; i++) {
      offers[i] = input.nextInt();
    }
    //long time2 = new Date().getTime();
    int[] tickets = ConcertTickets.distributeTickets(prices, offers);
    PrintWriter output = new PrintWriter(System.out);
    for (int i = 0; i < m; i++) {
      output.println(tickets[i]);
    }
    output.flush();
    //long time3 = new Date().getTime();
    //System.out.println(time2 - time1);
    //System.out.println(time3 - time2);
    //System.out.println(time3 - time1);
  }
}