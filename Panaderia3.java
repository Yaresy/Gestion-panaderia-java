import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Receta {                                      //Clase base
    protected String nombreReceta;
    protected String ingredientes;

    public Receta (String nombreReceta, String ingredientes) {
        this.nombreReceta = nombreReceta;
        this.ingredientes = ingredientes;
    }
    public String mostrarDetalles() {
        return "Nombre: " + nombreReceta + "\nIngredientes: " + ingredientes;
    }
    public String getNombreReceta() {
        return nombreReceta;
    }
}

class Pastel extends Receta {                     //Clase derivada 1
    private String saborCobertura;              //Atributo exclusivo

    public Pastel(String nombreReceta, String ingredientes, String saborCobertura) {
        super(nombreReceta, ingredientes);          // Llama al constructor de la clase base
        this.saborCobertura = saborCobertura;
    }

    @Override
    public String mostrarDetalles() {           //Sobrescribe el método de la clase base
        return super.mostrarDetalles() + "\nCobertura: " + saborCobertura;
    }
}

class PanDulce extends Receta {                 //Clase derivada 2
    private boolean tieneRelleno;               //Atributo exclusivo

    public PanDulce(String nombreReceta, String ingredientes, boolean tieneRelleno) {
        super(nombreReceta, ingredientes);
        this.tieneRelleno = tieneRelleno;
    }

    @Override
    public String mostrarDetalles() {
        String textoRelleno = tieneRelleno ? "Sí" : "No";
        return super.mostrarDetalles() + "\n¿Tiene relleno?: " + textoRelleno;
    }
}

public class Panaderia3 {                           //Programa principal
    private ArrayList<Receta> listaRecetas;         //Lista de la clase base Receta

    public Panaderia3() {
        listaRecetas = new ArrayList<>();
    }

    public void agregarReceta(Receta nuevaReceta) {
        listaRecetas.add(nuevaReceta);
    }

    public static void main(String[] args) {         //main
        Panaderia3 miPanaderia = new Panaderia3();
        //Instanciación de objetos de las subclases
        miPanaderia.agregarReceta(new Pastel("Pastel de fresa", "Harina, leche, huevos, mantequilla, fresa", "Crema batida de fresa"));
        miPanaderia.agregarReceta(new Pastel("Pastel de chocolate", "Harina, leche, huevos, mantequilla, chocolate", "Ganache oscuro"));
        miPanaderia.agregarReceta(new PanDulce("Concha de vainilla", "Harina, azúcar, manteca, huevo", false));
        miPanaderia.agregarReceta(new PanDulce("Empanada", "Harina, azúcar, manteca, piña", true));

        JFrame ventana = new JFrame("Sistema de Gestión - Mi Panadería 👩🏽‍🍳🥐");
        ventana.setSize(500, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        JTextArea pantallaTexto = new JTextArea();
        pantallaTexto.setEditable(false);
        pantallaTexto.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(pantallaTexto);

        JPanel panelBotones = new JPanel();
        JButton botonMostrar = new JButton("Mostrar inventario");
        JButton botonBuscar = new JButton("Buscar receta");
        panelBotones.add(botonMostrar);
        panelBotones.add(botonBuscar);

        botonMostrar.addActionListener(e -> {
            pantallaTexto.setText("--- Inventario Completo ---\n\n");
            for (Receta p : miPanaderia.listaRecetas) {
                pantallaTexto.append(p.mostrarDetalles() + "\n\n");
                pantallaTexto.append("------------------------------------------\n\n");
            }
        });

        botonBuscar.addActionListener(e -> {
            String palabraBuscada = JOptionPane.showInputDialog(ventana, "Escribe el nombre de la receta:");
            if (palabraBuscada != null && !palabraBuscada.trim().isEmpty()) {
                boolean encontrada = false;
                pantallaTexto.setText("");
                for (Receta p : miPanaderia.listaRecetas) {
                    if (p.getNombreReceta().equalsIgnoreCase(palabraBuscada.trim())) {
                        pantallaTexto.setText("--- Receta encontrada ---\n\n");
                        pantallaTexto.append(p.mostrarDetalles() + "\n");
                        encontrada = true;
                        break;
                    }
                }
                if (!encontrada) {
                    pantallaTexto.setText("La receta '"+ palabraBuscada +"' no se encuentra en el inventario.");
                }
            }
        });

        ventana.add(panelBotones, BorderLayout.NORTH);
        ventana.add(scroll, BorderLayout.CENTER);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}

