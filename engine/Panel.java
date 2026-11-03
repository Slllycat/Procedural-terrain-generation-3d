import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

class Panel extends JPanel {
    Runnable action;
    Consumer<Graphics> drawAction;
    public Panel() {
        //initialised
    }
    public void setDrawAction(Consumer<Graphics> drawAction) {
        this.drawAction = drawAction;
    }
    public void startTimer(Runnable action){
        this.action=action;
        Timer timer = new Timer(16, e -> {
            action.run();
            repaint();
        });
        timer.start();
    }
    public void startTimer(Runnable action,int delay){
        this.action=action;
        Timer timer = new Timer(
                delay, e -> {
            action.run();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (drawAction!=null) {
            drawAction.accept(g);
        }
    }
}