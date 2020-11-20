package epitech.eu.mobile;

public class Article {
    private String qrCode;
    private String name;
    private double prix;

    public Article(String qrCode, String name, double prix){
        this.qrCode = qrCode;
        this.name = name;
        this.prix = prix;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
