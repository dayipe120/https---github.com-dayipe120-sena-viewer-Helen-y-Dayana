package com.senaviewer.manager;

import com.senaviewer.data.Data;
import com.senaviewer.model.Book;
import com.senaviewer.model.Movie;

public class ReportManager {

    /**
     * Genera un reporte general de todo el contenido consumido.
     */
    public static void makeGeneralReport() {
        int totalMovies = Data.movies.size();
        int viewedMovies = countViewedMovies(Data.movies);
        
        int totalBooks = Data.books.size();
        int readBooks = countReadBooks(Data.books);

        // La lógica para Series y Magazines (si se pidieran reportes) iría aquí
        
        System.out.println("==================================");
        System.out.println("       REPORTE GENERAL (SENA)     ");
        System.out.println("==================================");
        
        // --- Películas ---
        System.out.println("🎬 PELÍCULAS:");
        System.out.println("   Total en catálogo: " + totalMovies);
        System.out.println("   Vistas: " + viewedMovies);
        System.out.println("   Pendientes: " + (totalMovies - viewedMovies));
        
        // --- Libros ---
        System.out.println("📚 LIBROS:");
        System.out.println("   Total en catálogo: " + totalBooks);
        System.out.println("   Leídos: " + readBooks);
        System.out.println("   Pendientes: " + (totalBooks - readBooks));
        
        // --- Tiempo Total (Simulado) ---
        long totalTimeSeconds = sumTotalTimeViewed();
        System.out.println("--- CONSUMO TOTAL SIMULADO ---");
        System.out.println("Tiempo Total Consumido: " + formatSeconds(totalTimeSeconds));
        
        System.out.println("==================================");
    }
    
    // --- Lógica de Conteo ---
    
    private static int countViewedMovies(java.util.List<Movie> movies) {
        int count = 0;
        for (Movie movie : movies) {
            if (movie.isViewed()) {
                count++;
            }
        }
        return count;
    }
    
    private static int countReadBooks(java.util.List<Book> books) {
        int count = 0;
        for (Book book : books) {
            if (book.isReaded()) {
                count++;
            }
        }
        return count;
    }
    
    private static long sumTotalTimeViewed() {
        long totalSeconds = 0;
        
        // Suma tiempo de Películas
        for (Movie movie : Data.movies) {
            // Nota: Asumimos que getTimeViewed() devuelve los segundos (como se definió en Etapa 3)
            totalSeconds += movie.getTimeViewed();
        }
        
        // Suma tiempo de Libros
        for (Book book : Data.books) {
            // Nota: Asumimos que getTimeReaded() devuelve los segundos
            totalSeconds += book.getTimeReaded();
        }
        
        // Podría sumar tiempo de Series/Capítulos aquí
        
        return totalSeconds;
    }
    
    /**
     * Convierte segundos a un formato legible (Horas:Minutos:Segundos).
     */
    private static String formatSeconds(long totalSeconds) {
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        
        return String.format("%d horas, %d minutos, %d segundos", hours, minutes, seconds);
    }

    /**
     * Genera un reporte basado en el consumo realizado en el día actual.
     * * NOTA: Debido a que la aplicación de consola no persiste el estado 
     * ni registra la fecha de visualización (solo si fue visto/leído),
     * este reporte se simulará mostrando el consumo total en la sesión 
     * actual (desde que se abrió la app). 
     * En una aplicación real, los métodos startToSee/stopToSee guardarían la fecha.
     */
    public static void makeTodayReport() {
        long totalTimeSeconds = sumTotalTimeViewed();
        
        System.out.println("==================================");
        System.out.println("      REPORTE DEL DÍA (SESIÓN)    ");
        System.out.println("==================================");
        
        System.out.println("Resumen del consumo en esta sesión:");
        System.out.println("Películas Vistas: " + countViewedMovies(Data.movies));
        System.out.println("Libros Leídos: " + countReadBooks(Data.books));
        
        System.out.println("\nTiempo Total Consumido (Simulado): " + formatSeconds(totalTimeSeconds));
        System.out.println("==================================");
    }
}
