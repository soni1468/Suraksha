package hu.pe.yummykart.surakshauser;

/**
 * Created by PRanshu on 29-10-2017.
 */

public class RecyclerItemVariation
{
    String sCrime;
    String s2014num;
    String s2015num;
    String sVariation;
    String sArrestees;

    public RecyclerItemVariation(String sCrime, String s2014num, String s2015num, String sVariation, String sArrestees) {
        this.sCrime = sCrime;
        this.s2014num = s2014num;
        this.s2015num = s2015num;
        this.sVariation = sVariation;
        this.sArrestees = sArrestees;
    }

    public String getsCrime() {
        return sCrime;
    }

    public void setsCrime(String sCrime) {
        this.sCrime = sCrime;
    }

    public String gets2014num() {
        return s2014num;
    }

    public void sets2014num(String s2014num) {
        this.s2014num = s2014num;
    }

    public String gets2015num() {
        return s2015num;
    }

    public void sets2015num(String s2015num) {
        this.s2015num = s2015num;
    }

    public String getsVariation() {
        return sVariation;
    }

    public void setsVariation(String sVariation) {
        this.sVariation = sVariation;
    }

    public String getsArrestees() {
        return sArrestees;
    }

    public void setsArrestees(String sArrestees) {
        this.sArrestees = sArrestees;
    }
}
