import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         WebLogParser wlp = new WebLogParser();
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             records.add(wlp.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)) {
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         int count = 0;
         for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(statusCode);
                count += 1;
             }
         }
         System.out.println("Count of log entries greater than " + num + ": " + count);
     }
     
     public void uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> IPs = new ArrayList<String>();
         String dateString;
         
         for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String ipAddr = le.getIpAddress();
             dateString = d.toString();
             if((dateString.contains(someday)) && (!IPs.contains(ipAddr))) {
                 IPs.add(ipAddr);
             }
         }
         for (int i = 0; i < IPs.size(); i++) {
             System.out.println(IPs.get(i));
         }
         System.out.println("Number of unique IP visits on day: " + IPs.size());
     }
     
     public void countUniqueIPsInRange(int low, int high) {
         ArrayList<String> IPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             int statusCode = le.getStatusCode();
             if ((!IPs.contains(ipAddr)) && (statusCode >= low && statusCode <= high)) {
                 IPs.add(ipAddr);
             }
         }
         for (int i = 0; i < IPs.size(); i++) {
             System.out.println(IPs.get(i));
         }
         System.out.println("Number of unique IPs in range: " + IPs.size());
     }
}
