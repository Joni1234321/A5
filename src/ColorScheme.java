import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class ColorScheme {
    enum Type { RANDOM, DYNAMIC, SIMPLE, NONE }

    int max;
    Type type;
    Color[] scheme;

    public ColorScheme (Type type, int max) throws EnumConstantNotPresentException {
        this.type = type;
        this.max = max;
        switch (type) {
            case RANDOM -> this.scheme = RandomColorScheme(max);
            case DYNAMIC -> this.scheme = DynamicColorScheme(max);
            case SIMPLE -> this.scheme = SimpleColorScheme(max);
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
    public ColorScheme (String path, int max) throws FileNotFoundException {
        this.max = max;
        this.scheme = LoadColorScheme("src/colors/" + path + ".mnd", max);
    }

    public Color[] getScheme () {
        return this.scheme;
    }
    public Type getType () {
        return this.type;
    }
    public int getMax () {
        return this.max;
    }

    // ==================== COLOR SCHEMES ====================== //
    Color[] RandomColorScheme (int max) {
        Color[] scheme = new Color[max+1];
        Random r = new Random();
        for (int i = 0; i < scheme.length; i++)
        {
            scheme[i] = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        }
        return scheme;
    }


    Color[] LoadColorScheme (String path, int max) throws FileNotFoundException {
        if (max > 255) throw new ArrayIndexOutOfBoundsException("MAX is above 255, loaded color schemes have max 255 different colors");

        Scanner reader = new Scanner(new File(path));
        if (!reader.hasNextLine()) return null;

        ArrayList<String> lines = new ArrayList<String>();

        // Add all lines to list
        while (reader.hasNextLine()){
            lines.add(reader.nextLine());
        }
        reader.close();

        Color[] scheme = new Color[lines.size()];
        for (int i = 0; i < lines.size(); i++){
            reader = new Scanner(lines.get(i));
            int r = reader.nextInt();
            int g = reader.nextInt();
            int b = reader.nextInt();
            scheme[i] = new Color(r, g, b);
        }


        return scheme;
    }

    Color[] DynamicColorScheme (int max) {
        Color[] scheme = new Color[max + 1];
        for (int i = 0; i < scheme.length; i++) {
            int rgb = Color.HSBtoRGB(.25f + (float)i / scheme.length * .75f, 1,1);
            scheme[i] = new Color(rgb);
        }
        return scheme;
    }

    static Color[] SimpleColorScheme (int max) {
        Color[] scheme = new Color[max + 1];
        for (int i = 0; i < scheme.length; i++) {
            if (i == scheme.length - 1) scheme[i] = Color.black;
            else scheme[i] = Color.white;
        }
        return scheme;
    }
    static Color colorNormal (int n, int max) {
        if (n == max) return Color.red;
        return Color.white;
    }
}
