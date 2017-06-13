package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorPanel extends JPanel {
	private JPanel jp;   //�м�����
	private double result;
	private boolean start;   //�����ж��Ƿ����״����룬true��ʾ�״Σ�false��ʾ�����״�
	private boolean flag;  //�����ж��Ƿ������ʾ�����ֵ��true��ʾ��Ҫ��false��ʾ����Ҫ
	private String lastCommand;   //���ڱ��������
	JButton display;  //��ʾ����
	
	public CalculatorPanel(){
		result=0;
		start=true;
		flag=false;
		lastCommand="=";
		
		//����CalculatorPanel���Ĳ���Ϊ��ܲ���
		setLayout(new BorderLayout());
		//�����ť������ʾֵ������
		display=new JButton("0.0");
		display.setEnabled(false);   //��ť��ʽ����Ϊ������ʽ
		
		//������ʼ��������õ�
		JButton clear=new JButton("����");
		clear.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			//��ʼ������
			result=0;
			start=true;
			flag=false;
			lastCommand="=";
			display.setText("0.0");
		}
	});
		
		jp=new JPanel();
		//����岼������Ϊ���񲼾֣�4��4��
		jp.setLayout(new GridLayout(4,4));
		//ʵ��������������
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
		
		//�����������������뵽�ҵ���壬ʵ�ּ������Ľ���
		add(display,BorderLayout.NORTH);
		add(jp,BorderLayout.CENTER);
		add(clear,BorderLayout.SOUTH);
		
		}
	
	private void makeButton(String buttonName,ActionListener al){
		JButton jb=new JButton(buttonName);
		jp.add(jb);
		jb.addActionListener(al);
	}
	//���ּ�����
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
				if(display.getText().indexOf(".")!=-1){//�������û��С���㣬���С����
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


