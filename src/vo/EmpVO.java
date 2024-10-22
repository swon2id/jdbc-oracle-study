package vo;

// VO(Value Object) : DB 레코드를 자바로 매핑한 객체 (데이터만 다루는 객체)
// VO 객체는 데이터베이스 테이블의 각 컬럼을 다루기 위한 인스턴스 필드를 가짐
// 일반적으로 데이터베이스 테이블의 레코드 한행이 VO 하나의 객체에 대응
// VO 객체는 비즈니스 로직을 다루지 않음

import java.math.BigDecimal;
import java.sql.Date;

// EMP 테이블의 VO를 생성하기 위한 클래스
public class EmpVO {
    private int empNo;
    private String name;
    private String job;
    private int mgr;
    private Date hiredate;
    private BigDecimal sal;
    private BigDecimal comm;
    private int deptNo;

    public EmpVO(int empNo, String name, String job, int mgr, Date hiredate, BigDecimal sal, BigDecimal comm, int deptNo) {
        this.empNo = empNo;
        this.name = name;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }
    public EmpVO() {}

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getHireDate() {
        return hiredate;
    }

    public void setDate(Date date) {
        this.hiredate = date;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    public BigDecimal getComm() {
        return comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }
}
