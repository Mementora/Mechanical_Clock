import javax.swing.JPanel;
import javax.swing.plaf.TableHeaderUI;
import java.awt.*;
import java.util.Calendar;

public class Panel extends JPanel {
    int winWidth = 450;
    int winHeight = 450;

    double angle;
    int x, y;

    private Graphics2D g2D;
    Graphics g;


    Font font;

    Panel() {
        this.setPreferredSize(new Dimension(winWidth,winHeight));
            ReDraw();
    }

    // TODO: Потрібно злапати покемона, так як при схованні та відкриті вікна все гуд...
    private void ReDraw() {
        Thread t = new Thread(() -> {
                while(true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("popa");


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    updateUI();
                    repaint();
                }
        });
        t.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;

        Graphics2D g2D = (Graphics2D) g;
        Graphics2D drawCircle = (Graphics2D) g;
        Graphics2D secondHand = (Graphics2D) g;

        font = new Font("Helvetica", Font.PLAIN, 18);

        //коло
        drawCircle.setStroke(new BasicStroke(4));

        drawCircle.drawOval(5, 1, 450, 450);

        //Годинні числа
        for (int i = 0; i < 12; i++) {
            drawCircle.setFont(font);
            angle = i * Math.PI / 6;
            x = (int) (200 * Math.sin(angle));
            y = (int) (200 * Math.cos(angle));
            g2D.setStroke(new BasicStroke(2));
            g2D.drawString(Integer.toString(i), 225 + x, 225 - y);
            //drawCircle.drawLine(210 + x, 210 - y, xCenter + x, yCenter - y);

            drawCircle.drawLine(225 + x, 225 - y, 225 + x, 225 - y);
        }
        //хвилинні числа
        for (int i = 0; i < 360; i++) {
            angle = i * Math.PI / 30;
            x = (int) (200 * Math.sin(angle));
            y = (int) (200 * Math.cos(angle));
            g2D.drawLine(225 + x, 225 - y, 225 + x, 225 - y);
        }
        //ініціалізація часу
        Calendar cal = Calendar.getInstance();
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);


        //Годинникова стрілка
        angle = (hour * Math.PI / 6) +
                (minute * Math.PI / (6 * 60)) +
                (second * Math.PI / (360 * 60));
        x = (int) (160 * Math.sin(angle));
        y = (int) (160 * Math.cos(angle));
        g2D.drawLine(225, 225, 225 + x, 225 - y);

        //хвилинна стрілка
        angle = (minute * Math.PI / 30) +
                (second * Math.PI / (30 * 60));
        x = (int) (190 * Math.sin(angle));
        y = (int) (190 * Math.cos(angle));
        g2D.drawLine(225, 225, 225 + x, 225 - y);
        //секундна стрілка
        secondHand.setColor(Color.RED);
        angle = (second * Math.PI / (30));
        x = (int) (190 * Math.sin(angle));
        y = (int) (190 * Math.cos(angle));
        secondHand.drawLine(225, 225, 225 + x, 225 - y);
        secondHand.dispose();

    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
