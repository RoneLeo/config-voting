<template>
	<view v-show="loginFormShow" class="login_mask" :style="{display: loginFormShow? 'flex': 'none'}">
		<view class="login_form">
			<view class="login_form_item">
				<text class="form_item_tt">用户名</text>
				<input v-model="zh" placeholder="请填写用户名" name="input" style="width: 200upx;"></input>
			</view>
			<view class="login_form_item">
				<text class="form_item_tt">
					密码
				</text>
				<input v-model="mm" :password="password" placeholder="请填写密码" name="input" style="width: 200upx;"></input>
				<view style="width: 40upx;margin-left: 20upx;">
					<image v-show="password" @tap="password = !password;" src="../../static/img/close_eye.png" mode="" style="width: 40upx;height: 40upx"></image>
					<image v-show="!password" @tap="password = !password;" src="../../static/img/eye.png" mode="" style="width: 40upx;height: 40upx"></image>
				</view>
			</view>
			<view class="login_form_item">
				<button class="login_btn" type="primary" @tap="login">登 录</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: "login",
		props:{
			loginFormShow: {
				type: Boolean,
				default: false
			},
		},
		data() {
			return {
				zh: '',
				mm: '',
				password: true
			}
		},
		created() {
			this.zh = this.getGlobalUser() != null && this.getGlobalUser().zh;
		},
		methods: {
			login() {
				uni.request({
					url: 'http://182.151.22.247:8899/user/login', //仅为示例，并非真实接口地址。
					data: {
						'zh': this.zh,
						'mm': this.mm
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						console.log(res)
						let data = res.data.data;
						if(res.data.resCode == 200) {
							this.$store.commit('setLogin', true)
							uni.setStorageSync("logined", JSON.parse(JSON.stringify('true')));
							uni.setStorageSync("userInfo", JSON.parse(JSON.stringify(data.user)));
							uni.setStorageSync("token", JSON.parse(JSON.stringify(data.accessToken)));
							this.$emit("hide", true) 
							setTimeout(() => {
								// this.loginFormShow = false;
							}, 500)
						}else {
							console.log(111)
							uni.showToast({
								title: res.data.resMsg,
								icon: 'none'
							})
						}
						
					}
				});

			}
		}
	}
</script>

<style lang="scss">
	.login_mask {
		background-color: rgba(0, 0, 0, .6);
		z-index: 901;
		display: flex;
		justify-content: center;
		align-items: center;
		position: fixed;
		top: 0;
		width: 100%;
		height: 100%;

		.login_form {
			padding: 40upx;
			display: flex;
			flex-direction: column;
			font-size: 16px;
			border-radius: 20upx;
			background-color: #ffffff;
			box-shadow: 2px 2px 15px #aaaaaa;

			.login_form_item {
				display: flex;
				padding: 20upx 0;

				.form_item_tt {
					width: 110upx;
				}

				.login_btn {
					font-size: 14px;
					padding: 5upx 60upx
				}
			}
		}
	}
</style>
