package mentoring.automation.constants;

public enum SiteLanguage {
    UA("UA"),
    RU("RU");

    private final String name;

    SiteLanguage(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
