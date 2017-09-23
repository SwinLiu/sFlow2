package com.lyplay.sflow.data.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;


public class CompIdKeyGenerator extends SFlowKeyGenerator {

	public static final String sequenceName = "SEQ_COMP_ID";
	
	@Override
	public String getSequenceName() {
		return sequenceName;
	}
	
	@Override
	protected String getNext() {
		StringBuffer key = new StringBuffer();
		if(sequence != null){
			key.append(StringUtils.defaultIfEmpty(sequence.getPrefix(), ""));
			key.append(DateUtil.toDateStr(new Date(), DateUtil.YYYYMMDD));
			key.append(StringUtils.leftPad(String.valueOf(sequence.getCurrValue()), sequence.getLpadLength(), sequence.getLpadChar()));
			key.append(StringUtils.defaultIfEmpty(sequence.getSuffix(), ""));
		}
		return key.toString();
	}
	
}
