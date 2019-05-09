package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.InfoRecord;
import java.util.List;	

/**
 * 下载记录 数据层
 * 
 * @author ruoyi
 * @date 2019-04-29
 */
public interface InfoRecordMapper 
{
	/**
     * 查询下载记录信息
     * 
     * @param id 下载记录ID
     * @return 下载记录信息
     */
	public InfoRecord selectInfoRecordById(Integer id);
	
	/**
     * 查询下载记录列表
     * 
     * @param infoRecord 下载记录信息
     * @return 下载记录集合
     */
	public List<InfoRecord> selectInfoRecordList(InfoRecord infoRecord);
	
	/**
     * 新增下载记录
     * 
     * @param infoRecord 下载记录信息
     * @return 结果
     */
	public int insertInfoRecord(InfoRecord infoRecord);
	
	/**
     * 修改下载记录
     * 
     * @param infoRecord 下载记录信息
     * @return 结果
     */
	public int updateInfoRecord(InfoRecord infoRecord);
	
	/**
     * 删除下载记录
     * 
     * @param id 下载记录ID
     * @return 结果
     */
	public int deleteInfoRecordById(Integer id);
	
	/**
     * 批量删除下载记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteInfoRecordByIds(String[] ids);
	
}