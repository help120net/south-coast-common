package cn.south.toast.common.redis.dto;

import java.util.Date;

/**
 * 
 * @author huangbh
 *  date of 2018年4月4日
 */
public class RedisDeviceDTO {

	private String deviceId;		//设备编码  RH000001
	
	private String accessUrl;		//接入地址
	
	private Integer onlineStatus;	//在线状态
	
	private Date updateTime;		//更新时间
	
	private Long lastHeartBeatTime; //最后一次心跳时间（时间戳，毫秒）
	
	private Integer heartBeatTime;	//设备心跳间隔时间（秒）
	
	private String upushParamJson;//扫码推送参数json串
	
	private Long lastHasPeopleTime;		//最后一次有人时间
	
	private Integer closeLigthTime;		//关灯延时时间，单位秒
	
	private String dataJson;		//设备相关数据

	private String doorToken;  //门禁token

	private Long doorTokenTime; //更新门禁token时间

	private Integer heartBeatType;	//心跳类型，1：自己小象app心跳


	public Long getDoorTokenTime() {
		return doorTokenTime;
	}

	public void setDoorTokenTime(Long doorTokenTime) {
		this.doorTokenTime = doorTokenTime;
	}

	public String getDoorToken() {
		return doorToken;
	}

	public void setDoorToken(String doorToken) {
		this.doorToken = doorToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public Integer getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getLastHeartBeatTime() {
		return lastHeartBeatTime;
	}

	public void setLastHeartBeatTime(Long lastHeartBeatTime) {
		this.lastHeartBeatTime = lastHeartBeatTime;
	}

	public Integer getHeartBeatTime() {
		return heartBeatTime;
	}

	public void setHeartBeatTime(Integer heartBeatTime) {
		this.heartBeatTime = heartBeatTime;
	}

	public String getUpushParamJson() {
		return upushParamJson;
	}

	public void setUpushParamJson(String upushParamJson) {
		this.upushParamJson = upushParamJson;
	}

	public Long getLastHasPeopleTime() {
		return lastHasPeopleTime;
	}

	public void setLastHasPeopleTime(Long lastHasPeopleTime) {
		this.lastHasPeopleTime = lastHasPeopleTime;
	}

	public Integer getCloseLigthTime() {
		return closeLigthTime;
	}

	public void setCloseLigthTime(Integer closeLigthTime) {
		this.closeLigthTime = closeLigthTime;
	}

	public String getDataJson() {
		return dataJson;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	public Integer getHeartBeatType() {
		return heartBeatType;
	}

	public void setHeartBeatType(Integer heartBeatType) {
		this.heartBeatType = heartBeatType;
	}
}
