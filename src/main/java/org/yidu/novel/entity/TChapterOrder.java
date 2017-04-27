package org.yidu.novel.entity;

import org.yidu.novel.entity.base.BaseTChapterOrder;
import org.yidu.novel.entity.base.BaseTUser;

public class TChapterOrder extends BaseTChapterOrder {


    /**
	 * 
	 */
	private static final long serialVersionUID = 8016480317430137079L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public TChapterOrder () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TChapterOrder (int chapterorderno) {
		super(chapterorderno);
	}

    /* [CONSTRUCTOR MARKER END] */



    // 为了Struts2的Json Result
    @Override
    public int getChapterorderno() {
        return super.getChapterorderno();
    }


}