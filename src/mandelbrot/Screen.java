package mandelbrot;

import java.awt.*;
import java.awt.Graphics2D;
import javax.swing.*;

//描画用スクリーン
class Screen extends JPanel{
	// コンストラクタ
	public Screen(){
		setBackground(Color.white);
	}
	
	// 描画処理
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(102, 153, 255));
	}
}