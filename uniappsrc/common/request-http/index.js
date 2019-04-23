import http from './interface'

/**
 * 将业务所有接口统一起来便于维护
 * 如果项目很大可以将 url 独立成文件，接口分成不同的模块
 * 
 */

const autoLogin = () => {
	uni.login({
		provider: "weixin",
		success: (loginResult) => {
			let code = loginResult.code;
			uni.getUserInfo({
				provider: 'weixin',
				success: (infoRes) => {
					let encryptedData = infoRes.encryptedData;
					let iv = infoRes.iv;
					post('/user/weLogin', {
						code: code,
						sqm: '',
						encryptedData: encryptedData,
						iv: iv
					}).then(res => {
						if (res.resCode === 200) {
							uni.showToast({
								title: '登录成功',
								icon: "none"
							})
							uni.setStorageSync("globalUser", res.data.userInfo);
							uni.setStorageSync("sessionId", res.data.sessionId);
						} else {
							uni.showToast({
								title: '登录失败，手动登录',
								icon: "none"
							})
							uni.navigateTo({
								url: '../../pages/login/login'
							})
						}
					})
				}
			});
		}
	})
}

const getGlobalUser = function(key) {
	var userInfo = uni.getStorageSync("globalUser");
	if (userInfo != null && userInfo != "" && userInfo != undefined) {
		return userInfo;
	} else {
		return null;
	}
}

const getSessionId = function(key) {
	var sessionId = uni.getStorageSync("sessionId");
	if (sessionId != null && sessionId != "" && sessionId != undefined) {
		return sessionId;
	} else {
		return null;
	}
}


// 单独导出(测试接口) import {test} from '@/common/vmeitime-http/'
export const post = (url, data) => {
	http.config.baseUrl = "http://www.chiy.online:8083"
	//设置请求前拦截器
	http.interceptor.request = (config) => {
		if (url.indexOf("Login") != -1) { //如果是登录接口
			config.header = {
				'content-type': 'application/x-www-form-urlencoded'
			}
		} else {
			if (getGlobalUser() == null || getSessionId() == null) { //如果缓存没有的话,就要登录
				autoLogin();
				return;
			}
			config.header = {
				'content-type': 'application/x-www-form-urlencoded',
				'cookie': 'JSESSIONID=' + uni.getStorageSync("sessionId")
			}
		}
	}
	//设置请求结束后拦截器
	http.interceptor.response = (response) => {
		if (response.statusCode === 200) {
			let data = JSON.parse(response.data)
			if (data.resCode === 100 || data.resCode === 101) { //未登录或登录失败
				uni.showToast({
					title: '自动登录中',
					icon: "none"
				})
				autoLogin();
			} else {
				return data;
			}
		} else if (response.statusCode === 500) {
			uni.showToast({
				title: '500-' + response.errMsg,
				duration: 2000,
				image: "../../static/icon/error.png"
			})
		}else {
			uni.showToast({
				title: response.errMsg,
				icon: 'none'
			})
		}
		//判断返回状态 执行相应操作

	}
	return http.request({
		url: url,
		dataType: 'text',
		method: 'POST',
		data,
	})
}





// 默认全部导出  import api from '@/common/vmeitime-http/'
export default {
	post
}
