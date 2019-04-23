<template>
	<view v-show="loginFormShow" class="login_mask" :style="{display: loginFormShow? 'flex': 'none'}">
		<view class="login_form">
			<view class="login_form_item">
				<text class="form_item_tt">用户名</text>
				<input placeholder="请填写用户名" name="input"></input>
			</view>
			<view class="login_form_item">
				<text class="form_item_tt">
					密码
				</text>
				<input placeholder="请填写密码" name="input"></input>
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
		data() {
			return {
				loginFormShow: false,
			}
		},
		created() {
			console.log(this.getGlobalUser(), !this.getGlobalUser())
			if (!this.getGlobalUser()) {
				this.loginFormShow = true;
			}
		},
		methods: {
			login() {
				uni.setStorageSync("user", {
					name: '111'
				});
				this.loginFormShow = false;
			}
		}
	}
</script>

<style lang="scss">
	.login_mask {
		background-color: rgba(0, 0, 0, .6);
		z-index: 2000;
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
					width: 130upx;
				}

				.login_btn {
					font-size: 14px;
					padding: 5upx 60upx
				}
			}
		}
	}
</style>
