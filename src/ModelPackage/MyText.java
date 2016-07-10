package ModelPackage;

import ControlPackage.Control;
import ControlPackage.Drawable;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.AttrDecls;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * Created by Y50 on 7/8/2016.
 */
public class MyText implements Drawable
{
    private int m_width;
    private int m_height;
    private String m_text;
    private AttributedCharacterIterator m_iterator;
    private int m_start;
    private int m_end;
    private int x ;
    private int y ;
    private int fontSize;

    public MyText(String text,int x, int y , int width, int height,  int fontSize )
    {
        this.fontSize = fontSize;
        this.x = x;
        this.y = y;
        m_text = text;
        m_width = width;
        m_height = height;

        AttributedString styledText = new AttributedString(text);

        styledText.addAttribute(TextAttribute.FONT , (new Font("calibri" , Font.BOLD , 30)) );
        m_iterator = styledText.getIterator();
        m_start = m_iterator.getBeginIndex();
        m_end = m_iterator.getEndIndex();
    }

    public static void moveUp(MyText myText) {
        for (int i = 0; i < Control.FPS; i++) {
            myText.y = (myText.getY() - 1);
            try {
                Thread.sleep( 1000 / Control.FPS);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
    }

    public void draw(Graphics2D g2d , Control control)
    {
//        Font font = new Font("calibri" , Font.CENTER_BASELINE , 30);
//
//        g2d.finalize();

        FontRenderContext frc = g2d.getFontRenderContext();


        LineBreakMeasurer measurer = new LineBreakMeasurer(m_iterator, frc);

        measurer.setPosition(m_start);

        float a = x, b = y;
        while (measurer.getPosition() < m_end)
        {
            TextLayout layout = measurer.nextLayout(m_width);


            b += layout.getAscent();
            float dx = layout.isLeftToRight() ?
                    0 : m_width - layout.getAdvance();

            layout.draw(g2d, a + dx, b);
            b += layout.getDescent() + layout.getLeading();
        }
    }

    public int getY() {
        return y;
    }
}
