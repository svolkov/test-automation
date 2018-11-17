package mentoring.automation.constants;

public enum Browsers {
    CHROME ("Chrome", "webdriver.chrome.driver", "/chromedriver"),
    FIREFOX ("FireFox", "webdriver.gecko.driver", "/geckodriver"),
    INTERNET_EXPLORER ("InternetExplorer", "webdriver.ie.driver", "/IEDriverServer.exe");

    final String name;
    final String systemProperty;
    final String path;

    Browsers(String name, String systemProperty, String path){
        this.name = name;
        this.systemProperty = systemProperty;
        this.path = path;
    }

    public String getName(){
        return name;
    }

    public String getSystemProperty(){
        return systemProperty;
    }

    public String getPath(){
        return path;
    }
}
