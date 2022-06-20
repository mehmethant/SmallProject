package Run;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Actors.Baloon;
import Actors.Factory;
import Actors.Storage;
import Operations.Transporter;

import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {
	private Object lock = new Object();
	private JPanel contentPane;
	private JTextField fld_Storage;
	
	static int j = 0;
	static int i = 1;
	static int transporterCount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
				
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setAlwaysOnTop(true);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel factoryPanel = new JPanel();
		factoryPanel.setBackground(Color.LIGHT_GRAY);
		factoryPanel.setBounds(10, 11, 278, 539);
		contentPane.add(factoryPanel);
		factoryPanel.setLayout(null);
		
		JTextArea area_Factories = new JTextArea();
		area_Factories.setEditable(false);
		area_Factories.setBounds(10, 28, 258, 373);
		area_Factories.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		factoryPanel.add(area_Factories);
		
		JTextArea area_CurrentInfo = new JTextArea();
		area_CurrentInfo.setEditable(false);
		area_CurrentInfo.setBounds(10, 412, 258, 116);
		area_CurrentInfo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		area_CurrentInfo.setText("Active Factories: 0\r\nActive Transporters: 0\r\nBaloon Count: 0");
		factoryPanel.add(area_CurrentInfo);
		
		JLabel lblNewLabel = new JLabel("Factory List");
		lblNewLabel.setBounds(10, 6, 258, 20);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		factoryPanel.add(lblNewLabel);
		
		JPanel transporterPanel = new JPanel();
		transporterPanel.setBackground(Color.LIGHT_GRAY);
		transporterPanel.setBounds(596, 11, 278, 539);
		contentPane.add(transporterPanel);
		transporterPanel.setLayout(null);
		
		JLabel lblTransporterList = new JLabel("Transporter List");
		lblTransporterList.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransporterList.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblTransporterList.setBounds(10, 8, 258, 20);
		transporterPanel.add(lblTransporterList);
		
		JTextArea area_Transporters = new JTextArea();
		area_Transporters.setEditable(false);
		area_Transporters.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		area_Transporters.setBounds(9, 30, 260, 500);
		transporterPanel.add(area_Transporters);
		
		JPanel storagePanel = new JPanel();
		storagePanel.setBackground(Color.LIGHT_GRAY);
		storagePanel.setBounds(298, 281, 287, 269);
		contentPane.add(storagePanel);
		storagePanel.setLayout(null);
		
		fld_Storage = new JTextField();
		fld_Storage.setEditable(false);
		fld_Storage.setBounds(10, 11, 267, 247);
		storagePanel.add(fld_Storage);
		fld_Storage.setColumns(10);
		
		JSlider productionSpeed = new JSlider();
		productionSpeed.setMinimum(500);
		productionSpeed.setMaximum(3000);
		productionSpeed.setMinorTickSpacing(250);
		productionSpeed.setSnapToTicks(true);
		productionSpeed.setPaintLabels(true);
		productionSpeed.setMajorTickSpacing(500);
		productionSpeed.setPaintTicks(true);
		productionSpeed.setBounds(298, 232, 288, 38);
		contentPane.add(productionSpeed);
		
		
		
		JButton btn_CreateFactory = new JButton("Create Factory");
		btn_CreateFactory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread factory = new Thread(new Factory(productionSpeed, area_CurrentInfo),("Factory-"+(++Main.factoryCount)));
	            Main.factoryList.add(factory); 
	            updateFactoryList(area_Factories);
	            updateCurrentInfo(area_CurrentInfo);
			}
		});
		btn_CreateFactory.setBounds(298, 173, 127, 23);
		contentPane.add(btn_CreateFactory);
		
		JButton btn_DeleteFactory = new JButton("Delete Factory");
		btn_DeleteFactory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.factoryList.remove(0);	
				updateFactoryList(area_Factories);
	            updateCurrentInfo(area_CurrentInfo);
			}
		});
		btn_DeleteFactory.setBounds(459, 173, 127, 23);
		contentPane.add(btn_DeleteFactory);
		
		JLabel lbl_Title = new JLabel("Baloon Factory");
		lbl_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lbl_Title.setBounds(295, 11, 291, 26);
		contentPane.add(lbl_Title);
		
		JLabel lbl_ProductionSpeed = new JLabel("Production Speed: ");
		lbl_ProductionSpeed.setBounds(298, 207, 115, 14);
		contentPane.add(lbl_ProductionSpeed);
		
		JButton btn_StartProduction = new JButton("Start Production!");
		btn_StartProduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					startProduction(area_CurrentInfo);
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_StartProduction.setBounds(298, 139, 127, 23);
		contentPane.add(btn_StartProduction);
		
		JButton btn_CreateTransporter = new JButton("Create Transporter");
		btn_CreateTransporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTransporters(area_CurrentInfo, area_Transporters);
			}
		});
		btn_CreateTransporter.setBounds(459, 139, 127, 23);
		contentPane.add(btn_CreateTransporter);
		
		JButton btn_ReleaseALL = new JButton("EMERGENCY!");
		btn_ReleaseALL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReleaseMain.main(null);
			}
		});
		btn_ReleaseALL.setBounds(381, 105, 127, 23);
		contentPane.add(btn_ReleaseALL);
	}
	
	void startProduction(JTextArea area_CurrentInfo) throws InterruptedException {
		for(Thread thread : Main.factoryList){
            thread.start();
        }

        while(Storage.baloons.size() != 99){
            Thread.sleep(1);
        }
        //startTransportation();
	}
	
	
	void createTransporters(JTextArea area_CurrentInfo, JTextArea area_Transporters) {

        HashMap<Long,Baloon> transporterBaloons = new HashMap<>();

        for(; MainFrame.j<MainFrame.i*10 && MainFrame.j < Storage.baloons.size(); MainFrame.j++){
            Object ID = Storage.baloons.keySet().toArray()[MainFrame.j];
            Baloon baloon = Storage.baloons.get((Long)ID);
            transporterBaloons.put((Long)ID,baloon);
        }

        Thread transporter = new Thread(new Transporter(transporterBaloons),("Transporter-"+(++transporterCount)));
        Main.transporters.add(transporter);
        MainFrame.i++;
        updateCurrentInfo(area_CurrentInfo);
        updateTransporterList(area_Transporters);
        startTransportation(transporter);
	}
	
	void startTransportation(Thread transporter) {
		transporter.start();
		try {
			transporter.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void updateFactoryList(JTextArea area_Factories) {
		StringBuilder stringBuilder = new StringBuilder();
		
		for(Thread thread : Main.factoryList) {
			stringBuilder.append(thread.getName() + "\n");
		}
		
		area_Factories.setText(stringBuilder.toString());
	}
	
	void updateTransporterList(JTextArea area_Factories) {
		StringBuilder stringBuilder = new StringBuilder();
		
		for(Thread thread : Main.transporters) {
			stringBuilder.append(thread.getName() + "\n");
		}
		
		area_Factories.setText(stringBuilder.toString());
	}
	
	void updateCurrentInfo(JTextArea area_CurrentInfo) {
		StringBuilder stringBuilder = new StringBuilder();
	
		stringBuilder.append("Active Factories: " + Main.factoryList.size() + "\n");
		stringBuilder.append("Active Transporters: " + Main.transporters.size() + "\n");
		stringBuilder.append("Baloon Count: " + Storage.baloons.size() + "\n");

		area_CurrentInfo.setText(stringBuilder.toString());
	}
}
