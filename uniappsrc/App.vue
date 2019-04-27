<script>
	import Vue from 'vue'
	export default {
		onLaunch: function() {
			uni.clearStorage();
			uni.getSystemInfo({
				success: function(e) {
					// #ifndef MP
					if (e.platform == 'android') {
						Vue.prototype.CustomBar = e.statusbarHeight ? e.statusBarHeight + 50 : 50;
					} else {
						Vue.prototype.CustomBar = e.statusbarHeight ? e.statusbarHeight + 45 : 45;
					};
					// #endif

					// #ifdef MP-WEIXIN
					Vue.prototype.StatusBar = e.statusBarHeight || 0;
					let custom = wx.getMenuButtonBoundingClientRect();
					Vue.prototype.Custom = custom;
					Vue.prototype.CustomBar = custom.bottom + custom.top - e.statusBarHeight;
					// #endif		

					// #ifdef MP-ALIPAY
					Vue.prototype.StatusBar = e.statusBarHeight;
					Vue.prototype.CustomBar = e.statusBarHeight + e.titleBarHeight;
					// #endif
				}
			})
		},
		onShow: function() {
			// console.log('App Show', this.getGlobalUser())

			// console.log('App Show')
		},
		onHide: function() {
			// console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	@import "colorui/main.css";
	@import "colorui/icon.css";

	uni-checkbox .uni-checkbox-input.uni-checkbox-input-checked {
		background-color: #007aff;
	}

	input[type='file'] {
		background: none;
		outline: none;
		border: 0px;
	}

	.page {
		width: 100%;
		height: auto;
		min-height: 100%;
		position: absolute;
		background-color: #ffffff;
		padding-bottom: 30upx;
	}

	.page-block {
		background-color: #ffffff;
	}

	.line-wapper {
		padding: 0upx 20upx;
	}

	.line {
		height: 1px;
		background-color: #DBDBDA;
	}

	.title {
		text-align: center;
		line-height: 3;
		/* border-bottom: 1px solid #eee; */
	}

	.font18 {
		font-size: 18px;
	}
	
	.cu-modal .padding-xl {
		text-align: left;
	}
	
	.vote-item-del {
		position: absolute;
		top: 10upx;
		right: 10upx;
		background: transparent;
		width: 60upx;
		height: 60upx;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.dash-btn {
		border: 1px dashed #ccc; border-radius: 10upx;height: 60upx;display: flex;align-items: center;font-size: 18px;
	}
</style>
