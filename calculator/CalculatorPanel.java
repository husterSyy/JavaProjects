package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorPanel extends JPanel {
	private JPanel jp;   //中间的面板
	private double result;
	private boolean start;   //用于判断是否是首次输入，true表示首次，false表示不是首次
	private boolean flag;  //用于判断是否清空显示区域的值，true表示需要，false表示不需要
	private String lastCommand;   //用于保存运算符
	JButton display;  //显示区域
	
	public CalculatorPanel(){
		result=0;
		start=true;
		flag=false;
		lastCommand="=";
		
		//设置CalculatorPanel面板的布局为框架布局
		setLayout(new BorderLayout());
		//这个按钮用来显示值的区域
		display=new JButton("0.0");
		display.setEnabled(false);   //按钮样式设置为禁用样式
		
		//用来初始化，清除用的
		JButton clear=new JButton("清零");
		clear.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			//初始化操作
			result=0;
			start=true;
			flag=false;
			lastCommand="=";
			display.setText("0.0");
		}
	});
		
		jp=new JPanel();
		//将面板布局设置为网格布局，4列4行
		jp.setLayout(new GridLayout(4,4));
		//实例化监听器对象
		NumberAction na=new NumberAction();
		CommandAction ca=new CommandAction();
		
		makeButton("7",na);
		makeButton("8",na);
		makeButton("9",na);
		makeButton("/",ca);
		
		makeButton("4",na);
		makeButton("5",na);
		makeButton("6",na);
		makeButton("*",ca);
		
		makeButton("1",na);
		makeButton("2",na);
		makeButton("3",na);
		makeButton("-",ca);
		
		makeButton("0",na);
		makeButton(".",na);
		makeButton("+",ca);
		makeButton("=",ca);
		
		//将面板和两个按键加入到我的面板，实现计算器的界面
		add(display,BorderLayout.NORTH);
		add(jp,BorderLayout.CENTER);
		add(clear,BorderLayout.SOUTH);
		
		}
	
	private void makeButton(String buttonName,ActionListener al){
		JButton jb=new JButton(buttonName);
		jp.add(jb);
		jb.addActionListener(al);
	}
	//数字监听器
	private class NumberAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			JButton jb=(JButton) e.getSource();
			String input=jb.getText();
			if(start){
				if(input.equals(".")){
					return;
				}
				
				if(display.getText().equals("0.0")){
					display.setText(" ");
					
				}
				start=false;
			}
			else
				if(display.getText().indexOf(".")!=-1){//如果数字没有小数点，添加小数点
					if(input.equals(".")){
						return ;
					}
				}
			if(display.getText().equals("-")){
				if(input.equals(".")){
					return ;
				}
			}
			if(display.getText().equals("0")){
				if(!input.equals(".")){
					return ;
				}
			}
			
		if(flag){
			display.setText(" ");
			flag=false;
		}
		display.setText(display.getText()+input);
		}
	}

	private class CommandAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			JButton jb=(JButton)e.getSource();
			String inputCommand=jb.getText();
			
			if(start){
				if(inputCommand.equals("-")){
					display.setText("-");
					start=false;
				}
			}
			else {
				if(!flag){
					calculate(Double.parseDouble(display.getText()));
					
				}
				lastCommand=inputCommand;
				flag=true;
				
			}
		}
	}
	
	private void calculate(double x){
		if(lastCommand.equals("+")){
			result+=x;
			
		}else if(lastCommand.equals("-")){
			result-=x;
		}else if(lastCommand.equals("*")){
			result*=x;
	}else if(lastCommand.equals("/")){
		result/=x;
	}else if(lastCommand.equals("=")){
		result=x;
	}
		display.setText(""+result);
	}
}


