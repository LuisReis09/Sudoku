

import java.awt.Font;
import java.awt.FontFormatException;

import java.io.File;
import java.io.IOException;

public class Fontes {
    public static final Font CHANGA_BOLD;
    public static final Font CHANGA_EXTRABOLD;
    public static final Font CHANGA_EXTRALIGHT;
    public static final Font CHANGA_LIGHT;
    public static final Font CHANGA_MEDIUM;
    public static final Font CHANGA_REGULAR;
    public static final Font CHANGA_SEMIBOLD;
    public static final Font CHANGA_VARIABLE;

    static {
        // Caminho base para os arquivos de fontes
        String basePath = "assets/Changa/static/";

        // Carrega cada fonte e define um tamanho padrão
        CHANGA_BOLD = loadFont(basePath + "Changa-Bold.ttf", 18f);
        CHANGA_EXTRABOLD = loadFont(basePath + "Changa-ExtraBold.ttf", 18f);
        CHANGA_EXTRALIGHT = loadFont(basePath + "Changa-ExtraLight.ttf", 18f);
        CHANGA_LIGHT = loadFont(basePath + "Changa-Light.ttf", 18f);
        CHANGA_MEDIUM = loadFont(basePath + "Changa-Medium.ttf", 18f);
        CHANGA_REGULAR = loadFont(basePath + "Changa-Regular.ttf", 18f);
        CHANGA_SEMIBOLD = loadFont(basePath + "Changa-SemiBold.ttf", 18f);
        CHANGA_VARIABLE = loadFont("assets/Changa/Changa-VariableFont_wght.ttf", 18f);
    }

    // Método para carregar a fonte
    private static Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
        } catch (IOException | FontFormatException e) {
            System.out.println("Erro ao carregar a fonte: " + path);
            return new Font("Serif", Font.PLAIN, (int) size); // Fallback para uma fonte padrão
        }
    }
}
