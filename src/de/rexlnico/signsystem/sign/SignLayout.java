package de.rexlnico.signsystem.sign;

public class SignLayout {

    private int current;
    private String[] layout;

    public SignLayout() {
        this.current = 1;
        this.layout = null;
    }

    public int getCurrent() {
        return current;
    }

    public void setLayout(String[] layout) {
        this.layout = layout;
    }

    public void setNextCurrent(int max) {
        this.current = this.current >= max ? 1 : this.current+1;
    }

    public String[] getLayout() {
        return layout;
    }
}
