import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Convertidor {
    static String obtenerMoneda(int opcion) {
        switch (opcion) {
            case 1:
                return "USD";
            case 2:
                return "EUR";
            case 3:
                return "GBP";
            case 4:
                return "CHF";
            case 5:
                return "JPY";
            case 6:
                return "HKD";
            case 7:
                return "CAD";
            case 8:
                return "CNY";
            case 9:
                return "AUD";
            case 10:
                return "BRL";
            case 11:
                return "RUB";
            case 12:
                return "MXN";
            case 13:
                return "COP";
            default:
                return "Opción no válida";
        }
    }

    public static double obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws Exception {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/" + Main.API_KEY + "/latest/" + monedaOrigen))
                .build();
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonObject = JsonParser.parseString(respuesta.body()).getAsJsonObject();
        return jsonObject.getAsJsonObject("tasa_de_conversión").get(monedaDestino).getAsDouble();
    }
    public static void agregarAHistorial(String monedaOrigen, String monedaDestino, double cantidad, double cantidadConvertida) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String ahora = LocalDateTime.now().format(formatter);
        String entradaHistorial = "Fecha: " + ahora + ", Moneda Origen: " + monedaOrigen + ", Moneda Destino: " + monedaDestino + ", Cantidad Original: " + cantidad + ", Cantidad Convertida: " + cantidadConvertida;
        Main.historial.add(entradaHistorial);
    }

    public static void mostrarHistorial() {
        System.out.println("Historial de Conversiones:");
        for (String entrada : Main.historial) {
            System.out.println(entrada);
        }

}
}
