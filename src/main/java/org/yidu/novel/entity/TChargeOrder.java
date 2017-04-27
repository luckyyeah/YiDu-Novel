package org.yidu.novel.entity;

import org.yidu.novel.entity.base.BaseTChargeOrder;

public class TChargeOrder extends BaseTChargeOrder {


    /**
	 * 
	 */
	private static final long serialVersionUID = 8016480317430137079L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public TChargeOrder () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TChargeOrder (int chargeorderno) {
		super(chargeorderno);
	}

    /* [CONSTRUCTOR MARKER END] */



    // 为了Struts2的Json Result
    @Override
    public int getChargeorderno() {
        return super.getChargeorderno();
    }


}