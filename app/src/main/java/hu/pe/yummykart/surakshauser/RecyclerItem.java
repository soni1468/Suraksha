package hu.pe.yummykart.surakshauser;


public class RecyclerItem {
    String sState;
    String sCrime;
    String s2010;
    String s2011;
    String s2012;

    public RecyclerItem(String sState, String sCrime, String s2010, String s2011, String s2012) {
        this.sState = sState;
        this.sCrime = sCrime;
        this.s2010 = s2010;
        this.s2011 = s2011;
        this.s2012 = s2012;
    }

    public String getsState() {
        return sState;
    }

    public void setsState(String sState) {
        this.sState = sState;
    }

    public String getsCrime() {
        return sCrime;
    }

    public void setsCrime(String sCrime) {
        this.sCrime = sCrime;
    }

    public String getS2010() {
        return s2010;
    }

    public void setS2010(String s2010) {
        this.s2010 = s2010;
    }

    public String getS2011() {
        return s2011;
    }

    public void setS2011(String s2011) {
        this.s2011 = s2011;
    }

    public String getS2012() {
        return s2012;
    }

    public void setS2012(String s2012) {
        this.s2012 = s2012;
    }
}
