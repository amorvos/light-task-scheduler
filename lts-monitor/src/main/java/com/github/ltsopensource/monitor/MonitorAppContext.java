package com.github.ltsopensource.monitor;

import com.github.ltsopensource.core.AppContext;
import com.github.ltsopensource.monitor.access.face.JVMGCAccess;
import com.github.ltsopensource.monitor.access.face.JVMMemoryAccess;
import com.github.ltsopensource.monitor.access.face.JVMThreadAccess;
import com.github.ltsopensource.monitor.access.face.JobClientMAccess;
import com.github.ltsopensource.monitor.access.face.JobTrackerMAccess;
import com.github.ltsopensource.monitor.access.face.TaskTrackerMAccess;
import com.github.ltsopensource.monitor.cmd.MDataSrv;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Robert HG (254963746@qq.com) on 3/10/16.
 */
@Setter
@Getter
@ToString
public class MonitorAppContext extends AppContext {

	private int httpCmdPort;

	private JobTrackerMAccess jobTrackerMAccess;

	private TaskTrackerMAccess taskTrackerMAccess;

	private JobClientMAccess jobClientMAccess;

	private JVMGCAccess jvmGCAccess;

	private JVMMemoryAccess jvmMemoryAccess;

	private JVMThreadAccess jvmThreadAccess;

	private MDataSrv mDataSrv;
}
