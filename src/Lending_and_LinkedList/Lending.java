package Lending_and_LinkedList;

public class Lending
{
    protected String rcode, bcode;
    protected int state;
    public Lending() {}
    public Lending(String rcode, String bcode, int state)
    {
        if (state < 0 || state > 2)
            throw new IllegalArgumentException("| >> State should be between 0 and 2.");
        this.rcode = rcode;
        this.bcode = bcode;
        this.state = state;
    }
    public String getRcode() { return rcode; }
    public void setRcode(String rcode) { this.rcode = rcode; }
    public String getBcode() { return bcode; }
    public void setBcode(String bcode) { this.bcode = bcode; }
    public int getState() { return state; }
    public void setState(int state) { this.state = state; }
}
