package by.teachmeskills.shop.exeption;

public class UniversalExseption extends Exception{
    private String text;

    public String getText() {
        return text;
    }

    public UniversalExseption() {
    }

    public UniversalExseption(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
