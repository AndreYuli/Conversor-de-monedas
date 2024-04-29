import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String API_KEY = "041ab2c42d648f6301e7c07d";
    public static List<String> historial = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        String otraConversion = null;
        String nombre;
        do {
            System.out.println("------------Conversor de Monedas------------");
            System.out.print("Por favor Introduce tu nombre: ");
            nombre = teclado.nextLine();
            System.out.println("--------------------------------------------");
            System.out.println("Hola " + nombre + ", por favor escoge tu moneda de origen:");
            System.out.println("1. USD\n2. EUR\n3. GBP\n4. CHF\n5. JPY\n6. HKD\n7. CAD\n8. CNY\n9. AUD\n10. BRL\n11. RUB\n12. MXN\n13. COP\n14. Salir");

            int opcion = teclado.nextInt();
            if (opcion == 14) {
                break;
            }

            String monedaOrigen = Convertidor.obtenerMoneda(opcion);
            System.out.println("Has seleccionado: " + monedaOrigen + " como moneda de origen.");
            System.out.println("Ahora por favor digita la cantidad que deseas convertir: ");
            double cantidad = teclado.nextDouble();

            System.out.println("Por favor, escoge a qué moneda quieres cambiar:");
            System.out.println("1. USD\n2. EUR\n3. GBP\n4. CHF\n5. JPY\n6. HKD\n7. CAD\n8. CNY\n9. AUD\n10. BRL\n11. RUB\n12. MXN\n13. COP");
            int opcionDestino;
            String monedaDestino;
            do {
                System.out.println("Por favor, escoge a qué moneda quieres cambiar:");
                System.out.println("1. USD\n2. EUR\n3. GBP\n4. CHF\n5. JPY\n6. HKD\n7. CAD\n8. CNY\n9. AUD\n10. BRL\n11. RUB\n12. MXN\n13. COP");
                opcionDestino = teclado.nextInt();
                if (opcionDestino < 1 || opcionDestino > 13) {
                    System.out.println("Opción no válida. Por favor, elige una opción del menú.");
                }
            } while (opcionDestino < 1 || opcionDestino > 13);
            monedaDestino = Convertidor.obtenerMoneda(opcionDestino);


            System.out.println("Has seleccionado: " + monedaDestino + " como moneda de destino.");
            double tasaCambio = Convertidor.obtenerTasaCambio(monedaOrigen, monedaDestino);
            double cantidadConvertida = cantidad * tasaCambio;

            System.out.println(cantidad + " " + monedaOrigen + " son equivalentes a " + cantidadConvertida + " " + monedaDestino);
            System.out.println("¿Deseas realizar otra conversión? (si/no)");
            otraConversion = teclado.next();
            Convertidor.agregarAHistorial(monedaOrigen, monedaDestino, cantidad, cantidadConvertida);

        } while (otraConversion.equalsIgnoreCase("si"));
        System.out.println("¡Gracias " + nombre + " por usar nuestro conversor de monedas!");
        Convertidor.mostrarHistorial();
    }


}
