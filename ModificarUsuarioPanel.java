/**
 * Universidad del Valle de Guatemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import javax.swing.*;
import java.awt.*;

/**
 * Clase ModificarUsuarioPanel.
 * Representa un panel gráfico para modificar los datos de un usuario.
 * Utiliza el modelo de diseño MVC, donde esta clase pertenece a la capa de Vista.
 * 
 * El panel incluye campos de texto para editar el nombre, nombre de usuario,
 * edad, sexo y contraseña, así como botones para guardar o cancelar los cambios.
 * 
 */
public class ModificarUsuarioPanel extends JPanel {
    private JTextField txtNombre, txtNombreUsuario, txtEdad, txtSexo;
    private JPasswordField txtContrasena;
    private JButton btnGuardar, btnCancelar;

    /**
    * Constructor de la clase ModificarUsuarioPanel.
    * Inicializa los componentes gráficos y establece el diseño del panel.
    */
    public ModificarUsuarioPanel() {
        setLayout(new GridLayout(6, 2, 10, 10)); // 6 filas, 2 columnas, 10px de separación
        
        // Etiquetas y campos de texto
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Nombre de Usuario:"));
        txtNombreUsuario = new JTextField();
        add(txtNombreUsuario);

        add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        add(txtEdad);

        add(new JLabel("Sexo:"));
        txtSexo = new JTextField();
        add(txtSexo);

        add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        add(txtContrasena);

        // Botones
        btnGuardar = new JButton("Guardar");
        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        add(btnCancelar);
    }

    /**
    * Obtiene el campo de texto para el nombre.
    * @return JTextField correspondiente al campo de nombre.
    */
    public JTextField getTxtNombre() { return txtNombre; }

    /**
     * Obtiene el campo de texto para el nombre de usuario.
     * @return JTextField correspondiente al campo de nombre de usuario.
     */
    public JTextField getTxtNombreUsuario() { return txtNombreUsuario; }

    /**
     * Obtiene el campo de texto para la edad.
     * @return JTextField correspondiente al campo de edad.
     */
    public JTextField getTxtEdad() { return txtEdad; }
    
    /**
     * Obtiene el campo de texto para el sexo.
     * @return JTextField correspondiente al campo de sexo.
     */
    public JTextField getTxtSexo() { return txtSexo; }

    /**
     * Obtiene el campo de texto para la contraseña.
     * @return JPasswordField correspondiente al campo de contraseña.
     */
    public JPasswordField getTxtContrasena() { return txtContrasena; }

    /**
     * Obtiene el botón de guardar.
     * @return JButton correspondiente al botón de guardar.
     */
    public JButton getBtnGuardar() { return btnGuardar; }

    /**
     * Obtiene el botón de cancelar.
     * @return JButton correspondiente al botón de cancelar.
     */
    public JButton getBtnCancelar() { return btnCancelar; }
}
