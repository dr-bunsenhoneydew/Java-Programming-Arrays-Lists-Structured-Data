public class UniqueIPTester {
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
}
