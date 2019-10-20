package cn.edu.zzuli.purchasesalestock.bean;

import lombok.Data;

@Data
public class Clerk {

    private Integer clerkId;
    private String clerkName;
    private Integer clerkNo;
    private String clerkCompany;
    private String clerkPosition;
    private String clerkArea;
    private String clerkSpell;
    private String clerkBirthday;
    private String clerkTelphone;
    private String clerkLocation;

    public Clerk() {
        super();
    }

    public Clerk(Integer clerkId, String clerkName, Integer clerkNo, String clerkCompany, String clerkPosition, String clerkArea, String clerkSpell, String clerkBirthday, String clerkTelphone, String clerkLocation) {
        this.clerkId = clerkId;
        this.clerkName = clerkName;
        this.clerkNo = clerkNo;
        this.clerkCompany = clerkCompany;
        this.clerkPosition = clerkPosition;
        this.clerkArea = clerkArea;
        this.clerkSpell = clerkSpell;
        this.clerkBirthday = clerkBirthday;
        this.clerkTelphone = clerkTelphone;
        this.clerkLocation = clerkLocation;
    }


}
