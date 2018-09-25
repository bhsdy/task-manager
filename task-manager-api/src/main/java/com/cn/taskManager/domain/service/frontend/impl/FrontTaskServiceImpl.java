package com.cn.taskManager.domain.service.frontend.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.taskManager.domain.entity.FrontTask;
import com.cn.taskManager.domain.entity.FrontTaskNode;
import com.cn.taskManager.domain.entity.SysDict;
import com.cn.taskManager.domain.mapper.frontend.FrontTaskMapper;
import com.cn.taskManager.domain.service.backend.SysDictService;
import com.cn.taskManager.domain.service.frontend.FrontTaskService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FrontTaskServiceImpl extends ServiceImpl<FrontTaskMapper, FrontTask> implements FrontTaskService {
	@Autowired
	private FrontTaskNodeServiceImpl frontTaskNodeService;
	@Autowired
	private SysDictService sysDictService;

	/**
	 * 新增任务
	 * @param record 对象
	 * @return FrontTask
	 */
	@Override
	@Transactional
	public FrontTask addTask(FrontTask record) {
		boolean insert = super.insert(record);
		if(insert){
			record.getFrontTaskNodeList().forEach(item ->{
				//新增
				String uuid = UUID.randomUUID().toString().replace("-","");
				item.setId(uuid);
				item.setTaskId(record.getId());
			});
			boolean insertBatch = frontTaskNodeService.insertBatch(record.getFrontTaskNodeList());
			if(insertBatch){
				record.setFrontTaskNodeList(record.getFrontTaskNodeList());
			}
		}
		return record;
	}
	/**
	 * 编辑任务
	 * @param record 对象
	 * @return FrontTask
	 */
	@Override
	@Transactional
	public FrontTask editTask(FrontTask record) {
		boolean update = super.updateById(record);
		if(update){
			List<FrontTaskNode> insertList = Lists.newArrayList();
			List<FrontTaskNode> updateList = Lists.newArrayList();
			List<String> deleteIds = Lists.newArrayList();
			record.getFrontTaskNodeList().forEach(item ->{
				if(StringUtils.isEmpty(item.getId())){
					//新增
					String uuid = UUID.randomUUID().toString().replace("-","");
					item.setId(uuid);
					item.setTaskId(record.getId());
					insertList.add(item);
				}else {
					//更新
					item.setTaskId(record.getId());
					updateList.add(item);
				}

			});
			//删除
			List<String> currentIds = updateList.parallelStream().map(item -> item.getId()).collect(Collectors.toList());
			EntityWrapper<FrontTaskNode> ew = new EntityWrapper<>();
			ew.where("task_id={0}",record.getId());
			List<FrontTaskNode> frontTaskNodes = frontTaskNodeService.selectList(ew);
			frontTaskNodes.forEach(item ->{
				if(!currentIds.contains(item.getId())){
					deleteIds.add(item.getId());
				}
			});
			if(!CollectionUtils.isEmpty(deleteIds)){
				frontTaskNodeService.deleteBatchIds(deleteIds);
			}
			if(!CollectionUtils.isEmpty(updateList)){
				frontTaskNodeService.updateBatchById(updateList);
			}
			if(!CollectionUtils.isEmpty(insertList)){
				frontTaskNodeService.insertBatch(insertList);
			}
			record.setFrontTaskNodeList(record.getFrontTaskNodeList());
		}
		return record;
	}

	/**
	 * 查询任务列表
	 */
	public Page<FrontTask> queryTaskList(FrontTask record){
		Page<FrontTask> frontTaskPage = super.selectPage(new Page<FrontTask>(record.getPageNum(), record.getPageSize()));
		List<FrontTask> frontTaskList = frontTaskPage.getRecords();
		frontTaskList.forEach(item ->{
			//数据字典：优先级
			EntityWrapper<SysDict> ew = new EntityWrapper<>();
			ew.where("dict_code={0}",item.getDictCode());
			SysDict sysDictQuery = sysDictService.selectOne(ew);
			item.setSysDict(sysDictQuery);
			//时间节点
			FrontTaskNode frontTaskNode = new FrontTaskNode();
			frontTaskNode.setTaskId(item.getId());

			EntityWrapper<FrontTaskNode> ew2 = new EntityWrapper<>();
			ew2.where("task_id={0}",item.getId());
			ew2.orderBy("time",false);
			List<FrontTaskNode> frontTaskNodeList = frontTaskNodeService.selectList(ew2);
			item.setFrontTaskNodeList(frontTaskNodeList);
		});
		return frontTaskPage;
	}
}
