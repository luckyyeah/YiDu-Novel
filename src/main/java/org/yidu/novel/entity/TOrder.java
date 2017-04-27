package org.yidu.novel.entity;

import org.yidu.novel.entity.base.BaseTOrder;

public class TOrder extends BaseTOrder {


    /**
	 * 
	 */
	private static final long serialVersionUID = 8016480317430137079L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public TOrder () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TOrder (int orderid) {
		super(orderid);
	}

    /* [CONSTRUCTOR MARKER END] */



    // 为了Struts2的Json Result
    @Override
    public int getOrderid() {
        return super.getOrderid();
    }


}