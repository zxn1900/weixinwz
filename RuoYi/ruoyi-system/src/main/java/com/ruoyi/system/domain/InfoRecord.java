package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 下载记录表 info_record
 * 
 * @author ruoyi
 * @date 2019-04-29
 */
public class InfoRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 用户id */
	private Integer userId;
	/** 记录类型 */
	private String dataType;
	/** 浏览器标识 */
	private String userAgent;
	/** 远程ip */
	private String remoteAddr;
	/** 方法类型 */
	private String method;
	/** 访问url */
	private String requestUri;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setDataType(String dataType) 
	{
		this.dataType = dataType;
	}

	public String getDataType() 
	{
		return dataType;
	}
	public void setUserAgent(String userAgent) 
	{
		this.userAgent = userAgent;
	}

	public String getUserAgent() 
	{
		return userAgent;
	}
	public void setRemoteAddr(String remoteAddr) 
	{
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteAddr() 
	{
		return remoteAddr;
	}
	public void setMethod(String method) 
	{
		this.method = method;
	}

	public String getMethod() 
	{
		return method;
	}
	public void setRequestUri(String requestUri) 
	{
		this.requestUri = requestUri;
	}

	public String getRequestUri() 
	{
		return requestUri;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .append("dataType", getDataType())
            .append("userAgent", getUserAgent())
            .append("remoteAddr", getRemoteAddr())
            .append("method", getMethod())
            .append("requestUri", getRequestUri())
            .toString();
    }
}
