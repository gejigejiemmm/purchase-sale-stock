package cn.edu.zzuli.purchasesalestock.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Clerk {

    private Integer clerkId;
    private String clerkName;
    private Integer clerkNo;
    private String clerkCompany;
    private String clerkPosition;
    private String clerkArea;
    private String clerkSpell;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate clerkBirthday;
    private String clerkTelphone;
    private String clerkLocation;

    public Clerk() {
        super();
    }

    public Clerk(Integer clerkId, String clerkName, Integer clerkNo,
                 String clerkCompany, String clerkPosition, String clerkArea,
                 String clerkSpell, LocalDate clerkBirthday, String clerkTelphone,
                 String clerkLocation) {
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
