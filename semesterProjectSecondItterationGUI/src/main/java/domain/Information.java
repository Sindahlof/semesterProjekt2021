package domain;

public class Information extends PlaceableObject {

    private String content;

    public Information(String objectName, String content, int y, int x) {
        super(objectName, y, x);
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public String print() {
        return this.content;
    }

}
