import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Formulario formulario = new Formulario();
            formulario.setVisible(true);
        });
    }
}