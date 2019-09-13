package com.udsp.sdc.icbc.comm.exception;

import org.springframework.http.HttpStatus;

/**
 * 异常编码定义
 * 
 * @author huangjl
 *
 */
public enum ExceptionCode {

	DATA_NOT_FOUND("数据未找到", 404),
	PERMISSION_NOT_ALLOWED("未授权", 403,HttpStatus.FORBIDDEN),
	TOKEN_INVALID("未登陆,Token无效", 401,HttpStatus.UNAUTHORIZED),
	KICKING_OUT_USER("您已经在其他地点登录",401,HttpStatus.UNAUTHORIZED),
	INNER_EXCEPTION("内部异常", 500),

	
	USER_NOT_LOGIN("用户未登录", 11001),
	USER_NOT_FOUND("用户名或者密码错误", 11002),
	USER_PWD_ERROR("用户名或者密码错误", 11003),
	USER_EXIST("用户已存在", 11004),
	USER_LOCKED("用户已锁定", 11005),
	USER_LOGIN_CODE_ERROR("验证码错误", 11006),
	USER_ATTEMPTS("用户尝试登录次数过多", 11007),
	USER_STATUS_ERROR("账号状态错误", 11008),
	USER_NOT_ROLE("该用户未分配角色", 11009),
	USER_ACCOUNT_UNVALID("无效用户",11010),
	USER_CANNOT_MODIFY("账号不可修改", 11011),
	USER_PWD_ERROR_CODE("用户名或者密码错误", 11012),//下次登录需要验证码标志
	USER_NOT_CODE("请输入验证码", 11013),
	CODE_PURPOSE_MATCH("验证码用途不匹配", 11014),
	USER_UNKNOW("用户名或者密码错误",11015),
	FILE_IS_NOT_MEET("上传文件类型不符，请重新上传excel文件",11016),
	FAIL_IS_NOT_EXIST("文件不存在",11017),
	USER_VERIFICATION_CODE_IS_TIME_OUT("验证码失效",11018),
	USER_PHONE_CODE_TIME_OUT("短信验证码失效",11019),
	USER_PHONE_CODE_ERROR("短信验证码错误",11020),
	USER_PHONE_CODE_TOSEND_FAIL("短信验证码发送失败",11021),
	USER_PHONE_UPDATE_FAIL("手机号更新失败",11022),
	USER_EMAIL_UPDATE_FAIL("邮箱更新失败",11023),
	USER_EMAIL_CODE_ERROR("邮箱验证码错误",11024),
	REQUEST_TYPE_ERRO("请求类型错误",11025),
	PROJECT_MANAGER_CONNECTION_NOT_ACTIVE("项目经理联系方式未激活",11026),
	ACCUNT_DATA_ERROR("数据错误",11027),
	USER_ROLE_UNAUTH("用户未分配角色 ，请分配角色后登录",11028),
	

	TENANT_USERNAME_EXIST("用户名已存在", 22001),
	TENANT_GIT_GROUP_EXIST("GIT组路径已存在", 22002),
	TENANT_NOT_EXIST("租户不存在", 22003),
	TENANT_UPDATE_FAIL("租户信息更新失败", 22004),
	TENANT_UPDATE_PASSWORD_FAIL("更新密码失败", 22005),
	TENANT_GIT_GROUP_OR_TENANT_ADMIN_USRNAME_REPEAT("租户的管理员账号与租户的TENANT_PATH_GROUP不能相同", 22006),
	TENANT_ROLE_LIST_NOT_EXIST("租户下挂角色未创建", 22007),
	TENANT_NAME_ALREADY_EXIST("租户名称已经存在", 22008),
	TENANT_ASYNC_FAIL("租户同步信息失败,请联系管理员进行信息同步", 22009),
	TENANT_THROUGH_AUDIT("该租户已经通过审核", 22010),
	TENANT_GITLAB_USERNAME_EXIST("用户名在gitlab中已存在", 22011),
	TENANT_MOBILEPHONE_EXIST("电话号码已存在", 22012),
	TENANT_EMAIL_EXIST("邮箱已存在", 22013),
	ACCOUNT_PHONE_ERRO("账号与手机号不匹配",22014),
	TENANT_DELETE_FAIL("租户删除失败", 22015),

	PROJECT_NAME_EXIST("项目名称已经存在", 31000),
	PROJECT_GIT_GROUP_EXIST("GITLAB中分组名称已经存在", 31001),
	PROJECT_NOT_EXIST("项目不存在", 31002),
	PROJECT_GIT_GROUP_NOT_EXIST("项目group在git中不存在", 31003),
	PROJECT_UPDATE_FAIL("项目更新失败", 31004),
	PROJECT_DELETE_FAIL("项目删除失败", 31005),
	PROJECT_PARENT_GIT_GROUP_NOT_EXIST("项目的父级git group在gitlab中不存在", 31006),
	PROJECT_ASYNC_FAIL("租户项目同步信息失败", 31007),
	PROJECT_CREATE_NOT_ALLOW("只有租户管理员和项目经理可以创建项目", 31008),

	REPO_NOTE_EXIST("应用不存在(代码仓库不存在)", 32001),
	REPO_ADD_MEMBER_FAIL("gitlab人员创建失败(人员名称与tenant gitlab path相同)", 32002),
	REPO_EXIST("代码仓库已经存在", 32003),
	REPO_PARENT_GIT_GROUP_NOT_EXIST("代码仓库上级git group在gitlab中不存在", 32003),
	REPO_MEMBER_ALREADY_EXIST("在代码库中已经添加该成员,请勿重复添加", 32004),
	REPO_MEMBER_NOT_REMOVE("应用创建人不能移除", 32005),
	REPO_NOT_ALLOW("只有租户管理员，项目经理可以创建应用、添加成员与移出成员", 32006),
	REPO_MEMBER_NOT_ALLOW("只有租户管理员，项目经理可以添加成员与移出成员", 32007),

	REPO_NOT_UNAUTHORIED("Nexus 认证失败!", 33001),
	FILE_NOT_EXIST("未选择文件!", 33002),
	PROJECT_ID_IS_NULL("上传介质项目Id属性为空!", 33003),
	UPLOAD_PARAM_GAV_IS_NULL("上传介质GAV参数为空！", 33004),
	ARTIFACT_TYPE_ERROR("上传介质类型错误！", 33005),
	NEXUS_IS_ERROR("nexus访问错误！", 33006),
	NEXUS_REPO_IS_NOT_EXIST("指定的nexus仓库不存在！", 33006),

	CODE_REPO_CREATE_FAILED("代码库创建失败", 32010),
	CODE_REPO_DELETE_FAILED("代码库删除失败", 32011),
	CODE_REPO_CREATE_BRANCHE_FAILED("创建分支失败", 32012),
	CODE_REPO_QUERY_BRANCHES_FAILED("查询分支列表失败", 32013),
	CODE_REPO_DELETE_BRANCHE_FAILED("删除分支失败", 32014),
	CODE_REPO_CREATE_TAG_FAILED("创建标签失败", 32015),
	CODE_REPO_DELETE_TAG_FAILED("删除标签失败", 32016),
	CODE_REPO_QUERY_TAG_FAILED("查询标签失败", 32017),
	CODE_REPO_QUERY_COMMITS_FAILED("查询提交记录失败", 32018),
	CODE_REPO_GET_FILES_TREE_FAILED("获取代码库文件树列表失败", 32019),
	CODE_REPO_GET_FILE_CONTENT_FAILED("获取代码库文件内容失败", 32020),

	CODE_REPO_GET_PROJECT_FAILED("获取代码库详情失败", 32021),
	
	// ============自动化测试===============
	CODE_REPO_CD_UI_FILE_SUFFIX("测试文件后缀名称错误,请选择.robot文件",34001),
	CODE_REPO_CD_UI_NOT_EXIST("测试定义不存在",34002),
	CODE_REPO_CD_BUILD_INSTANCE_NOT_EXIST("构建实例不存在",34003),
	CODE_REPO_CD_PIPELINE_TEMPLATE_NOT_EXIST("构建流水线模板不存在",34004),
	
	DEPLOY_STATUS_NOT_STOPPED("项目未处于停止状态", 35003), 
	DEPLOY_STATUS_NOT_STARTED("项目未处于启动状态", 35004), 
	DEPLOY_REMOTE_ERROR("接口调用失败", 35005),
	DEPLOY_CPU_NOT_ENOUGH("CPU不足", 35006),
	DEPLOY_MEMORY_NOT_ENOUGH("内存不足", 35007),
	DEPLOY_NOT_EXIST("部署不存在", 35008),
	REGISTRA_MIN_LIMIT("注册中心最小限制", 35009),

	WORKORDER_FAIL("流程流转失败", 40001),

	// ============租户成员相关===============
	TENANT_MEMBER_NAME_REPEAT("用户名重复", 23001),
	TENANT_MEMBER_PASSWORD_IS_EMPTY("账户密码为空", 23002),
	TENANT_MEMBER_ROLE_IS_EMPTY("账户角色为空", 23003),
	TENANT_MEMBER_PROJECT_REFERENCE("租户成员被项目引用", 23004),
	TENANT_MEMBER_ACCOUNT_HAS_BEEN_USED("账户已经存在", 23005),
	TENANT_MEMBER_GIT_DELETE_FAILED("删除GIT成员失败", 23006),
	TENANT_ADMIN_CONNOT_DELETE("不能删除租户管理员", 23007),
	TENANT_MEMBER_HAVE_BEEN_DELETE("租户成员已删除", 23008),
	TENANT_MEMBER_EMAIL_HAS_BEEN_USED("邮箱已经存在", 23009),
	TENANT_MEMBER_CELL_PHONE_NUMBERS_ARE_OCCUPIED("手机号码被占用", 23010),

	// ============项目成员相关===============
	PROJECT_MEMBER_IS_EMPTY("项目成员为空", 37001),
	PROJECT_MEMBER_ROLE_IS_EMPTY("项目成员角色为空", 37002),
	PROJECT_MEMBER_GIT_DELETE_FAILED("删除GIT成员失败", 37003),
	PROJECT_MEMBER_REPEATED_INJECTION("项目成员重复插入", 37004),
	PROJECT_MEMBER_NOT_ALLOW("只有租户管理员，项目经理可以添加成员与移出成员", 37005),
	PROJECT_MEMBER_OWNER_NOT_ALLOW("租户管理员，项目经理不可以移出", 37006),
	
	// ============工单相关===============
	WORKORDER_JOBNO_IS_EXIST("工单创建失败,工单号已存在",40002),
	WORKORDER_IS_NOT_EXIST("工单号错误,工单不存在",40003),
	WORKORDER_NODE_IS_NOT_FINAL("当前审批环节不是结束环节",40004),
	WORKORDER_NO_PLATFORM_LEADER("没有分管领导", 40005),
	// ============资源相关===============
	RESOURCE_IS_NOT_ENOUGH("资源同步失败,容器化资源不足",51001),
	RESOURCE_IS_NOT_ALL_EMPTY("资源扩容申请失败,cpu、内存、存储不能都为0",51002),
	RESOURCE_GET_FAILURE("资源获取失败",51003),
	RESOURCE_UNAUTHORIZED("资源集群使用接口调用失败，未授权",51004),
	RESOURCE_SYNC_FAILURE("资源同步失败",51005),
	RESOURCE_CPU_IS_NOT_EMPTY("资源申请cpu为必填项，10C以上",51006),
	RESOURCE_MEMORY_IS_NOT_EMPTY("资源申请内存为必填项，40G以上",51007),
	RESOURCE_DISK_IS_NOT_EMPTY("资源申请存储为必填项",51008),
	RESOURCE_CLUSTER_ID_IS_NOT_EMPTY("资源申请集群id为必填项",51009),
	RESOURCE_IS_EXIST("资源已存在",51010),
	RESOURCE_IS_OCCUPIED("资源减容失败，部分资源占用，减容量小于等于剩余量",51011),
	
	// ============能力相关===============
	CAPABILITY_IS_NOT_AUTH_PROJECT("所访问的project为授权",52001),
	CAPABILITY_IS_NOT_EXIST("能力信息不存在",52002),	
	CAPABILITY_SERVICE_ID_IS_NOT_NULL("能力申请服务id为必填项",52003),
	CAPABILITY_IP_ADDRESS_ID_IS_NOT_NULL("能力申请Ip地址为必填项",52004),
	CAPABILITY_PORT_ID_IS_NOT_NULL("能力申请端口号为必填项",52005),
	
	IPAAS_CONTAINER_SERVICE_BROKER_FAILED("I-Paas容器化服务订购失败",53001),
	IPAAS_CONTAINER_SERVICE_BROKER_NOT_EXIST("查询的service实例不存在",53002),
	IPAAS_CONTAINER_SERVICE_BROKER_NOT_ENOUGH("容器化资源不足",53003),
	IPAAS_CONTAINER_SERVICE_BROKER_DISK("磁盘只能扩容",53004),
	
	
	
	MICRO_SERVICE_CONFIGRUATION_FILE_ALREADY_EXIST_IN_PROJECT("微服务配置中心的配置文件已经在gitlab存在,请勿重复创建", 60001),
	MICRO_SERVICE_PROJECT_CREATE_FAIL("微服务项目创建失败", 60002),
	MICRO_SERVICE_TRACKER_CREATE_FAIL("微服务跟踪器创建失败", 60003),
	MICRO_SERVICE_GATEWAY_CREATE_FAIL("微服务网关创建失败", 60004),
	MICRO_SERVICE_BREAKER_CREATE_FAIL("微服务熔断器创建失败", 60005),
	MICRO_SERVICE_CONFIGRUATION_CREATE_FAIL("微服务配置中心创建失败", 60006),
	MICRO_SERVICE_REGISTORY_CREATE_FAIL("微服务注册中心创建失败", 60007),
	MICRO_SERVICE_OTHER_ERROR("微服务其他错误", 61000),
	
	MS_SERVICE_BROKER_EUREKA_IS_EXIST("创建失败，同一个项目ID不允许添加多个服务注册中心",62000),
	MS_SERVICE_BROKER_CPU_IS_OUT("创建失败，CPU数超过申请值，请重试",62001),
	MS_SERVICE_BROKER_MEMORY_IS_OUT("创建失败，内存数超过申请值，请重试",62002),
	MS_SERVICE_BROKER_EUREKA_IS_NOT_CREATE("创建失败，未创建服务注册中心或服务注册中心未启动",62003),
	MS_SERVICE_BROKER_IS_EXIST("组件已存在，不可重复创建",62004),
	MS_SERVICE_BROKER_IS_NOT_EXIST("组件已存在，不可重复创建",62005),
	MS_SERVICE_BROKER_FAILED("操作失败",62006),
	
	// ============harbor===============
	HARBOR_REPO_TAG_FAIL("harbor 获取repo tag 失败", 70001),
	HARBOR_PUBLIC_PROJECT_IS_EXIST("共有harbor创建项目失败，项目已存在", 70002),
	HARBOR_PUBLIC_USER_IS_EXIST("共有harbor创建用户失败，用户已存在", 70003),
	ARBOR_PUBLIC_PROJECT_IS_NOT_EXIST("共有harbor项目不存在", 70004),
	
	// ============进度管理===============
	PROGRESS_STORY_PROJECTID_AND_REPOID_NULL("项目ID和应用ID不能都是NULL", 75001),

	//============ 禅道计划=================
	PLAN_TIMESCOPE("计划时间区间错误",73001),
	TIME_BIG_ERROR("输入的记录工时和剩余工时之和大于原始剩余工时，请重新输入！",73002),
	TIME_SMALL_ERROR("输入的记录工时和剩余工时之和小于原始剩余工时，请重新输入！",73003),
	TASK_FINISHED_TIME_BIG_ERROR("输入的消耗工时大于最初预计的工时,请重新输入！",73004),
	TASK_FINISHED_TIME_SMALL_ERROR("输入的消耗工时小于已经消耗的工时,请重新输入！",73005),
	EDIT_TASK_TIME_SMALL_ERROR("输入的预计工时小于最初的预计工时，请重新输入！",73006),
	
	
	// ============禅道质量===============
	ZENTAO_CASE_NOT_EXIST("用例不存在", 76001),

	// ============build===============
	BUILD_PARAMETER_IS_NULL("参数为空", 80001),
	BUILD_DEFINITION_CREATE_FAIL("创建构建定义失败", 81001),
	BUILD_DEFINITION_DELETE_FAIL("删除构建定义失败", 81002),
	BUILD_DEFINITION_QUERY_FAIL("查询构建定义失败", 81003),
	BUILD_DEFINITION_UPDATE_FAIL("更新构建定义失败", 81004),
	BUILD_DEFINITION_NOT_EXISTS("构建定义不存在", 81005),
	BUILD_DEFINITION_ALREADY_EXISTS("构建定义已存在", 81006),
	BUILD_DEFINITION_IS_EXECUTING("构建定义正在执行", 81007),
	BUILD_DEFINITION_EXECUTE_FAIL("构建定义执行失败", 81008),

	BUILD_INSTANCE_CREATE_FAIL("创建构建实例失败", 82001),
	BUILD_INSTANCE_DELETE_FAIL("删除构建实例失败", 82002),
	BUILD_INSTANCE_QUERY_FAIL("查询构建实例失败", 82003),
	BUILD_INSTANCE_UPDATE_FAIL("更新构建实例失败", 82004),
	BUILD_INSTANCE_NOT_EXISTS("构建实例不存在", 82005),
	BUILD_DEFINITION_DELETE_FAIL_DELETE_INSTANCE_FIRST("请先删除构建实例", 82006),
	
	
	// ============build===============
	
	CD_TEST_EXECUTE_FAIL("执行压测失败", 83001),
	
	GITLAB_EXCEPTION("gitlab处理异常",90002),
	OTHER_ERROR("其他错误", 90001),
	
	FILE_TYPE_ERROR("文件类型错误", 91001);
	
	
	/**
	 * 描述
	 */
	private String name;

	/**
	 * 编号
	 */
	private long index;

	/**
	 * http状态（目前不使用）
	 */
	private HttpStatus status = null;

	/**
	 * 构造方法
	 * 
	 * @param name
	 *            状态描述
	 * @param index
	 *            状态编号
	 */
	private ExceptionCode(String name, long index) {
		this.name = name;
		this.index = index;
	}

	/**
	 * 构造方法
	 * 
	 * @param name
	 *            状态描述
	 * @param index
	 *            状态编号
	 * @param status
	 *            httpStatus状态
	 */
	private ExceptionCode(String name, long index, HttpStatus status) {
		this.name = name;
		this.index = index;
		this.status = status;
	}

	public long getIndex() {
		return this.index;
	}

	public String getName() {
		return this.name;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	@Override
	public String toString() {
		return this.index + "_" + this.name;
	}

	/**
	 * 根据编号查找异常项
	 * 
	 * @param index
	 *            异常编码
	 * @return 异常项
	 */
	public static ExceptionCode find(long index) {
		for (ExceptionCode v : ExceptionCode.values()) {
			if (v.index == index) {
				return v;
			}
		}
		return null;
	}
}
