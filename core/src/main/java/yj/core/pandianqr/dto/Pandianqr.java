package yj.core.pandianqr.dto;

/**
 * Auto Generated By Hap Code Generator
 **/

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ExtensionAttribute(disable = true)
@Table(name = "wip_pandianqr")
public class Pandianqr extends BaseDTO {
    @Id
    @GeneratedValue
    private String id; //记录号
    @NotEmpty
    private String fevor;

    @NotEmpty
    private String fevorTxt;

    @NotEmpty
    private String zbarcode;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFevor(String fevor) {
        this.fevor = fevor;
    }

    public String getFevor() {
        return fevor;
    }

    public void setFevorTxt(String fevorTxt) {
        this.fevorTxt = fevorTxt;
    }

    public String getFevorTxt() {
        return fevorTxt;
    }

    public void setZbarcode(String zbarcode) {
        this.zbarcode = zbarcode;
    }

    public String getZbarcode() {
        return zbarcode;
    }

}
