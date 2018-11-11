package mentoring.automation.constants;

public enum Browsers {
    CHROME ("Chrome"),
    FIREFOX ("FireFox"),
    INTERNET_EXPLORER ("InternetExplorer");

    final String name;

    Browsers(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
