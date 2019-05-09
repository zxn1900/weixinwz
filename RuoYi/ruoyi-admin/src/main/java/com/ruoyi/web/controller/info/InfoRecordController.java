package com.ruoyi.web.controller.info;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.InfoRecord;
import com.ruoyi.system.service.IInfoRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 下载记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/system/infoRecord")
public class InfoRecordController extends BaseController
{
    private String prefix = "info/infoRecord";
	
	@Autowired
	private IInfoRecordService infoRecordService;
	
	@RequiresPermissions("system:infoRecord:view")
	@GetMapping()
	public String infoRecord()
	{
	    return prefix + "/infoRecord";
	}
	
	/**
	 * 查询下载记录列表
	 */
	@RequiresPermissions("system:infoRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(InfoRecord infoRecord)
	{
		startPage();
        List<InfoRecord> list = infoRecordService.selectInfoRecordList(infoRecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出下载记录列表
	 */
	@RequiresPermissions("system:infoRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InfoRecord infoRecord)
    {
    	List<InfoRecord> list = infoRecordService.selectInfoRecordList(infoRecord);
        ExcelUtil<InfoRecord> util = new ExcelUtil<InfoRecord>(InfoRecord.class);
        return util.exportExcel(list, "infoRecord");
    }
	
	/**
	 * 新增下载记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存下载记录
	 */
	@RequiresPermissions("system:infoRecord:add")
	@Log(title = "下载记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(InfoRecord infoRecord)
	{		
		return toAjax(infoRecordService.insertInfoRecord(infoRecord));
	}

	/**
	 * 修改下载记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		InfoRecord infoRecord = infoRecordService.selectInfoRecordById(id);
		mmap.put("infoRecord", infoRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存下载记录
	 */
	@RequiresPermissions("system:infoRecord:edit")
	@Log(title = "下载记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(InfoRecord infoRecord)
	{		
		return toAjax(infoRecordService.updateInfoRecord(infoRecord));
	}
	
	/**
	 * 删除下载记录
	 */
	@RequiresPermissions("system:infoRecord:remove")
	@Log(title = "下载记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(infoRecordService.deleteInfoRecordByIds(ids));
	}
	
}
