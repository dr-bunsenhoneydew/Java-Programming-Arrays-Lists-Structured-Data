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
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> IPs = new ArrayList<String>();
         String dateString;
         
         for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String ipAddr = le.getIpAddress();
             dateString = d.toString().substring(4,10);
             if((dateString.contains(someday)) && (!IPs.contains(ipAddr))) {
                 IPs.add(ipAddr);
             }
         }
         for (int i = 0; i < IPs.size(); i++) {
             System.out.println(IPs.get(i));
         }
         return IPs;
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
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> myMap = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             if (!myMap.containsKey(ipAddr)) {
                 myMap.put(ipAddr, 1); 
             }
             else {
                 myMap.put(ipAddr, myMap.get(ipAddr) + 1);
             }
         }
         return myMap;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> myMap) {
         int max = 0;
         for (String s : myMap.keySet()) {
             if (myMap.get(s) > max) {
                 max = myMap.get(s);
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> myMap) {
         ArrayList<String> topIPs = new ArrayList<String>();
         int max = mostNumberVisitsByIP(myMap);
         for (String s : myMap.keySet()) {
             if (myMap.get(s) == max) {
                 topIPs.add(s);
             }
         }
         return topIPs;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> myMap = new HashMap<String,ArrayList<String>>();
         ArrayList<String> IPs = new ArrayList<String>();
         String dateString;
         for (LogEntry le: records) {
             Date d = le.getAccessTime();
             String ipAddr = le.getIpAddress();
             dateString = d.toString().substring(4,10);
             IPs = uniqueIPVisitsOnDay(dateString);
             myMap.put(dateString,IPs);  
         }
         return myMap;
     }
    
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> myMap) {
         int max = Integer.MIN_VALUE;
         String maxDateString = "";
         for (String s : myMap.keySet()) {
             if (max == Integer.MIN_VALUE || myMap.get(s).size() > max) {
                 max = myMap.get(s).size();
                 maxDateString = s;
             }
         }
         return maxDateString;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> dateAndIPsMap, String date){
        ArrayList<String> mostIPs = new ArrayList<String>();
        ArrayList<String> IPsInDay = new ArrayList<String>();
        HashMap<String, Integer> myMap = new HashMap<String, Integer>();
        
        for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String ipAddr = le.getIpAddress();
             String dateString = d.toString().substring(4,10);
             if (dateString.equals(date)) {
                 IPsInDay.add(ipAddr);
             }
         }
        for (String s : IPsInDay) {
            if (!myMap.containsKey(s)){
                myMap.put(s, 1);
            }
            else {
                myMap.put(s, myMap.get(s) + 1);
            }
        }
        int maxNumVisits = 0;
        for (String s : myMap.keySet()) {
             if (myMap.get(s) > maxNumVisits) {
                 maxNumVisits = myMap.get(s);
             }
        }
        System.out.println("MaxNumVisits: " + maxNumVisits);
        for (String s : myMap.keySet()) {
            if (myMap.get(s) == maxNumVisits) {
                mostIPs.add(s);
            }
        }
        return mostIPs;
    }
     
     
}
