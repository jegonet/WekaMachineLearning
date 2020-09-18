package jegonet.desercionml;

import javax.swing.UIManager;

public class Main
{
    static Form mainForm;
    
    public static void main(final String[] args) {
        Main.mainForm = new Form();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}