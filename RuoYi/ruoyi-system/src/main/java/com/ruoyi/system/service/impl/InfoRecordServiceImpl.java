package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.InfoRecordMapper;
import com.ruoyi.system.domain.InfoRecord;
import com.ruoyi.system.service.IInfoRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 下载记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-29
 */
@Service
public class InfoRecordServiceImpl implements IInfoRecordService 
{
	@Autowired
	private InfoRecordMapper infoRecordMapper;

	/**
     * 查询下载记录信息
     * 
     * @param id 下载记录ID
     * @return 下载记录信息
     */
    @Override
	public InfoRecord selectInfoRecordById(Integer id)
	{
	    return infoRecordMapper.selectInfoRecordById(id);
	}
	
	/**
     * 查询下载记录列表
     * 
     * @param infoRecord 下载记录信息
     * @return 下载记录集合
     */
	@Override
	public List<InfoRecord> selectInfoRecordList(InfoRecord infoRecord)
	{
	    return infoRecordMapper.selectInfoRecordList(infoRecord);
	}
	
    /**
     * 新增下载记录
     * 
     * @param infoRecord 下载记录信息
     * @return 结果
     */
	@Override
	public int insertInfoRecord(InfoRecord infoRecord)
	{
	    return infoRecordMapper.insertInfoRecord(infoRecord);
	}
	
	/**
     * 修改下载记录
     * 
     * @param infoRecord 下载记录信息
     * @return 结果
     */
	@Override
	public int updateInfoRecord(InfoRecord infoRecord)
	{
	    return infoRecordMapper.updateInfoRecord(infoRecord);
	}

	/**
     * 删除下载记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteInfoRecordByIds(String ids)
	{
		return infoRecordMapper.deleteInfoRecordByIds(Convert.toStrArray(ids));
	}
	
}
