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
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> myMap = la.countVisitsPerIP();
        System.out.println(myMap);
        System.out.println("Unique IPs: " + myMap.size());
    }
}
