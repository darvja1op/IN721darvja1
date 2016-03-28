package bit.darvja1.languagetrainer;

public class Question {
    private String imgPath;
    private String english;
    private String noun;
    private String article;

    public Question(String english,String noun, String article, String imgPath){
        this.english = english;
        this.noun = noun;
        this.article = article;
        this.imgPath = imgPath;
    }
}
