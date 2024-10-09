package ovchip.domain;

public class OVChipkaartProduct {
    private int productNummer;
    private int kaartNummer;

    public OVChipkaartProduct() {}

    public OVChipkaartProduct(int productNummer, int kaartNummer) {
        this.productNummer = productNummer;
        this.kaartNummer = kaartNummer;
    }

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }
}
