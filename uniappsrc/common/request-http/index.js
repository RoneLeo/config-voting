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
	var userInfo = uni.getStorageSync("userInfo");
	console.log('userInfo', userInfo)
	if (userInfo != null && userInfo != "" && userInfo != undefined) {
		return userInfo;
	} else {
		return null;
	}
}

const getToken = function(key) {
	var token = uni.getStorageSync("token");
	console.log('token', token);
	if (token != null && token != "" && token != undefined) {
		return token;
	} else {
		return null;
	}
}


// 单独导出(测试接口) import {test} from '@/common/vmeitime-http/'
export const post = (url, data) => {
	http.config.baseUrl = "http://182.151.22.247:8899"
	//设置请求前拦截器
	http.interceptor.request = (config) => {
		if (url.indexOf("login") != -1) { //如果是登录接口
			config.header = {
				'content-type': 'application/x-www-form-urlencoded'
			}
		} else if(url.indexOf("score/addScoreDx") != -1 || url.indexOf("score/addScoreOP") != -1
			|| url.indexOf("score/score") != -1 || url.indexOf("voting/vote") != -1){
			config.header = {
				// 'content-type': 'application/x-www-form-urlencoded',
				'content-type': 'application/json',
				'accessToken': uni.getStorageSync("token")
			}
		}else {
			config.header = {
				'content-type': 'application/x-www-form-urlencoded',
				'accessToken': uni.getStorageSync("token")
			}
		}
	}
	//设置请求结束后拦截器
	http.interceptor.response = (response) => {
		// console.log(response)
		if (response.statusCode === 200) {
			let data = JSON.parse(response.data)
// 			if (data.resCode === 100 || data.resCode === 101) { //未登录或登录失败
// 				uni.showToast({
// 					title: '自动登录中',
// 					icon: "none"
// 				})
// 				autoLogin();
// 			} else {
// 				return data;
// 			}
			return data;
		} else if (response.statusCode === 500) {
			uni.showToast({
				title: '500-' + response.errMsg,
				duration: 2000,
				icon: 'none'
			})
		} else {
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

export const get = (url, data) => {
	// http.config.baseUrl = "http://182.151.22.247:8899"
	http.interceptor.request = (config) => {
		if (url.indexOf("login") != -1) { //如果是登录接口
			config.header = {
				'content-type': 'application/x-www-form-urlencoded'
			}
		} else {
			config.header = {
				'content-type': 'application/x-www-form-urlencoded',
				'accessToken': uni.getStorageSync("token")
			}
		}
	}
	http.interceptor.response = (response) => {
		if (response.statusCode === 200) {
			let data = JSON.parse(response.data)
			return data;
		} else if (response.statusCode === 500) {
			uni.showToast({
				title: '500-' + response.errMsg,
				duration: 2000,
				icon: 'none'
			})
		} else {
			uni.showToast({
				title: response.errMsg,
				icon: 'none'
			})
		}
	}
	return http.request({
		url: url,
		method: 'GET',
		data,
	})
}




// 默认全部导出  import api from '@/common/vmeitime-http/'
export default {
	post,
	get
}
