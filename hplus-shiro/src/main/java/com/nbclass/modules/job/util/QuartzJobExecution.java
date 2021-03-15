package com.nbclass.modules.job.util;

import org.quartz.JobExecutionContext;

import com.nbclass.modules.job.model.SysJob;

/**
 * 定时任务处理（允许并发执行）
 */
public class QuartzJobExecution extends AbstractQuartzJob {
	@Override
	protected void doExecute(JobExecutionContext context, SysJob job) throws Exception {
		JobInvokeUtil.invokeMethod(job);
	}

}
