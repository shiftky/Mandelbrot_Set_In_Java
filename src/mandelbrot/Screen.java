package mandelbrot;

import java.awt.*;
import java.awt.Graphics2D;
import javax.swing.*;

//�`��p�X�N���[��
class Screen extends JPanel{
	// �R���X�g���N�^
	public Screen(){
		setBackground(Color.white);
	}
	
	// �`�揈��
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(102, 153, 255));
	}
}