package project;

public class DrawHangMan {
    private int life = 5;

    public int getLife() {
        return life;
    }
    
    public void setLife(int life) {
        this.life = life;
    }

    public int draw_man() {
        switch (life) {
            case 0:
                System.out.println("  _______ ");
                System.out.println("  |     | ");
                System.out.println("  |     | ");
                System.out.println("        | ");
                System.out.println("        | ");
                System.out.println("        | ");
                break;
            case 1:
                System.out.println("  _______ ");
                System.out.println("  |     | ");
                System.out.println("  |     | ");
                System.out.println("  ●     | ");
                System.out.println("        | ");
                System.out.println("        | ");
                break;
            case 2:
                System.out.println("  _______ ");
                System.out.println("  |     | ");
                System.out.println("  |     | ");
                System.out.println("  ●     | ");
                System.out.println("  |     | ");
                System.out.println("        | ");
                break;
            case 3:
                System.out.println("  _______ ");
                System.out.println("  |     | ");
                System.out.println("  |     | ");
                System.out.println("  ●     | ");
                System.out.println(" -|     | ");
                System.out.println("        | ");
                break;
            case 4:
                System.out.println("  _______ ");
                System.out.println("  |     | ");
                System.out.println("  |     | ");
                System.out.println("  ●     | ");
                System.out.println(" -|-    | ");
                System.out.println("        | ");
                break;
            case 5:
                System.out.println("  _______ ");
                System.out.println("  |     | ");
                System.out.println("  |     | ");
                System.out.println("  ●     | ");
                System.out.println(" -|-    | ");
                System.out.println(" | |    | ");
        }

        return life;
    }
}
