import java.util.ArrayList;
import java.util.logging.Logger;                        //Herramienta Logger de Java
import java.util.logging.Level;

public class Panaderia {                            //Clase panadería
    private static final Logger LOGGER = Logger.getLogger(Panaderia.class.getName());         //Objeto Logger

    private ArrayList<Panaderia> recetas;               //Lista de recetas

    private String receta;                                 //Atributos
    private String ingredientes;

    public Panaderia(String receta, String ingredientes) {     //Constructor para inicializar los atributos
        this.receta = receta;
        this.ingredientes = ingredientes;
    }

    public Panaderia() { recetas = new ArrayList<>();           //Constructor para inicializar la panadería
    }

    public void mostrarInformacion() {                   //Método para mostrar la información
        LOGGER.log(Level.INFO, "Receta: {0}", receta);
        LOGGER.log(Level.INFO, "Ingredientes: {0}", ingredientes);
    }

    public void agregarReceta(Panaderia nuevaReceta) {          //Método para agregar una receta
        recetas.add(nuevaReceta);
    }

    public void mostrarTodasLasRecetas() {                      // Método para mostrar todas las recetas
        for (Panaderia r : recetas) {
            r.mostrarInformacion();
            LOGGER.log(Level.INFO, "");
        }
    }

    public Panaderia buscarReceta(String nombre) {
        for (Panaderia r : recetas) {
            if (r.receta.equalsIgnoreCase(nombre)) {
                return r;                                       // Devuelve la receta encontrada
            }
        } return null;                                          // Si no la encuentra, devuelve null
    }

    static void main(String[] args) {                    //Crear objetos de la clase
        Panaderia miPanaderia = new Panaderia();

        Panaderia receta1 = new Panaderia("Pastel de fresa", "Harina, leche, huevos, mantequilla, fresa");
        Panaderia receta2 = new Panaderia("Pastel de chocolate", "Harina, leche, huevos, mantequilla, chocolate");
        Panaderia receta3 = new Panaderia("Pay de limón", "Galletas, limones, leche condensada, leche evaporada");

        miPanaderia.agregarReceta(receta1);                     //Agregar recetas
        miPanaderia.agregarReceta(receta2);
        miPanaderia.agregarReceta(receta3);

        miPanaderia.mostrarTodasLasRecetas();                      //Mostrar todas las recetas

        Panaderia buscada = miPanaderia.buscarReceta("Pay de limón");           //Buscar recetas por nombre
        if (buscada != null) {
            LOGGER.log(Level.INFO, "Receta encontrada:");
            buscada.mostrarInformacion();
        } else {
            LOGGER.log(Level.INFO, "Receta no encontrada.");
        }
    }
}