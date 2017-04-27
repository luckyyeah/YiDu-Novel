package org.yidu.novel.entity;

import org.yidu.novel.entity.base.BaseTMessage;

public class TMessage extends BaseTMessage {
    private static final long serialVersionUID = 1L;

    /* [CONSTRUCTOR MARKER BEGIN] */
    public TMessage() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public TMessage(int messageno) {
        super(messageno);
    }

    /* [CONSTRUCTOR MARKER END] */

    public Boolean getIsread() {
        return isIsread();
    }

    public Boolean getDeleteflag() {
        return isDeleteflag();
    }

}