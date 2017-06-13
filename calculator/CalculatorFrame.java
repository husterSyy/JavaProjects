package calculator;

import java.awt.Dimension;
import javax.swing.JFrame;

public class CalculatorFrame extends JFrame{
	public CalculatorFrame(){
		add(new CalculatorPanel());
		MyFrameUitl.init(this,new Dimension(400,300),"简易计算器",null,true);
		this.pack();  //窗口大小刚好能够容纳所有组件
	}
}
