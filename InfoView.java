package c_info2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InfoView {

	//1. 멤버변수선언
	JFrame f;
	JTextField tfName, tfId, tfTel, tfGender, tfAge, tfHome;
	JTextArea ta;
	JButton bAdd, bShow, bSearch, bDelete, bCancel, bExit;

	// 비지니스로직 - 모델단
	InfoModel model;


	//2. 멤버변수 객체생성
	InfoView(){
		//프레임 설정
		f = new JFrame("DBTest"); 
		//좌측 설정
		tfName = new JTextField(10);
		tfId = new JTextField(10);
		tfTel = new JTextField(10);
		tfGender = new JTextField(10);
		tfAge = new JTextField(10);
		tfHome = new JTextField(10);
		//center 입력창
		ta = new JTextArea(40,20);
		//하단 설정
		bAdd = new JButton("Add");
		bShow = new JButton("Show");
		bSearch = new JButton("Search");
		bDelete = new JButton("Delete");
		bCancel = new JButton("Cancel");
		bExit = new JButton("수정하기");


		// 모델객체 생성
		try {
			model = new InfoModelImpl();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//3. 화면구성하고 출력
	/*
	 * 전체 프레임 BorderLayout 지정 
	 * 		- WEST	 : JPanel 붙이기(GridLAyout(6,2))	//6행 2열
	 * 		- CENTER : 텍스트에어리어
	 * 		- SOUTH  : JPanel 붙이기 (GridLayout(1,6))//1행 6열
	 */
	public void addLayout() {

		//붙이기 작업

		//좌측 설정
		JPanel pWest = new JPanel();				//JPanel 객체 생성
		pWest.setLayout(new GridLayout(6,2));		//행과 열 지정
		f.add(pWest, BorderLayout.WEST);			//어느쪽에 만들건지
		pWest.add(tfName.add(new JLabel("Name",JLabel.CENTER)));
		pWest.add(tfName);
		pWest.add(tfId.add(new JLabel("Id", JLabel.CENTER)));
		pWest.add(tfId);
		pWest.add(tfTel.add(new JLabel("Tel", JLabel.CENTER)));
		pWest.add(tfTel);
		pWest.add(tfGender.add(new JLabel("Gender", JLabel.CENTER)));
		pWest.add(tfGender);
		pWest.add(tfAge.add(new JLabel("Age", JLabel.CENTER)));
		pWest.add(tfAge);
		pWest.add(tfHome.add(new JLabel("Home", JLabel.CENTER)));
		pWest.add(tfHome);

		//하단 설정
		JPanel pSouth = new JPanel();
		pSouth.setLayout(new GridLayout(1,6));
		f.add(pSouth, BorderLayout.SOUTH);
		pSouth.add(bAdd);
		pSouth.add(bShow);
		pSouth.add(bSearch);
		pSouth.add(bDelete);
		pSouth.add(bCancel);
		pSouth.add(bExit);

		//입력값 설정
		f.add(ta        , BorderLayout.CENTER);

		//화면 출력
		f.setBounds(100,100,500,350);	//화면 크기
		f.setVisible(true);				//화면 출력
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//실행했을 때 X를 누르면 실행취소

	}

	/*void eventProc() {
		bAdd.addActionListener(new ActionListener() {
			public void actionPerfrormed(ACtionEvent e) {
				insertDate();
			}
		});
	}
	 */
	void eventProc() {
		// Add 버튼이 눌렸을 때
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertData();

			}
		});

		// Show 버튼이 눌렸을 때
		bShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAll();

			}
		});

		// bSearch 버튼이 눌렸을 때
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectByTel();

			}
		});
		//전화번호 텍스트필드에서 엔터쳤을 때
		tfTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectByTel();

			}
		});
		
		//bDelete 버튼이 눌렸을 때
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteByTel();
			}
		});
		
		//bexit 버튼이 눌렸을 때
		bExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyByAll();
			}
		});
		
	} //end of eventProc()
	
	void modifyByAll() {
		// (1) 사용자입력값 얻어오기
				String name		= tfName.getText();
				String id 		= tfId.getText();
				String tel 		= tfTel.getText();
				String gender 	= tfGender.getText();
				int age 		= Integer.parseInt(tfAge.getText());
				String home 	= tfHome.getText();
				
		// (2) 1번의 값들을 InfoVO에 지정 - (1) 생성자 (2) setter
				InfoVO vo = new InfoVO(name, id, tel, gender, age, home);
				vo.setName(name);
				vo.setId(id);
				vo.setTel(tel);
				vo.setGender(gender);
				vo.setAge(age);
				vo.setHome(home);
		
		// (3) 모델의 insertInfo() 호출
				try {
					model.modifyByAll(vo);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		// (4) 화면의 입력값들을 지우기
			clearText();
	}
	
	
	void deleteByTel() {
		// (1) 입력한 전화번호 값을 얻어오기
		String tel = tfTel.getText();
		
		// (2) 모델단에 deleteByTel() 호출
		try {
			model.delete(tel); // 예외 발생할 가능성이 있는 문장
			
		// (3) 화면을 지우고
			clearText();
		} catch (SQLException e) {
			ta.setText("삭제실패 :" + e.getMessage()); // SQLException이 발생했을 경우 이를 처리하기 위한 문장
		}
	}// end of deleteByTel()
	
	
	void selectByTel() {
		try {
		// (1) 입력한 전화번호 값을 얻어오기
			String tel = tfTel.getText();
			
		// (2) 모델단에 selectByTel() 호출
			InfoVO vo = model.selectByTel(tel);
			
		// (3) 받은 결과를 각각의 텍스트필드에 지정(출력)
			tfName.setText(vo.getName());
			tfId.setText(vo.getId());
			tfTel.setText(vo.getTel());
			tfGender.setText(vo.getGender());
			tfAge.setText(String.valueOf(vo.getAge()));
			tfHome.setText(vo.getHome());
	}catch(Exception ex) {
		ta.setText("전화번호 검색 실패:" + ex.getMessage());
		}
	} //end of selectByTel()
	
	
	void selectAll() {
		try {
			ArrayList<InfoVO> data = model.selectAll();
			ta.setText(" ----- 검색결과 ----- \n\n");
			for(InfoVO vo : data) {
				ta.append(vo.toString());
			}
		}catch (SQLException e) {
			ta.setText("검색실패: " + e.getMessage());	
		}
	}
	
	
	void insertData() {
		// (1) 사용자입력값 얻어오기
		String name		= tfName.getText();
		String id 		= tfId.getText();
		String tel 		= tfTel.getText();
		String gender 	= tfGender.getText();
		int age 		= Integer.parseInt(tfAge.getText());
		String home 	= tfHome.getText();

		// (2) 1번의 값들을 InfoVO에 지정 - (1) 생성자 (2) setter
		InfoVO vo = new InfoVO(name, id, tel, gender, age, home);
		vo.setName(name);
		vo.setId(id);
		vo.setTel(tel);
		vo.setGender(gender);
		vo.setAge(age);
		vo.setHome(home);

		// (3) 모델의 insertInfo() 호출
		try {
			model.insertInfo(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// (4) 화면의 입력값들을 지우기
		clearText();
	}
	
	void clearText() {
		tfName.setText(null);
		tfId.setText(null);
		tfTel.setText(null);
		tfGender.setText(null);
		tfAge.setText(null);
		tfHome.setText(null);
	}
	
	public static void main(String[] args) {

		InfoView info = new InfoView();
		info.addLayout();
		info.eventProc();
	}

}
