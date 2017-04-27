package org.yidu.novel.entity;

import org.yidu.novel.entity.base.BaseTSystemBlock;

public class TSystemBlock extends BaseTSystemBlock {
    private static final long serialVersionUID = 1L;

    /* [CONSTRUCTOR MARKER BEGIN] */
    public TSystemBlock() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public TSystemBlock(int blockno) {
        super(blockno);
    }

    /* [CONSTRUCTOR MARKER END] */

    public Boolean getIsasc() {
        return isIsasc();
    }

    public Boolean getDeleteflag() {
        return isDeleteflag();
    }
}