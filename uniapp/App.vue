<script>
	import Vue from 'vue'
	export default {
		onLaunch: function() {
			uni.getSystemInfo({
				success: function(e) {
					// #ifndef MP
					if (e.platform == 'android') {
						Vue.prototype.CustomBar = e.statusbarHeight ? e.statusBarHeight + 50 :50;
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
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	@import "colorui/main.css";
	@import "colorui/icon.css";

	input[type='file'] {
		background: none;
		outline: none;
		border: 0px;
	}
	.page {
		width: 100%;
		height: 100%;
		background-color: #ffffff;
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
</style>
