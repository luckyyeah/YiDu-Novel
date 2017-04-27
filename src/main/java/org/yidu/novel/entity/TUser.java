package org.yidu.novel.entity;

import org.yidu.novel.entity.base.BaseTUser;

public class TUser extends BaseTUser {
    private static final long serialVersionUID = 1L;

    /* [CONSTRUCTOR MARKER BEGIN] */
	public TUser () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TUser (int userno) {
		super(userno);
	}

    /* [CONSTRUCTOR MARKER END] */

    public Boolean getDeleteflag() {
        return isDeleteflag();
    }
    
    public Boolean getActivedflag() {
        return isActivedflag();
    }


    // 为了Struts2的Json Result
    @Override
    public int getUserno() {
        return super.getUserno();
    }

    @Override
    public String getLoginid() {
        return super.getLoginid();
    }

    @Override
    public Short getType() {
        return super.getType();
    }

    @Override
    public String getOpenid() {
        return super.getOpenid();
    }

}