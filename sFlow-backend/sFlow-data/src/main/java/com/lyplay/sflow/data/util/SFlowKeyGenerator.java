package com.lyplay.sflow.data.util;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyplay.sflow.data.domain.system.Sequence;


public class SFlowKeyGenerator implements IdentifierGenerator, Configurable {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String SEQUENCE_NAME = "SEQUENCE_NAME";
	public Sequence sequence;
	
	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		String key = "";
		if(StringUtils.isNotEmpty(getSequenceName())){
			sequence = getSequence(session.connection());
			key = getNext();
			
		}
		return key;
	}
	
	private Sequence getSequence(Connection conn){
		Sequence resultSeq = null;
		CallableStatement callableStatement = null;
		try {    
			//CALL nextval(in_sequence_name,@prefix,@curr_value,@lpad_char, @lpad_length, @suffix);
			String sql = "{call nextval(?, ?, ?, ?, ?, ?) }";
			logger.debug("get primary key sql %s , params [%s] ", sql, getSequenceName());
			callableStatement = conn.prepareCall(sql);
			callableStatement.setString(1, getSequenceName());
			callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.registerOutParameter(3, Types.BIGINT);
            callableStatement.registerOutParameter(4, Types.CHAR);
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.registerOutParameter(6, Types.VARCHAR);
			
            callableStatement.executeUpdate();  
            
            resultSeq = new Sequence();
            resultSeq.setSequenceName(getSequenceName());
            resultSeq.setPrefix(callableStatement.getString(2));
            resultSeq.setCurrValue(callableStatement.getLong(3));
            resultSeq.setLpadChar(callableStatement.getString(4).charAt(0));
            resultSeq.setLpadLength(callableStatement.getInt(5));
            resultSeq.setSuffix(callableStatement.getString(6));
            
        }catch(Exception e)    
        {    
        	logger.error(e.getMessage(), e);
            throw new RuntimeException(e);    
        }    
        finally {   
			try {
				if (callableStatement != null) {
					callableStatement.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
	            throw new RuntimeException(e);    
			}
            
        }  
		return resultSeq;
	}

	protected String getNext() {
		StringBuffer key = new StringBuffer();
		if(sequence != null){
			key.append(StringUtils.defaultIfEmpty(sequence.getPrefix(), ""));
			key.append(StringUtils.leftPad(String.valueOf(sequence.getCurrValue()), sequence.getLpadLength(), sequence.getLpadChar()));
			key.append(StringUtils.defaultIfEmpty(sequence.getSuffix(), ""));
		}
		return key.toString();
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
	}

	public String getSequenceName() {
		return StringUtils.EMPTY;
	}

}
