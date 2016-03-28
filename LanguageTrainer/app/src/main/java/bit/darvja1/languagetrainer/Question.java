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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
