import dao.EmpDAO;
import vo.EmpVO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JdbcMain
{
    private final static Scanner sc = new Scanner(System.in);
    public static void menuSelect() {
        EmpDAO dao = new EmpDAO();
        while (true) {
            System.out.println("======= EMP TABLE =======");
            System.out.println("메뉴를 선택하세요");
            System.out.print("[1]SELECT [2]INSERT [3]UPDATE [4]DELETE [5]EXIT\n>> ");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    List<EmpVO> list = dao.empSelect();
                    if (list == null) System.out.println("EMP 테이블 조회 실패 !");
                    else dao.printSelectResult(list);
                    break;
                case 2:
                    if (dao.empInsert(empInput())) System.out.println("EMP 테이블 INSERT 성공 !");
                    else System.out.println("EMP 테이블 INSERT 실패 !");
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("JDBC Oracle Project");
        JdbcMain.menuSelect();
    }

    // 컨트롤러의 데이터 전달 과정을 터미널 입력으로 대체
    public static EmpVO empInput() {
        System.out.println("사원 정보를 입력 하세요.");
        System.out.print("사원번호: ");
        int empNo = sc.nextInt();

        System.out.print("사원이름: ");
        String ename = sc.next();

        System.out.print("직책: ");
        String job = sc.next();

        System.out.print("상관 사원번호: ");
        int mgr = sc.nextInt();

        System.out.print("입사일 (yyyy-mm-dd): ");
        String hireDateStr = sc.next();

        System.out.print("급여: ");
        BigDecimal sal = sc.nextBigDecimal();

        System.out.print("성과금: ");
        BigDecimal comm = sc.nextBigDecimal();

        System.out.print("부서번호: ");
        int deptNo = sc.nextInt();

        sc.nextLine();

        return new EmpVO(
                empNo, ename, job, mgr, Date.valueOf(hireDateStr), sal, comm, deptNo
        );
    }
}
