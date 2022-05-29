
/**
 * Write a description of UniqueIPTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class IPTester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " unique IPs");
    }
    
    public void testPrintHigherThan() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.uniqueIPVisitsOnDay("Mar 24");
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.countUniqueIPsInRange(300,399);
    }
    
    public HashMap<String, Integer> testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, Integer> myMap = la.countVisitsPerIP();
        System.out.println(myMap);
        System.out.println("Unique IPs: " + myMap.size());
        return myMap;
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int max = la.mostNumberVisitsByIP(testCountVisitsPerIP());
        System.out.println("Most number of visits by a single IP: " + max);
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, Integer> testMap = la.countVisitsPerIP();
        ArrayList<String> maxString = la.iPsMostVisits(testMap);
        System.out.println("Top IPs are: " + maxString);
    }    
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> testMap = la.iPsForDays();
        for (String s : testMap.keySet()) {
            System.out.println("Date: " + s + " \t Number of addresses: " + testMap.get(s));
        }
        System.out.println(testMap);
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> testMap = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(testMap));
    }
    
    public void testiPsWithMostVisitsOnDay() {
       LogAnalyzer la = new LogAnalyzer();
       la.readFile("weblog1_log");
       HashMap<String,ArrayList<String>> testMap = la.iPsForDays();
       System.out.println(la.iPsWithMostVisitsOnDay(testMap, "Mar 17"));
       
    }
}
