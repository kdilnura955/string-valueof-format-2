public class StringValueofFormatAdvanced {

    static String progressBar(double percent, int width) {
        int filled = (int)(percent / 100 * width);
        return "[" + "#".repeat(filled) + "-".repeat(width-filled) + "]" +
               String.format(" %5.1f%%", percent);
    }

    static String tableRow(Object... cols) {
        StringBuilder sb = new StringBuilder("|");
        for (Object col : cols) sb.append(String.format(" %-14s|", col));
        return sb.toString();
    }

    static String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024*1024) return String.format("%.1f KB", bytes/1024.0);
        if (bytes < 1024*1024*1024) return String.format("%.1f MB", bytes/(1024.0*1024));
        return String.format("%.2f GB", bytes/(1024.0*1024*1024));
    }

    static String formatDuration(long seconds) {
        long h = seconds/3600, m = (seconds%3600)/60, s = seconds%60;
        if (h > 0) return String.format("%d:%02d:%02d", h, m, s);
        return String.format("%d:%02d", m, s);
    }

    public static void main(String[] args) {
        System.out.println("=== Progress Bars ===");
        for (double p : new double[]{0, 25, 50, 75, 100})
            System.out.println(progressBar(p, 30));

        System.out.println("\n=== Table ===");
        String sep = "+" + "-".repeat(16) + "+" + "-".repeat(16) + "+" + "-".repeat(16) + "+";
        System.out.println(sep);
        System.out.println(tableRow("Name","Score","Grade"));
        System.out.println(sep);
        Object[][] rows = {{"Alice",95,"A"},{"Bob",82,"B"},{"Carol",74,"C"}};
        for (Object[] r : rows) { System.out.println(tableRow(r)); System.out.println(sep); }

        System.out.println("\n=== Byte Formatter ===");
        for (long b : new long[]{512, 2048, 1500000, 2_147_483_648L})
            System.out.printf("%15d → %s%n", b, formatBytes(b));

        System.out.println("\n=== Duration Formatter ===");
        for (long s : new long[]{45, 90, 3661, 7384})
            System.out.printf("%6d sec → %s%n", s, formatDuration(s));
    }
}
