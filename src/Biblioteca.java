import Telas.TelaInicial;

import javax.swing.*;

public class Biblioteca {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        TelaInicial tela;
        tela = new TelaInicial();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
}