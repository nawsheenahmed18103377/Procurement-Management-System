package incognitocoders;

public class ProductModel {
    String pid, pname, pquantity, pprice;

    public ProductModel(String pid, String pname, String pquantity, String pprice) {
        this.pid = pid;
        this.pname = pname;
        this.pquantity = pquantity;
        this.pprice = pprice;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPquantity() {
        return pquantity;
    }

    public void setPquantity(String pquantity) {
        this.pquantity = pquantity;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }
    
    
    
}
