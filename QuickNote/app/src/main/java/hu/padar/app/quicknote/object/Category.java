package hu.padar.app.quicknote.object;

/**
 * Created by Pádi on 2016. 01. 12..
 */
public class Category {
    private String title;
    private String date;
    private String category;
    private int color;

    public Category(String title, String date, String category, int color) {
        this.title = title;
        this.date = date;
        this.category = category;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
