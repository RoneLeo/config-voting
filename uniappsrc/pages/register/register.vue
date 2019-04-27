<template>
	<view class="page" style="background: #ffffff;">
		<view class="login-wrapper">
			<view class="header-box">
				<div class="header">
					<image class="header-img" src="../../static/img/man.png" mode=""></image>

				</div>
			</view>
			<view class="form-wrapper">
				<view v-show="loginShow">
					<view class="form-item">
						<view class="form-item-title">
							<image class="item-img" src="../../static/img/user.png" mode=""></image>
						</view>
						<view class="form-item-input">
							<input v-model="loginForm.zh" placeholder="系统账号" name="input"></input>
						</view>
					</view>
					<view class="form-item">
						<view class="form-item-title">
							<image class="item-img" src="../../static/img/password.png" mode=""></image>
						</view>
						<view class="form-item-input">
							<input v-model="loginForm.mm" placeholder="登录密码" name="input"></input>
						</view>
					</view>
					<view class="btn-wrapper">
						<button class="cu-btn bg-blue login-btn" @tap="login" style="margin-top: 60upx;">登 录</button>
					</view>
					<view class="tip" @tap="showChange">
						没有账号？去注册 >>
					</view>
					
				</view>
				<view v-show="!loginShow">
					<view class="form-item">
						<view class="form-item-title">
							<image class="item-img" src="../../static/img/name.png" mode=""></image>
						</view>
						<view class="form-item-input">
							<input v-model="registerForm.mc" placeholder="用户姓名" name="input"></input>
						</view>
					</view>
					<view class="form-item">
						<view class="form-item-title">
							<image class="item-img" src="../../static/img/user.png" mode=""></image>
						</view>
						<view class="form-item-input">
							<input v-model="registerForm.zh" placeholder="系统账号" name="input"></input>
						</view>
					</view>
					<view class="form-item">
						<view class="form-item-title">
							<image class="item-img" src="../../static/img/password.png" mode=""></image>
						</view>
						<view class="form-item-input">
							<input v-model="registerForm.mm" placeholder="登录密码" name="input"></input>
						</view>
					</view>
					<view class="btn-wrapper">
						<button class="cu-btn bg-blue login-btn" @tap="register" style="margin-top: 60upx;">注册</button>
					</view>
					<view class="tip" @tap="showChange">
						已有账号？去登录 >>
					</view>

				</view>

			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				loginShow: true,
				loginForm: {
					zh: '',
					mm: ''
				},
				registerForm: {
					zh: '',
					mm: '',
					mc: ''
				}
			};
		},
		methods: {
			showChange() {
				this.loginShow = !this.loginShow;
			},
			register() {
				uni.request({
					url: 'http://182.151.22.247:8899/user/add', //仅为示例，并非真实接口地址。
					data: {
						'zh': this.registerForm.zh,
						'mm': this.registerForm.mm,
						'mc': this.registerForm.mc,
						'js': '0' //0:普通用户, 1:管理员
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if(res.data.resCode == 200) {
							let data = res.data.data;
							this.showChange();
							uni.showToast({
								title: '注册成功，请登录',
								icon: 'none'
							})
						}
					}
				});
			},
			login() {
				uni.request({
					url: 'http://182.151.22.247:8899/user/login', //仅为示例，并非真实接口地址。
					data: {
						'zh': this.loginForm.zh,
						'mm': this.loginForm.mm
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if(res.data.resCode == 200) {
							let data = res.data.data;
							uni.setStorageSync("userInfo", JSON.parse(JSON.stringify(data.user)));
							uni.setStorageSync("token", JSON.parse(JSON.stringify(data.accessToken)));
							setTimeout(() => {
								uni.redirectTo({
									url:'../../pages/index/index'
								})
							}, 500)
						}
					}
				});
			},
		}
	}
</script>

<style lang="scss">
	.login-wrapper {
		padding: 100upx 30upx 50upx;

		.header-box {
			margin-top: 40upx;
			height: 200upx;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;

			.header {
				width: 200upx;
				height: 200upx;
				display: flex;
				justify-content: center;
				align-items: center;
				background: #C6E0F5;
				border: 10upx solid #46ACF8;
				border-radius: 50%;
				.header-img {
					width: 120upx;
					height: 120upx;

				}
			}
		}

		.form-wrapper {
			padding: 40upx 20upx;

			.form-item {
				display: flex;
				justify-content: flex-start;
				align-items: center;
				margin: 30upx;
				padding: 20upx 40upx;
				height: 80upx;
				font-size: 16px;
				border-bottom: 1px solid #eeeeee;

				.form-item-title {
					width: 50upx;
					margin-right: 20upx;
					.item-img {
						width: 50upx;
						height: 50upx;
					}
				}

				.form-item-input {
					input {
						letter-spacing: 2px;
						color: #808080;
					}
				}
			}
			.btn-wrapper {
				text-align: center;
				.login-btn {
					padding: 50upx 250upx;
					font-size: 16px;
				}
			}
			.tip {
				margin-top: 20upx;
				font-size: 12px;
				color: #999;
				text-align: right;
				line-height: 3;
			}
			
		}
	}
</style>
