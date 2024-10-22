package dao;

import common.Common;
import vo.EmpVO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO(Database Access Object) : 데이터베이스에 접근하여 데이터를 CRUD 하는데 사용
// DAO는 자바 애플리케이션의 VO와 데이터베이스의 Table 간 데이터 교환을 수행
// SQL 문은 Statement 객체를 이용하여 통신
// 참고로 Statement는 정적인 SQL문을 처리하며, PreparedStatement는 동적인 SQL문을 처리
public class EmpDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    // SELECT 기능 구현
    public List<EmpVO> empSelect() {
        List<EmpVO> list = new ArrayList<>();
        boolean isSuccess = true;
        try {
            this.conn = Common.getConnection(); // 오라클 DB 연결
            this.stmt = this.conn.createStatement(); // Statement 생성
            String query = "SELECT * FROM EMP";

            // Statement.executeQuery(String query) : select 문과 같이 결과 값이 여러 개의 레코드로 반환되는 경우 사용
            // ResultSet은 Iterator를 사용한 메서드 제공 주요 메서드 next(), previous(), first(), last()
            this.rs = this.stmt.executeQuery(query);
            while (this.rs.next()) {
                list.add(new EmpVO(
                        this.rs.getInt("EMPNO"),
                        this.rs.getString("ENAME"),
                        this.rs.getString("JOB"),
                        this.rs.getInt("MGR"),
                        this.rs.getDate("HIREDATE"),
                        this.rs.getBigDecimal("SAL"),
                        this.rs.getBigDecimal("COMM"),
                        this.rs.getInt("DEPTNO")
                ));
            }
        } catch (Exception e) {
            isSuccess = false;
        } finally {
            Common.close(this.rs);
            Common.close(this.stmt);
            Common.close(this.conn);
        }
        return isSuccess ? list : null;
    }

    public void printSelectResult(List<EmpVO> list) {
        System.out.println("-".repeat(58));
        System.out.println(" ".repeat(24) + "사원 정보");
        for (EmpVO e: list) {
            System.out.printf("%4d, ", e.getEmpNo());
            System.out.printf("%6s, ", e.getName());
            System.out.printf("%9s, ", e.getJob());
            System.out.printf("%4d, ", e.getMgr());
            System.out.printf("%s, ", e.getHireDate());
            System.out.printf("%4s, ", e.getSal());
            System.out.printf("%4s, ", e.getComm());
            System.out.printf("%2d\n", e.getDeptNo());
        }
        System.out.println("-".repeat(58));
    }

    public boolean empInsert(EmpVO vo) {
        String query = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?,?,?,?,?,?,?,?)";
        boolean isSuccess = true;
        try {
            conn = Common.getConnection();

            // SQL 동적으로 처리, 쿼리문이 완성되어 있지 않은 Prepared Statement 객체에 전달
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, vo.getEmpNo());
            pstmt.setString(2, vo.getName());
            pstmt.setString(3, vo.getJob());
            pstmt.setInt(4, vo.getMgr());
            pstmt.setDate(5, vo.getHireDate());
            pstmt.setBigDecimal(6, vo.getSal());
            pstmt.setBigDecimal(7, vo.getComm());
            pstmt.setInt(8, vo.getDeptNo());

            // INSERT, UPDATE, DELETE SQL 구문을 실행하는 메서드
            pstmt.executeUpdate();
        } catch(Exception e) {
            isSuccess = false;
        } finally {
            Common.close(this.pstmt);
            Common.close(this.conn);
        }
        return isSuccess;
    }
}
