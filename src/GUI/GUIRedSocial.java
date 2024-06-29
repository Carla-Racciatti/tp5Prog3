package GUI;
import Logica.RedSocial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GUIRedSocial extends JFrame {
    private JPanel panel1;
    private JButton agregarUsuarioButton;
    private JTextField textField1;
    private JList<String> usuariosList;
    private JButton agregarAmistadButton;
    private JTextArea textArea1;
    private JButton eliminarAmistadButton;
    private JButton buscarRelacionButton;
    private JButton eliminarUsuarioButton;
    private JLabel nombreUsuarioLabel;
    private JLabel redSocialLabel;
    private JLabel alumnosLabel;

    private RedSocial redSocial;
    private DefaultListModel<String> listModel;
    private JCheckBox[] checkBoxes;

    public GUIRedSocial() {
        redSocial = new RedSocial();
        listModel = new DefaultListModel<>();
        usuariosList.setModel(listModel);
        usuariosList.setCellRenderer(new CheckboxListCellRenderer());
        usuariosList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        setContentPane(panel1);
        setTitle("Red Social");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        configurarEventos();
    }

    private void configurarEventos() {
        agregarUsuarioButton.addActionListener(e -> agregarUsuario());
        agregarAmistadButton.addActionListener(e -> agregarAmistad());
        eliminarAmistadButton.addActionListener(e -> eliminarAmistad());
        buscarRelacionButton.addActionListener(e -> buscarRelacion());
        eliminarUsuarioButton.addActionListener(e -> eliminarUsuario());

        usuariosList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                JList<String> list = (JList<String>) event.getSource();
                int index = list.locationToIndex(event.getPoint());
                if (index >= 0) {
                    Rectangle bounds = list.getCellBounds(index, index);
                    if (event.getX() < bounds.x + 20) { // Asume que el checkbox tiene 20 píxeles de ancho
                        checkBoxes[index].setSelected(!checkBoxes[index].isSelected());
                        list.repaint(bounds);
                    }
                }
            }
        });
    }

    private void agregarUsuario() {
        String nombreUsuario = textField1.getText().trim();
        if (!nombreUsuario.isEmpty() && !redSocial.getUsuarios().contains(nombreUsuario)) {
            redSocial.agregarUsuario(nombreUsuario);
            listModel.addElement(nombreUsuario);
            actualizarCheckBoxes();
            textField1.setText("");
            textArea1.setText("Usuario " + nombreUsuario + " agregado.");
        } else {
            textArea1.setText("No se pudo agregar el usuario. El nombre está vacío o ya existe.");
        }
    }

    private void agregarAmistad() {
        List<String> seleccionados = obtenerUsuariosSeleccionados();
        if (seleccionados.size() == 2) {
            String usuario1 = seleccionados.get(0);
            String usuario2 = seleccionados.get(1);
            redSocial.agregarAmistad(usuario1, usuario2);
            textArea1.setText("Amistad agregada entre " + usuario1 + " y " + usuario2);
        } else {
            textArea1.setText("Selecciona exactamente dos usuarios para agregar una amistad.");
        }
    }

    private void eliminarAmistad() {
        List<String> seleccionados = obtenerUsuariosSeleccionados();
        if (seleccionados.size() == 2) {
            String usuario1 = seleccionados.get(0);
            String usuario2 = seleccionados.get(1);
            redSocial.eliminarAmistad(usuario1, usuario2);
            textArea1.setText("Amistad eliminada entre " + usuario1 + " y " + usuario2);
        } else {
            textArea1.setText("Selecciona exactamente dos usuarios para eliminar una amistad.");
        }
    }

    private void buscarRelacion() {
        List<String> seleccionados = obtenerUsuariosSeleccionados();
        if (seleccionados.size() == 2) {
            String usuario1 = seleccionados.get(0);
            String usuario2 = seleccionados.get(1);

            List<String> caminoBFS = redSocial.busquedaAnchura(usuario1, usuario2);
            if (caminoBFS != null && caminoBFS.size() == 2) {
                textArea1.setText(usuario1 + " y " + usuario2 + " son amigos directos.");
            } else {
                List<String> amigosEnComun = redSocial.obtenerAmigosEnComun(usuario1, usuario2);
                if (!amigosEnComun.isEmpty()) {
                    textArea1.setText(usuario1 + " y " + usuario2 + " no son amigos directos, pero tienen amigos en común: " + amigosEnComun);
                } else {
                    textArea1.setText(usuario1 + " y " + usuario2 + " no son amigos y no tienen amigos en común.");
                }
            }
        } else {
            textArea1.setText("Selecciona exactamente dos usuarios para buscar su relación.");
        }
    }

    private void eliminarUsuario() {
        List<String> seleccionados = obtenerUsuariosSeleccionados();
        if (seleccionados.size() == 1) {
            String usuarioSeleccionado = seleccionados.get(0);
            redSocial.eliminarUsuario(usuarioSeleccionado);
            listModel.removeElement(usuarioSeleccionado);
            actualizarCheckBoxes();
            textArea1.setText("Usuario " + usuarioSeleccionado + " eliminado");
        } else {
            textArea1.setText("Selecciona un usuario para eliminarlo.");
        }
    }

    private List<String> obtenerUsuariosSeleccionados() {
        List<String> seleccionados = new ArrayList<>();
        for (int i = 0; i < listModel.size(); i++) {
            if (checkBoxes[i].isSelected()) {
                seleccionados.add(listModel.get(i));
            }
        }
        return seleccionados;
    }

    private void actualizarCheckBoxes() {
        checkBoxes = new JCheckBox[listModel.size()];
        for (int i = 0; i < listModel.size(); i++) {
            checkBoxes[i] = new JCheckBox();
        }
        usuariosList.repaint();
    }

    private class CheckboxListCellRenderer extends JPanel implements ListCellRenderer<String> {
        private JCheckBox checkBox;
        private JLabel label;

        public CheckboxListCellRenderer() {
            setLayout(new BorderLayout());
            checkBox = new JCheckBox();
            label = new JLabel();
            add(checkBox, BorderLayout.WEST);
            add(label, BorderLayout.CENTER);
        }

        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            label.setText(value);
            checkBox.setSelected(checkBoxes[index].isSelected());
            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            return this;
        }
    }
}