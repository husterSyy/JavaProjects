package calculator;

import java.awt.Dimension;
import javax.swing.JFrame;

public class CalculatorFrame extends JFrame{
	public CalculatorFrame(){
		add(new CalculatorPanel());
		MyFrameUitl.init(this,new Dimension(400,300),"���׼�����",null,true);
		this.pack();  //���ڴ�С�պ��ܹ������������
	}
}
