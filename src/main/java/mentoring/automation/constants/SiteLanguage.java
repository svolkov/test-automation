package mentoring.automation.constants;

public enum SiteLanguage {
    UA("ua"),
    RU("ru");

    private final String name;

    SiteLanguage(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
