public class TrafficLight implements ColorChanging {
    private enum Color {
        RED, YELLOW, GREEN
    };

    private Color currentColor;

    public TrafficLight() {
        currentColor = Color.GREEN;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    @Override
    public void changeColor() {
        switch (currentColor) {
        case RED:
            currentColor = Color.GREEN;
            break;
        case GREEN:
            currentColor = Color.YELLOW;
            break;
        case YELLOW:
            currentColor = Color.RED;
            break;
        }
    }

    @Override
    public void changeColor(String c) {
    }
}
